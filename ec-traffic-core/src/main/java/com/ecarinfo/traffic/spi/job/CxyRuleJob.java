package com.ecarinfo.traffic.spi.job;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.config.DefaultConfig;
import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.PinYinUtil;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.SpiRule;
import com.ecarinfo.traffic.persist.tables.TSpiRule;
import com.ecarinfo.traffic.protocol.rule.cxy.CxyRule;
import com.ecarinfo.traffic.protocol.rule.cxy.CxyRule.Rule;

@Component("cxyRuleJob")
public class CxyRuleJob {
	@Resource
	private DefaultConfig defaultConfig;
	@Resource
	private DB db;
	
	@PostConstruct
	public void init() {
		execute();
	}
	
	private static final Logger logger = Logger.getLogger(CxyRuleJob.class);
	public void execute() {
		logger.info("RuleJob.execute");
		getRuleFromRemote();
	}
	
	private void getRuleFromRemote() {
		try {
			String json = null;
			try {
				json = HttpClientUtils.get(defaultConfig.getVal("spi.cxy.rule.url"));
			} catch (Exception e) {
				throw new RuntimeException("RuleJob.mergerCxy -- 网络异常",e);
			}
			List<CxyRule> rules = JSONUtil.getList(json, CxyRule.class);
			System.err.println(BeanUtils.toString(rules));
			TSpiRule ts = Tables.get(TSpiRule.class);
			for(CxyRule cr:rules) {
				Integer provinceID = cr.getProvinceID();
				String provinceName = cr.getProvinceName();
				String provincePrefix = cr.getProvincePrefix();
				for(Rule r:cr.getCities()) {
					r.setProvinceID(provinceID);
					r.setProvinceName(provinceName);
					r.setProvincePrefix(provincePrefix);
					
					boolean needUpdate = true;
					
					SpiRule spiRule = db.findObject(SpiRule.class, ts.cityName.eq(r.getName()));
					if(spiRule == null) {
						spiRule = new SpiRule();
						needUpdate = false;
					}
					
					init(spiRule,r);
					
					if(needUpdate) {
						db.update(spiRule);
					} else {
						db.save(spiRule);
					}
				}
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	private SpiRule init(SpiRule spiRule,Rule r) {
		spiRule.setCarEngineLen(r.getCarEngineLen());
		spiRule.setCarFrameLen(r.getCarCodeLen());
		spiRule.setOwnerNameLen(r.getCarOwnerLen()==null?-1:Integer.valueOf(r.getCarOwnerLen()));
		spiRule.setCarNoPrefix(r.getCarNumberPrefix());
		spiRule.setCityId(r.getCityID());
		spiRule.setCityName(r.getName());
		spiRule.setCityPinyin(PinYinUtil.getPingYin(r.getName()));
		Date now = new Date();
		spiRule.setCreateTime(now);
		spiRule.setOwnerNameLen(r.getCarOwnerLen());
		spiRule.setProvinceName(r.getProvinceName());
		spiRule.setProvinceId(r.getProvinceID());
		spiRule.setProvincePinyin(PinYinUtil.getPingYin(r.getProvinceName()));
		spiRule.setProvincePrefix(r.getProvincePrefix());
		spiRule.setSpiName("chexy");
		spiRule.setUpdateTime(now);
		
		return spiRule;
	}
}
