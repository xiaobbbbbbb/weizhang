package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.QueryInfo;

/**
 * Description:query_info
 */

public class QueryInfoVO extends QueryInfo {

	private static final long serialVersionUID = 1L;

	private String orgName;

	private String taskName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
