package com.ecarinfo.traffic.api.queue;

import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.traffic.api.task.SaveQurerInfoTask;

@Component("queryInfoQueue")
public class QueryInfoQueue {
	private static final Logger logger = Logger.getLogger(QueryInfoQueue.class);
	private static final ArrayBlockingQueue<SaveQurerInfoTask> queue = new ArrayBlockingQueue<SaveQurerInfoTask>(20480);
	
	public void add(SaveQurerInfoTask saveQurerInfoTask) {
		try {
			queue.put(saveQurerInfoTask);
			if(logger.isDebugEnabled()) {
				logger.debug("QueryInfoQueue.queue.size="+queue.size());
			}
		} catch (InterruptedException e) {
			logger.error("",e);
		}
	}
	
	@PostConstruct
	private void run() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						SaveQurerInfoTask saveQurerInfoTask = queue.take();
						saveQurerInfoTask.execute();
					} catch (Exception e) {
						logger.error("",e);
					}
				}
			}
		}).start();
	}
}
