package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.db4j.common.DBContext.count;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.traffic.vo.customer.SpiQueryVO;

/**
 * Description:查询对账
 */

@Controller
@RequestMapping("/queryAccount")
public class QueryAccountController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(QueryAccountController.class);

	// 来源违章接口对账
	@RequestMapping(value = "/spiAccount")
	public String spiAccount(String name, String beginQueryTime, String endQueryTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TSpiInfo spi = Tables.get(TSpiInfo.class, "spi");

		Condition cond = qi.spiId.gt(0);

		if (StringUtils.isNotEmpty(name))
			cond.and(spi.name.like("%" + name.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
			cond.and(qi.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));

		String sql = db.select(spi.name, qi.spiId, count(qi.spiId).as("num")).from(qi).leftJoin(spi).on(qi.spiId.eq(spi.id)).where(cond).groupBy(qi.spiId)
				.getSql();

		ECPage<SpiQueryVO> ECPage = genService.queryPage(SpiQueryVO.class, pagerOffset, PAGE_SIZE, sql);

		map.put("ECPage", ECPage);
		return "customer/query_account/spi_account_list";
	}

	// 批查查询对账
	@RequestMapping(value = "/spiBatchAccount")
	public String spiBatchAccount(String name, String beginQueryTime, String endQueryTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TOrgInfo oi = Tables.get(TOrgInfo.class, "oi");

		Condition cond = qi.taskId.gt(0);

		if (StringUtils.isNotEmpty(name))
			cond.and(oi.name.like("%" + name.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
			cond.and(qi.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));

		String sql = db.select(oi.name, oi.code, count(qi.orgCode).as("num")).from(oi).leftJoin(qi).on(oi.code.eq(qi.orgCode)).where(cond)
				.groupBy(qi.orgCode).getSql();

		ECPage<SpiQueryVO> ECPage = genService.queryPage(SpiQueryVO.class, pagerOffset, PAGE_SIZE, sql);
		map.put("ECPage", ECPage);
		return "customer/query_account/spi_batch_account_list";
	}

	// 实时查询对账
	@RequestMapping(value = "/spiRealTimeAccount")
	public String spiRealTimeAccount(String name, String beginQueryTime, String endQueryTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TOrgInfo oi = Tables.get(TOrgInfo.class, "oi");

		Condition cond = qi.taskId.isNull();

		if (StringUtils.isNotEmpty(name))
			cond.and(oi.name.like("%" + name.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
			cond.and(qi.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));

		String sql = db.select(oi.name, oi.code, count(qi.orgCode).as("num")).from(oi).leftJoin(qi).on(oi.code.eq(qi.orgCode)).where(cond)
				.groupBy(qi.orgCode).getSql();

		ECPage<SpiQueryVO> ECPage = genService.queryPage(SpiQueryVO.class, pagerOffset, PAGE_SIZE, sql);

		map.put("ECPage", ECPage);
		return "customer/query_account/spi_realtime_account_list";
	}
}
