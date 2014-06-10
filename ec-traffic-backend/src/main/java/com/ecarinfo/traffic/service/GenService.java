package com.ecarinfo.traffic.service;

import java.util.List;
import java.util.Map;

import com.ecarinfo.db4j.paginate.ECPage;

public interface GenService {
	
	public <T> ECPage<T> queryPage(Class<T> entityClass, int page, int rowsPerPage, String sql, Object... params);
	
	public <T> void dictDateReplace(List<T> result, List<Map<String, Object>> dicDatas,String code,String name);

	List<Map<String, Object>> dictDate(String dictTable, String dictField, String dictText);
}
