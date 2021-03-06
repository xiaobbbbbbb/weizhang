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

public class TSpiTack extends AbstractTable {

	static {
		init();
	}

	private TSpiTack(){
		super.tableName = "spi_tack";
	}

	private TSpiTack(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TSpiTack getInstance() {
		return new TSpiTack();
	}

	public static final TSpiTack getInstance(String aliasName) {
		return new TSpiTack(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("id", "id");
		allFieldMap.put("tackId", "tack_id");
		allFieldMap.put("spiId", "spi_id");
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
	 * | int(11)
	*/
	public final TableField<Integer>  pk = new TableFieldImpl<Integer>(this,"id","id","");
	public final TableField<Integer> id = new TableFieldImpl<Integer>(this,"id","id","");

	/**
	 *策略id | int(11)
	*/
	public final TableField<Integer> tackId = new TableFieldImpl<Integer>(this,"tack_id","tackId","策略id");

	/**
	 *spi的id | int(11)
	*/
	public final TableField<Integer> spiId = new TableFieldImpl<Integer>(this,"spi_id","spiId","spi的id");

	/**
	 *创建时间 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建时间");


	private final TableField<?>[] allFields = new TableField<?>[] {id,tackId,spiId,createTime};

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
		return "TSpiTack[table=spi_tack]";
	}

}
