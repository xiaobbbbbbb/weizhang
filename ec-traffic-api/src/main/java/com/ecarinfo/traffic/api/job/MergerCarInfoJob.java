package com.ecarinfo.traffic.api.job;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.CarInfo;
import com.ecarinfo.traffic.persist.po.QueryInfo;
import com.ecarinfo.traffic.persist.po.UpdateFlag;
import com.ecarinfo.traffic.persist.tables.TCarInfo;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TUpdateFlag;
import com.ecarinfo.traffic.protocol.meta.StaticType;


@Component("mergerCarInfoJob")
public class MergerCarInfoJob {
	private static final Logger logger = Logger.getLogger(MergerCarInfoJob.class);
	
	private Lock lock = new ReentrantLock();
	@Resource
	private DB db;
	public void execute() {
		logger.info("MergerCarInfoJob.execute");
		if(lock.tryLock()) {
			try {
				TUpdateFlag tuf = Tables.get(TUpdateFlag.class);
				TCarInfo tc = Tables.get(TCarInfo.class);
				TQueryInfo tq = Tables.get(TQueryInfo.class);
				UpdateFlag flag = db.findObject(UpdateFlag.class, tuf.name.eq(tq.getTableName()));
				if(flag == null) {
					flag = new UpdateFlag();
					Date now = new Date();
					flag.setCreateTime(now);
					flag.setLastUpdateTime(now);
					flag.setMaxId(0l);
					flag.setName(tq.getTableName());
					flag.setUpdateTime(now);
					db.save(flag);
				}
				int page = 1;
				int rowsPerPage = ECPage.DEFAULT_SIZE;
				
				while(true) {
					ECPage<QueryInfo> queryPage = db.findPage(QueryInfo.class, db.where(tq.id.gt(flag.getMaxId())
							.and(tq.errorCode.eq(StaticType.ErrorCode.SUCCESS))), page, rowsPerPage);
					
					if(queryPage.getList().size() == 0) {
						flag.setUpdateTime(new Date());
						flag.setLastUpdateTime(new Date());
						db.update(flag);
						break;
					}
					page ++ ;
					
					for(QueryInfo q:queryPage.getList()) {
						CarInfo carInfo = db.findObject(CarInfo.class, tc.carNo.eq(q.getCarNo())
								.and(tc.carType.eq(q.getCarType())));
						if(carInfo != null) {
							if(mergerInfo(q,carInfo)) {
								db.update(carInfo);
							}
						} else {
							carInfo= new CarInfo();
							simpleInit(q,carInfo);
							db.save(carInfo);
						}
						flag.setMaxId(q.getId());
					}
				}
				
			} finally {
				lock.unlock();
			}
		}
	}
	
	/**
	 * 合并车辆数据
	 * @param q
	 * @param c
	 * @return
	 */
	private boolean mergerInfo(QueryInfo q,CarInfo c) {
		boolean needUpdate = false;
		String carNo = q.getCarNo();
		String carCertificate = q.getCarCertificate();
		String carEngineNo = q.getCarEngineNo();
		String carFrameNo = q.getCarFrameNo();
		if(c.getCarNo() == null) {
			c.setCarNo(carNo);
			needUpdate = true;
		}
		
		//保存比较长（比较完整）的数据
		if(c.getCarCertificate() == null) {
			if(carCertificate != null) {
				c.setCarCertificate(carCertificate);
				needUpdate = true;
			}
		} else if(carCertificate != null && carCertificate.length() > c.getCarCertificate().length()) {
			c.setCarCertificate(carCertificate);
			needUpdate = true;
		}
		
		//保存比较长（比较完整）的数据
		if(c.getCarEngineNo() == null) {
			if(carEngineNo != null) {
				c.setCarEngineNo(carEngineNo);
				needUpdate = true;
			}
		} else if(carEngineNo != null && carEngineNo.length() > c.getCarEngineNo().length()) {
			c.setCarEngineNo(carEngineNo);
			needUpdate = true;
		}
		
		//保存比较长（比较完整）的数据
		if(c.getCarFrameNo() == null) {
			if(carFrameNo != null) {
				c.setCarFrameNo(carFrameNo);
				needUpdate = true;
			}
		} else if(carFrameNo != null && carFrameNo.length() > c.getCarFrameNo().length()) {
			c.setCarFrameNo(carFrameNo);
			needUpdate = true;
		}
		
		
		if(c.getCreateTime() == null) {
			c.setCreateTime(new Date());
			needUpdate = true;
		}
		if(needUpdate) {
			c.setStatus(StaticType.Status.VALID);
			c.setUpdateTime(new Date());
			c.setIsDelete(false);
			c.setCarType(q.getCarType());
		}
		return needUpdate;
	}
	
	private void simpleInit(QueryInfo q,CarInfo c) {
		c.setCarCertificate(q.getCarCertificate());
		c.setCarEngineNo(q.getCarEngineNo());
		c.setCarFrameNo(q.getCarFrameNo());
		c.setCarNo(q.getCarNo());
		c.setCarType(q.getCarType());
		Date now = new Date();
		c.setCreateTime(now);
		c.setIsDelete(false);
		c.setStatus(StaticType.Status.VALID);
		c.setUpdateTime(now);
	}
}
