package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.QueryArea;

/**
 * Description:违章查询区域
 */

public class QueryAreaVO extends QueryArea {

	private static final long serialVersionUID = 1L;

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
