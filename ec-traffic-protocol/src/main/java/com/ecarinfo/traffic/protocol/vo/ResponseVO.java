package com.ecarinfo.traffic.protocol.vo;

import com.ecarinfo.traffic.protocol.meta.StaticType;

public class ResponseVO {

	private Integer errorCode = StaticType.ErrorCode.SUCCESS;
	private String errorMessage;
	private String queryTime;
	private Integer costTime;
	private String orgCode;
	private String taskId;
	private Integer spiId;
	private TrafficResponseVO value;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public TrafficResponseVO getValue() {
		return value;
	}

	public void setValue(TrafficResponseVO value) {
		this.value = value;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getSpiId() {
		return spiId;
	}

	public void setSpiId(Integer spiId) {
		this.spiId = spiId;
	}

}
