package com.ecarinfo.traffic.vo;


public class UploadView {
	
	private String carNo;
	private String userName;
	private String carEngineNo;
	private String certificate;//证书编号
	private String carFrameNo;
	private Integer type; //车辆类型
	private Integer queryType;//查询类型
	
	//重新定义hashCode 
	 public int hashCode(){
	        return carNo.hashCode();
	 }
	 
	 @Override
	 public boolean equals(Object st){
		 UploadView view= (UploadView) st;
	        if (carNo.equals(view.carNo)&&carFrameNo.equals(view.carFrameNo)) 
	        	return true;
	        else 
	        	return false;
	}
	 


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCarEngineNo() {
		return carEngineNo;
	}
	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}
	public String getCarFrameNo() {
		return carFrameNo;
	}
	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	@Override
	public String toString() {
		return "UploadView [carNo=" + carNo + ", userName=" + userName
				+ ", carEngineNo=" + carEngineNo + ", certificate="
				+ certificate + ", carFrameNo=" + carFrameNo + ", type=" + type
				+ ", queryType=" + queryType + "]";
	}
	
}
