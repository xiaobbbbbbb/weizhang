package com.ecarinfo.traffic.protocol.vo;

import java.util.HashMap;
import java.util.Map;

public class RequestVO {
	private String carNo;
	private String carFrameNo;
	private String carEngineNo;
	private Integer carType;
	private String orgCode;
	private String taskId;
	private Integer provinceId;
	private Integer cityId;
	private String queryTime;
	private String sign;

	private Map<String, String> params;
	public Map<String, String> getParams() {
		if(params != null) {
			return params;
		}
		params = new HashMap<String, String>();
		params.put("carNo", carNo);
		params.put("carFrameNo", carFrameNo);
		params.put("carEngineNo", carEngineNo);
		if(carType != null) {
			params.put("carType", String.valueOf(carType));
		}
		params.put("orgCode", orgCode);
		params.put("taskId", taskId);
		if(provinceId != null) {
			params.put("provinceId", String.valueOf(provinceId));
		}
		if(cityId != null) {
			params.put("cityId",String.valueOf(cityId));
		}
		params.put("queryTime", queryTime);
		params.put("sign", sign);
		return params;
	}
	
	public RequestVO initVO(String carNo,String carFrameNo,String carEngineNo,Integer carType,String orgCode,String taskId,Integer provinceId,Integer cityId,String queryTime,String sign) {
		this.setCarEngineNo(carEngineNo);
		this.setCarFrameNo(carFrameNo);
		this.setCarNo(carNo);
		this.setCarType(carType);
		this.setCityId(cityId);
		this.setOrgCode(orgCode);
		this.setProvinceId(provinceId);
		this.setQueryTime(queryTime);
		this.setSign(sign);
		this.setTaskId(taskId);
		return this;
	}
	
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarFrameNo() {
		return carFrameNo;
	}

	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}

	public String getCarEngineNo() {
		return carEngineNo;
	}

	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
