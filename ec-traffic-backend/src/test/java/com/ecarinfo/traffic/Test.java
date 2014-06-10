package com.ecarinfo.traffic;

import static com.ecarinfo.db4j.common.DBContext.count;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgUserInfo;

import org.apache.commons.lang.StringUtils;

import sun.security.provider.MD5;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;

public class Test {

	public static void main(String[] args) {
		System.err.println(TOrgUserInfo.getFieldName("nickName"));
		System.err.println(TOrgUserInfo.nickName.getName());
		System.err.println(TOrgUserInfo.getTableName());
		System.err.println(TOrgCarInfo.orgCode.getJavaName());
		DB db = new DB();
		// System.err.println(db.selectFrom(TOrgUserInfo).queryRecords());
		System.err.println(db.select(TOrgUserInfo.all).from(TOrgUserInfo).where(TOrgUserInfo.job.eq("dd")));
		System.err.println(db.selectFrom(TOrgUserInfo));
		

		TQueryInfo qi = Tables.get(TQueryInfo.class, "qi");
		TSpiInfo spi = Tables.get(TSpiInfo.class, "spi");

		Condition cond = qi.spiId.gt(0);
		
		String name="2";
		
		String beginQueryTime="2015-10-10";
		String endQueryTime="2015-10-10";

		if (StringUtils.isNotEmpty(name))
			cond.and(spi.name.like("%" + name.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginQueryTime) && StringUtils.isNotEmpty(endQueryTime))
			
			cond.and(qi.createTime.between(DateUtils.stringToDate(beginQueryTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endQueryTime.trim() + " 23:59:59")));

		
		
		String sqlString=db.select(spi.name, qi.spiId, count(qi.spiId)).from(qi).leftJoin(spi).on(qi.spiId.eq(spi.id)).where(cond).groupBy(qi.spiId).getSql();
		
		System.err.println(sqlString);
		
		System.err.println(MD5Utils.md5("123456"));
	}
}
