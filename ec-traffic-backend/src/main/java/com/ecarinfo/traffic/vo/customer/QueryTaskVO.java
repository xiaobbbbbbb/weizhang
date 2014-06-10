package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.QueryTask;

/**
 * Author:     Dawn/邓支晓
 * Created on: 2014-05-30 16:03:24
 * Description:批查任务
 */

public class QueryTaskVO extends QueryTask {

	private static final long serialVersionUID = 1L;

	private String orgName;
	
	private String operatorName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
