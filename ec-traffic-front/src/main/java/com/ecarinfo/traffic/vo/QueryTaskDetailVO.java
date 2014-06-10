package com.ecarinfo.traffic.vo;

import com.ecarinfo.traffic.persist.po.QueryTaskDetail;

public class QueryTaskDetailVO extends QueryTaskDetail {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
