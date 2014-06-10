package com.ecarinfo.traffic.api.test.vo;

import com.ecarinfo.common.anno.EcField;

public class CxyTrafficDto {

	@EcField("序号")
	private Integer id = 0;

	@EcField("车牌号")
	private String carNo;// 车牌号

	@EcField("车辆类型")
	private String carType = "02";// 车辆类型(01大型车，02小型车)

	@EcField("车架号")
	private String carFrameNo;// 车架号

	@EcField("发动机号")
	private String carEngineNo;// 发动机号

	@EcField("时间")
	private String trafficDate;// 违章日期

	@EcField("违章发生地点")
	private String trafficAddr;// 违章地点

	@EcField("违章扣分")
	private String trafficScore;// 违章扣分

	@EcField("罚款金额")
	private String trafficMoney;// 违章罚金

	@EcField("违章类型")
	private String trafficDetail;// 违章类型

	@EcField("违章城市")
	private String city;// 违章城市

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getTrafficDate() {
		return trafficDate;
	}

	public void setTrafficDate(String trafficDate) {
		this.trafficDate = trafficDate;
	}

	public String getTrafficAddr() {
		return trafficAddr;
	}

	public void setTrafficAddr(String trafficAddr) {
		this.trafficAddr = trafficAddr;
	}

	public String getTrafficScore() {
		return trafficScore;
	}

	public void setTrafficScore(String trafficScore) {
		this.trafficScore = trafficScore;
	}

	public String getTrafficMoney() {
		return trafficMoney;
	}

	public void setTrafficMoney(String trafficMoney) {
		this.trafficMoney = trafficMoney;
	}

	public String getTrafficDetail() {
		return trafficDetail;
	}

	public void setTrafficDetail(String trafficDetail) {
		this.trafficDetail = trafficDetail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarFrameNo() {
		return carFrameNo;
	}

	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}

	public String getCarEngineNo() {
		return carEngineNo;
	}

	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}

	// @EcField("错误提示")
	// private String errorMsg;//错误提示

	// @EcField("类别")
	// private String type = "01";// 类别(01运营车车，02行政车辆)

}
