package com.ecarinfo.traffic.spi.cxy.beans;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.AESEncryptor;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.SpiRule;
import com.ecarinfo.traffic.persist.tables.TSpiRule;
import com.ecarinfo.traffic.protocol.rule.cxy.CxyRule;

/**
 * 支持外籍车及港澳车的查询，需要传正确的车型代码，港牌车 是 传26，澳牌车 传 27，外籍车 传 06 *
 */
@Component
public class CxySPI {
	@Resource
	private DB db;
	
	//map<carNoPrefix,spiRule> || map<provinceName-cityName,spiRule>
	private final Map<String, SpiRule> ruleMap = new ConcurrentHashMap<String, SpiRule>();
	private static final Logger logger = Logger.getLogger(CxySPI.class);
	
	private Lock lock = new ReentrantLock();
	
	public Map<String, SpiRule> getRuleMap() {
		return ruleMap;
	}
	
	@PostConstruct
	public void loadRules() {
		initRules();
	}
	
	/**
	 * 初始化规则
	 * @param ruleUrl
	 */
	private final Map<String, SpiRule> initRules() {
		if(lock.tryLock()) {
			logger.info("===================initRules start==================");
			try {
				TSpiRule tsr = Tables.get(TSpiRule.class);
				List<SpiRule> spiRules = db.findObjectsForList(SpiRule.class, db.where(tsr.spiName.eq("chexy")));
				for(SpiRule rule:spiRules) {
					ruleMap.put(rule.getCarNoPrefix(), rule);
					ruleMap.put(rule.getProvinceName()+"-"+rule.getCityName(), rule);
				}
			} catch (Exception e) {
				logger.error("",e);
			} finally {
				lock.unlock();
			}
		}
		
		return ruleMap;
	}
		
	/**
	 * 获取违章信息
	 * @param carNo
	 * @param carFrameNo
	 * @param engineNo
	 * @return
	 */
	public final CxyQueryResult query(String carNo,String carFrameNo,String engineNo, String carTypeCode,String provinceName,String cityName,String queryUrl,String userName,String password,String key) throws CxyGripException{
		
		//invoke
		QueryInput input = new QueryInput();
		input.setUserid(userName);//用户名
		input.setUserpwd(password);//密码
		carNo = carNo.toUpperCase();
		input.setCarNo(carNo);//车牌
		input.setCarFrameNo(carFrameNo);//车架号
		input.setEngineNo(engineNo);//发动机号
		
		input.setCarType(carTypeCode);//车辆类型(02=小型车)
		String fromTime = DateUtils.plusMonths(new Date(), -24,TimeFormatter.FORMATTER2);//从两年前开始查询
		input.setTimeAxis(fromTime);
		input.setUseActivePrice("0");
		input.setVersion("2");
	
		CxyQueryResult result = null;
		
		try {
			Map<String, String> params = getMap(input,provinceName,cityName,key);
			String data = query(queryUrl, params);
			logger.info("carNo="+carNo+",carType="+carTypeCode+","+data);
			result = JSONUtil.fromJson(data, CxyQueryResult.class);
			//附加信息
			result.setCarNo(carNo);
			result.setCarFrameNo(carFrameNo);
			result.setEngineNo(engineNo);
		} catch (Exception e) {
			throw new CxyGripException(e);
		}
		return result;
	}
	
	private final String query(String url,Map<String, String> params) throws IOException {
		Map<String, String> paramsNeeded = new HashMap<String, String>();
		if (params!=null && params.size()>0) {
			for ( Entry<String, String> e : params.entrySet()) {
				paramsNeeded.put(e.getKey(), e.getValue()==null? null :e.getValue().toString());
			}
		}
		return HttpClientUtils.get(url, paramsNeeded);
	}
	
	private synchronized final Map<String, String> getMap(QueryInput input,String provinceName,String cityName,String key) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if(ruleMap.size() == 0) {
			loadRules();
		}
		
		SpiRule rule = getRuleByCarNoPrefix(input.getCarNo().substring(0, 2),provinceName,cityName);
		
