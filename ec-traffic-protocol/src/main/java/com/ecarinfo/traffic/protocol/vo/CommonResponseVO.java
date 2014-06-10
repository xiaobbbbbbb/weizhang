package com.ecarinfo.traffic.protocol.vo;

import com.ecarinfo.traffic.protocol.meta.StaticType;

public class CommonResponseVO {
	private Integer errorCode = StaticType.ErrorCode.SUCCESS;
	
	
	public static final CommonResponseVO SUCCESS = new CommonResponseVO(StaticType.ErrorCode.SUCCESS);
	
	public CommonResponseVO(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
