package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TProvince;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.Province;

/**
 * Description:选择省份
 */

@Controller
@RequestMapping("/provincePopup")
public class ProvincePopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(ProvincePopupController.class);

	@RequestMapping(value = "/")
	public String provincePopup(String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TProvince.name.like("%" + name.trim() + "%"));

		ECPage<Province> ECPage = db.findPage(Province.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);

		map.put("ECPage", ECPage);
		return "popup/province_popup";
	}
}