		if(rule != null) {
			if(provinceName == null) {
				input.setProvinceId(rule.getProvinceId());
			}
			if(cityName == null) {
				input.setCityId(rule.getCityId());
			}
			
			int carFrameNoLen = input.getCarFrameNo().length();
			if(rule.getCarFrameLen() == 0) {//none
				input.setCarFrameNo("");
			} else if(rule.getCarFrameLen() == 99) {//all
				//do noth
			} else {//fixed
				input.setCarFrameNo(input.getCarFrameNo().substring(carFrameNoLen - rule.getCarFrameLen(), carFrameNoLen));
			}
			
			
			int engineLen = input.getEngineNo().length();
			if(rule.getCarEngineLen() == 0) {
				input.setEngineNo("");
			} else if(rule.getCarEngineLen() == 99) {
				//do noth
			} else {
				input.setEngineNo(input.getEngineNo().substring(engineLen - rule.getCarEngineLen(),engineLen));
			}
			
			if(input.getCarOwner() != null) {
				int ownerLen = input.getCarOwner().length();
				if(rule.getOwnerNameLen() == 0) {
					input.setCarOwner("");
				} else if(rule.getOwnerNameLen() == 99) {
					//do noth
				} else {
					input.setCarOwner(input.getCarOwner().substring(ownerLen - rule.getOwnerNameLen(),ownerLen));
				}
			}
		}
		
		if(input.getEngineNo() != null) {
			map.put("cardrivenumber", input.getEngineNo());
		}
		
		map.put("userid", input.getUserid());
		map.put("userpwd", input.getUserpwd());
		map.put("cartype", input.getCarType());
		map.put("carnumber", input.getCarNo());
		if(input.getCarFrameNo() != null) {
			map.put("carcode", input.getCarFrameNo());
		}
		
		if(input.getProvinceId() != null) {
			map.put("provinceid", String.valueOf(input.getProvinceId()));
		}
		if(input.getCityId() != null) {
			map.put("cityid", String.valueOf(input.getCityId()));
		}
		if(input.getCarOwner() != null) {
			map.put("carowner", input.getCarOwner());
		}
		map.put("useactiveprice", input.getUseActivePrice());
		map.put("version", input.getVersion());
		map.put("timeaxis", input.getTimeAxis());
		if("2".equals(input.getVersion())) {
			//sign = Base64(AES(Md5(车牌号+车架号+发动机号+用户名+密码+版本号+时间
			String ps = input.getCarNo()+input.getCarFrameNo()+input.getEngineNo()+input.getUserid()+input.getUserpwd()+input.getVersion()+input.getTimeAxis();
			String md5Str = MD5Utils.md5(ps);
			String sign = AESEncryptor.encrypt(md5Str, key);
			logger.info(String.format("========================[ps %s], [md5Str %s], [sign %s]", ps, md5Str, sign));
			map.put("sign", sign);
		}
		
		if("3".equals(input.getVersion())) {//RAS
//			String password = "";
//			String sign = "";
//			map.put("sign", sign);
		}
		if(input.getOther() != null) {
			map.put("other", input.getOther()); 
		}

		return map;
	}
	
	/**
	 * 根据车牌前缀查找规则
	 * @param carNumberPrefix
	 * @return
	 */
	private final SpiRule getRuleByCarNoPrefix(String carNoPrefix,String provinceName,String cityName) {
		if(provinceName == null || cityName == null) {
			return ruleMap.get(carNoPrefix);
		}
		return ruleMap.get(provinceName+"-"+cityName);
	}
	
	public static void main(String[] args) {
//		initRules("http://www.cx580.com/InputsCondition.aspx");
//		for(Map.Entry<String, Rule> en:ruleMap.entrySet()) {
//			System.err.println(BeanUtils.toString(en.getValue()));
//		}
		String url = "http://www.cx580.com/InputsCondition.aspx";
		String json = HttpClientUtils.get(url);
		List<CxyRule> rules = JSONUtil.getList(json, CxyRule.class);
		System.err.println(rules);
	}
	
}
