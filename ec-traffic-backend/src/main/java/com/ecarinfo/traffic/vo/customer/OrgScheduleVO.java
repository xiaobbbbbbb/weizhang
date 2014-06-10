package com.ecarinfo.traffic.vo.customer;

import com.ecarinfo.traffic.persist.po.OrgSchedule;

/**
 * Author:     Dawn/邓支晓
 * Created on: 2014-06-03 17:38:22
 * Description:任务调度配置
 */

public class OrgScheduleVO extends OrgSchedule {

	private static final long serialVersionUID = 1L;

	private String orgName;
	
	private String scheduleName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}	
}
