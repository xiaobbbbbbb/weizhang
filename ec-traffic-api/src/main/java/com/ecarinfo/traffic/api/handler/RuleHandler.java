package com.ecarinfo.traffic.api.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecarinfo.netty.http.annotation.Module;
import com.ecarinfo.netty.http.annotation.RequestMethod;
import com.ecarinfo.netty.http.annotation.RequestURI;
import com.ecarinfo.netty.http.bean.RequestContext;
import com.ecarinfo.netty.http.bean.ResponseContext;
import com.ecarinfo.traffic.protocol.vo.RuleResponseVO;

@Module("/rules")
@Component
public class RuleHandler {
	
	@RequestURI(value="/rules",method={RequestMethod.GET,RequestMethod.POST})
	public RuleResponseVO getRuleDatas(String spiName,String sign,RequestContext request,ResponseContext response) {
		
		Map<String, String> params = request.getParameters();//请求的所有参数
		System.err.println(params);
		
		return null;
	}
}
