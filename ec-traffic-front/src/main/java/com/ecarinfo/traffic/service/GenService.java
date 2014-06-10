package com.ecarinfo.traffic.service;

import java.util.List;
import java.util.Map;

import com.ecarinfo.db4j.paginate.ECPage;

public interface GenService {
	
	public <T> ECPage<T> queryPage(Class<T> entityClass, int page, int rowsPerPage, String sql, Object... params);
	
}
