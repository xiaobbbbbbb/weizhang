package com.ecarinfo.traffic.vo;

import java.io.Serializable;

public class SpiQueryVO implements Serializable {

	private static final long serialVersionUID = 3278841915216491797L;

	private String orgCode;

	private String name;

	private Integer num;

	private Integer spiId;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSpiId() {
		return spiId;
	}

	public void setSpiId(Integer spiId) {
		this.spiId = spiId;
	}
}
