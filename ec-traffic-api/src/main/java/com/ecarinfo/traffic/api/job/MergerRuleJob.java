package com.ecarinfo.traffic.api.job;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.PinYinUtil;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.City;
import com.ecarinfo.traffic.persist.po.InputRule;
import com.ecarinfo.traffic.persist.po.SpiInfo;
import com.ecarinfo.traffic.persist.tables.TCity;
import com.ecarinfo.traffic.persist.tables.TInputRule;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.traffic.protocol.vo.RuleResponseVO;
import com.ecarinfo.traffic.protocol.vo.RuleVO;

@Component("mergerRuleJob")
public class MergerRuleJob {
	private static final Logger logger = Logger.getLogger(MergerRuleJob.class);
	@Resource
	private DB db;
	private Lock lock = new ReentrantLock();

	@PostConstruct
	public void init() {
		execute();
	}
	
	public void execute() {
		logger.info("RuleJob.execute");
		if(lock.tryLock()) {
			try {
				mergerRule();
			} finally {
				lock.unlock();
			}
		}
	}
	
	/**
	 * 将第三方调用参数合并
	 */
	private void mergerRule() {
		mergerCxy();
		mergerWeiche();
	}
	
	private void mergerCxy() {
		try {
			String json = null;
			try {
				TSpiInfo ts = Tables.get(TSpiInfo.class);
				SpiInfo spiInfo = db.findObject(SpiInfo.class, ts.enName.eq("chexy"));
				json = HttpClientUtils.get(spiInfo.getNodeUrl()+"getRules?spiEnName=chexy");
			} catch (Exception e) {
				throw new RuntimeException("RuleJob.mergerCxy -- 网络异常",e);
			}
			RuleResponseVO responseVO = JSONUtil.fromJson(json, RuleResponseVO.class);
			if(responseVO == null) {
				logger.error("spi's rule_url error 。");
				throw new RuntimeException("spi's rule_url error 。");
			}
			System.err.println(BeanUtils.toString(responseVO));
			TInputRule tir = Tables.get(TInputRule.class);
			TCity tc = Tables.get(TCity.class);
			for(RuleVO ruleVO:responseVO.getRules()) {
				boolean needUpdate = true;
				InputRule inputRule = db.findObject(InputRule.class, tir.cityName.eq(ruleVO.getCityName()));
				if(inputRule == null) {
					inputRule = new InputRule();
					needUpdate = false;
					City city = db.findObject(City.class, tc.name.eq(ruleVO.getCityName()));
					if(city == null) {
						logger.error("no cityName="+ruleVO.getCityName()+" found !"+(BeanUtils.toString(ruleVO)));
						continue;
					}
					inputRule.setProvinceId(city.getProvinceId());
					inputRule.setCityId(city.getId());
				} 
				
				init(inputRule,ruleVO);
				
				if(needUpdate) {
					db.update(inputRule);
				} else {
					db.save(inputRule);
				}
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	private InputRule init(InputRule inputRule,RuleVO r) {
		Integer engineLen = inputRule.getCarEngineLen();
		if(engineLen == null || engineLen < r.getCarEngineLen()) {
			inputRule.setCarEngineLen(r.getCarEngineLen());
		}
		Integer frameLen = inputRule.getCarFrameLen();
		if(frameLen == null || frameLen < r.getCarFrameLen()) {
			inputRule.setCarFrameLen(r.getCarFrameLen());
		}
		
		Integer ownerNameLen = inputRule.getOwnerNameLen() == null?-1:Integer.valueOf(inputRule.getOwnerNameLen());
		if(ownerNameLen == null || ownerNameLen < r.getOwnerNameLen()) {
			inputRule.setOwnerNameLen(r.getOwnerNameLen()==null?-1:Integer.valueOf(r.getOwnerNameLen()));
		}
		Integer carCertificateLen = inputRule.getCarCertificateLen();
		if(carCertificateLen == null || carCertificateLen < r.getCarCertificateLen()) {
			inputRule.setCarCertificateLen(r.getCarCertificateLen()==null?-1:Integer.valueOf(r.getCarCertificateLen()));
		}
		
		inputRule.setCarNoPrefix(r.getCarNoPrefix());
		inputRule.setCityName(r.getCityName());
		inputRule.setCityPinyin(PinYinUtil.getPingYin(r.getCityName()));
		Date now = new Date();
		inputRule.setCreateTime(now);
		inputRule.setOwnerNameLen(r.getOwnerNameLen());
		inputRule.setProvinceName(r.getProvinceName());
		inputRule.setProvincePinyin(PinYinUtil.getPingYin(r.getProvinceName()));
		inputRule.setProvincePrefix(r.getProvincePrefix());
		inputRule.setUpdateTime(now);
		
		return inputRule;
	}
	
	private void mergerWeiche() {
		//TODO
	}
}
