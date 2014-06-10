package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.SpiTack;

/**
 * Author: Dawn/邓支晓 Created on: 2014-06-05 10:49:32 Description:Spi策略配置
 */

public class SpiTackVO extends SpiTack {

	private static final long serialVersionUID = 1L;

	private String tackName;

	private String spiName;

	public String getTackName() {
		return tackName;
	}

	public void setTackName(String tackName) {
		this.tackName = tackName;
	}

	public String getSpiName() {
		return spiName;
	}

	public void setSpiName(String spiName) {
		this.spiName = spiName;
	}
}
