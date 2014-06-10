package com.ecarinfo.traffic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TOrgUserInfo;
import com.ecarinfo.traffic.vo.AjaxJson;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request,ModelMap map) {
		
		return "admin/index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login/login";
	}
	@RequestMapping(value = "/exit")
	public String saveExit(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "login/login";
	}
	
	@RequestMapping(value = "/dologin")
	@ResponseBody
	public AjaxJson doLogin(String loginName, String passwd, HttpServletRequest request) {
		AjaxJson jsonObj = new AjaxJson();
		TOrgUserInfo ou= Tables.get(TOrgUserInfo.class);
		TOrgInfo oi= Tables.get(TOrgInfo.class);
		OrgUserInfo orgUserInfo = db.findObject(OrgUserInfo.class, db.where(ou.userName.eq(loginName).and(ou.password.eq(MD5Utils.md5(passwd)))));
		OrgInfo org = db.findObject(OrgInfo.class, db.where(oi.code.eq(orgUserInfo.getOrgCode()).and(oi.status.eq(1))));
		if (orgUserInfo != null) {
			request.getSession().setAttribute("user", orgUserInfo);
			request.getSession().setAttribute("org", org);
			jsonObj.setSuccess(true);
			jsonObj.setMsg("登陆成功");
		} else {
			jsonObj.setSuccess(false);
			jsonObj.setMsg("用户名密码错误！");
		}
		return jsonObj;
	}
}
