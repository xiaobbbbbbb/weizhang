package com.ecarinfo.traffic.spi.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.netty.http.annotation.Module;
import com.ecarinfo.netty.http.annotation.RequestMethod;
import com.ecarinfo.netty.http.annotation.RequestURI;
import com.ecarinfo.netty.http.bean.RequestContext;
import com.ecarinfo.netty.http.bean.ResponseContext;
import com.ecarinfo.traffic.persist.po.SpiRule;
import com.ecarinfo.traffic.persist.tables.TSpiRule;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.traffic.protocol.vo.RuleResponseVO;
import com.ecarinfo.traffic.protocol.vo.RuleVO;
import com.ecarinfo.traffic.protocol.vo.TrafficResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficVO;
import com.ecarinfo.traffic.spi.cxy.beans.CxyGripException;
import com.ecarinfo.traffic.spi.cxy.beans.CxyQueryResult;
import com.ecarinfo.traffic.spi.cxy.beans.CxyQueryResult.Record;
import com.ecarinfo.traffic.spi.cxy.beans.CxySPI;

@Module("/chexy")
@Component
public class CxyTrafficHandler {
	@Resource
	private CxySPI cxySPI;
	
	@Resource
	private DB db;
	
	private static final Logger logger = Logger.getLogger(CxyTrafficHandler.class);
	@RequestURI(value="/getTrafficDatas",method={RequestMethod.GET,RequestMethod.POST})
	public ResponseVO getTrafficDatas(String carNo,String carFrameNo,String carEngineNo,Integer carType,String provinceName,String cityName,String queryTime,String queryUrl,String sign,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
		CxyQueryResult cxyResult = null;
		ResponseVO responseVO = null;
		try {
			String sign2 = Base64.decode(sign);
			String [] signArry = sign2.split(" ");
			String userName = signArry[0];
			String password = signArry[1];
			String key = signArry[2];
			cxyResult = cxySPI.query(carNo, carFrameNo, carEngineNo, 0+String.valueOf(carType),provinceName,cityName, queryUrl, userName, password, key);
			responseVO = getResponseVOFromCxyResult(cxyResult,carType);
		} catch (CxyGripException e) {
			logger.error("",e);
			responseVO = new ResponseVO();
			responseVO.setErrorCode(StaticType.ErrorCode.SOCKET_ERROR);
			responseVO.setErrorMessage("网络异常");
		} catch (Exception e) {
			logger.error("",e);
			responseVO = new ResponseVO();
			responseVO.setErrorCode(StaticType.ErrorCode.FAIL);
			responseVO.setErrorMessage("服务器异常");
		}
		return responseVO;
	}
	
	private ResponseVO getResponseVOFromCxyResult(CxyQueryResult cxyResult,Integer carType) {
		ResponseVO responseVO = new ResponseVO();
		Integer errorCode = cxyResult.getErrorCode();
		responseVO.setErrorCode(errorCode);
		responseVO.setErrorMessage(cxyResult.getErrMessage());
		TrafficResponseVO trafficResponseVO = new TrafficResponseVO();
		trafficResponseVO.setCarEngineNo(cxyResult.getEngineNo());
		trafficResponseVO.setCarFrameNo(cxyResult.getCarFrameNo());
		trafficResponseVO.setCarNo(cxyResult.getCarNo());
		trafficResponseVO.setCarType(carType);
		List<TrafficVO> traffics = new ArrayList<TrafficVO>();
		trafficResponseVO.setTraffics(traffics);
		if(errorCode == 0 && cxyResult.getRecords().size()> 0) {
			for(Record record:cxyResult.getRecords()) {
				TrafficVO tvo = new TrafficVO();
				tvo.setArchive(record.getArchive());
				tvo.setCarNo(cxyResult.getCarNo());
				tvo.setCode(record.getCode());
				tvo.setMoney(Float.valueOf(record.getCount()));
				tvo.setScores(Integer.valueOf(record.getDegree()));
				tvo.setTrafficDetail(record.getReason());
				tvo.setTrafficLocation(record.getLocation());
				tvo.setTrafficTime(DateUtils.stringToDate(record.getTime()));
				tvo.setTrafficCity(record.getLocationName());
				tvo.setStatus(Integer.valueOf(record.getStatus()));
				tvo.setCategory(record.getCategory());
				traffics.add(tvo);
			}
		}
		responseVO.setValue(trafficResponseVO);
		return responseVO;
	}
	
	@RequestURI(value="/getRules",method={RequestMethod.GET,RequestMethod.POST})
	public RuleResponseVO getRules(String spiEnName) {
		TSpiRule ts = Tables.get(TSpiRule.class);
		List<SpiRule> spiRules = db.findObjectsForList(SpiRule.class, db.where(ts.spiName.eq(spiEnName)));
		List<RuleVO> rules = new ArrayList<RuleVO>();
		for(SpiRule rule:spiRules) {
			RuleVO ruleVO = new RuleVO();
			init(rule, ruleVO);
			rules.add(ruleVO);
		}
		RuleResponseVO responseVO = new RuleResponseVO(spiEnName, rules);
		return responseVO;
	}
	
	public void init(SpiRule rule,RuleVO vo) {
		vo.setCarCertificateLen(rule.getCarCertificateLen());
		vo.setCarEngineLen(rule.getCarEngineLen());
		vo.setCarFrameLen(rule.getCarFrameLen());
		vo.setCarNoPrefix(rule.getCarNoPrefix());
//		vo.setCityId(cityId);
//		vo.setProvinceId(provinceId);
		vo.setCityName(rule.getCityName());
		vo.setCityPinyin(rule.getCityPinyin());
		vo.setOwnerNameLen(rule.getOwnerNameLen());
		vo.setProvinceName(rule.getProvinceName());
		vo.setProvincePinyin(rule.getProvincePinyin());
		vo.setProvincePrefix(rule.getProvincePrefix());
		vo.setSpiName(rule.getSpiName());
	}
			
	
}
