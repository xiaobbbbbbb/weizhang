package com.ecarinfo.traffic.core.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.spi.handler.CxyTrafficHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class TrafficHandlerTest {
	
	@Resource
	private CxyTrafficHandler cxyTrafficHandler;
	@Test
	public void testGetTrafficData() {
		String carNo = "粤BV3727";
		String carFrameNo = "L3AKECGN4DY401086";
		String carEngineNo = "1613H129438";
		Integer carType = 1;
		String provinceName = null;
		String cityName = null;
		String queryTime = DateUtils.currentDateStr();
		String queryUrl = "http://chaxun.cx580.com:9008/DYQueryindex.aspx";
		String appKey = "1234567890123456";
		String userName = "aochuangkeji";
		String password = "4V94AaPA2/rrF1PZmJjwVQ==";
		String sign = Base64.encode(userName+" "+password+" "+appKey);
		cxyTrafficHandler.getTrafficDatas(carNo, carFrameNo, carEngineNo, carType, provinceName, cityName, queryTime, queryUrl, sign, null, null);
	}

}
