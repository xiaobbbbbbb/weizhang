package com.ecarinfo.traffic.api.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.api.cache.MoneyCache;
import com.ecarinfo.traffic.api.router.SpiRouter;
import com.ecarinfo.traffic.api.service.TrafficService;
import com.ecarinfo.traffic.persist.po.ApiMoney;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.QueryTask;
import com.ecarinfo.traffic.persist.po.QueryTaskDetail;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TOrgSchedule;
import com.ecarinfo.traffic.persist.tables.TQueryTask;
import com.ecarinfo.traffic.persist.tables.TQueryTaskCar;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;

public class QueryJob implements Job {
	private static final Logger logger = Logger.getLogger(QueryJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String jobName = context.getJobDetail().getName();
		Integer jobId = Integer.valueOf(jobName.split("-")[1]);
		CronTrigger trigger = (CronTrigger)context.getTrigger();
		String cronExp = trigger.getCronExpression();
		logger.info("===========QueryJob["+jobName+"]start.======="+cronExp+"======"+DateUtils.currentDateStr());
		ApplicationContext applicationContext = (ApplicationContext)context.getJobDetail().getJobDataMap().get("applicationContext");
		DB db = (DB)applicationContext.getBean("db");
		SpiRouter spiRouter = (SpiRouter)applicationContext.getBean("spiRouter");
		TrafficService trafficService = (TrafficService)applicationContext.getBean("trafficService");
		MoneyCache moneyCache = (MoneyCache)applicationContext.getBean("moneyCache");
		TOrgInfo toi = Tables.get(TOrgInfo.class);
		TOrgCarInfo toc = Tables.get(TOrgCarInfo.class,"toc");
		TOrgSchedule tos = Tables.get(TOrgSchedule.class,"tos");
		TQueryTask qt = Tables.get(TQueryTask.class,"qt");
		TQueryTaskCar qtc = Tables.get(TQueryTaskCar.class,"qtc");
		
		//根据scheduleId找出所有查询任务
		List<QueryTask> queryTasks = db.select(qt.all).from(qt,tos)
				.where(tos.scheduleId.eq(jobId)
						.and(tos.id.eq(qt.orgScheduleId))
						.and(tos.status.eq(StaticType.Status.VALID))
						.and(qt.status.eq(StaticType.Status.VALID))
						).queryObjectsForList(QueryTask.class);
		
		if(logger.isDebugEnabled()) {
			logger.debug("===========queryTasks.size="+queryTasks.size());
		}
		
		Map<String, OrgInfo> orgMap = db.findObjectsForMap(OrgInfo.class, toi.code, null);
		for(QueryTask queryTask:queryTasks) {
			
			ApiMoney apiMoney = moneyCache.getApiMoney(queryTask.getOrgCode(), queryTask.getId());
			if(apiMoney == null) {
				logger.error("no apiMoney found. orgCode="+queryTask.getOrgCode());
				continue;
			}
			int page = 1;
			Date startTime = new Date();
			int totalCount = 0;
			int validCount = 0;
			int failCount = 0;
			int trafficCount = 0;
			while (true) {
				//根据查询任务找出所有车辆
				ECPage<OrgCarInfo> orgCars = db.select(toc.all).from(toc,qt,qtc)
						.where(
								qtc.queryTaskId.eq(qt.id)
								.and(qtc.orgCarId.eq(toc.id))
								.and(qtc.queryTaskId.eq(queryTask.getId()))
								.and(qtc.status.eq(StaticType.Status.VALID))
								.and(toc.status.eq(StaticType.Status.VALID))
								.and(qt.status.eq(StaticType.Status.VALID))
								).queryPage(OrgCarInfo.class, page, ECPage.DEFAULT_SIZE);
				
				if(logger.isDebugEnabled()) {
					logger.debug("==page=["+page+"]==taskId=["+queryTask.getId()+"]======orgCars.size="+orgCars.getList().size());
				}
				
				//调用查询接口
				for(OrgCarInfo carInfo:orgCars.getList()) {
					String orgCode = carInfo.getOrgCode();
					RequestVO requestVO = new RequestVO();
					String queryTime = DateUtils.currentDateStr();
					OrgInfo orgInfo = orgMap.get(carInfo.getOrgCode());
					String sign = MD5Utils.md5(Base64.encode(carInfo.getCarNo()+carInfo.getCarType()+queryTime+orgInfo.getAppKey()));
					Integer provinceId = null;
					Integer cityId = null;
					requestVO.initVO(carInfo.getCarNo(), carInfo.getCarFrameNo(), carInfo.getCarEngineNo(), carInfo.getCarType(), carInfo.getOrgCode(), String.valueOf(queryTask.getId()), provinceId, cityId, queryTime, sign);
					Long start = System.currentTimeMillis();
					ResponseVO responseVO = spiRouter.getTrafficDatas(requestVO);
					responseVO.setTaskId(requestVO.getTaskId());
					responseVO.setQueryTime(requestVO.getQueryTime());
					responseVO.setCostTime((int)(System.currentTimeMillis() - start));
					responseVO.setOrgCode(orgCode);
					trafficService.saveQueryInfo(responseVO,requestVO);
					totalCount++;
					if(responseVO.getErrorCode() == StaticType.ErrorCode.SUCCESS) {
						validCount ++;
						if(responseVO.getValue()!= null) {
							trafficCount += responseVO.getValue().getTraffics().size();
						}
					} else {
						failCount ++;
					}
				}
				
				if(orgCars.isLastPage()) {
					break;
				}
				page++;
				
			}
			if(totalCount > 0) {
				Date endTime = new Date();
				QueryTaskDetail detail = new QueryTaskDetail();
				detail.setCreateTime(endTime);
				detail.setEndTime(endTime);
				detail.setStartTime(startTime);
				detail.setFailCount(failCount);
				detail.setQueryTaskId(queryTask.getId());
				detail.setTotalCount(totalCount);
				detail.setValidCount(validCount);
				detail.setTrafficCount(trafficCount);
				db.save(detail);
			}
			
		}
				
	}

}
