package com.ecarinfo.traffic.api.test;

import org.junit.Test;

import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.tables.TQueryTraffic;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;

public class Db4jTest {

	@Test
	public void test() {
		DB db = new DB();
		TQueryTraffic tqt = Tables.get(TQueryTraffic.class,"tqt");
		TTrafficInfo ti = Tables.get(TTrafficInfo.class,"ti");
		String sql = db.select(ti.all).from(ti,tqt)
				.where(tqt.trafficId.eq(ti.id)
						.and(tqt.queryId.eq(2L)))
						.getSql();
		System.err.println(sql);
	}
}
