package com.ecarinfo.traffic.api.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecarinfo.common.config.DefaultConfig;
import com.ecarinfo.common.server.EcApp;
import com.ecarinfo.common.utils.ServerUtils;
import com.ecarinfo.netty.server.Netty4HttpServer;

public class ApiMain {
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			DefaultConfig defaultConfig = (DefaultConfig)context.getBean("defaultConfig");
			int port = Integer.parseInt(defaultConfig.getVal("server.http.port"));
			EcApp app = new ApiApp();
			new Netty4HttpServer(context, port, "com.ecarinfo.traffic");
			ServerUtils.waitingForShutdown(app, defaultConfig);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
