package com.ecarinfo.traffic.persist.logic;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.db4j.transaction.EcTransaction;
import com.ecarinfo.traffic.persist.po.InputRule;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.QueryArea;
import com.ecarinfo.traffic.persist.tables.TInputRule;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TQueryArea;

@Component("merger4Query")
public class Merger4Query {
	private static final Logger logger = Logger.getLogger(Merger4Query.class);
	@Resource
	private DB db;
	
	/**
	 * 合并服务机构查询规则
	 * @param orgCode
	 */
	public void mergerRule4Org(String orgCode) {
		TOrgInfo to = Tables.get(TOrgInfo.class);
		TQueryArea ta = Tables.get(TQueryArea.class);
		TInputRule ti = Tables.get(TInputRule.class);
		OrgInfo orgInfo = db.findObject(OrgInfo.class, to.code.eq(orgCode));
		if(orgInfo == null) {
			logger.warn("no orgInfo found. orgCode="+orgCode);
			return;
		}
		List<QueryArea> areas = db.findObjectsForList(QueryArea.class,db.where(ta.orgCode.eq(orgCode)));
		if(areas == null) {
			logger.warn("no areas found. orgCode="+orgCode);
			return;
		}
		EcTransaction trans = new EcTransaction(db);
		trans.beginTrans();
		try {
			boolean needUpdate = false;
			for(QueryArea area:areas) {
				InputRule rule = db.findObject(InputRule.class, 
						ti.provinceId.eq(area.getProvinceId())
						.and(ti.cityId.eq(area.getCityId())));
				if(rule == null) {
					logger.warn("no InputRule found.=== area="+BeanUtils.toString(area));
					continue;
				}
				if(rule.getCarCertificateLen() > orgInfo.getCarCertificateLen()) {
					orgInfo.setCarCertificateLen(rule.getCarCertificateLen());
					needUpdate = true;
				}
				if(rule.getCarEngineLen() > orgInfo.getCarEngineLen()) {
					orgInfo.setCarEngineLen(rule.getCarEngineLen());
					needUpdate = true;
				}
				if(rule.getCarFrameLen() > orgInfo.getCarFrameLen()) {
					orgInfo.setCarFrameLen(rule.getCarFrameLen());
					needUpdate = true;
				}
				if(rule.getOwnerNameLen() > orgInfo.getOwnerNameLen()) {
					orgInfo.setOwnerNameLen(rule.getOwnerNameLen());
					needUpdate = true;
				}
			}
			if(needUpdate) {
				orgInfo.setUpdateTime(new Date());
				db.update(orgInfo);
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			logger.error("",e);
		}
		
		
	}
}
