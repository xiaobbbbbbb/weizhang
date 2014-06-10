package com.ecarinfo.traffic.protocol.vo;

import java.util.Date;

public class TrafficVO {
	private String carNo;// 车牌号
	private String category;//违章分类（现场，电子监控等等）
	private String trafficDetail;// 违章描述
	private String trafficLocation;// 违章地点
	private Date trafficTime;// 违章时间
	private String archive;// 文书号
	private String code;// 违章代号
	private Integer scores;// 违章扣分
	private Float money;// 违章罚款
	private String uniqueKey;//唯一标识一条违章记录=md5(base64(carNo+carType+detail+happenTime))
	private Integer status;//处理状态
	private String trafficCity;//违章城市

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getCarNo() {
		return carNo;
	}

	public String getTrafficCity() {
		return trafficCity;
	}

	public void setTrafficCity(String trafficCity) {
		this.trafficCity = trafficCity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getTrafficDetail() {
		return trafficDetail;
	}

	public void setTrafficDetail(String trafficDetail) {
		this.trafficDetail = trafficDetail;
	}

	public String getTrafficLocation() {
		return trafficLocation;
	}

	public void setTrafficLocation(String trafficLocation) {
		this.trafficLocation = trafficLocation;
	}

	public Date getTrafficTime() {
		return trafficTime;
	}

	public void setTrafficTime(Date trafficTime) {
		this.trafficTime = trafficTime;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getScores() {
		return scores;
	}

	public void setScores(Integer scores) {
		this.scores = scores;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
