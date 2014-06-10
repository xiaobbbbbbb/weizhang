package com.ecarinfo.traffic.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecarinfo.db4j.common.DBContext;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Table;
import com.ecarinfo.db4j.util.TableUtils;
import com.ecarinfo.traffic.service.GenService;
import com.ecarinfo.traffic.vo.FormConstant;

@Service
public class GenServiceImpl implements GenService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public <T> ECPage<T> queryPage(Class<T> entityClass, int page, int rowsPerPage, String sql, Object... params) {
		Table table = null;
		try {
			table = DBContext.getTable(entityClass);
		} catch (Exception e) {
		}
		String countSql = "SELECT COUNT(*) FROM (" + sql + ") TEMP";
		Integer totalRows = jdbcTemplate.queryForInt(countSql);
		List<T> datas = null;
		if (params.length > 0) {
			datas = jdbcTemplate.query(sql, params, TableUtils.getSimpleRowMapper(entityClass, table));
		} else {
			datas = jdbcTemplate.query(sql, TableUtils.getSimpleRowMapper(entityClass, table));
		}
		ECPage<T> pageEntity = ECPage.initPage(datas, totalRows, rowsPerPage, page);
		return pageEntity;
	}
}
