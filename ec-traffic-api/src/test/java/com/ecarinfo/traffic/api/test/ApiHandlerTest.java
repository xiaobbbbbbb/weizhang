package com.ecarinfo.traffic.api.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.traffic.api.handler.TrafficHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class ApiHandlerTest {
	// ① 对接口进行模拟
	@Resource
	private TrafficHandler trafficHandler;// = mock(TrafficService.class);


	@Test
	public void getTrafficData() {
//		"粤BR4559", "LZYTATE64C1025513", "P54128", "01")
		System.err.println(trafficHandler);
		String carNo = "粤BR4559";
		String carFrameNo = "LZYTATE64C1025513";
		String carEngineNo = "P54128";
		String appKey = "12345678987654321";
		Integer carType = 01;
		String queryTime = DateUtils.currentDateStr();
		String sign = MD5Utils.md5(Base64.encode(carNo+carType+queryTime+appKey));
		String taskId = "1";
		String orgCode = "000000";
		Integer provinceId = -1;
		Integer cityId = -1;
		trafficHandler.getTrafficDatas(carNo, carFrameNo, carEngineNo, carType, orgCode, taskId, provinceId, cityId, queryTime, sign, null, null);
	}

}
