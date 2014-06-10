package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.OrgInfo;

public class OrgInfoVO extends OrgInfo {

	private static final long serialVersionUID = 1L;
	private int queryTypeReal;
	private int queryTypeBatch;

	private String provinceName;
	private String cityName;
	
	private String tackName;

	public int getQueryTypeReal() {
		return queryTypeReal;
	}

	public void setQueryTypeReal(int queryTypeReal) {
		this.queryTypeReal = queryTypeReal;
	}

	public int getQueryTypeBatch() {
		return queryTypeBatch;
	}

	public void setQueryTypeBatch(int queryTypeBatch) {
		this.queryTypeBatch = queryTypeBatch;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTackName() {
		return tackName;
	}

	public void setTackName(String tackName) {
		this.tackName = tackName;
	}
}
