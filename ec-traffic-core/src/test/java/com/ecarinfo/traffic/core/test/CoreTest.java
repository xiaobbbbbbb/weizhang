package com.ecarinfo.traffic.core.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.MD5Utils;

public class CoreTest {

	@Test
	public void datas() {
		String url = "http://192.168.1.241:7202/chexy/getTrafficDatas";
		
		boolean releaseConnnection = false;
		Map<String, String> params = new HashMap<String, String>();
		String carNo = "粤BSS231";
		String carFrameNo = "LJNTFU2S37N011891";
		String carEngineNo = "QD32T013207T";
		String spiKey = "1234567890123456";
		Integer carType = 02;
		String queryTime = DateUtils.currentDateStr();
		String queryUrl = "http://chaxun.cx580.com:9008/DYQueryindex.aspx";
		params.put("carNo", carNo);
		params.put("carFrameNo", carFrameNo);
		params.put("carEngineNo", carEngineNo);
		params.put("carType", String.valueOf(carType));
		params.put("orgCode", "888888");
		params.put("taskId", "1");
//		params.put("provinceId", String.valueOf(provinceId));
//		params.put("cityId",String.valueOf(cityId));
		params.put("queryTime", queryTime);
		params.put("queryUrl", queryUrl);
		params.put("key", spiKey);
		
		params.put("sign", Base64.encode("aochuangkeji"+" "+"4V94AaPA2/rrF1PZmJjwVQ=="+" "+spiKey));
		String res = HttpClientUtils.get(url, params, releaseConnnection);
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
