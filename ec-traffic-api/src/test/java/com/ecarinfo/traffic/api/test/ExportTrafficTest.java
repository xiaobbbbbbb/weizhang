package com.ecarinfo.traffic.api.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.common.utils.Base64;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.HttpClientUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.api.test.vo.CxyTrafficDto;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.TrafficInfo;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.traffic.persist.tables.TQueryTraffic;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;
import com.ecarinfo.traffic.protocol.vo.ResponseVO;
import com.ecarinfo.utils.poi.ExcelUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class ExportTrafficTest {
	@Resource
	private DB db;
	
	
//	public void testMerger() {
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader("d:/carInfo.txt"));
//			String line = null;
//			Date now = new Date();
//			while((line = reader.readLine()) != null) {
////				System.err.println(line);
//				String [] arry = line.split(" ");
//				String carType = arry[3];
//				String carNo = arry[2];
//				String carFrameNo = arry[4];
//				String carEngineNo = arry[5];
//				System.err.println(carType+","+carNo+","+carFrameNo+","+carEngineNo);
//				OrgCarInfo orgCarInfo = new OrgCarInfo();
//				orgCarInfo.setCarEngineNo(carEngineNo);
//				orgCarInfo.setCarFrameNo(carFrameNo);
//				orgCarInfo.setCarNo(carNo);
//				orgCarInfo.setCarType(Integer.valueOf(carType));
//				orgCarInfo.setOrgCode("000000");
//				orgCarInfo.setQueryType(StaticType.QueryType.BAT);
//				orgCarInfo.setCreateTime(now);
//				orgCarInfo.setUpdateTime(now);
//				db.save(orgCarInfo);
//			}
//			reader.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void work() throws Exception {
		String url = "http://192.168.1.241:7201/traffic/datas";
		TOrgCarInfo tc = Tables.get(TOrgCarInfo.class);
		List<OrgCarInfo> carInfos = db.findAllObjects(OrgCarInfo.class);
//		List<OrgCarInfo> carInfos = db.findObjectsForList(OrgCarInfo.class, db.where(tc.carNo.in("粤BSS231")));
//		TCxyTrafficRequest tcq = Tables.get(TCxyTrafficRequest.class);
		for (OrgCarInfo carInfo:carInfos) {
			boolean releaseConnnection = false;
			Map<String, String> params = new HashMap<String, String>();
			String carNo = carInfo.getCarNo();
			String carFrameNo = carInfo.getCarFrameNo();
			String carEngineNo = carInfo.getCarEngineNo();
			String appKey = "12345678987654321";
			Integer carType = Integer.valueOf(carInfo.getCarType());
			String queryTime = DateUtils.currentDateStr();
			params.put("carNo", carNo);
			params.put("carFrameNo", carFrameNo);
			params.put("carEngineNo", carEngineNo);
			params.put("carType", String.valueOf(carType));
			params.put("orgCode", "000000");
			params.put("taskId", "1");
	//		params.put("provinceId", String.valueOf(provinceId));
	//		params.put("cityId",String.valueOf(cityId));
			params.put("queryTime", queryTime);
			params.put("sign", MD5Utils.md5(Base64.encode(carNo+carType+queryTime+appKey)));
		
			System.err.println(carNo+","+carFrameNo+","+carFrameNo+","+carType);
			
			String res = HttpClientUtils.get(url, params, releaseConnnection);
			System.err.println(res);
			ResponseVO result = JSONUtil.fromJson(res, ResponseVO.class);
		}
		
	}
	
