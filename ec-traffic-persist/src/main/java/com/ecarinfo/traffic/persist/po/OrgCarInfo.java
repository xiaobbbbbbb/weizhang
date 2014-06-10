package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  机构车辆表
*
**/
@Entity(table=TOrgCarInfo.class)
public class OrgCarInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String orgCode;//机构编号
	private String carNo;//车牌号
	private String carFrameNo;//车架号
	private String carEngineNo;//发动机号
	private String certificate;//车辆证书编号
	private Integer carType;//车辆类型
	private String batNo;//批次
	private Integer status;//状态
	private Boolean isDelete = false;//是否删除
	private Integer queryType = 1;//查询类型（1：实时查询，2：包年）
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
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

    public String getCertificate () {
        return certificate;
    }

    public void setCertificate (String certificate) {
        this.certificate = certificate;
    }

    public Integer getCarType () {
        return carType;
    }

    public void setCarType (Integer carType) {
        this.carType = carType;
    }

    public String getBatNo () {
        return batNo;
    }

    public void setBatNo (String batNo) {
        this.batNo = batNo;
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

    public Integer getQueryType () {
        return queryType;
    }

    public void setQueryType (Integer queryType) {
        this.queryType = queryType;
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