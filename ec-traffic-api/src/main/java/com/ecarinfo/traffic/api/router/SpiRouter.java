package com.ecarinfo.traffic.api.router;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.api.cache.MoneyCache;
import com.ecarinfo.traffic.persist.po.ApiMoney;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.persist.po.SpiInfo;
import com.ecarinfo.traffic.persist.po.SpiMoney;
import com.ecarinfo.traffic.persist.po.SpiTack;
import com.ecarinfo.traffic.persist.po.TackInfo;
import com.ecarinfo.traffic.persist.po.TrafficInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TQueryTraffic;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.traffic.persist.tables.TSpiTack;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.meta.StaticType.ErrorCode;
import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficVO;

@Service
public class SpiRouter {
	@Resource
	private DB db;
	@Resource 
	private MoneyCache moneyCache;
	
	private static final Logger logger = Logger.getLogger(SpiRouter.class);
	public ResponseVO getTrafficDatas(RequestVO request) {
		ResponseVO responseVO = null;
		try {
			//1.校验参数的合法性
			Object[] objects = check(request);
			if(objects[0] != null) {
				return (ResponseVO)objects[0];
			}
			OrgInfo orgInfo = (OrgInfo)objects[1];
			//2.获取策略
			//根据机构查询策略
			if(orgInfo.getTackId() == null) {
				return getResponseVO(StaticType.ErrorCode.TACK_NOT_FOUND, "没有找到策略,orgCode="+request.getOrgCode());
			}
			TackInfo tackInfo = db.findByPK(TackInfo.class, orgInfo.getTackId());
			if(tackInfo == null) {
				return getResponseVO(StaticType.ErrorCode.TACK_NOT_FOUND, "没有找到策略,orgCode="+request.getOrgCode());
			}
//			boolean isRealTime = false;
//			if(tackInfo != null && tackInfo.getQueryType() == StaticType.QueryType.REAL_TIME) {
//				isRealTime = true;
//			}
			//3.非实时的，先取缓存
			QueryInfo queryInfo = null;
//			if(!isRealTime) {
			TQueryInfo tq = Tables.get(TQueryInfo.class);
			//area = md5(base64(carNo-provinceId-cityId-errorCode))
			String area = MD5Utils.md5(Base64.encode(request.getCarNo()+"-"+request.getProvinceId()+"-"+request.getCityId()+"-"+StaticType.ErrorCode.SUCCESS));
			queryInfo = db.findObject(QueryInfo.class, 
					db.where(
					tq.area.eq(area)
//					.and(tq.createTime.gt(DateUtils.getTodayDate(0, 0, 0))))
//					.and(tq.createTime.gt(DateUtils.plusHours(DateUtils.getTodayDate(23, 59, 59), tackInfo.getCacheTime()*-1))))
					.and(tq.createTime.gt(DateUtils.plusHours(new Date(), tackInfo.getCacheTime()*-1))))
					.limit(1));
			if(queryInfo != null) {
				responseVO = new ResponseVO();
				List<TrafficVO> trafficVOs = new ArrayList<TrafficVO>();
				TrafficResponseVO trafficResponseVO = new TrafficResponseVO();
				trafficResponseVO.setTraffics(trafficVOs);
				responseVO.setValue(trafficResponseVO);
				render(trafficResponseVO,request);
				if(queryInfo.getTrafficCounts() > 0) {
					//查找中间表
					TQueryTraffic tqt = Tables.get(TQueryTraffic.class,"tqt");
					TTrafficInfo ti = Tables.get(TTrafficInfo.class,"ti");
					
					List<TrafficInfo> trafficInfos = db.select(ti.all).from(ti,tqt)
							.where(tqt.trafficId.eq(ti.id)
									.and(tqt.queryId.eq(queryInfo.getId())))
									.queryObjectsForList(TrafficInfo.class);
					
					for(TrafficInfo info:trafficInfos) {
						trafficVOs.add(getTrafficVOFromPo(info));
					}
				}
			}
//			}
			
			//4.调用接口
			if(queryInfo == null) {
				SpiInfo spiInfo = findSpiByTack(tackInfo);//根据策略查找接口
				if(spiInfo == null) {
					return getResponseVO(StaticType.ErrorCode.SPI_NOT_FOUND, "没有找到SPI信息,orgCode="+request.getOrgCode());
				}
				SpiMoney spiMoney = moneyCache.getSpiMoney(spiInfo.getId());
				if(spiMoney == null) {
					return getResponseVO(StaticType.ErrorCode.SPI_MONEY_NOT_FOUND, "没有找到SPI调用价钱信息,spiId="+spiInfo.getId());
				}
				
				Map<String, String> params = request.getParams();
				ResponseVO rvo = renderSpiToMap(spiInfo,params);
				if(rvo != null) {
					return rvo;
				}
				String json = HttpClientUtils.post(spiInfo.getNodeUrl()+"getTrafficDatas", params, false);
				responseVO = JSONUtil.fromJson(json, ResponseVO.class);
				responseVO.setSpiId(spiInfo.getId());
				rendErrorCode(responseVO);
				if(logger.isDebugEnabled()) {
					logger.debug("get data from spi["+spiInfo.getName()+"]");
				}
			} else {
				if(logger.isDebugEnabled()) {
					logger.debug("get data from cache.");
				}
			}
			
		} catch (Exception e) {
			//TODO
			logger.error("",e);
			responseVO = getResponseVO(StaticType.ErrorCode.FAIL, "服务器异常");
		}
		//5.返回数据
		return responseVO;
	}
	
