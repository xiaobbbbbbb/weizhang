package com.ecarinfo.traffic.controller.security;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalUser;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.RalUser;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.security.RalUserVO;

/**
 * Description:用户管理
 */

@Controller
@RequestMapping("/ralUser")
public class RalUserController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(RalUserController.class);

	@RequestMapping(value = "/")
	public String index(String name, String loginName, Integer status, String beginCreateTime, String endCreateTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(name))
			cond.and(TRalUser.name.like("%" + name.trim() + "%"));

		if (StringUtils.isNotEmpty(loginName))
			cond.and(TRalUser.loginName.like("%" + loginName.trim() + "%"));

		// 是否有效
		if (status != null)
			cond.and(TRalUser.status.eq(status));

		// 创建日期
		if (StringUtils.isNotEmpty(beginCreateTime) && StringUtils.isNotEmpty(endCreateTime))
			cond.and(TRalUser.createTime.between(DateUtils.stringToDate(beginCreateTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endCreateTime.trim() + " 23:59:59")));

		ECPage<RalUser> ECPage = db.findPage(RalUser.class, db.where(cond), pagerOffset, PAGE_SIZE);

		map.put("ECPage", ECPage);

		return "security/ral_user/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		RalUserVO vo = null;
		if (id != null && id > 0) {
			vo = this.ralService.findUserInfo(id);
		}
		model.put("dto", vo);
		return "security/ral_user/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody RalUserVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getUserId() != null && vo.getUserId() > 0) {
				RalUser po = db.findByPK(RalUser.class, vo.getUserId());
				boolean isExist = false;
				if (!vo.getLoginName().equals(po.getLoginName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					if (!vo.getPassword().equals(po.getPassword())) {
						vo.setPassword(MD5Utils.md5(vo.getPassword()));
					}
					this.ralService.update(vo);
				}
			} else {
				vo.setPassword(MD5Utils.md5(vo.getPassword()));
				if (!isExist(json, vo))
					this.ralService.save(vo);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, RalUserVO vo) {
		RalUser ralUser = db.findObject(RalUser.class, db.where(TRalUser.loginName.eq(vo.getLoginName())));
		if (ralUser != null) {
			json.setSuccess(false);
			json.setMsg("已存在登录名!");
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
				db.updateTable(TRalUser).set(TRalUser.status, StaticType.Status.IN_VALID).where(TRalUser.userId.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("用户管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("用户管理删除失败!");
			logger.error(json.getMsg(), e);
		}
		return json;
	}
}
