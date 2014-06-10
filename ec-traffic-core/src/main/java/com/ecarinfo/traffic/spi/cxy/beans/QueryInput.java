package com.ecarinfo.traffic.spi.cxy.beans;

/**
 * 违章查询条件
 * 
 * @author xiaodx
 * 
 */
public class QueryInput {

	private String userid;
	private String userpwd;

	private String carType = "02";// 车辆类型（02=小型车）
	private String carNo;// 车牌号
	private String carFrameNo;// 车架号
	private String engineNo;// 发动机号

	private Integer provinceId;
	private Integer cityId;
	private String carOwner;
	private String useActivePrice;
	private String version = "1";
	private String timeAxis;
	private String other;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarFrameNo() {
		return carFrameNo;
	}

	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getUseActivePrice() {
		return useActivePrice;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public void setUseActivePrice(String useActivePrice) {
		this.useActivePrice = useActivePrice;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimeAxis() {
		return timeAxis;
	}

	public void setTimeAxis(String timeAxis) {
		this.timeAxis = timeAxis;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
