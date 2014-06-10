package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgType;

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
import com.ecarinfo.traffic.persist.po.OrgType;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.OrgTypeVO;

/**
 * Description:客户类型
 */

@Controller
@RequestMapping("/orgType")
public class OrgTypeController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgTypeController.class);

	@RequestMapping(value = "/")
	public String index(String name, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(name))
			cond.and(TOrgType.name.like("%" + name.trim() + "%"));

		// 状态
		if (status != null)
			cond.and(TOrgType.status.eq(status));

		ECPage<OrgType> ECPage = db.findPage(OrgType.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/org_type/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgType dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(OrgType.class, id);
		}
		model.put("dto", dto);
		return "customer/org_type/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody OrgTypeVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				OrgType po = db.findByPK(OrgType.class, vo.getId());
				boolean isExist = false;
				if (!vo.getName().equals(po.getName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setName(vo.getName());// 类型名称
					po.setUpdateTime(new Date());// 更新时间
					db.update(po);
				}
			} else {
				OrgType po = new OrgType();
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

	private boolean isExist(AjaxJson json, OrgTypeVO vo) {
		OrgType old = db.findObject(OrgType.class, db.where(TOrgType.name.eq(vo.getName()).and(TOrgType.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("类型名称不能重复！");
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
				db.updateTable(TOrgType).set(TOrgType.status, StaticType.Status.IN_VALID).where(TOrgType.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("客户类型删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("客户类型删除失败!");
			logger.error("客户类型删除失败!", e);
		}
		return json;
	}
}
