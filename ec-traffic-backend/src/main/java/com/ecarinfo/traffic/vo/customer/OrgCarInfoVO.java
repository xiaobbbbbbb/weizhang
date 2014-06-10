package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.OrgCarInfo;

/**
 * Author:     Dawn/邓支晓
 * Created on: 2014-05-26 19:29:19
 * Description:org_car_info
 */

public class OrgCarInfoVO extends OrgCarInfo {

	private static final long serialVersionUID = 1L;

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
