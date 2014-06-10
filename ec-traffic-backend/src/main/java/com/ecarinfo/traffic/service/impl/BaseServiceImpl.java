package com.ecarinfo.traffic.service.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecarinfo.db4j.ctx.DB;

@Service
public class BaseServiceImpl {

	@Resource
	public JdbcTemplate jdbcTemplate;

	@Resource
	public DB db;
}
