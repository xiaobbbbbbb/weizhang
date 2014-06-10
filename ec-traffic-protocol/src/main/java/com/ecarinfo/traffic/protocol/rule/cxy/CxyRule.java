package com.ecarinfo.traffic.protocol.rule.cxy;

import java.util.List;

import com.ecarinfo.traffic.protocol.vo.RuleVO;

public class CxyRule extends RuleVO {
	private Integer ProvinceID;
	private String ProvinceName;
	private String ProvincePrefix;
	private List<Rule> Cities;

	public List<Rule> getCities() {
		return Cities;
	}

	public void setCities(List<Rule> cities) {
		Cities = cities;
	}

	public Integer getProvinceID() {
		return ProvinceID;
	}

	public void setProvinceID(Integer provinceID) {
		ProvinceID = provinceID;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}

	public String getProvincePrefix() {
		return ProvincePrefix;
	}

	public void setProvincePrefix(String provincePrefix) {
		ProvincePrefix = provincePrefix;
	}

	public static class Rule {

		private Integer ProvinceID;//
		private String ProvinceName;
		private String ProvincePrefix;//

		private Integer CityID; // 是否开通代办。 1表示开通代办，0表示未开通代办
		private String CityName;// 城市全名（省+市
		private String Name;// 城市名称
		private String CarNumberPrefix;// 城市所对应的车牌前缀，除京，沪，津，渝的代码为1位之外，其他城市代码为2位（汉字+字母）
		private Integer CarCodeLen;// 询所需车架号长度 其中：0 代表不需要 99 代表完整 具体数字代表 后多少位 如
									// 6
									// 代表需要车架号后6位 99 代表需要完整车架号（下同）

		private Integer CarEngineLen;// 查询所需发动机号长度（同上）
		private Integer CarOwnerLen;// 查询所需车辆所有人信息长度（同上）
		private Integer ProxyEnable;// 是否开通代办。 1表示开通代办，0表示未开通代办

		public Integer getCityID() {
			return CityID;
		}

		public void setCityID(Integer cityID) {
			CityID = cityID;
		}

		public String getCityName() {
			return CityName;
		}

		public void setCityName(String cityName) {
			CityName = cityName;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getCarNumberPrefix() {
			return CarNumberPrefix;
		}

		public void setCarNumberPrefix(String carNumberPrefix) {
			CarNumberPrefix = carNumberPrefix;
		}

		public Integer getCarCodeLen() {
			return CarCodeLen;
		}

		public void setCarCodeLen(Integer carCodeLen) {
			CarCodeLen = carCodeLen;
		}

		public Integer getCarEngineLen() {
			return CarEngineLen;
		}

		public void setCarEngineLen(Integer carEngineLen) {
			CarEngineLen = carEngineLen;
		}

		public Integer getCarOwnerLen() {
			return CarOwnerLen;
		}

		public void setCarOwnerLen(Integer carOwnerLen) {
			CarOwnerLen = carOwnerLen;
		}

		public Integer getProxyEnable() {
			return ProxyEnable;
		}

		public void setProxyEnable(Integer proxyEnable) {
			ProxyEnable = proxyEnable;
		}

		public Integer getProvinceID() {
			return ProvinceID;
		}

		public void setProvinceID(Integer provinceID) {
			ProvinceID = provinceID;
		}

		public String getProvinceName() {
			return ProvinceName;
		}

		public void setProvinceName(String provinceName) {
			ProvinceName = provinceName;
		}

		public String getProvincePrefix() {
			return ProvincePrefix;
		}

		public void setProvincePrefix(String provincePrefix) {
			ProvincePrefix = provincePrefix;
		}

	}

}
