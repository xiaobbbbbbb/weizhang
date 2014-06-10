package com.ecarinfo.traffic.controller.security;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalRole;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalUserRole;

import java.util.List;

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
import com.ecarinfo.traffic.persist.po.RalRole;
import com.ecarinfo.traffic.persist.po.RalUserRole;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.security.RalRoleVO;
import com.ecarinfo.traffic.vo.security.ZtreeVO;

/**
 * Description:用户角色
 */

@Controller
@RequestMapping("/ralRole")
public class RalRoleController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(RalRoleController.class);

	@RequestMapping(value = "/")
	public String index(String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TRalRole.name.like("%" + name.trim() + "%"));

		ECPage<RalRole> ECPage = db.findPage(RalRole.class, db.where(cond), pagerOffset, PAGE_SIZE);

		map.put("ECPage", ECPage);

		return "security/ral_role/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		RalRoleVO vo = null;
		if (id != null && id > 0) {
			vo = new RalRoleVO();
			RalRole dto = db.findByPK(RalRole.class, id);
			BeanUtils.copyProperties(dto, vo);
		}
		model.put("dto", vo);
		return "security/ral_role/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody RalRoleVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getRoleId() != null && vo.getRoleId() > 0) {
				RalRole po = db.findByPK(RalRole.class, vo.getRoleId());
				boolean isExist = false;
				if (!vo.getName().equals(po.getName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					this.ralService.update(vo);
				}
			} else {
				if (!isExist(json, vo))
					this.ralService.save(vo);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, RalRoleVO vo) {
		RalRole ralRole = db.findObject(RalRole.class, db.where(TRalRole.name.eq(vo.getName())));
		if (ralRole != null) {
			json.setSuccess(false);
			json.setMsg("已存在角色名称");
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
				List<RalUserRole> list = db.findObjectsForList(RalUserRole.class, db.where(TRalUserRole.roleId.in(ids)));
				if (list != null && list.size() > 0) {
					json.setSuccess(false);
					json.setMsg("该角色还在使用中，不允许删除");
				} else {
					db.delete(RalRole.class, db.where(TRalRole.roleId.in(ids)));
				}
			} else {
				json.setSuccess(false);
				json.setMsg("用户角色删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("用户角色删除失败!");
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	// 获取所有权限
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<ZtreeVO> getZtree(boolean showUrl, Integer roleId, String id) {
		List<ZtreeVO> dto = null;
		try {
			int pid = 0;
			if (id != null && id.trim().length() > 0) {
				pid = Integer.parseInt(id);
			}
			String target = "resource_rightFrame";
			String url = "ralasafe/resource/updateUI";
			dto = this.ralService.getZtree(showUrl, roleId, pid, target, url, null);
		} catch (Exception e) {
			logger.error("资源树获取失败!", e);
		}
		return dto;
	}
}
