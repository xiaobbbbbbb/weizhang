package com.ecarinfo.traffic;

import static com.ecarinfo.db4j.common.DBContext.count;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.traffic.service.GenService;
import com.ecarinfo.traffic.vo.customer.SpiQueryVO;

public class FTesrt extends SimpleTest {

	@Resource
	protected GenService genService;

	@Test
	public void Gen() {
		
		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TSpiInfo spi = Tables.get(TSpiInfo.class, "spi");
		
		ECPage<SpiQueryVO> vos=db.select(spi.name,qi.spiId,count(qi.spiId).as("num")).from(qi).leftJoin(spi).on(qi.spiId.eq(spi.id)).where(qi.spiId.isNotNull()).groupBy(qi.spiId).queryPage(SpiQueryVO.class, 1, 10);
		
		System.err.println(BeanUtils.toString(vos));

		
	}
	
	@Test
	public void Gen2() {
		
		
		
		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TOrgInfo oi = Tables.get(TOrgInfo.class, "oi");
		
		ECPage<SpiQueryVO> vos=db.select(oi.name,oi.code,count(oi.id).as("num")).from(oi).leftJoin(qi).on(oi.code.eq(qi.orgCode)).where(qi.taskId.isNotNull()).groupBy(qi.orgCode).queryPage(SpiQueryVO.class, 1, 10);
		
		System.err.println(BeanUtils.toString(vos));

		
	}
}
