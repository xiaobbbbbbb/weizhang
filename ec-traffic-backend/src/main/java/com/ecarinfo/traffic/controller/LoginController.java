package com.ecarinfo.traffic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.common.utils.MD5Utils;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	/**
	 * 返回登陆页
	 */
	@RequestMapping(value = "/redirect")
	public String redirect() {
		return "common/redirect";
	}

	@RequestMapping(value = "/")
	public String index(ModelMap map) {
		return "admin/security/login";
	}

	@RequestMapping(value = "/doLogin")
	public String doLoginAdmin(String userName, String password, ModelMap map) {
		password = MD5Utils.md5(password);
		boolean result = this.ralService.loginIn(userName, password);
		if (result) {
			return REDIRECT + "/admin/";
		} else {
			map.put("msg", "用户名或密码错误!");
			return "admin/security/login";
		}
	}

	@RequestMapping(value = "/loginOut")
	public String loginOut() {
		if (ralService.loginOut())
			return REDIRECT + "/login/";
		else
			return null;
	}
}
