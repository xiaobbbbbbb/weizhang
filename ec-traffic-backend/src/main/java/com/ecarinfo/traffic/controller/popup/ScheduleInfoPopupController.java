package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TScheduleInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.ScheduleInfo;

/**
 * Description:选择调度信息
 */

@Controller
@RequestMapping("/scheduleInfoPopup")
public class ScheduleInfoPopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(ScheduleInfoPopupController.class);

	@RequestMapping(value = "/")
	public String scheduleInfoPopup(String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TScheduleInfo.name.like("%" + name.trim() + "%"));

		ECPage<ScheduleInfo> ECPage = db.findPage(ScheduleInfo.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);

		map.put("ECPage", ECPage);
		return "popup/schedule_info_popup";
	}
}
