package com.ecarinfo.traffic.protocol.vo;

import java.util.List;

public class TrafficResponseVO {
	private String carNo;
	private String carFrameNo;
	private String carEngineNo;
	private Integer carType;

	List<TrafficVO> traffics;

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

	public String getCarEngineNo() {
		return carEngineNo;
	}

	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public List<TrafficVO> getTraffics() {
		return traffics;
	}

	public void setTraffics(List<TrafficVO> traffics) {
		this.traffics = traffics;
	}

}
