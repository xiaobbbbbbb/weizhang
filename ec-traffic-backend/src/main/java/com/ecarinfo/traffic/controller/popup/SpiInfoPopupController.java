package com.ecarinfo.traffic.controller.popup;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.SpiInfo;

/**
 * Description:选择第三方配置表
 */

@Controller
@RequestMapping("/spiInfoPopup")
public class SpiInfoPopupController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(SpiInfoPopupController.class);

	@RequestMapping(value = "/")
	public String spiInfoPopup(String name, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TSpiInfo.name.like("%" + name.trim() + "%"));

		// 接口状态
		if (status != null)
			cond.and(TSpiInfo.status.eq(status));

		ECPage<SpiInfo> ECPage = db.findPage(SpiInfo.class, db.where(cond), pagerOffset, PAGE_MIN_SIZE);

		map.put("ECPage", ECPage);
		return "popup/spi_info_popup";
	}
}
