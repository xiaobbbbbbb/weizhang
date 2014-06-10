package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.OrgUserInfo;

/**
 * Author:     Dawn/邓支晓
 * Created on: 2014-05-27 19:36:20
 * Description:客户系统管理员
 */

public class OrgUserInfoVO extends OrgUserInfo {

	private static final long serialVersionUID = 1L;

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
