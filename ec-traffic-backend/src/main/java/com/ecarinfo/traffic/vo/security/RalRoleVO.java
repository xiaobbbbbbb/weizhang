package com.ecarinfo.traffic.vo.security;

import java.io.Serializable;

import com.ecarinfo.traffic.persist.po.RalRole;

public class RalRoleVO extends RalRole implements Serializable {

	private static final long serialVersionUID = 3581325610327414087L;
	private String ids; // 拥有的资源的id，说明用于接收前台传过来的资源id值

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
