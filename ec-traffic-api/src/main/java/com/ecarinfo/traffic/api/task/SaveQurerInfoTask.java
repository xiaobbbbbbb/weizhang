package com.ecarinfo.traffic.api.task;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.traffic.api.service.TrafficService;
import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficResponseVO;
import com.ecarinfo.traffic.protocol.vo.TrafficVO;

public class SaveQurerInfoTask {
	private ResponseVO responseVO;
	private RequestVO requestVO;
	private TrafficService trafficService;
	
	public SaveQurerInfoTask(TrafficService trafficService,ResponseVO responseVO, RequestVO requestVO) {
		this.trafficService = trafficService;
		this.responseVO = responseVO;
		this.requestVO = requestVO;
		TrafficResponseVO tVo = (TrafficResponseVO)responseVO.getValue();
		if(tVo != null) {
			if(tVo.getTraffics().size() > 0) {
				for(TrafficVO trafficVO:tVo.getTraffics()) {
					String uKey = MD5Utils.md5(Base64.encode(trafficVO.getCarNo()+requestVO.getCarType()+DateUtils.dateToString(trafficVO.getTrafficTime(), TimeFormatter.FORMATTER1)+trafficVO.getTrafficDetail()));
					trafficVO.setUniqueKey(uKey);
				}
			}
		}
					
	}

	public void execute() {
		trafficService.saveQueryInfo(responseVO, requestVO);
	}

}
