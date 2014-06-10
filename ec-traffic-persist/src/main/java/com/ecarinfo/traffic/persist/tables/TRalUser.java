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

public class TRalUser extends AbstractTable {

	static {
		init();
	}

	private TRalUser(){
		super.tableName = "ral_user";
	}

	private TRalUser(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TRalUser getInstance() {
		return new TRalUser();
	}

	public static final TRalUser getInstance(String aliasName) {
		return new TRalUser(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("userId", "user_id");
		allFieldMap.put("loginName", "login_name");
		allFieldMap.put("password", "password");
		allFieldMap.put("name", "name");
		allFieldMap.put("phone", "phone");
		allFieldMap.put("email", "email");
		allFieldMap.put("level", "level");
		allFieldMap.put("message", "message");
		allFieldMap.put("status", "status");
		allFieldMap.put("createTime", "create_time");
		allFieldMap.put("updateTime", "update_time");

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
	 *用户id | int(11)
	*/
	public final TableField<Integer>  pk = new TableFieldImpl<Integer>(this,"user_id","userId","用户id");
	public final TableField<Integer> userId = new TableFieldImpl<Integer>(this,"user_id","userId","用户id");

	/**
	 *登录名 | char(50)
	*/
	public final TableField<String> loginName = new TableFieldImpl<String>(this,"login_name","loginName","登录名");

	/**
	 *密码 | char(40)
	*/
	public final TableField<String> password = new TableFieldImpl<String>(this,"password","password","密码");

	/**
	 *姓名 | char(30)
	*/
	public final TableField<String> name = new TableFieldImpl<String>(this,"name","name","姓名");

	/**
	 *电话 | char(255)
	*/
	public final TableField<String> phone = new TableFieldImpl<String>(this,"phone","phone","电话");

	/**
	 *邮件 | char(255)
	*/
	public final TableField<String> email = new TableFieldImpl<String>(this,"email","email","邮件");

	/**
	 *级别(排序) | int(11)
	*/
	public final TableField<Integer> level = new TableFieldImpl<Integer>(this,"level","level","级别(排序)");

	/**
	 *备注 | varchar(255)
	*/
	public final TableField<String> message = new TableFieldImpl<String>(this,"message","message","备注");

	/**
	 *账号是否启用，默认为1启用 | int(1)
	*/
	public final TableField<Integer> status = new TableFieldImpl<Integer>(this,"status","status","账号是否启用，默认为1启用");

	/**
	 *创建日期 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建日期");

	/**
	 * | datetime
	*/
	public final DateTableField<Date> updateTime = new DateTableFieldImpl<Date>(this,"update_time","updateTime","");


	private final TableField<?>[] allFields = new TableField<?>[] {userId,loginName,password,name,phone,email,level,message,status,createTime,updateTime};

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
		return "TRalUser[table=ral_user]";
	}

}