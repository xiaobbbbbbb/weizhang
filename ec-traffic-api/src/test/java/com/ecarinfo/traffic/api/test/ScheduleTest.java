package com.ecarinfo.traffic.api.test;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.ThreadUtils;
import com.ecarinfo.traffic.protocol.schedule.ScheduleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class ScheduleTest {
	
	@Resource
	private ScheduleService scheduleService;
	@Test
	public void testSchedule() {
		String cronExpression = "0/1 * * * * ?";
		try {
			String taskName = "taskId1";
			scheduleService.startJob(taskName,TestJob.class, cronExpression);
			
			ThreadUtils.sleep(5000);
			scheduleService.updateJob(taskName, "0/3 * * * * ?");
			ThreadUtils.sleep(5000);
			scheduleService.updateJob(taskName, "0/10 * * * * ?");
			ThreadUtils.sleep(5000);
			scheduleService.stopJob(taskName);
			ThreadUtils.sleep(5000);
			scheduleService.restartJob(taskName);
			ThreadUtils.sleep(10000000);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCronExp() {
		try {
			CronExpression cronExpression = new CronExpression("0/10 * * * * ?");
			Date now = new Date();
			System.err.println("now  = "+DateUtils.dateToString(now, TimeFormatter.FORMATTER1));
			Date next = cronExpression.getNextValidTimeAfter(now);
//			cronExpression.getNextValidTimeAfter(date)
			System.err.println(DateUtils.dateToString(cronExpression.getFinalFireTime(), TimeFormatter.FORMATTER1));
			System.err.println("next = "+DateUtils.dateToString(next, TimeFormatter.FORMATTER1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static class TestJob implements Job {
		@Override
		public void execute(JobExecutionContext context)
				throws JobExecutionException {
			System.err.println("TestJob.execute"+DateUtils.currentDateStr());
		}
	}
}
