package com.ecarinfo.traffic.api.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.ApiMoney;
import com.ecarinfo.traffic.persist.po.SpiMoney;
import com.ecarinfo.traffic.persist.tables.TApiMoney;
import com.ecarinfo.traffic.persist.tables.TSpiMoney;
import com.ecarinfo.traffic.protocol.meta.StaticType;

@Component
public class MoneyCache {
	@Resource
	private DB db;
	private static final Logger logger = Logger.getLogger(MoneyCache.class);
	private static final TApiMoney tapi = Tables.get(TApiMoney.class);
	private static final TSpiMoney tspi = Tables.get(TSpiMoney.class);
	private static final Map<String, List<ApiMoney>> apiMoneyMap = new ConcurrentHashMap<String, List<ApiMoney>>();
	private static final Map<Integer, List<SpiMoney>> spiMoneyMap = new ConcurrentHashMap<Integer, List<SpiMoney>>();
	private Lock lock = new ReentrantLock();
	@PostConstruct
	public void init() {
		reLoad();
	}
	
	public ApiMoney getApiMoney(String orgCode,Integer taskId) {
		lock.lock();
		try {
			List<ApiMoney> list = apiMoneyMap.get(orgCode);
			if(list == null) {
				list = apiMoneyMap.get(StaticType.DEFAULT_ORG_CODE);
			}
			if(list != null && list.size() > 0) {
				return list.get(0);
			}
//			for(ApiMoney money:list) {
//				if(taskId != null) {
//					if (money.getType() != StaticType.MoneyType.PER_REQUEST) {
//						return money;
//					}
//				} else {
//					if(money.getType() == StaticType.MoneyType.PER_REQUEST) {
//						return money;
//					}
//				}
//			}
			return null;//new RuntimeException("no apiMoney found. orgCode="+orgCode);
		} finally {
			lock.unlock();
		}
		
	}
	
	public SpiMoney getSpiMoney(Integer spiId) {
		lock.lock();
		try {
			List<SpiMoney> list = spiMoneyMap.get(spiId);
			if(list == null) {
				return null;//throw new RuntimeException("no spiMoney found. spiId="+spiId);
			}
			SpiMoney money = list.get(0);
			if(money == null) {
				return null;//throw new RuntimeException("no spiMoney found. spiId="+spiId);
			}
			return money;
		} finally {
			lock.unlock();
		}
	}
	
	public void reLoad() {
		lock.lock();
		try {
			apiMoneyMap.clear();
			spiMoneyMap.clear();
			List<ApiMoney> apiMoney = db.findObjectsForList(ApiMoney.class,db.where(
					tapi.status.eq(StaticType.Status.VALID)
					));
			for(ApiMoney money:apiMoney) {
				if(money.getOrgCode() == null) {
					logger.warn("ApiMoney.orgCode is null,ApiMoney.id="+money.getId());
					continue;
				}
				List<ApiMoney> list = apiMoneyMap.get(money.getOrgCode());
				if(list == null) {
					list = new ArrayList<ApiMoney>();
					apiMoneyMap.put(money.getOrgCode(), list);
				}
				list.add(money);
			}
			
			List<SpiMoney> spiMoney = db.findObjectsForList(SpiMoney.class,db.where(
					tspi.status.eq(StaticType.Status.VALID)
					));
			for(SpiMoney money:spiMoney) {
				if(money.getSpiId() == null) {
					logger.warn("SpiMoney.spi is null,SpiMoney.id="+money.getId());
					continue;
				}
				List<SpiMoney> list = spiMoneyMap.get(money.getSpiId());
				if(list == null) {
					list = new ArrayList<SpiMoney>();
					spiMoneyMap.put(money.getSpiId(), list);
				}
				list.add(money);
			}
		} finally {
			lock.unlock();
		}
	}
}
