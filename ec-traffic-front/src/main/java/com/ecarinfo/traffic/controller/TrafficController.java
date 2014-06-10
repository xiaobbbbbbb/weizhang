package com.ecarinfo.traffic.controller;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryTaskCar;
import static com.ecarinfo.traffic.persist.tables.StaticImport.tOrgInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.common.utils.PropUtil;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.db4j.transaction.EcTransaction;
import com.ecarinfo.traffic.dto.QueryDto;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.logic.OperatorType;
import com.ecarinfo.traffic.persist.po.InputRule;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.po.QueryArea;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.persist.po.QueryTask;
import com.ecarinfo.traffic.persist.po.QueryTaskCar;
import com.ecarinfo.traffic.persist.po.TaskExpression;
import com.ecarinfo.traffic.persist.po.TrafficInfo;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TOrgSchedule;
import com.ecarinfo.traffic.persist.tables.TQueryArea;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TQueryTask;
import com.ecarinfo.traffic.persist.tables.TQueryTaskCar;
import com.ecarinfo.traffic.persist.tables.TQueryTaskDetail;
import com.ecarinfo.traffic.persist.tables.TQueryTraffic;
import com.ecarinfo.traffic.persist.tables.TScheduleInfo;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.traffic.utils.DtoUtil;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.OrgScheduleVO;
import com.ecarinfo.traffic.vo.QueryTaskDetailVO;

