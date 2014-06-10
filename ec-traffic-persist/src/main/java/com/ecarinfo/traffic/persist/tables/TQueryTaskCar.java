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

public class TQueryTaskCar extends AbstractTable {

	static {
		init();
	}

	private TQueryTaskCar(){
		super.tableName = "query_task_car";
	}

	private TQueryTaskCar(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TQueryTaskCar getInstance() {
		return new TQueryTaskCar();
	}

	public static final TQueryTaskCar getInstance(String aliasName) {
		return new TQueryTaskCar(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("id", "id");
		allFieldMap.put("queryTaskId", "query_task_id");
		allFieldMap.put("orgCarId", "org_car_id");
		allFieldMap.put("status", "status");
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
	 *查询任务id | int(20)
	*/
	public final TableField<Integer> queryTaskId = new TableFieldImpl<Integer>(this,"query_task_id","queryTaskId","查询任务id");

	/**
	 *机构车辆id | bigint(20)
	*/
	public final TableField<Long> orgCarId = new TableFieldImpl<Long>(this,"org_car_id","orgCarId","机构车辆id");

	/**
	 *状态(0:为查询，1：已经查询) | int(11)
	*/
	public final TableField<Integer> status = new TableFieldImpl<Integer>(this,"status","status","状态(0:为查询，1：已经查询)");

	/**
	 *创建时间 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建时间");


	private final TableField<?>[] allFields = new TableField<?>[] {id,queryTaskId,orgCarId,status,createTime};

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
		return "TQueryTaskCar[table=query_task_car]";
	}

}
