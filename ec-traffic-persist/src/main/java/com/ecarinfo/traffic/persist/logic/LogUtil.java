package com.ecarinfo.traffic.persist.logic;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.traffic.persist.po.LogInfo;

@Component
public class LogUtil {
	@Resource
	private DB db;
	private static final ArrayBlockingQueue<LogInfo> queue = new ArrayBlockingQueue<LogInfo>(1024);
	private static final Logger logger = Logger.getLogger(LogUtil.class);
	
	/**
	 * 
	 * @param operatorType 操作类型  如：OperatorType.BACK_END
	 * @param operatorId	操作员id
	 * @param content	操作内容
	 * @param remark 备注
	 */
	public final void log(OperatorType operatorType,Integer operatorId,String operatorName,String content,String remark) {
		try {
			LogInfo log = new LogInfo();
			log.setContent(content);
			log.setCreateTime(new Date());
			log.setOperatorId(operatorId);
			log.setOperatorName(operatorName);
			log.setOperatorType(operatorType.toString());
			log.setRemark(remark);
			queue.put(log);
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
						LogInfo log = queue.take();
						db.save(log);
					} catch (Exception e) {
						logger.error("",e);
					}
				}
			}
		}).start();
	}
	
}