	private void rendErrorCode(ResponseVO vo) {
		Integer code = vo.getErrorCode();
		if(code.equals(StaticType.ErrorCode.SUCCESS)) {
			return;
		}
		if(Math.abs(code) < 99) {
			vo.setErrorCode(-2000+code);
		} else {
			logger.error("---unkown error code = "+code);
			vo.setErrorCode(ErrorCode.ERROR_UNKNOWN);
		}
	}
	
	
	private Object[] check(RequestVO request) {
		Object[] resObj = new Object[2];
		//计费
		ApiMoney apiMoney = moneyCache.getApiMoney(request.getOrgCode(), request.getTaskId()==null?null:Integer.valueOf(request.getTaskId()));
		if(apiMoney == null) {
			resObj[0] = getResponseVO(StaticType.ErrorCode.API_MONEY_NOT_FOUND,"没有找到API价钱信息. orgCode=["+request.getOrgCode()+"]");
			return resObj;
		}
		
		if(!StringUtils.hasText(request.getOrgCode())) {
			resObj[0] = getResponseVO(StaticType.ErrorCode.ORG_ERROR,"orgCode is required.");
			return resObj;
		}
		TOrgInfo toc = Tables.get(TOrgInfo.class);
		OrgInfo orgInfo = db.findObject(OrgInfo.class, toc.code.eq(request.getOrgCode()));
		if(orgInfo == null) {
			resObj[0] = getResponseVO(StaticType.ErrorCode.ORG_ERROR,"org not found. orgCode=["+request.getOrgCode()+"]");
			return resObj;
		}
		resObj[1] = orgInfo;
		if(!StringUtils.hasText(request.getSign())) {
			resObj[0] = getResponseVO(StaticType.ErrorCode.SIGN_ERROR,"sign is required.");
			return resObj;
		}
		
		Date queryTime = DateUtils.stringToDate(request.getQueryTime());
		if(Math.abs(System.currentTimeMillis() - queryTime.getTime()) > 30000) {
			resObj[0] = getResponseVO(StaticType.ErrorCode.QUERY_TIME_OUT_ERROR,"queryTime is error");
			return resObj;
		}
		
		String newSign = MD5Utils.md5(Base64.encode(request.getCarNo()+request.getCarType()+request.getQueryTime()+orgInfo.getAppKey()));
		if(!request.getSign().equals(newSign)) {
			logger.warn("newSign=["+newSign+"],sign=["+request.getSign()+"]");
			resObj[0] = getResponseVO(StaticType.ErrorCode.SIGN_ERROR,"sign is error");
			return resObj;
		}
		
		return resObj;
	}
//	
//	public static void main(String[] args) {
//		// request.uri=/traffic/datas?sign=bad263d19177e3d17530e683e11abddf&provinceId=20&carType=1
//		//;&carFrameNo=L8AL2CGF2CB000559&carNo=%E7%B2%A4BQ9443
//		//&cityId=214&orgCode=123456&queryTime=2014-06-05+11%3A34%3A11&carEngineNo=1412G022039
//		String carNo = "粤BQ9443";
//		String queryTime = "2014-06-05 11:34:11";
//		String carType = "1";
//		String appKey = "12345678987654321";
//		String newSign = MD5Utils.md5(Base64.encode(carNo+carType+queryTime+appKey));
//		System.err.println(newSign);
//	}
	
