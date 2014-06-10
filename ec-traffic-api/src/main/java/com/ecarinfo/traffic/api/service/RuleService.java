package com.ecarinfo.traffic.api.service;

import com.ecarinfo.traffic.persist.po.InputRule;

public interface RuleService {
	InputRule getRule(Integer spiId,Integer provinceId,Integer cityId);
}
