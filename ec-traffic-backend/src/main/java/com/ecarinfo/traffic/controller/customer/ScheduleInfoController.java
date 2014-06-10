package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TScheduleInfo;

import java.util.Date;

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
import com.ecarinfo.traffic.persist.po.ScheduleInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.ScheduleInfoVO;

/**
 * Description:任务类型
 */

@Controller
@RequestMapping("/scheduleInfo")
public class ScheduleInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(ScheduleInfoController.class);

	@RequestMapping(value = "/")
	public String index(String name, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(name))
			cond.and(TScheduleInfo.name.like("%" + name.trim() + "%"));

		// 状态
		if (status != null)
			cond.and(TScheduleInfo.status.eq(status));

		ECPage<ScheduleInfo> ECPage = db.findPage(ScheduleInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/schedule_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		ScheduleInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(ScheduleInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/schedule_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody ScheduleInfoVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				ScheduleInfo po = db.findByPK(ScheduleInfo.class, vo.getId());
				boolean isExist = false;
				if (!vo.getName().equals(po.getName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setName(vo.getName());// 任务名称
					po.setValue(vo.getValue());// 表达式的值
					po.setStatus(vo.getStatus());// 状态
					po.setUpdateTime(new Date());// 更新时间
					db.update(po);
				}
			} else {
				ScheduleInfo po = new ScheduleInfo();
				BeanUtils.copyProperties(vo, po);
				po.setCreateTime(new Date());// 创建时间
				po.setUpdateTime(new Date());// 更新时间
				if (!isExist(json, vo))
					db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, ScheduleInfoVO vo) {
		ScheduleInfo old = db.findObject(ScheduleInfo.class,
				db.where(TScheduleInfo.name.eq(vo.getName()).and(TScheduleInfo.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("任务名称不能重复！");
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
				db.updateTable(TScheduleInfo).set(TScheduleInfo.status, StaticType.Status.IN_VALID).where(TScheduleInfo.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("任务类型删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("任务类型删除失败!");
			logger.error("任务类型删除失败!", e);
		}
		return json;
	}

}
