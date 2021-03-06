package com.ecarinfo.traffic.persist.tables;
import com.ecarinfo.db4j.table.AllField;
import com.ecarinfo.db4j.table.AbstractTable;
import com.ecarinfo.db4j.table.TableField;
import com.ecarinfo.db4j.table.TableFieldImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.ecarinfo.db4j.table.DateTableField;
import com.ecarinfo.db4j.table.DateTableFieldImpl;

public class TSpiQueryMoney extends AbstractTable {

	static {
		init();
	}

	private TSpiQueryMoney(){
		super.tableName = "spi_query_money";
	}

	private TSpiQueryMoney(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TSpiQueryMoney getInstance() {
		return new TSpiQueryMoney();
	}

	public static final TSpiQueryMoney getInstance(String aliasName) {
		return new TSpiQueryMoney(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("id", "id");
		allFieldMap.put("orgCode", "org_code");
		allFieldMap.put("spiMoneyId", "spi_money_id");
		allFieldMap.put("operatorId", "operator_id");
		allFieldMap.put("startTime", "start_time");
		allFieldMap.put("endTime", "end_time");
		allFieldMap.put("createTime", "create_time");

	}

	@Override
	public String getFieldName(String javaFieldName) {
		return allFieldMap.get(javaFieldName);
	}

	public final TableField<Integer> all = new AllField<Integer>(this,  "*",null);

	@SuppressWarnings("rawtypes")
	@Override
	public TableField allField() {
		return all;
	}

	/**
	 * | bigint(20)
	*/
	public final TableField<Long>  pk = new TableFieldImpl<Long>(this,"id","id","");
	public final TableField<Long> id = new TableFieldImpl<Long>(this,"id","id","");

	/**
	 *机构编号 | char(6)
	*/
	public final TableField<String> orgCode = new TableFieldImpl<String>(this,"org_code","orgCode","机构编号");

	/**
	 *spi_money配置表id | int(11)
	*/
	public final TableField<Integer> spiMoneyId = new TableFieldImpl<Integer>(this,"spi_money_id","spiMoneyId","spi_money配置表id");

	/**
	 *操作员id | int(11)
	*/
	public final TableField<Integer> operatorId = new TableFieldImpl<Integer>(this,"operator_id","operatorId","操作员id");

	/**
	 *开始时间 | datetime
	*/
	public final DateTableField<Date> startTime = new DateTableFieldImpl<Date>(this,"start_time","startTime","开始时间");

	/**
	 *结束时间 | datetime
	*/
	public final DateTableField<Date> endTime = new DateTableFieldImpl<Date>(this,"end_time","endTime","结束时间");

	/**
	 *创建时间 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建时间");


	private final TableField<?>[] allFields = new TableField<?>[] {id,orgCode,spiMoneyId,operatorId,startTime,endTime,createTime};

	@Override
	public TableField<?>[] getAllFields() {
		return allFields;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TableField<?> getPK() {
		return pk;
	}

	@Override
	public final boolean isAutoGeneratedPK() {
		return true;
	}

	@Override
	public String toString() {
		return "TSpiQueryMoney[table=spi_query_money]";
	}

}
