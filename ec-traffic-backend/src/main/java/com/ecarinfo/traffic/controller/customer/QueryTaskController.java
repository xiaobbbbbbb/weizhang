package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgUserInfo;
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

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.QueryTask;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.QueryTaskVO;

/**
 * Description:批查任务
 */

@Controller
@RequestMapping("/queryTask")
public class QueryTaskController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(QueryTaskController.class);

	@RequestMapping(value = "/")
	public String index(String orgCode, Integer taskType, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TQueryTask.orgCode.like("%" + orgCode.trim() + "%"));

		// 任务类型
		if (taskType != null)
			cond.and(TQueryTask.taskType.eq(taskType));

		// 状态
		if (status != null)
			cond.and(TQueryTask.status.eq(status));

		ECPage<QueryTask> ECPage = db.findPage(QueryTask.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<QueryTaskVO> vos = new ArrayList<QueryTaskVO>();
		for (QueryTask po : ECPage.getList()) {
			QueryTaskVO vo = new QueryTaskVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		List<Map<String, Object>> dictDateUsers = genService.dictDate(TOrgUserInfo.getTableName(), TOrgUserInfo.id.getName(), TOrgUserInfo.nickName.getName());
		genService.dictDateReplace(vos, dictDateUsers, "operatorId", "operatorName");

		ECPage<QueryTaskVO> page = new ECPage<QueryTaskVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/query_task/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		QueryTask dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(QueryTask.class, id);
		}
		model.put("dto", dto);
		return "customer/query_task/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody QueryTaskVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				QueryTask po = db.findByPK(QueryTask.class, dto.getId());
				po.setOrgCode(dto.getOrgCode());// 机构编号
				po.setOperatorId(dto.getOperatorId());// 操作员
				po.setUpdateTime(dto.getUpdateTime());// 更新时间
				po.setCreateTime(dto.getCreateTime());// 创建时间
				po.setStatus(dto.getStatus());// 任务状态
				po.setTaskType(dto.getTaskType());// 任务类型
				po.setTaskDetail(dto.getTaskDetail());// 任务描述
				db.update(po);
			} else {
				QueryTask po = new QueryTask();
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
	public AjaxJson deleteModel(Integer[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.delete(QueryTask.class, db.where(TQueryTask.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("批查任务删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("批查任务删除失败!");
			logger.error("批查任务删除失败!", e);
		}
		return json;
	}

}
