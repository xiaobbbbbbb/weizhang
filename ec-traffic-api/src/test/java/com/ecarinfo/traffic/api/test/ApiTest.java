package com.ecarinfo.traffic.api.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;

public class ApiTest { 

	@Test
<<<<<<< .mine
	public void datas() {
		String url = "http://192.168.1.8:7201/traffic/datas";
=======
	public void datas() { 
		String url = "http://192.168.1.241:7201/traffic/datas";
>>>>>>> .r161
		
		boolean releaseConnnection = false;
		Map<String, String> params = new HashMap<String, String>();
<<<<<<< .mine
		String carNo = "粤BV3727";
		String carFrameNo = "L3AKECGN4DY401086";
//		String carEngineNo = "1613H129438";
=======
		String carNo = "粤BSS231";
		String carFrameNo = "LJNTFU2S37N011891";
		String carEngineNo = "QD32T013207T";
>>>>>>> .r161
		String appKey = "12345678987654321";
<<<<<<< .mine
		Integer carType =1;
=======
		Integer carType = 01; 
>>>>>>> .r161
		String queryTime = DateUtils.currentDateStr();
		params.put("carNo", carNo);
		params.put("carFrameNo", carFrameNo);
//		params.put("carEngineNo", carEngineNo);
		params.put("carType", String.valueOf(carType));
<<<<<<< .mine
		params.put("orgCode", "000000");
//		params.put("taskId", "1");
//		params.put("provinceId", String.valueOf(provinceId));
//		params.put("cityId",String.valueOf(cityId));
=======
		params.put("orgCode", "000000"); 
//		params.put("taskId", "1");
//		params.put("provinceId", String.valueOf(20));
//		params.put("cityId",String.valueOf(214));
>>>>>>> .r161
		params.put("queryTime", queryTime);
		params.put("sign", MD5Utils.md5(Base64.encode(carNo+carType+queryTime+appKey)));
		String res = HttpClientUtils.get(url, params, releaseConnnection);
		ResponseVO responseVO = JSONUtil.fromJson(res, ResponseVO.class);
		System.err.println(res);
	}
	
	@Test
	public void md5() {
		String detail = "机动车违反规定使用专用车道的机动车违反规定使用专用车道的机动车违反规定车道的机动车违反规定使用专用车道的车道的机动车违反规定使用专用车道的车道的机动车违反规定使用专用车道的使用专用车道的机动车违反规定使用专用车道的机动车违反规定使用专用车道的";
		String carNo = "粤BD2120";
		String hTime = "2014-05-10 10:42:00";
		String md5 = MD5Utils.md5(Base64.encode(detail+carNo+hTime));
		System.err.println(md5);
	}
	
	@Test
	public void decode() throws UnsupportedEncodingException {
		String s = "YW9jaHVhbmdrZWppIDRWOTRBYVBBMi9yckYxUFptSmp3VlE9PSAxMjM0NTY3ODkwMTIzNDU2";
		String [] ar = Base64.decode(s).split(" ");
		for(String st:ar) {
			System.err.println(st);
		}
	}
}
