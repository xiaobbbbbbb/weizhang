package com.ecarinfo.traffic.protocol.vo;


public class RuleVO {
	private String spiName;//第三方代号
	private Integer provinceId;//车信城市id
	private String provinceName;//省份名称
	private String provincePinyin;//省份拼音
	private String provincePrefix;//省份前缀
	private Integer cityId;//城市id
	private String cityPinyin;//城市拼音
	private String cityName;//城市名称
	private String carNoPrefix;//车牌前缀
	private Integer carFrameLen;//车架号长度
	private Integer carEngineLen;//发动机号长度
	private Integer ownerNameLen;//车辆拥有者姓名长度
	private Integer carCertificateLen = -1;//车辆登记证书长度
	public String getSpiName() {
		return spiName;
	}
	public void setSpiName(String spiName) {
		this.spiName = spiName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvincePinyin() {
		return provincePinyin;
	}
	public void setProvincePinyin(String provincePinyin) {
		this.provincePinyin = provincePinyin;
	}
	public String getProvincePrefix() {
		return provincePrefix;
	}
	public void setProvincePrefix(String provincePrefix) {
		this.provincePrefix = provincePrefix;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityPinyin() {
		return cityPinyin;
	}
	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCarNoPrefix() {
		return carNoPrefix;
	}
	public void setCarNoPrefix(String carNoPrefix) {
		this.carNoPrefix = carNoPrefix;
	}
	public Integer getCarFrameLen() {
		return carFrameLen;
	}
	public void setCarFrameLen(Integer carFrameLen) {
		this.carFrameLen = carFrameLen;
	}
	public Integer getCarEngineLen() {
		return carEngineLen;
	}
	public void setCarEngineLen(Integer carEngineLen) {
		this.carEngineLen = carEngineLen;
	}
	public Integer getOwnerNameLen() {
		return ownerNameLen;
	}
	public void setOwnerNameLen(Integer ownerNameLen) {
		this.ownerNameLen = ownerNameLen;
	}
	public Integer getCarCertificateLen() {
		return carCertificateLen;
	}
	public void setCarCertificateLen(Integer carCertificateLen) {
		this.carCertificateLen = carCertificateLen;
	}
	
}
