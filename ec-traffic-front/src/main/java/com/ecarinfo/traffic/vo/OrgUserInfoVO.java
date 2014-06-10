package com.ecarinfo.traffic.vo;

import com.ecarinfo.traffic.persist.po.OrgUserInfo;

public class OrgUserInfoVO extends OrgUserInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldPasswd;
	private String passwd;
	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
