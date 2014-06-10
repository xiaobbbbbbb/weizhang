package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TCarInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TCarInfo.class)
public class CarInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String carNo;//车牌号
	private String carFrameNo;//车架号
	private String carEngineNo;//发动机号
	private String carCertificate;//车辆证书编号
	private String ownerName;//车辆拥有者姓名
	private Integer carType = 2;//车辆类型(1:大型车,2:小型车)
	private Integer status;//状态
	private Boolean isDelete;//是否删除
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getCarNo () {
        return carNo;
    }

    public void setCarNo (String carNo) {
        this.carNo = carNo;
    }

    public String getCarFrameNo () {
        return carFrameNo;
    }

    public void setCarFrameNo (String carFrameNo) {
        this.carFrameNo = carFrameNo;
    }

    public String getCarEngineNo () {
        return carEngineNo;
    }

    public void setCarEngineNo (String carEngineNo) {
        this.carEngineNo = carEngineNo;
    }

    public String getCarCertificate () {
        return carCertificate;
    }

    public void setCarCertificate (String carCertificate) {
        this.carCertificate = carCertificate;
    }

    public String getOwnerName () {
        return ownerName;
    }

    public void setOwnerName (String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getCarType () {
        return carType;
    }

    public void setCarType (Integer carType) {
        this.carType = carType;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Boolean getIsDelete () {
        return isDelete;
    }

    public void setIsDelete (Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }
}