	private ResponseVO getResponseVO(Integer errorCode,String errorMessage) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setErrorCode(errorCode);
		responseVO.setErrorMessage(errorMessage);
		logger.warn(errorMessage);
		return responseVO;
	}
	
	/**
	 * 添加spi数据
	 * @param spiInfo
	 * @param params
	 */
	private ResponseVO renderSpiToMap(SpiInfo spiInfo,Map<String, String> params) {
		
		if(spiInfo.getTrafficUrl() == null) {
			return getResponseVO(StaticType.ErrorCode.SPI_TRAFFIC_URL_NOT_FOUND, "没有找到SPI.TRAFFIC_URL信息,orgCode="+params.get("orgCode"));
		}
		if(spiInfo.getSpiKey() == null) {
			return getResponseVO(StaticType.ErrorCode.SPI_KEY_NOT_FOUND, "没有找到SPI.SPI_KEY信息,orgCode="+params.get("orgCode"));
		}
		if(spiInfo.getUserName() == null || spiInfo.getPassword() == null) {
			return getResponseVO(StaticType.ErrorCode.SPI_USERNAME_OR_PASSWORD_NOT_FOUND, "SPI的用户名或密码为空,orgCode="+params.get("orgCode"));
		}
		params.put("queryUrl", spiInfo.getTrafficUrl());
		params.put("key", spiInfo.getSpiKey());
		params.put("sign", Base64.encode(spiInfo.getUserName()+" "+spiInfo.getPassword()+" "+spiInfo.getSpiKey()));
		return null;
	}
	
	/**
	 * 基本数据填充
	 * @param responseVO
	 * @param request
	 */
	private void render(TrafficResponseVO responseVO,RequestVO request) {
		responseVO.setCarEngineNo(request.getCarEngineNo());
		responseVO.setCarFrameNo(request.getCarFrameNo());
		responseVO.setCarNo(request.getCarNo());
		responseVO.setCarType(request.getCarType());
	}
	
	private TrafficVO getTrafficVOFromPo(TrafficInfo info) {
		TrafficVO vo = new TrafficVO();
		vo.setArchive(info.getArchive());
		vo.setCarNo(info.getCarNo());
		vo.setCode(info.getCode());
		vo.setMoney(info.getMoney());
		vo.setScores(info.getScores());
		vo.setTrafficDetail(info.getTrafficDetail());
		vo.setTrafficLocation(info.getTrafficLocation());
		vo.setTrafficTime(info.getTrafficTime());
		return vo;
	}
	
	private SpiInfo findSpiByTack(TackInfo tackInfo) {
		//TODO
		TSpiInfo ts = Tables.get(TSpiInfo.class);
		TSpiTack tst = Tables.get(TSpiTack.class);
		SpiTack spiTack = db.findObject(SpiTack.class, db.where(tst.tackId.eq(tackInfo.getId())).limit(1));
		SpiInfo spiInfo = db.findObject(SpiInfo.class, ts.id.eq(spiTack.getSpiId()));
		return spiInfo;
	}
	
}
