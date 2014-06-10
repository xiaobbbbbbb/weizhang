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

public class TSpiRule extends AbstractTable {

	static {
		init();
	}

	private TSpiRule(){
		super.tableName = "spi_rule";
	}

	private TSpiRule(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TSpiRule getInstance() {
		return new TSpiRule();
	}

	public static final TSpiRule getInstance(String aliasName) {
		return new TSpiRule(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("id", "id");
		allFieldMap.put("spiName", "spi_name");
		allFieldMap.put("provinceId", "province_id");
		allFieldMap.put("provinceName", "province_name");
		allFieldMap.put("provincePinyin", "province_pinyin");
		allFieldMap.put("provincePrefix", "province_prefix");
		allFieldMap.put("cityId", "city_id");
		allFieldMap.put("cityPinyin", "city_pinyin");
		allFieldMap.put("cityName", "city_name");
		allFieldMap.put("carNoPrefix", "car_no_prefix");
		allFieldMap.put("carFrameLen", "car_frame_len");
		allFieldMap.put("carEngineLen", "car_engine_len");
		allFieldMap.put("ownerNameLen", "owner_name_len");
		allFieldMap.put("carCertificateLen", "car_certificate_len");
		allFieldMap.put("updateTime", "update_time");
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
	 *第三方代号 | char(20)
	*/
	public final TableField<String> spiName = new TableFieldImpl<String>(this,"spi_name","spiName","第三方代号");

	/**
	 *车信城市id | int(11)
	*/
	public final TableField<Integer> provinceId = new TableFieldImpl<Integer>(this,"province_id","provinceId","车信城市id");

	/**
	 *省份名称 | char(30)
	*/
	public final TableField<String> provinceName = new TableFieldImpl<String>(this,"province_name","provinceName","省份名称");

	/**
	 *省份拼音 | varchar(255)
	*/
	public final TableField<String> provincePinyin = new TableFieldImpl<String>(this,"province_pinyin","provincePinyin","省份拼音");

	/**
	 *省份前缀 | char(2)
	*/
	public final TableField<String> provincePrefix = new TableFieldImpl<String>(this,"province_prefix","provincePrefix","省份前缀");

	/**
	 *城市id | int(11)
	*/
	public final TableField<Integer> cityId = new TableFieldImpl<Integer>(this,"city_id","cityId","城市id");

	/**
	 *城市拼音 | varchar(255)
	*/
	public final TableField<String> cityPinyin = new TableFieldImpl<String>(this,"city_pinyin","cityPinyin","城市拼音");

	/**
	 *城市名称 | char(50)
	*/
	public final TableField<String> cityName = new TableFieldImpl<String>(this,"city_name","cityName","城市名称");

	/**
	 *车牌前缀 | char(2)
	*/
	public final TableField<String> carNoPrefix = new TableFieldImpl<String>(this,"car_no_prefix","carNoPrefix","车牌前缀");

	/**
	 *车架号长度 | int(11)
	*/
	public final TableField<Integer> carFrameLen = new TableFieldImpl<Integer>(this,"car_frame_len","carFrameLen","车架号长度");

	/**
	 *发动机号长度 | int(11)
	*/
	public final TableField<Integer> carEngineLen = new TableFieldImpl<Integer>(this,"car_engine_len","carEngineLen","发动机号长度");

	/**
	 *车辆拥有者姓名长度 | int(10)
	*/
	public final TableField<Integer> ownerNameLen = new TableFieldImpl<Integer>(this,"owner_name_len","ownerNameLen","车辆拥有者姓名长度");

	/**
	 *车辆登记证书长度 | int(11)
	*/
	public final TableField<Integer> carCertificateLen = new TableFieldImpl<Integer>(this,"car_certificate_len","carCertificateLen","车辆登记证书长度");

	/**
	 *更新时间 | datetime
	*/
	public final DateTableField<Date> updateTime = new DateTableFieldImpl<Date>(this,"update_time","updateTime","更新时间");

	/**
	 *创建时间 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建时间");


	private final TableField<?>[] allFields = new TableField<?>[] {id,spiName,provinceId,provinceName,provincePinyin,provincePrefix,cityId,cityPinyin,cityName,carNoPrefix,carFrameLen,carEngineLen,ownerNameLen,carCertificateLen,updateTime,createTime};

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
		return "TSpiRule[table=spi_rule]";
	}

}
