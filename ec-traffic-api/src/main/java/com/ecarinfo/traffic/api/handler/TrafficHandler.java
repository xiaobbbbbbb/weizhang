package com.ecarinfo.traffic.api.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ecarinfo.netty.http.annotation.Module;
import com.ecarinfo.netty.http.annotation.RequestMethod;
import com.ecarinfo.netty.http.annotation.RequestURI;
import com.ecarinfo.netty.http.bean.RequestContext;
import com.ecarinfo.netty.http.bean.ResponseContext;
import com.ecarinfo.traffic.api.queue.QueryInfoQueue;
import com.ecarinfo.traffic.api.router.SpiRouter;
import com.ecarinfo.traffic.api.service.TrafficService;
import com.ecarinfo.traffic.api.task.SaveQurerInfoTask;
import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;

@Module("/traffic")
@Component
public class TrafficHandler {
	
	@Resource
	private SpiRouter spiRouter;
	@Resource
	private QueryInfoQueue queryInfoQueue;
	
	@Resource
	private TrafficService trafficService;
	
	
	@RequestURI(value="/datas",method={RequestMethod.GET,RequestMethod.POST})
	public ResponseVO getTrafficDatas(
			String carNo,
			String carFrameNo,
			String carEngineNo,
			Integer carType,
			String orgCode,
			String taskId,
			Integer provinceId,
			Integer cityId,
			String queryTime,
			String sign,RequestContext request,ResponseContext response) {
		
//		Map<String, String> params = request.getParameters();//请求的所有参数
//		System.err.println(params);
		
		RequestVO requestVO = new RequestVO();
		requestVO.initVO(carNo, carFrameNo, carEngineNo, carType, orgCode, taskId, provinceId, cityId, queryTime, sign);
		Long start = System.currentTimeMillis();
		ResponseVO responseVO = spiRouter.getTrafficDatas(requestVO);
		responseVO.setTaskId(requestVO.getTaskId());
		responseVO.setQueryTime(requestVO.getQueryTime());
		responseVO.setCostTime((int)(System.currentTimeMillis() - start));
		responseVO.setOrgCode(orgCode);
		
		//add to queue asyn
		queryInfoQueue.add(new SaveQurerInfoTask(trafficService, responseVO, requestVO));
		
		return responseVO;
	}
}
