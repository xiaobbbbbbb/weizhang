package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.SpiMoney;

/**
 * Description:SPI价格管理
 */

public class SpiMoneyVO extends SpiMoney {

	private static final long serialVersionUID = 1L;
    
    private String spiInfoName; //字段spiId,关联表spi_info,显示字段name
    
	public String getSpiInfoName() {
		return spiInfoName;
	}

	public void setSpiInfoName(String spiInfoName) {
		this.spiInfoName = spiInfoName;
	}
}
