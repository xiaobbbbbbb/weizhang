package com.ecarinfo.traffic.controller;

import static com.ecarinfo.db4j.common.DBContext.count;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.stat.TableStat.Mode;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.dto.QueryDto;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TOrgSchedule;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TQueryTask;
import com.ecarinfo.traffic.persist.tables.TQueryTaskCar;
import com.ecarinfo.traffic.persist.tables.TScheduleInfo;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.traffic.service.GenService;
import com.ecarinfo.traffic.vo.OrgCarVO;
import com.ecarinfo.traffic.vo.QueryInfoVO;
import com.ecarinfo.traffic.vo.SpiQueryVO;

/**
 * Description:查询对账
 */

@Controller
@RequestMapping("/queryAccount")
public class QueryAccountController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(QueryAccountController.class);
	@Resource
	protected GenService genService;

	// 批查查询对账
	@RequestMapping(value = "/batch")
	public String spiBatchAccount(HttpServletRequest request,String name, String beginQueryTime, String endQueryTime, ModelMap map) {

		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		String orgcode =  getUser(request).getOrgCode();
		Condition cond = qi.taskId.gt(0).and(qi.orgCode.eq(orgcode));
		QueryDto dto = new QueryDto();
		//累计车辆数
		TOrgCarInfo ci= Tables.get(TOrgCarInfo.class);
		Condition cd= ci.orgCode.eq(orgcode).and(ci.isDelete.eq(false).and( ci.queryType.eq(2)));//包年

		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime)){
			cond.and(qi.createTime.between(beginQueryTime + " 00:00:00",
					endQueryTime + " 23:59:59"));
			cd.and(ci.createTime.between(beginQueryTime+" 00:00:00", endQueryTime+" 23:59:59"));
			dto.setStartTime(beginQueryTime);
			dto.setEndTime(endQueryTime);
		}

		String sql = db.select(count(qi.orgCode).as("num")).from(qi).where(cond).
				getSql();
		System.out.println("----------sql-----:"+sql);
		Map<String, Object>  timesMap = db.findOneRecord(sql);
		Long times =(Long) timesMap.get("num");
		String sql2 = db.select(count(ci.id).as("num")).from(ci).where(cd).getSql();
		Map<String, Object> carmap = db.findOneRecord(sql2);
		Long carNums= (Long)carmap.get("num");
		map.put("carNums", carNums);
		map.put("times", times);
		map.put("dto", dto);
		return "query_account/spi_batch_account_list";
	}

	// 实时查询对账
	@RequestMapping(value = "/realTime")
	public String spiRealTimeAccount(HttpServletRequest request,String name, String beginQueryTime, String endQueryTime, ModelMap map) {
		QueryDto dto = new QueryDto();
		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");

		Condition cond = qi.taskId.isNull().and(qi.orgCode.eq(getUser(request).getOrgCode()));


		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime)){
			cond.and(qi.createTime.between(beginQueryTime.trim() + " 00:00:00",
					endQueryTime.trim() + " 23:59:59"));
			dto.setStartTime(beginQueryTime);
			dto.setEndTime(endQueryTime);
		}
		String sql = db.select(count(qi.orgCode).as("num")).from(qi).where(cond).getSql();

		Map<String, Object>  timesMap = db.findOneRecord(sql);
		Long times =(Long) timesMap.get("num");
		map.put("dto", dto);
		map.put("times", times);
		return "query_account/spi_realtime_account_list";
	}

	//批查车辆明细
	@RequestMapping(value="/batCars")
	public String batCats(HttpServletRequest request,Integer queryType ,String carNo,String batNo,Integer carType,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		try{
//			SELECT
//			oi.car_no,
//			si.`name`,
//			tc.create_time
//		FROM
//			org_car_info oi
//		LEFT JOIN query_task_car tc ON oi.id = tc.org_car_id
//		AND tc.`status` = 1
//		LEFT JOIN query_task qt ON tc.query_task_id = qt.id
//		LEFT JOIN org_schedule os ON qt.org_schedule_id = os.id
//		LEFT JOIN schedule_info si ON si.id = os.schedule_id
			Condition cd = Condition.getInstance();
			TOrgCarInfo oi = Tables.get(TOrgCarInfo.class,"oi");
			TQueryTaskCar tc = Tables.get(TQueryTaskCar.class,"tc");
			TQueryTask qt = Tables.get(TQueryTask.class,"qt");
			TOrgSchedule os = Tables.get(TOrgSchedule.class,"os");
			TScheduleInfo si = Tables.get(TScheduleInfo.class,"si");
			QueryDto dto = new QueryDto();
			cd.and(oi.orgCode.eq(getUser(request).getOrgCode()))
			.and(oi.isDelete.eq(false))
			.and(oi.queryType.eq(2));
			if(StringUtils.isNotBlank(carNo)){
				dto.setCarNo(carNo);
				cd.and(oi.carNo.like("%"+carNo+"%"));
			}if(StringUtils.isNotBlank(batNo)){
				dto.setBatNo(batNo);
				cd.and(oi.batNo.eq(batNo));
			}if(carType!=null&&carType>0){
				dto.setCarType(carType);
				cd.and(oi.carType.eq(carType));
			}if(StringUtils.isNotBlank(startTime)){
				dto.setStartTime(startTime);
				cd.and(oi.createTime.greaterOrEqual(startTime));
			}if(StringUtils.isNotBlank(endTime)){
				dto.setEndTime(endTime);
				cd.and(oi.createTime.lessOrEqual(endTime));
			}
			ECPage<OrgCarVO>	page = db.select(oi.all,si.name,tc.createTime.as("task_date")).from(oi).leftJoin(tc).on(oi.id.eq(tc.orgCarId).and(tc.status.eq(1)))
			.leftJoin(qt).on(tc.queryTaskId.eq(qt.id)).leftJoin(os).on(qt.orgScheduleId.eq(os.id)).leftJoin(si).on(si.id.eq(os.scheduleId))
			.where(cd).queryPage(OrgCarVO.class, pagerOffset, PAGE_SIZE);
			map.put("ECPage", page);
			map.put("dto", dto);
		}
		catch (Exception e) {
				logger.error("",e);
		}
		return "query_account/bat_car_list";
		
	}
	
	@RequestMapping(value = "/batDetial")
	public String realTime(HttpServletRequest request,String carNo,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		QueryDto dto  = new QueryDto();
		try{
			
//			SELECT
//			qi.*,
//			si. NAME
//		FROM
//			query_info qi
//		LEFT JOIN query_task qt ON qt.id = qi.task_id
//		LEFT JOIN org_schedule os ON os.id = qt.org_schedule_id
//		LEFT JOIN schedule_info si ON si.id = os.schedule_id
//		WHERE
//			qi.task_id > 0
			Condition cd = Condition.getInstance();
			TQueryInfo qi = Tables.get(TQueryInfo.class,"qi");
			TQueryTask qt= Tables.get(TQueryTask.class,"qt");
			TOrgSchedule os= Tables.get(TOrgSchedule.class,"os");
			TScheduleInfo si= Tables.get(TScheduleInfo.class,"si");
			cd.and(qi.orgCode.eq(getUser(request).getOrgCode()).and(qi.taskId.greaterThan(0)));
			if (StringUtils.isNotBlank(carNo)) {
				cd.and(qi.carNo.eq(carNo));
				dto.setCarNo(carNo);
			}
			if (StringUtils.isNotBlank(startTime)) {
				cd.and(qi.queryTime.ge(startTime));
				dto.setStartTime(startTime);
			}
			if (StringUtils.isNotBlank(endTime)) {
				cd.and(qi.queryTime.le(endTime));
				dto.setEndTime(endTime);
			}
			ECPage<QueryInfoVO> page = db.select(qi.all,si.name).from(qi).leftJoin(qt).on(qt.id.eq(qi.taskId))
					.leftJoin(os).on(os.id.eq(qt.orgScheduleId)).leftJoin(si).on(si.id.eq(os.scheduleId)).where(cd)
					.queryPage(QueryInfoVO.class, pagerOffset, PAGE_SIZE);
			map.put("dto", dto);
			map.put("ECPage", page);
		}
		catch (Exception e) {
				logger.error("违章批查结果列表错误！",e);
		}
		return "query_account/bat_query";
	}
	
	@RequestMapping(value = "/realTimeDetial")
	public String realTimeDetial(HttpServletRequest request,String carNo,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		QueryDto dto  = new QueryDto();
		try{
			
			Condition cd = Condition.getInstance();
			TQueryInfo qi = Tables.get(TQueryInfo.class);
			cd.and(qi.orgCode.eq(getUser(request).getOrgCode()).and(qi.taskId.isNull()));
			if (StringUtils.isNotBlank(carNo)) {
				cd.and(qi.carNo.eq(carNo));
				dto.setCarNo(carNo);
			}
			if (StringUtils.isNotBlank(startTime)) {
				cd.and(qi.queryTime.ge(startTime));
				dto.setStartTime(startTime);
			}
			if (StringUtils.isNotBlank(endTime)) {
				cd.and(qi.queryTime.le(endTime));
				dto.setEndTime(endTime);
			}
			ECPage<QueryInfo> page  = db.findPage(QueryInfo.class, db.where(cd), pagerOffset, PAGE_SIZE);
			map.put("dto", dto);
			map.put("ECPage", page);
		}
		catch (Exception e) {
				logger.error("违章批查结果列表错误！",e);
		}
		return "query_account/real_time_query";
	}
}
