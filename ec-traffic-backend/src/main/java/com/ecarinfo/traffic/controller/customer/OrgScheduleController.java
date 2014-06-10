package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgSchedule;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TScheduleInfo;

import java.util.ArrayList;
import java.util.Date;
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
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgSchedule;
import com.ecarinfo.traffic.persist.po.ScheduleInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.OrgScheduleVO;

/**
 * Description:任务调度配置
 */

@Controller
@RequestMapping("/orgSchedule")
public class OrgScheduleController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgScheduleController.class);

	@RequestMapping(value = "/")
	public String index(String orgCode, String beginCreateTime, String endCreateTime, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TOrgSchedule.orgCode.like("%" + orgCode.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginCreateTime) && StringUtils.isNotEmpty(endCreateTime))
			cond.and(TOrgSchedule.createTime.between(DateUtils.stringToDate(beginCreateTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endCreateTime.trim() + " 23:59:59")));

		// 状态
		if (status != null)
			cond.and(TOrgSchedule.status.eq(status));

		ECPage<OrgSchedule> ECPage = db.findPage(OrgSchedule.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<OrgScheduleVO> vos = new ArrayList<OrgScheduleVO>();
		for (OrgSchedule po : ECPage.getList()) {
			OrgScheduleVO vo = new OrgScheduleVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		List<Map<String, Object>> dictDateSchedules = genService.dictDate(TScheduleInfo.getTableName(), TScheduleInfo.id.getName(),
				TScheduleInfo.name.getName());
		genService.dictDateReplace(vos, dictDateSchedules, "scheduleId", "scheduleName");

		ECPage<OrgScheduleVO> page = new ECPage<OrgScheduleVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/org_schedule/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgScheduleVO vo = null;
		if (id != null && id > 0) {
			vo = new OrgScheduleVO();
			OrgSchedule dto = db.findByPK(OrgSchedule.class, id);
			BeanUtils.copyProperties(dto, vo);
			OrgInfo org = db.findObject(OrgInfo.class, db.where(TOrgInfo.code.eq(vo.getOrgCode())));
			if (org != null)
				vo.setOrgName(org.getName());
			ScheduleInfo info = db.findObject(ScheduleInfo.class, db.where(TScheduleInfo.id.eq(vo.getScheduleId())));
			if (info != null)
				vo.setScheduleName(info.getName());
		}
		model.put("dto", vo);
		return "customer/org_schedule/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody OrgScheduleVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				OrgSchedule po = db.findByPK(OrgSchedule.class, vo.getId());
				boolean isExist = false;
				if (!vo.getOrgCode().equals(po.getOrgCode()) && !vo.getScheduleId().equals(po.getScheduleId()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setOrgCode(vo.getOrgCode());// 客户编号
					po.setScheduleId(vo.getScheduleId());// 调度名称
					po.setUpdateTime(new Date());// 更新时间
					po.setStatus(vo.getStatus());// 状态
					db.update(po);
				}
			} else {
				OrgSchedule po = new OrgSchedule();
				BeanUtils.copyProperties(vo, po);
				po.setUpdateTime(new Date());// 更新时间
				po.setCreateTime(new Date());// 创建时间
				if (!isExist(json, vo))
					db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, OrgScheduleVO vo) {
		OrgSchedule old = db.findObject(
				OrgSchedule.class,
				db.where(TOrgSchedule.orgCode.eq(vo.getOrgCode()).and(TOrgSchedule.scheduleId.eq(vo.getScheduleId()))
						.and(TOrgSchedule.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("客户下的任务调度不能重复！");
			return true;
		}
		return false;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxJson deleteModel(Integer[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.updateTable(TOrgSchedule).set(TOrgSchedule.status, StaticType.Status.IN_VALID).where(TOrgSchedule.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("任务调度配置删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("任务调度配置删除失败!");
			logger.error("任务调度配置删除失败!", e);
		}
		return json;
	}
}