//	@Test
//	public void updateCity() {
//		try {
//			String cStr = "INFO com.ecarinfo.traffic.spi.cxy.beans.CxySPI - {\"ErrorCode\":0";
//			BufferedReader reader = new BufferedReader(new FileReader("d:/logic.log"));
//			String line = null;
//			Date now = new Date();
//			while((line = reader.readLine()) != null) {
//				if(line.contains(cStr)) {
//					System.err.println(line);
//				}
//			}
//			reader.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void export() {
		List<CxyTrafficDto> dtos = new ArrayList<CxyTrafficDto>();
		List<CxyTrafficDto> newDtos = new ArrayList<CxyTrafficDto>();
		Date now = new Date();
		String exportPath = "d:/traffics/车辆违章信息-"+DateUtils.dateToString(now, TimeFormatter.YYYYMMDD)+".xlsx";
		String newExportPath = "d:/traffics/新增车辆违章信息-"+DateUtils.dateToString(now, TimeFormatter.YYYYMMDD)+".xlsx";
//		Date fromTime = DateUtils.stringToDate("20140529",TimeFormatter.YYYYMMDD);//最近一次查询时间
		Date endTime = DateUtils.stringToDate("2014-05-30 13:00:00",TimeFormatter.FORMATTER1);//上次查询时间
		try {
			TOrgCarInfo tCar = Tables.get(TOrgCarInfo.class,"c");
			TOrgCarInfo tOrgCarInfo = Tables.get(TOrgCarInfo.class);
			TTrafficInfo tt = Tables.get(TTrafficInfo.class,"tt");
			TQueryInfo tq = Tables.get(TQueryInfo.class,"tq");
			TQueryTraffic tqt = Tables.get(TQueryTraffic.class,"tqt");
			
			List<TrafficInfo> trafficInfos = db.select(tt.all).from(tt)
				.where(tt.id.in(
					db.select(tqt.trafficId).from(tqt,tq,tCar)
					.where(tqt.queryId.eq(tq.id)
							.and(tq.queryTime.gt(endTime))
							.and(tqt.trafficId.eq(tt.id))
							.and(tq.carNo.eq(tCar.carNo))
							)
					)
				)
			.queryObjectsForList(TrafficInfo.class);
			
//			List<TrafficInfo> trafficInfos = db.select(tt.all).from(tCar,tt)
//					.where(
//							tCar.carNo.eq(tt.carNo))
//							.queryObjectsForList(TrafficInfo.class);
			Set<String> set = new HashSet<String>();
			int idx = 1;
			
			for(TrafficInfo info:trafficInfos) {
				CxyTrafficDto dto = new CxyTrafficDto();
				OrgCarInfo carInfo = db.findObject(OrgCarInfo.class, tOrgCarInfo.carNo.eq(info.getCarNo()));
//				if(carInfo == null) {
//					System.err.println(info.getCarNo()+" not found.");
//					continue;
//				}
				init(info, dto,carInfo);
				dto.setId(idx++);
				set.add(info.getCarNo());
				dtos.add(dto);
				
				if(info.getCreateTime().getTime() > endTime.getTime()) {
					newDtos.add(dto);
				}
			}
			System.err.println("违章车辆数："+set.size());
			String merger = null;//"carNo";
			ExcelUtil.exportExcelFromObjects("全部违章",CxyTrafficDto.class, dtos,merger,exportPath , "yyyy-MM-dd");
			
			ExcelUtil.exportExcelFromObjects("新增车辆",CxyTrafficDto.class, newDtos,merger,newExportPath , "yyyy-MM-dd");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(TrafficInfo info,CxyTrafficDto dto,OrgCarInfo carInfo) {
		dto.setCarNo(info.getCarNo());
		dto.setCity(info.getTrafficCity());
		dto.setTrafficAddr(info.getTrafficLocation());
		dto.setTrafficDate(DateUtils.dateToString(info.getTrafficTime(), TimeFormatter.FORMATTER1));
		dto.setTrafficDetail(info.getTrafficDetail());
		dto.setTrafficMoney(String.valueOf(info.getMoney()));
		dto.setTrafficScore(String.valueOf(info.getScores()));
		dto.setCarEngineNo(carInfo.getCarEngineNo());
		dto.setCarFrameNo(carInfo.getCarFrameNo());
		dto.setCarType("0"+carInfo.getCarType());
	}
}
