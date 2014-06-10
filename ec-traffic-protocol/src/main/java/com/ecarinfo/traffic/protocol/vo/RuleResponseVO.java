package com.ecarinfo.traffic.protocol.vo;

import java.util.List;

public class RuleResponseVO {
	private String spiName;
	private List<RuleVO> rules;

	public RuleResponseVO() {
	}
	
	public RuleResponseVO(String spiName, List<RuleVO> rules) {
		super();
		this.spiName = spiName;
		this.rules = rules;
	}

	public String getSpiName() {
		return spiName;
	}

	public void setSpiName(String spiName) {
		this.spiName = spiName;
	}

	public List<RuleVO> getRules() {
		return rules;
	}

	public void setRules(List<RuleVO> rules) {
		this.rules = rules;
	}

}
