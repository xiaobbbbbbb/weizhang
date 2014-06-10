package com.ecarinfo.traffic.api.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class JavaScheduleTest {
	// 线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定
	// 线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务
	// 此处用Executors.newSingleThreadScheduledExecutor()更佳。
	private static final Logger logger = Logger.getLogger(JavaScheduleTest.class);
	private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);
	private Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();
	// 启动计时器
	public void lanuchTimer() {
		Runnable task = new Runnable() {
			public void run() {
				throw new RuntimeException();
			}
		};
		scheduExec.scheduleWithFixedDelay(task, 1000 * 5, 1000 * 10,
				TimeUnit.MILLISECONDS);
	}

	// 添加新任务
	public void addTask(final String name,final int secode) {
		logger.info("============addOneTask["+name+"]");
		Runnable task = new Runnable() {
			public void run() {
				System.out.println(name+" welcome to china");
			}
		};
		ScheduledFuture<?> future = scheduExec.scheduleWithFixedDelay(task, 1000 * 1, 1000*secode,TimeUnit.MILLISECONDS);
		futureMap.put(name, future);
	}
	
	public void deleteTask(String name) {
		logger.info("============deleteTask["+name+"]");
		ScheduledFuture<?> future = futureMap.get(name);
		boolean mayInterruptIfRunning = false;
		future.cancel(mayInterruptIfRunning);
	}
	
	public void modifyTime(String name,int interval) {
		deleteTask(name);
		addTask(name, interval);
	}

	public static void main(String[] args) throws Exception {
		JavaScheduleTest test = new JavaScheduleTest();
		test.lanuchTimer();
		Thread.sleep(1000 * 5);// 5秒钟之后添加新任务
		test.addTask("aaa",1);
		test.addTask("bbb",3);
		Thread.sleep(10000);
		test.modifyTime("bbb", 1);
		test.modifyTime("aaa", 3);
	}
}