@Controller
@RequestMapping("/traffic")
public class TrafficController extends BaseController {
	protected transient static Logger logger = Logger
			.getLogger(TrafficController.class);

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request,ModelMap map) {
		TQueryArea tq = Tables.get(TQueryArea.class);
		List<QueryArea> qArea = db.findObjectsForList(QueryArea.class, db
				.where(tq.orgCode.eq(getUser(request).getOrgCode())).groupBy(tq.provinceId));
		map.put("qArea", qArea);
		return "traffic/index";
	}

	@RequestMapping(value = "/getCity")
	@ResponseBody
	public List<QueryArea> getCity(HttpServletRequest request,Integer id) {
		try {
			TQueryArea tq = Tables.get(TQueryArea.class);
			if (id != null && id > 0) {
				List<QueryArea> qArea = db.findObjectsForList(
						QueryArea.class,
						db.where(
								tq.orgCode.eq(getUser(request).getOrgCode())
										.and(tq.provinceId.eq(id))
										.and(tq.cityId.greaterOrEqual(1)))
								.groupBy(tq.cityId));
				return qArea;
			}
			return null;
		} catch (Exception e) {
			logger.error("查询城市区域出错", e);
			return null;
		}
	}

	@RequestMapping(value = "/rule")
	@ResponseBody
	public InputRule rule(Integer provinceId, Integer cityId) {
		try {
			TQueryArea tq = Tables.get(TQueryArea.class);
			Condition condition = Condition.getInstance();
			if (provinceId != null && provinceId > 0) {
				condition.and(tq.provinceId.eq(provinceId));
			}
			if (cityId != null && cityId > 0) {
				condition.and(tq.cityId.eq(cityId));
			}
			InputRule rule = db
					.findObject(InputRule.class, db.where(condition));
			return rule;
		} catch (Exception e) {
			logger.error("查询出错!", e);
			return null;
		}
	}

	/*
	 * 违章查询
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public ResponseVO search(HttpServletRequest request,Integer provinceId, Integer cityId,
			Integer carType, String carNo, String carFrameNo,
			String carEngineNo, String certificate) {
		try {

			carNo = DtoUtil.incode(carNo);
			carFrameNo = DtoUtil.incode(carFrameNo);
			carEngineNo = DtoUtil.incode(carEngineNo);
			certificate = DtoUtil.incode(certificate);
			String url =PropUtil.getProperties("common.properties").getProperty("spi_url");
			System.out.println("|||||||||||||||||||查询参数：carNo=" + carNo);
			boolean releaseConnnection = false;
			Map<String, String> params = new HashMap<String, String>();
			String orgCode=getUser(request).getOrgCode();
			OrgInfo org= db.findObject(OrgInfo.class, tOrgInfo.code.eq(orgCode));//机构信息
			String appKey = org.getAppKey();
			String queryTime = DateUtils.currentDateStr();
			params.put("carNo", carNo);
			params.put("carFrameNo", carFrameNo);
			params.put("carEngineNo", carEngineNo);
			params.put("carType", String.valueOf(carType));
			params.put("orgCode", orgCode);
			params.put("provinceId", String.valueOf(provinceId));
			params.put("cityId", String.valueOf(cityId));
			params.put("queryTime", queryTime);
			params.put(
					"sign",
					MD5Utils.md5(Base64.encode(carNo + carType + queryTime
							+ appKey)));
			String res = HttpClientUtils.get(url, params, releaseConnnection);
			System.out.println("||||||||||||||违章结果:" + res);
			ResponseVO responseVO = JSONUtil.fromJson(res, ResponseVO.class); // 真正的代码在这
			logUtil.log(OperatorType.FRONT,getUser(request).getId(),getUser(request).getUserName(), "查询违章信息", "carNo="+carNo+"carFrameNo="+carFrameNo+"carEngineNo="+carEngineNo+"certificate="+certificate);//日志记录
			return responseVO;
		} catch (Exception e) {
			logger.error("查询出错!", e);
			return null;
		}
	}

	// 任务配置UI
	@RequestMapping(value = "/taskUI")
	public String taskUI(HttpServletRequest request,ModelMap map) {
		// db.findRecords(sql, params);
		List<OrgScheduleVO> voList = new ArrayList<OrgScheduleVO>();
		String orgCode = getUser(request).getOrgCode();
		List<Map<String, Object>> list = db
				.findRecords("select os.id,os.org_code,os.schedule_id,si.`name`,aa.nums from org_schedule os LEFT JOIN schedule_info si on os.schedule_id = si.id" +
						" LEFT JOIN (SELECT qt.org_schedule_id,count(qc.org_car_id) nums FROM query_task qt LEFT JOIN query_task_car qc ON qc.query_task_id = qt.id "+
						" WHERE qt.org_code = '"+orgCode+"' AND qc.`status` = 1 GROUP BY qc.query_task_id )aa ON os.id = aa.org_schedule_id" +
						" where os.org_code= '"+orgCode+"' and os.status =1 ");
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map1 : list) {
				OrgScheduleVO vo = new OrgScheduleVO();
				vo.setId((Integer) map1.get("id"));
				vo.setScheduleId((Integer) map1.get("schedule_id"));
				vo.setName((String) map1.get("name"));
				vo.setOrgCode((String) map1.get("org_code"));
				vo.setNums(map1.get("nums")!=null?(Long)map1.get("nums"):0l);
				voList.add(vo);
			}
		}

		map.put("list", voList);
		return "traffic/task_list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		List<TaskExpression> exs = db.findAllObjects(TaskExpression.class);
		QueryTask dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(QueryTask.class, id);
		}
		model.put("exs", exs);
		model.put("dto", dto);
		return "traffic/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(HttpServletRequest request,@RequestBody QueryTask dto) {
		logger.debug(BeanUtils.toString(dto));
		AjaxJson json = new AjaxJson();
		OrgUserInfo userInfo = getUser(request);
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				QueryTask po = db.findByPK(QueryTask.class, dto.getId());
				po.setUpdateTime(new Date());
				po.setOrgScheduleId(dto.getOrgScheduleId());
				po.setName(dto.getName());
				po.setTaskDetail(dto.getTaskDetail());
				po.setStatus(dto.getStatus());
				po.setOperatorId(userInfo.getId());
				db.update(po);
			} else {
				dto.setCreateTime(new Date());
				dto.setUpdateTime(new Date());
				dto.setOrgCode(userInfo.getOrgCode());
				dto.setOperatorId(userInfo.getId());
				db.save(dto);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	/**
	 * 添加任务车辆页面
	 * @param request
	 * @param id
	 *            （org_schedule_id）
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/select")
	public String select(HttpServletRequest request, Integer orgScheduleId,
			String carNo, String batNo, Integer carType, String startTime,
			String endTime, ModelMap map) {
		try {
			Condition cd = Condition.getInstance();
			TOrgCarInfo tq = Tables.get(TOrgCarInfo.class);
			QueryDto dto = new QueryDto();
			cd.and(TOrgCarInfo.orgCode.eq(getUser(request).getOrgCode()));
			if (StringUtils.isNotBlank(carNo)) {
				dto.setCarNo(carNo);
				cd.and(tq.carNo.like("%" + carNo + "%"));
			}
			if (StringUtils.isNotBlank(batNo)) {
				dto.setBatNo(batNo);
				cd.and(tq.batNo.eq(batNo));
			}
			if (carType != null && carType > 0) {
				dto.setCarType(carType);
				cd.and(tq.carType.eq(carType));
			}
			if (StringUtils.isNotBlank(startTime)) {
				dto.setStartTime(startTime);
				cd.and(tq.createTime.greaterOrEqual(startTime));
			}
			if (StringUtils.isNotBlank(endTime)) {
				dto.setEndTime(endTime);
				cd.and(tq.createTime.lessOrEqual(endTime));
			}
			int pagerOffset = SystemContext.getPageOffset();
			ECPage<OrgCarInfo> page = db.findPage(
					OrgCarInfo.class,
					db.where(cd.and(tq.status.eq(0).and(
							tq.isDelete.eq(false).and(tq.queryType.eq(2))))),
					pagerOffset, PAGE_SIZE);// 一车辆只能绑定一个任务
			map.put("ECPage", page);
			map.put("dto", dto);
			map.put("orgScheduleId", orgScheduleId);
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
		return "traffic/select";
	}

	/**
	 * 解绑车辆页面
	 * 
	 * @param request
	 * @param id
	 *            （org_schedule_id）
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delUI")
	public String delUI(HttpServletRequest request, Integer orgScheduleId,
			String carNo, String batNo, Integer carType, String startTime,
			String endTime, ModelMap map) {
		try {
			TOrgCarInfo oc = Tables.get(TOrgCarInfo.class, "oc");
			TQueryTask qt = Tables.get(TQueryTask.class, "qt");
			TQueryTaskCar qc = Tables.get(TQueryTaskCar.class, "qtc");
			QueryDto dto = new QueryDto();
			int pagerOffset = SystemContext.getPageOffset();
			Condition cd = qt.id.eq(qc.queryTaskId).and(qc.orgCarId.eq(oc.id))
					.and(qc.status.eq(StaticType.Status.VALID))
					.and(qt.orgScheduleId.eq(orgScheduleId))
					.and(oc.isDelete.eq(false));

			cd.and(oc.orgCode.eq(getUser(request).getOrgCode()));
			if (StringUtils.isNotBlank(carNo)) {
				dto.setCarNo(carNo);
				cd.and(oc.carNo.like("%" + carNo + "%"));
			}
			if (StringUtils.isNotBlank(batNo)) {
				dto.setBatNo(batNo);
				cd.and(oc.batNo.eq(batNo));
			}
			if (carType != null && carType > 0) {
				dto.setCarType(carType);
				cd.and(oc.carType.eq(carType));
			}
			if (StringUtils.isNotBlank(startTime)) {
				dto.setStartTime(startTime);
				cd.and(oc.createTime.greaterOrEqual(startTime));
			}
			if (StringUtils.isNotBlank(endTime)) {
				dto.setEndTime(endTime);
				cd.and(oc.createTime.lessOrEqual(endTime));
			}
			ECPage<OrgCarInfo> orgCarPage = db.select(oc.all).from(qt, qc, oc)
					.where(cd)
					.queryPage(OrgCarInfo.class, pagerOffset, PAGE_SIZE);

			map.put("ECPage", orgCarPage);
			map.put("dto", dto);
			map.put("orgScheduleId", orgScheduleId);
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
		return "traffic/del";
	}

	// 绑定车辆
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson bindCars(HttpServletRequest request,String[] ids, Integer orgScheduleId) {
		AjaxJson json = new AjaxJson();
		try {

			QueryTask task = null;
			if (orgScheduleId == null || orgScheduleId < 0) {
				json.setSuccess(false);
				json.setMsg("没有调度任务！");
				return json;
			} else {
				// 根据调度id查找有无此调度的任务，没有就创建一个
				TQueryTask tq = Tables.get(TQueryTask.class);
				task = db.findObject(QueryTask.class,
						db.where(tq.orgScheduleId.eq(orgScheduleId)));
				if (task == null) {
					task = new QueryTask();
					task.setCreateTime(new Date());
					task.setUpdateTime(new Date());
					task.setOrgScheduleId(orgScheduleId);
					task.setOrgCode(getUser(request).getOrgCode());
					task.setOperatorId(getUser(request).getId());
					task.setStatus(1);
					db.save(task);
				}
			}

			List<QueryTaskCar> list = new ArrayList<QueryTaskCar>();
			// 封装对象
			for (String id : ids) {

				QueryTaskCar info = new QueryTaskCar();
				info.setOrgCarId(Long.valueOf(id));
				info.setQueryTaskId(task.getId());
				info.setCreateTime(new Date());
				info.setStatus(1);
				list.add(info);
			}
			// 批量保存
			saveWithBatch(list);
			logUtil.log(OperatorType.FRONT, getUser(request).getId(),getUser(request).getUserName(), "绑定车辆","车辆id:"+ids.toString());//日志记录
			json.setSuccess(true);
			json.setMsg("添加成功！");
			return json;
		} catch (Exception e) {
			logger.error("任务绑定车辆失败!", e);
			json.setMsg("任务绑定车辆失败!");
			json.setSuccess(false);
			return json;
		}
	}

	// 解绑
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,String[] ids,Integer orgScheduleId) {
		AjaxJson json = new AjaxJson();
		EcTransaction trans = new EcTransaction(db);
		try {
			if(orgScheduleId==null||orgScheduleId<0){
				json.setSuccess(false);
				json.setMsg("没有调度任务！");
				return json;
			}else{
				int idx = 1;
				trans.beginTrans();
				for (String id : ids) {
					db.updateTable(TOrgCarInfo).set(TOrgCarInfo.status,0).where(TOrgCarInfo.id.eq(Long.valueOf(id))).executeUpdate();
					db.updateTable(TQueryTaskCar).set(TQueryTaskCar.status, -1).where(TQueryTaskCar.orgCarId.eq(Long.valueOf(id))).executeUpdate();
					idx++;
					if (idx % 30 == 0) {
						trans.commit();
					}
				}
				trans.commit();
			}
			logUtil.log(OperatorType.FRONT, getUser(request).getId(),getUser(request).getUserName(),"解绑车辆","车辆id:"+ids.toString());//日志记录
			json.setMsg("解绑成功！");
			json.setSuccess(true);
		}catch (Exception e) {
			trans.rollback();
			json.setMsg("删除车辆失败！");
			json.setSuccess(false);
			logger.error(json.getMsg(),e);
		}
		return json;
	}

	// 批量保存
	private void saveWithBatch(List<QueryTaskCar> list) {
		int idx = 1;
		EcTransaction trans = new EcTransaction(db);
		trans.beginTrans();
		try {
			for (QueryTaskCar g : list) {
				db.updateTable(TOrgCarInfo).set(TOrgCarInfo.status, 1)
						.where(TOrgCarInfo.id.eq(g.getOrgCarId()))
						.executeUpdate();
				db.save(g);
				if (idx % 30 == 0) {
					trans.commit();
				}
				idx++;
			}
			trans.commit();
		} catch (Exception e) {
			logger.error("批量保存出错！", e);
			trans.rollback();
		}
	}
	
	@RequestMapping(value = "/result")
	public String result(HttpServletRequest request,String name,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		try{
			Condition cd = Condition.getInstance();
			TQueryTaskDetail qd = Tables.get(TQueryTaskDetail.class,"qd");
			TQueryTask qt = Tables.get(TQueryTask.class,"qt");
			TScheduleInfo si =Tables.get(TScheduleInfo.class,"si");
			TOrgSchedule os =Tables.get(TOrgSchedule.class,"os");
			QueryDto dto = new QueryDto();
			cd = qt.orgCode.eq(getUser(request).getOrgCode())
					.and(qd.queryTaskId.eq(qt.id))
					.and(qt.orgScheduleId.eq(os.id))
					.and(si.id.eq(os.scheduleId));
			
			if(StringUtils.isNotBlank(name)){
				cd.and(si.name.like("%"+name+"%"));
				dto.setName(name);
			}if(StringUtils.isNotBlank(startTime)){
				cd.and(qd.startTime.greaterOrEqual(startTime));
				dto.setStartTime(startTime);
			}if(StringUtils.isNotBlank(endTime)){
				cd.and(qd.startTime.lessOrEqual(endTime));
				dto.setEndTime(endTime);
			}
			ECPage<QueryTaskDetailVO> page = db.select(qd.all,si.name).from(qd,qt,si,os).where(cd).orderBy(qd.startTime).desc().queryPage(QueryTaskDetailVO.class, pagerOffset, PAGE_SIZE);
			map.put("ECPage", page);
			map.put("dto", dto);
		}
		catch (Exception e) {
				logger.error("违章批查结果列表错误！",e);
		}
		return "traffic/result";
	}
	
	/**
	 * 
	 * @param request
	 * @param orgScheduleId
	 * @param carNo
	 * @param batNo
	 * @param carType
	 * @param startTime
	 * @param endTime
	 * @param map
	 * @return
	 */

	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,Integer taskId, String startTime,
			String endTime, ModelMap map) {
		try {
			
			
			Condition cd = Condition.getInstance();
			TQueryInfo qi = Tables.get(TQueryInfo.class);
			cd.and(TOrgCarInfo.orgCode.eq(getUser(request).getOrgCode()));
			if (taskId!=null&&taskId>0) {
				cd.and(qi.taskId.eq(taskId));
			}
			if (StringUtils.isNotBlank(startTime)) {
				cd.and(qi.queryTime.ge(startTime));
			}
			if (StringUtils.isNotBlank(endTime)) {
				cd.and(qi.queryTime.le(endTime));
			}
			int pagerOffset = SystemContext.getPageOffset();
			ECPage<QueryInfo> page = db.findPage(QueryInfo.class,db.where( cd).orderBy(qi.queryTime).desc(), pagerOffset, PAGE_SIZE);
			map.put("ECPage", page);
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
		return "traffic/detail";
	}
	
	/**
	 * 实时查询结果
	 * @param request
	 * @param carNo
	 * @param startTime
	 * @param endTime
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/realTime")
	public String realTime(HttpServletRequest request,String carNo,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		try{
			Condition cd = Condition.getInstance();
			TQueryInfo qi = Tables.get(TQueryInfo.class);
			cd.and(TOrgCarInfo.orgCode.eq(getUser(request).getOrgCode()).and(qi.taskId.isNull()));
			if (StringUtils.isNotBlank(carNo)) {
				cd.and(qi.carNo.eq(carNo));
			}
			if (StringUtils.isNotBlank(startTime)) {
				cd.and(qi.queryTime.ge(startTime));
			}
			if (StringUtils.isNotBlank(endTime)) {
				cd.and(qi.queryTime.le(endTime));
			}
			ECPage<QueryInfo> page = db.findPage(QueryInfo.class,db.where( cd), pagerOffset, PAGE_SIZE);
			map.put("ECPage", page);
		}
		catch (Exception e) {
				logger.error("违章批查结果列表错误！",e);
		}
		return "traffic/real_search";
	}
	
	//实查违章明细
	@RequestMapping(value="/traffic_items")
	public String  trafficItems(Long queryId,ModelMap map){
		TQueryTraffic qt = Tables.get(TQueryTraffic.class,"qt");
		TTrafficInfo ti=Tables.get(TTrafficInfo.class,"ti");
		List<TrafficInfo> list = db.findObjectsForList(TrafficInfo.class,db.select(ti.all).from(qt,ti).where(qt.trafficId.eq(ti.id).and(qt.queryId.eq(queryId))).getSql());
		map.put("list", list);
		return "traffic/traffic_items";
	}
	
	//批查违章明细
	@RequestMapping(value = "/bat_items")
	public String bat_items(HttpServletRequest request,String carNo,Integer taskId ,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		try{
			Condition cd = Condition.getInstance();
			TQueryInfo qi = Tables.get(TQueryInfo.class,"qi");
			TQueryTraffic qt=Tables.get(TQueryTraffic.class,"qt");
			TTrafficInfo ti = Tables.get(TTrafficInfo.class,"ti");
			QueryDto dto = new QueryDto();
			cd.and(qi.id.eq(qt.queryId)).and(qt.trafficId.eq(ti.id));
			if (taskId!=null &&taskId>0) {
				cd.and(qi.taskId.eq(taskId));
			}
			if (StringUtils.isNotBlank(startTime)) {
				cd.and(qi.queryTime.ge(startTime));
				dto.setStartTime(startTime);
			}
			if (StringUtils.isNotBlank(endTime)) {
				cd.and(qi.queryTime.le(endTime));
				dto.setEndTime(endTime);
			}if (StringUtils.isNotBlank(carNo)) {
				cd.and(ti.carNo.like("%"+carNo+"%"));
				dto.setCarNo(carNo);
			}
			ECPage<TrafficInfo> page = db.select(ti.all).from(qi,qt,ti).where(cd).queryPage(TrafficInfo.class, pagerOffset, PAGE_SIZE);
			map.put("ECPage", page);
			map.put("taskId", taskId);
			map.put("dto",dto);
		}
		catch (Exception e) {
				logger.error("违章批查结果列表错误！",e);
		}
		return "traffic/bat_items";
	}
}
