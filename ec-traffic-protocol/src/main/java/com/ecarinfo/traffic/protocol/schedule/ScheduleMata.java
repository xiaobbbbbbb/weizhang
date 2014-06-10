package com.ecarinfo.traffic.protocol.schedule;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

public class ScheduleMata {
	private String taskName;
	private Class<?> taskClass;
	private JobDetail jobDetail;
	private CronTrigger trigger;

	public ScheduleMata(String taskName,Class<?> taskClass, JobDetail jobDetail, CronTrigger trigger) {
		super();
		this.taskName = taskName;
		this.taskClass = taskClass;
		this.jobDetail = jobDetail;
		this.trigger = trigger;
	}

	public String getTaskId() {
		return taskName;
	}

	public void setTaskId(String taskId) {
		this.taskName = taskId;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public CronTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(CronTrigger trigger) {
		this.trigger = trigger;
	}

	public Class<?> getTaskClass() {
		return taskClass;
	}
}
