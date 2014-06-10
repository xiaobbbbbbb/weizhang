package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TTackInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.TackInfo;

/**
 * Description:选择策略信息
 */

@Controller
@RequestMapping("/tackInfoPopup")
public class TackInfoPopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(TackInfoPopupController.class);

	@RequestMapping(value = "/")
	public String tackInfoPopup(String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TTackInfo.name.like("%" + name.trim() + "%"));

		ECPage<TackInfo> ECPage = db.findPage(TackInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);

		map.put("ECPage", ECPage);
		return "popup/tack_info_popup";
	}
}
