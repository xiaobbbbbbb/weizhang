package com.ecarinfo.traffic.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecarinfo.common.utils.ReflectUtil;
import com.ecarinfo.db4j.common.DBContext;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Table;
import com.ecarinfo.db4j.util.TableUtils;
import com.ecarinfo.traffic.service.GenService;
import com.ecarinfo.traffic.vo.StaticConstant;

@Service
public class GenServiceImpl extends BaseServiceImpl implements GenService {

	protected transient static Logger logger = Logger.getLogger(GenServiceImpl.class);

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

	// 关联表的值的替换
	public <T> void dictDateReplace(List<T> result, List<Map<String, Object>> dicDatas, String code, String name) {
		for (T r : result) {
			Class<? extends Object> c = r.getClass();
			Field[] fs = ReflectUtil.getFields(c);
			boolean isReplace = false;
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				try {
					String value = String.valueOf(f.get(r));// 得到此属性的值
					String fN = f.getName();
					if (code.equalsIgnoreCase(fN)) {// 如果字段是要替换的字段
						for (Map<?, ?> m : dicDatas) {// 要替换的数据
							String codeKey = String.valueOf(m.get(StaticConstant.DICT_DATA_CODE));
							String codeValue = String.valueOf(m.get(StaticConstant.DICT_DATA_NAME));
							if (value.equalsIgnoreCase(codeKey)) {
								Field fk = c.getDeclaredField(name); // 得到单一属性方法
								if (fk != null) {
									fk.setAccessible(true); // 设置些属性是可以访问的
									f = fk;
								}
								f.set(r, codeValue); // 给属性设值
								isReplace = true;
								continue;
							}
						}
						if (isReplace)
							continue;
					}
				} catch (Exception e) {
					logger.error("关联表的值的替换", e);
				}
			}
		}
	}

	/**
	 * 列表中关联表单字段查询
	 * @param dictTable 表的名称
	 * @param dictField 数据库中存储的字段
	 * @param dictText 列表中显示的字典
	 * @return
	 */
	public List<Map<String, Object>> dictDate(String dictTable, String dictField, String dictText) {
		StringBuilder dicSql = new StringBuilder();
		dicSql.append("SELECT DISTINCT `").append(dictField).append("` AS " + StaticConstant.DICT_DATA_CODE + ", ");
		if ((dictText != null) && (dictText.length() > 0))
			dicSql.append("`").append(dictText).append("`").append(" AS " + StaticConstant.DICT_DATA_NAME + " ");
		else {
			dicSql.append("`").append(dictField).append("`").append(" AS " + StaticConstant.DICT_DATA_NAME + " ");
		}
		dicSql.append(" FROM ").append(dictTable);
		dicSql.append(" ORDER BY ").append(dictField);
		System.err.println("列表中关联表单字段查询sql:" + dicSql.toString());
		List<Map<String, Object>> dictDatas = jdbcTemplate.queryForList(dicSql.toString(), new Object[0]);
		return dictDatas;
	}
}
