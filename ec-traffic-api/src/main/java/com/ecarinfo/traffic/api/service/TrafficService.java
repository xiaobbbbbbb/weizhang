package com.ecarinfo.traffic.api.service;

import com.ecarinfo.traffic.protocol.vo.RequestVO;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;

public interface TrafficService {
	
	void saveQueryInfo(ResponseVO responseVO,RequestVO requestVO);
	
}
