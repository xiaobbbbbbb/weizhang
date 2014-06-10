package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.QueryInfoVO;

/**
 * Description:违章查询记录
 */

@Controller
@RequestMapping("/queryInfo")
public class QueryInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(QueryInfoController.class);

	@RequestMapping(value = "/")
	public String index(String ownerName,String beginQueryTime,String endQueryTime,String carNo,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		
		Condition cond = Condition.getInstance();
		
		if (StringUtils.isNotEmpty(ownerName))
		    cond.and(TQueryInfo.ownerName.like("%" + ownerName.trim() + "%"));
		    
        //查询时间(接口调用时间)
        if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
		    cond.and(TQueryInfo.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"), DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));
		
		if (StringUtils.isNotEmpty(carNo))
		    cond.and(TQueryInfo.carNo.like("%" + carNo.trim() + "%"));
		    
		ECPage<QueryInfo> ECPage = db.findPage(QueryInfo.class,db.where(cond), pagerOffset, PAGE_SIZE);
		
		List<QueryInfoVO> vos = new ArrayList<QueryInfoVO>();
		for (QueryInfo po : ECPage.getList()) {
			QueryInfoVO vo = new QueryInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");
		
		List<Map<String, Object>> dictDateTasks = genService.dictDate(TQueryTask.getTableName(), TQueryTask.id.getName(), TQueryTask.name.getName());
		genService.dictDateReplace(vos, dictDateTasks, "taskId", "taskName");


		ECPage<QueryInfoVO> page = new ECPage<QueryInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());
		
		map.put("ECPage", page);
		
		return "customer/query_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		QueryInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(QueryInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/query_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody QueryInfoVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				QueryInfo po = db.findByPK(QueryInfo.class, dto.getId());
				po.setOwnerName(dto.getOwnerName());//车主姓名
				po.setQueryTime(dto.getQueryTime());//查询时间(接口调用时间)
				po.setCostTime(dto.getCostTime());//查询耗时(单位毫秒)
				po.setCarCertificate(dto.getCarCertificate());//车辆登记证书
				po.setTaskId(dto.getTaskId());//任务ID
				po.setErrorCode(dto.getErrorCode());//错误代号
				po.setOrgCode(dto.getOrgCode());//机构编码
				po.setCarType(dto.getCarType());//车辆类型
				po.setCarFrameNo(dto.getCarFrameNo());//车架号
				po.setCarEngineNo(dto.getCarEngineNo());//发动机号
				po.setSpiId(dto.getSpiId());//spi的id(用于计费)
				po.setArea(dto.getArea());//区域
				po.setUpdateTime(dto.getUpdateTime());//更新时间
				po.setTrafficCounts(dto.getTrafficCounts());//违章记录条数
				po.setErrorMessage(dto.getErrorMessage());//错误信息
				po.setCreateTime(dto.getCreateTime());//创建时间
				po.setCarNo(dto.getCarNo());//车牌号
				db.update(po);
			} else {
				QueryInfo po = new QueryInfo();
				BeanUtils.copyProperties(dto, po);
				db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxJson deleteModel(Long[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.delete(QueryInfo.class, db.where(TQueryInfo.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("违章查询记录删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("违章查询记录删除失败!");
			logger.error("违章查询记录删除失败!", e);
		}
		return json;
	}
}
