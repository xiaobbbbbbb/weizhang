package com.ecarinfo.traffic.vo;

public class OrgScheduleVO {
	private Integer id;
	private  String name;
	private String orgCode;
	private Integer scheduleId;
	private Long nums;//绑定车辆数量
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getNums() {
		return nums;
	}
	public void setNums(Long nums) {
		this.nums = nums;
	}
	
}
