package com.ecarinfo.traffic.persist.logic;

public enum OperatorType {
	
	BACK_END("backEnd"),FRONT("front"),API("api"),CORE("core");
	private String value;
	
	private OperatorType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
