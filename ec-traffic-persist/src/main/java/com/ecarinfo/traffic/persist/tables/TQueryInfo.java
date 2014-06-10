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

public class TQueryInfo extends AbstractTable {

	static {
		init();
	}

	private TQueryInfo(){
		super.tableName = "query_info";
	}

	private TQueryInfo(String aliasName){
		this();
		setAliasName(aliasName);
	}

	public static final TQueryInfo getInstance() {
		return new TQueryInfo();
	}

	public static final TQueryInfo getInstance(String aliasName) {
		return new TQueryInfo(aliasName);
	}

	private static Map<String, String> allFieldMap;// = new HashMap<String, String>();
	private static final void init() {
		allFieldMap = new HashMap<String, String>();
		allFieldMap.put("id", "id");
		allFieldMap.put("carNo", "car_no");
		allFieldMap.put("carFrameNo", "car_frame_no");
		allFieldMap.put("carEngineNo", "car_engine_no");
		allFieldMap.put("carCertificate", "car_certificate");
		allFieldMap.put("ownerName", "owner_name");
		allFieldMap.put("carType", "car_type");
		allFieldMap.put("spiId", "spi_id");
		allFieldMap.put("fromCache", "from_cache");
		allFieldMap.put("orgCode", "org_code");
		allFieldMap.put("area", "area");
		allFieldMap.put("taskId", "task_id");
		allFieldMap.put("queryTime", "query_time");
		allFieldMap.put("costTime", "cost_time");
		allFieldMap.put("trafficCounts", "traffic_counts");
		allFieldMap.put("errorCode", "error_code");
		allFieldMap.put("errorMessage", "error_message");
		allFieldMap.put("apiMoneyId", "api_money_id");
		allFieldMap.put("apiMoneyType", "api_money_type");
		allFieldMap.put("apiMoneyValue", "api_money_value");
		allFieldMap.put("spiMoneyId", "spi_money_id");
		allFieldMap.put("spiMoneyType", "spi_money_type");
		allFieldMap.put("spiMoneyValue", "spi_money_value");
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
	 * | bigint(20)
	*/
	public final TableField<Long>  pk = new TableFieldImpl<Long>(this,"id","id","");
	public final TableField<Long> id = new TableFieldImpl<Long>(this,"id","id","");

	/**
	 *车牌号 | char(7)
	*/
	public final TableField<String> carNo = new TableFieldImpl<String>(this,"car_no","carNo","车牌号");

	/**
	 *车架号 | char(32)
	*/
	public final TableField<String> carFrameNo = new TableFieldImpl<String>(this,"car_frame_no","carFrameNo","车架号");

	/**
	 *发动机号 | char(32)
	*/
	public final TableField<String> carEngineNo = new TableFieldImpl<String>(this,"car_engine_no","carEngineNo","发动机号");

	/**
	 *车辆登记证书 | char(32)
	*/
	public final TableField<String> carCertificate = new TableFieldImpl<String>(this,"car_certificate","carCertificate","车辆登记证书");

	/**
	 *车主姓名 | char(30)
	*/
	public final TableField<String> ownerName = new TableFieldImpl<String>(this,"owner_name","ownerName","车主姓名");

	/**
	 *车辆类型 | int(11)
	*/
	public final TableField<Integer> carType = new TableFieldImpl<Integer>(this,"car_type","carType","车辆类型");

	/**
	 *spi的id(用于计费) | int(11)
	*/
	public final TableField<Integer> spiId = new TableFieldImpl<Integer>(this,"spi_id","spiId","spi的id(用于计费)");

	/**
	 *是否从缓存返回 | tinyint(11)
	*/
	public final TableField<Boolean> fromCache = new TableFieldImpl<Boolean>(this,"from_cache","fromCache","是否从缓存返回");

	/**
	 *机构编码 | char(6)
	*/
	public final TableField<String> orgCode = new TableFieldImpl<String>(this,"org_code","orgCode","机构编码");

	/**
	 *区域（md5(base64(carNo-provinceId-cityId-errorCode))） | char(32)
	*/
	public final TableField<String> area = new TableFieldImpl<String>(this,"area","area","区域（md5(base64(carNo-provinceId-cityId-errorCode))）");

	/**
	 *任务ID | int(11)
	*/
	public final TableField<Integer> taskId = new TableFieldImpl<Integer>(this,"task_id","taskId","任务ID");

	/**
	 *查询时间(接口调用时间) | datetime
	*/
	public final DateTableField<Date> queryTime = new DateTableFieldImpl<Date>(this,"query_time","queryTime","查询时间(接口调用时间)");

	/**
	 *查询耗时(单位毫秒) | int(11)
	*/
	public final TableField<Integer> costTime = new TableFieldImpl<Integer>(this,"cost_time","costTime","查询耗时(单位毫秒)");

	/**
	 *违章记录条数 | int(11)
	*/
	public final TableField<Integer> trafficCounts = new TableFieldImpl<Integer>(this,"traffic_counts","trafficCounts","违章记录条数");

	/**
	 *错误代号 | int(10)
	*/
	public final TableField<Integer> errorCode = new TableFieldImpl<Integer>(this,"error_code","errorCode","错误代号");

	/**
	 *错误信息 | varchar(255)
	*/
	public final TableField<String> errorMessage = new TableFieldImpl<String>(this,"error_message","errorMessage","错误信息");

	/**
	 *api单价id | int(11)
	*/
	public final TableField<Integer> apiMoneyId = new TableFieldImpl<Integer>(this,"api_money_id","apiMoneyId","api单价id");

	/**
	 *收费类型(1:按单个请求，2.按年，3：按月，4：按日) | int(11)
	*/
	public final TableField<Integer> apiMoneyType = new TableFieldImpl<Integer>(this,"api_money_type","apiMoneyType","收费类型(1:按单个请求，2.按年，3：按月，4：按日)");

	/**
	 *api单价 | float(11,2)
	*/
	public final TableField<Float> apiMoneyValue = new TableFieldImpl<Float>(this,"api_money_value","apiMoneyValue","api单价");

	/**
	 *spi单价id | int(11)
	*/
	public final TableField<Integer> spiMoneyId = new TableFieldImpl<Integer>(this,"spi_money_id","spiMoneyId","spi单价id");

	/**
	 *收费类型(1:按单个请求，2.按年，3：按月，4：按日) | int(11)
	*/
	public final TableField<Integer> spiMoneyType = new TableFieldImpl<Integer>(this,"spi_money_type","spiMoneyType","收费类型(1:按单个请求，2.按年，3：按月，4：按日)");

	/**
	 *spi单价 | float(11,2)
	*/
	public final TableField<Float> spiMoneyValue = new TableFieldImpl<Float>(this,"spi_money_value","spiMoneyValue","spi单价");

	/**
	 *更新时间 | datetime
	*/
	public final DateTableField<Date> updateTime = new DateTableFieldImpl<Date>(this,"update_time","updateTime","更新时间");

	/**
	 *创建时间 | datetime
	*/
	public final DateTableField<Date> createTime = new DateTableFieldImpl<Date>(this,"create_time","createTime","创建时间");


	private final TableField<?>[] allFields = new TableField<?>[] {id,carNo,carFrameNo,carEngineNo,carCertificate,ownerName,carType,spiId,fromCache,orgCode,area,taskId,queryTime,costTime,trafficCounts,errorCode,errorMessage,apiMoneyId,apiMoneyType,apiMoneyValue,spiMoneyId,spiMoneyType,spiMoneyValue,updateTime,createTime};

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
		return "TQueryInfo[table=query_info]";
	}

}
