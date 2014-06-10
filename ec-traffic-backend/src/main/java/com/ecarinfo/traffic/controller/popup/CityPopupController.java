package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TCity;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.City;

/**
 * Description:选择城市
 */

@Controller
@RequestMapping("/cityPopup")
public class CityPopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(CityPopupController.class);

	@RequestMapping(value = "/")
	public String cityPopup(Integer provinceId, String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		// 所属省份
		if (provinceId != null)
			cond.and(TCity.provinceId.eq(provinceId));

		if (StringUtils.isNotEmpty(name))
			cond.and(TCity.name.like("%" + name.trim() + "%"));

		ECPage<City> ECPage = db.findPage(City.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);

		map.put("ECPage", ECPage);
		return "popup/city_popup";
	}
}
