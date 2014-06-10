package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TLogInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.LogInfo;

/**
 * Description:系统日志
 */

@Controller
@RequestMapping("/logInfo")
public class LogInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(LogInfoController.class);

	@RequestMapping(value = "/")
	public String index(Integer operatorId, String beginCreateTime, String endCreateTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		// 操作员
		if (operatorId != null)
			cond.and(TLogInfo.operatorId.eq(operatorId));

		// 创建时间
		if (StringUtils.isNotEmpty(beginCreateTime) && StringUtils.isNotEmpty(endCreateTime))
			cond.and(TLogInfo.createTime.between(DateUtils.stringToDate(beginCreateTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endCreateTime.trim() + " 23:59:59")));

		ECPage<LogInfo> ECPage = db.findPage(LogInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/log_info/list";
	}
}
