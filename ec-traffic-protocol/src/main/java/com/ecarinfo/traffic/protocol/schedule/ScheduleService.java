package com.ecarinfo.traffic.protocol.schedule;


import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("scheduleService")
public class ScheduleService {

	private static final Logger logger = Logger.getLogger(ScheduleService.class);
	
	@Resource
	private Scheduler scheduler;
	
	@Resource
	private ApplicationContext applicationContext;
	
	private static final String DEFAULT_JOB_GROUP = "com.ecarinfo.traffic.job";
	private static final String DEFAULT_TRIGGER_GROUP ="com.ecarinfo.traffic.trigger";
	
	private Map<String, ScheduleMata> taskMap = new ConcurrentHashMap<String, ScheduleMata>();
	
	public void init() {
		System.err.println("=====================init=========================applicationContext="+applicationContext);
		scheduler = (Scheduler)applicationContext.getBean("scheduler");
		System.err.println("ScheduleService.init=============schedule="+scheduler);
	}
	
	public <T> void startJob(String jobName,Class<T> jobClass, String cronExpression) throws SchedulerException {
		
		if(scheduler == null) {
			init();
		}
		
		System.err.println("=====================startJob==========================");
		logger.info("===startJob["+jobName+"]--["+cronExpression+"]====");
		
		ScheduleMata scheduleMata = taskMap.get(jobName);
		if(scheduleMata != null) {
			taskMap.remove(jobName);
		}
		
		JobDetail jobDetail = new JobDetail(jobName, DEFAULT_JOB_GROUP, jobClass);
		JobDataMap data = jobDetail.getJobDataMap();
		data.put("jobName", jobName);
		data.put("applicationContext", applicationContext);
		CronTrigger trigger = new CronTrigger("trigger-"+jobName, DEFAULT_TRIGGER_GROUP);
		try {
			trigger.setCronExpression(cronExpression);
			trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
		} catch (ParseException e) {
			throw new SchedulerException("定时任务表达式错误。"+cronExpression,e);
		}
		try {
			System.err.println("-------------------------scheduler = "+scheduler);
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new SchedulerException("添加任务失败。",e);
		}
		taskMap.put(jobName,new ScheduleMata(jobName,jobClass, jobDetail, trigger));
		
	}

	public void stopJob(String jobName) throws SchedulerException {
		logger.info("===stopJob["+jobName+"]====");
		ScheduleMata scheduleMata = taskMap.get(jobName);
		if(scheduleMata == null) {
			throw new SchedulerException("暂停任务失败，任务已经删除"); 
		}
		try {
			scheduler.unscheduleJob(scheduleMata.getTrigger().getName(), DEFAULT_TRIGGER_GROUP);
			boolean opRes = scheduler.deleteJob(jobName, DEFAULT_JOB_GROUP);
			if(opRes) {
				logger.info("暂停任务成功！-"+jobName);
			}
		} catch (SchedulerException e) {
			logger.info("暂停任务失败-"+jobName,e);
			throw new SchedulerException("暂停任务失败-"+jobName,e); 
		}
	}
	
	public void restartJob(String jobName) throws SchedulerException {
		logger.info("===continueTask["+jobName+"]====");
		ScheduleMata scheduleMata = taskMap.get(jobName);
		if(scheduleMata == null) {
			logger.warn("继续执行任务失败，任务已经删除-"+jobName);
			throw new SchedulerException("继续执行任务失败，任务已经删除-"+jobName); 
		}
		try {
			stopJob(jobName);
			startJob(jobName,scheduleMata.getTaskClass(), scheduleMata.getTrigger().getCronExpression());
		} catch (Exception e) {
			logger.warn("继续执行任务失败-"+jobName);
			throw new SchedulerException("继续执行任务失败-"+jobName); 
		}
	}
	
	public void updateJob(String jobName, String cronExpression) throws SchedulerException {
		logger.info("===updateTask["+jobName+"]("+cronExpression+")====");
		ScheduleMata scheduleMata = taskMap.get(jobName);
		if(scheduleMata == null) {
			logger.warn("更新行任务失败，任务已经删除-"+jobName);
			throw new SchedulerException("更新行任务失败，任务已经删除-"+jobName); 
		}
		try {
			stopJob(jobName);
			startJob(jobName,scheduleMata.getTaskClass(), cronExpression);
		} catch (Exception e) {
			logger.warn("更新行任务失败-"+jobName);
			throw new SchedulerException("更新行任务失败-"+jobName); 
		}
	}
	
	public ScheduleMata getJobInfo(String jobName) {
		return taskMap.get(jobName);
	}

}
