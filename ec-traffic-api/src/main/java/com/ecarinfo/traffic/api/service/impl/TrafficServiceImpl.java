package com.ecarinfo.traffic.api.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.api.cache.MoneyCache;
import com.ecarinfo.traffic.api.service.TrafficService;
import com.ecarinfo.traffic.persist.po.ApiMoney;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.persist.po.QueryTraffic;
import com.ecarinfo.traffic.persist.po.SpiMoney;
import com.ecarinfo.traffic.persist.po.TrafficInfo;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficVO;

@Service("trafficService")
public class TrafficServiceImpl implements TrafficService {
	private static final Logger logger = Logger.getLogger(TrafficServiceImpl.class);
	@Resource
	private DB db;
	@Resource
	private MoneyCache moneyCache;
	
	@Override
	public void saveQueryInfo(ResponseVO responseVO,RequestVO requestVO) {
		QueryInfo queryInfo = new QueryInfo();
		TrafficResponseVO tVo = (TrafficResponseVO)responseVO.getValue();
		Date now = new Date();
		queryInfo.setCostTime(responseVO.getCostTime());
		queryInfo.setCreateTime(now);
		queryInfo.setErrorCode(responseVO.getErrorCode());
		queryInfo.setErrorMessage(responseVO.getErrorMessage());
		queryInfo.setFromCache(responseVO.getSpiId() == null);
		
		//计费
		ApiMoney apiMoney = moneyCache.getApiMoney(requestVO.getOrgCode(), requestVO.getTaskId()==null?null:Integer.valueOf(requestVO.getTaskId()));
		if(apiMoney != null) {
			logger.warn("no apiMoney found. orgCode="+requestVO.getOrgCode());
			queryInfo.setApiMoneyId(apiMoney.getId());
			queryInfo.setApiMoneyValue(apiMoney.getMoney());
			queryInfo.setApiMoneyType(apiMoney.getType());
		}
		
		if(responseVO.getSpiId() != null) {
			SpiMoney spiMoney = moneyCache.getSpiMoney(responseVO.getSpiId());
			queryInfo.setSpiMoneyId(spiMoney.getId());
			queryInfo.setSpiMoneyValue(spiMoney.getMoney());
			queryInfo.setSpiMoneyType(spiMoney.getType());
		}
		
		queryInfo.setSpiId(responseVO.getSpiId());
		
		String area = null;
		if (responseVO.getErrorCode() == StaticType.ErrorCode.SUCCESS) {
			area = MD5Utils.md5(Base64.encode(requestVO.getCarNo()+"-"+requestVO.getProvinceId()+"-"+requestVO.getCityId()+"-"+StaticType.ErrorCode.SUCCESS));
		}
		queryInfo.setArea(area);
		queryInfo.setOrgCode(responseVO.getOrgCode());
		queryInfo.setQueryTime(DateUtils.stringToDate(requestVO.getQueryTime()));
		if(responseVO.getTaskId() != null) {
			queryInfo.setTaskId(Integer.valueOf(responseVO.getTaskId()));
		}
		if(tVo != null) {
			queryInfo.setCarEngineNo(tVo.getCarEngineNo());
			queryInfo.setCarFrameNo(tVo.getCarFrameNo());
			queryInfo.setCarNo(tVo.getCarNo());
			queryInfo.setCarType(Integer.valueOf(tVo.getCarType()));
			
			queryInfo.setTrafficCounts(tVo.getTraffics().size());
			queryInfo.setUpdateTime(now);
		} else {
			queryInfo.setCarEngineNo(requestVO.getCarEngineNo());
			queryInfo.setCarFrameNo(requestVO.getCarFrameNo());
			queryInfo.setCarNo(requestVO.getCarNo());
			queryInfo.setCarType(Integer.valueOf(requestVO.getCarType()));
			queryInfo.setTrafficCounts(0);
			queryInfo.setUpdateTime(now);
		}
		db.save(queryInfo);
		if(tVo != null) {
			if(tVo.getTraffics().size() > 0) {
				for(TrafficVO trafficVO:tVo.getTraffics()) {
					String uKey = MD5Utils.md5(Base64.encode(trafficVO.getCarNo()+queryInfo.getCarType()+DateUtils.dateToString(trafficVO.getTrafficTime(), TimeFormatter.FORMATTER1)+trafficVO.getTrafficDetail()));
					TTrafficInfo ti = Tables.get(TTrafficInfo.class);
					TrafficInfo tInfo = db.findObject(TrafficInfo.class, ti.uniqueKey.eq(uKey));//new TrafficInfo();
					if(tInfo == null) {//如果违章不存在，则存之
						tInfo = new TrafficInfo();
						tInfo.setUniqueKey(uKey);
						tInfo.setArchive(trafficVO.getArchive());
						tInfo.setCarNo(tVo.getCarNo());
						tInfo.setCode(trafficVO.getCode());
						tInfo.setCreateTime(now);
						tInfo.setMoney(trafficVO.getMoney());
						tInfo.setCarType(queryInfo.getCarType());
						tInfo.setScores(trafficVO.getScores());
						tInfo.setTrafficDetail(trafficVO.getTrafficDetail());
						tInfo.setTrafficLocation(trafficVO.getTrafficLocation());
						tInfo.setTrafficTime(trafficVO.getTrafficTime());
						tInfo.setTrafficCity(trafficVO.getTrafficCity());
						tInfo.setCategory(trafficVO.getCategory());
						tInfo.setStatus(trafficVO.getStatus());
						db.save(tInfo);
					} else {
						if(trafficVO.getStatus() != null && tInfo.getStatus() != null) {
							if(!trafficVO.getStatus().equals(tInfo.getStatus())) {//修改违章状态
								tInfo.setStatus(trafficVO.getStatus());
								db.update(tInfo);
							}
						}
					}
					
					QueryTraffic queryTraffic = new QueryTraffic();
					queryTraffic.setCreateTime(now);
					queryTraffic.setQueryId(queryInfo.getId());
					queryTraffic.setTrafficId(tInfo.getId());
					db.save(queryTraffic);
					trafficVO.setUniqueKey(tInfo.getUniqueKey());
				}
			}
		}
	}

}
