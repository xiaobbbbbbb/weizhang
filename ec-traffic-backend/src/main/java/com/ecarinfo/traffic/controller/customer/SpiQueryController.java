package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.vo.customer.QueryInfoVO;

/**
 * Description:接口调用记录
 */

@Controller
@RequestMapping("/spiQuery")
public class SpiQueryController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(SpiQueryController.class);

	@RequestMapping(value = "/")
	public String index(String ownerName,String beginQueryTime,String endQueryTime,String carNo,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		
		Condition cond = TQueryInfo.spiId.isNotNull();
		
		if (StringUtils.isNotEmpty(ownerName))
		    cond.and(TQueryInfo.ownerName.like("%" + ownerName.trim() + "%"));
		    
        //查询时间(接口调用时间)
        if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
		    cond.and(TQueryInfo.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"), DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));
		
		if (StringUtils.isNotEmpty(carNo))
		    cond.and(TQueryInfo.carNo.like("%" + carNo.trim() + "%"));
		    
		
		ECPage<QueryInfo> ECPage = db.findPage(QueryInfo.class,db.where(cond), pagerOffset, PAGE_SIZE);
		
		List<QueryInfoVO> vos = new ArrayList<QueryInfoVO>();
		for (QueryInfo po : ECPage.getList()) {
			QueryInfoVO vo = new QueryInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");
		
		List<Map<String, Object>> dictDateTasks = genService.dictDate(TQueryTask.getTableName(), TQueryTask.id.getName(), TQueryTask.name.getName());
		genService.dictDateReplace(vos, dictDateTasks, "taskId", "taskName");

		ECPage<QueryInfoVO> page = new ECPage<QueryInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());
		
		map.put("ECPage", page);
		
		return "customer/spi_query/list";
	}
}
