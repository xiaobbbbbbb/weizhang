package com.ecarinfo.traffic.protocol.vo;

import java.util.Date;
import java.util.List;

public class CommonRuleVO extends RuleVO {
	private Integer provinceId;// 车信城市id
	private String provinceName;// 省份名称
	private String provincePinyin;// 省份拼音
	private String provincePrefix;// 省份前缀
	private List<CityRule> cities;

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

	public String getProvincePrefix() {
		return provincePrefix;
	}

	public void setProvincePrefix(String provincePrefix) {
		this.provincePrefix = provincePrefix;
	}

	public List<CityRule> getCities() {
		return cities;
	}

	public void setCities(List<CityRule> cities) {
		this.cities = cities;
	}

	public String getProvincePinyin() {
		return provincePinyin;
	}

	public void setProvincePinyin(String provincePinyin) {
		this.provincePinyin = provincePinyin;
	}

	public static class CityRule {
		private Integer cityId;// 城市id
		private String cityPinyin;// 城市拼音
		private String cityName;// 城市名称
		private String carNoPrefix;// 车牌前缀
		private Integer carFrameLen;// 车架号长度
		private Integer carEngineLen;// 发动机号长度
		private Integer ownerNameLen;// 车辆拥有者姓名
		private Date updateTime;// 更新时间
		private Date createTime;// 创建时间

		public Integer getCityId() {
			return cityId;
		}

		public void setCityId(Integer cityId) {
			this.cityId = cityId;
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

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public String getCityPinyin() {
			return cityPinyin;
		}

		public void setCityPinyin(String cityPinyin) {
			this.cityPinyin = cityPinyin;
		}

	}
}
