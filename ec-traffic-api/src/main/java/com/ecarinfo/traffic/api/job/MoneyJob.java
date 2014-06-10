package com.ecarinfo.traffic.api.job;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.traffic.api.cache.MoneyCache;

@Component("moneyJob")
public class MoneyJob {
	private static final Logger logger =Logger.getLogger(MoneyJob.class);
	@Resource
	private MoneyCache moneyCache;
	private Lock lock = new ReentrantLock();
	
	public void execute() {
		if (lock.tryLock()) {
			try {
				moneyCache.reLoad();
			} catch (Exception e) {
				logger.error("", e);
			} finally {
				lock.unlock();
			}
		}
	}
	
}
