package com.ecarinfo.traffic.vo;

import java.util.Date;

import com.ecarinfo.traffic.persist.po.OrgCarInfo;

public class OrgCarVO extends OrgCarInfo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date taskDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	

}
