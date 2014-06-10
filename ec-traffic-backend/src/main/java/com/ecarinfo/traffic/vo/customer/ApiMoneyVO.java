package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.ApiMoney;

/**
 * Author:     Dawn/邓支晓
 * Created on: 2014-06-05 15:37:44
 * Description:API价格管理
 */

public class ApiMoneyVO extends ApiMoney {

	private static final long serialVersionUID = 1L;

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
