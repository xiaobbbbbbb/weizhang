package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgSchedule;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;

/**
 * Description:选择机构
 */

@Controller
@RequestMapping("/orgInfoPopup")
public class OrgInfoPopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgInfoPopupController.class);

	@RequestMapping(value = "/")
	public String orgInfoPopup(String code, String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = TOrgSchedule.status.eq(StaticType.Status.VALID);

		if (StringUtils.isNotEmpty(name))
			cond.and(TOrgInfo.name.like("%" + name.trim() + "%"));

		if (StringUtils.isNotEmpty(code))
			cond.and(TOrgInfo.code.like("%" + code.trim() + "%"));

		ECPage<OrgInfo> ECPage = db.findPage(OrgInfo.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);
		map.put("ECPage", ECPage);
		return "popup/org_info_popup";
	}
}
