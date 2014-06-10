package com.ecarinfo.traffic.api.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.ScheduleInfo;
import com.ecarinfo.traffic.persist.tables.TScheduleInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.protocol.schedule.ScheduleMata;
import com.ecarinfo.traffic.protocol.schedule.ScheduleService;

/**
 * 动态任务
 * @author ecxiaodx
 *
 */
@Component("dynaJob")
public class DynaJob {
	
	private static final Logger logger = Logger.getLogger(DynaJob.class);
	@Resource
	private DB db;
	
	@Resource
	private ScheduleService scheduleService;
	
	public void execute() {
		TScheduleInfo ts = Tables.get(TScheduleInfo.class);
		List<ScheduleInfo> scheduleInfos = db.findObjectsForList(ScheduleInfo.class, db.where(ts.status.eq(StaticType.Status.VALID)));
		
		for(ScheduleInfo info:scheduleInfos) {
			ScheduleMata mata = scheduleService.getJobInfo("job-"+info.getId());
			try {
				if(mata == null) {
					scheduleService.startJob("job-"+info.getId(), QueryJob.class, info.getValue());
				} else {
					if(!mata.getTrigger().getCronExpression().equals(info.getValue())) {
						scheduleService.updateJob("job-"+info.getId(), info.getValue());
					}
				}
			} catch (SchedulerException e) {
				logger.error("",e);
			}
		}
//		System.err.println("BatQueryJob.execute. ==["+DateUtils.currentDateStr()+"]");
//		try {
//			System.err.println(scheduler);
//			String []gNames = scheduler.getJobGroupNames();
//			for(String name:gNames) {
//				System.err.println(name);
//			}
//			System.err.println("=================");
//			String []tNames = scheduler.getTriggerGroupNames();
//			for(String name:tNames) {
//				System.err.println(name);
//			}
//			
//			String []jNames = scheduler.getJobNames(JobGroupName);
//			for(String name:jNames) {
//				System.err.println(name);
//			}
//			System.err.println("========trigger start =========");
//			String []triggerNames = scheduler.getTriggerNames(TriggerGroupName);
//			for(String name:triggerNames) {
//				System.err.println(name);
//			}
//			System.err.println("========trigger end  =========");
//			
//			CronTrigger trigger = (CronTrigger)scheduler.getTrigger("batQueryTrigger", TriggerGroupName);
//			scheduler.unscheduleJob("batQueryTrigger", TriggerGroupName);
//			trigger.setCronExpression("0/10 * * * * ?");
//			trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
//			scheduler.scheduleJob(trigger);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
			
	}
	
}
