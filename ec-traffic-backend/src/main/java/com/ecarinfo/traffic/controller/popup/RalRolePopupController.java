package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalRole;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.RalRole;

/**
 * Description:选择用户角色
 */

@Controller
@RequestMapping("/ralRolePopup")
public class RalRolePopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(RalRolePopupController.class);

	@RequestMapping(value = "/")
	public String index(String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TRalRole.name.like("%" + name.trim() + "%"));

		ECPage<RalRole> ECPage = db.findPage(RalRole.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);

		map.put("ECPage", ECPage);

		return "popup/ral_role_popup";
	}
}
