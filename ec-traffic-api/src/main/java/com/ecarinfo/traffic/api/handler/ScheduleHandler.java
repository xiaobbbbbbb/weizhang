package com.ecarinfo.traffic.api.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecarinfo.netty.http.annotation.Module;
import com.ecarinfo.netty.http.annotation.RequestMethod;
import com.ecarinfo.netty.http.annotation.RequestURI;
import com.ecarinfo.netty.http.bean.RequestContext;
import com.ecarinfo.netty.http.bean.ResponseContext;
import com.ecarinfo.traffic.protocol.vo.CommonResponseVO;

@Module("/job")
@Component
public class ScheduleHandler {
//	@Resource
//	private ScheduleService scheduleService;
	
	@RequestURI(value="/start",method={RequestMethod.GET,RequestMethod.POST})
	public CommonResponseVO start(String taskName,String taskClass,String triggerName,String cronExp,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
//		scheduleService.start(taskName,taskClass,triggerName,cronExp);
		return CommonResponseVO.SUCCESS;
		
	}
	
	@RequestURI(value="/stop",method={RequestMethod.GET,RequestMethod.POST})
	public CommonResponseVO stop(String taskName,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
//		scheduleService.stop(taskName);
		return CommonResponseVO.SUCCESS;
		
	}
	
	@RequestURI(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	public CommonResponseVO update(String taskName,String triggerName,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
//		scheduleService.pause(taskName);
		return CommonResponseVO.SUCCESS;
		
	}
	
	@RequestURI(value="/pause",method={RequestMethod.GET,RequestMethod.POST})
	public CommonResponseVO pause(String taskName,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
//		scheduleService.pause(taskName);
		return CommonResponseVO.SUCCESS;
		
	}
}
