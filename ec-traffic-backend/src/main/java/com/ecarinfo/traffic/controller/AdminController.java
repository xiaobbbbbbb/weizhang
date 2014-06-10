package com.ecarinfo.traffic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@RequestMapping(value = "/")
	public String index(ModelMap map) {
		return "admin/index";
	}
}
