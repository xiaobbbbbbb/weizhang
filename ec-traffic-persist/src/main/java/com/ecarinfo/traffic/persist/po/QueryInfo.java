package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  查询记录表
*
**/
@Entity(table=TQueryInfo.class)
public class QueryInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String carNo;//车牌号
	private String carFrameNo;//车架号
	private String carEngineNo;//发动机号
	private String carCertificate;//车辆登记证书
	private String ownerName;//车主姓名
	private Integer carType;//车辆类型
	private Integer spiId;//spi的id(用于计费)
	private Boolean fromCache;//是否从缓存返回
	private String orgCode;//机构编码
	private String area;//区域（md5(base64(carNo-provinceId-cityId-errorCode))）
	private Integer taskId;//任务ID
	private Date queryTime;//查询时间(接口调用时间)
	private Integer costTime = 0;//查询耗时(单位毫秒)
	private Integer trafficCounts;//违章记录条数
	private Integer errorCode;//错误代号
	private String errorMessage;//错误信息
	private Integer apiMoneyId;//api单价id
	private Integer apiMoneyType = 1;//收费类型(1:按单个请求，2.按年，3：按月，4：按日)
	private Float apiMoneyValue = 0.00f;//api单价
	private Integer spiMoneyId;//spi单价id
	private Integer spiMoneyType;//收费类型(1:按单个请求，2.按年，3：按月，4：按日)
	private Float spiMoneyValue = 0.00f;//spi单价
	private Date updateTime;//更新时间
	private Date createTime;//创建时间

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

    public Integer getSpiId () {
        return spiId;
    }

    public void setSpiId (Integer spiId) {
        this.spiId = spiId;
    }

    public Boolean getFromCache () {
        return fromCache;
    }

    public void setFromCache (Boolean fromCache) {
        this.fromCache = fromCache;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public String getArea () {
        return area;
    }

    public void setArea (String area) {
        this.area = area;
    }

    public Integer getTaskId () {
        return taskId;
    }

    public void setTaskId (Integer taskId) {
        this.taskId = taskId;
    }

    public Date getQueryTime () {
        return queryTime;
    }

    public void setQueryTime (Date queryTime) {
        this.queryTime = queryTime;
    }

    public Integer getCostTime () {
        return costTime;
    }

    public void setCostTime (Integer costTime) {
        this.costTime = costTime;
    }

    public Integer getTrafficCounts () {
        return trafficCounts;
    }

    public void setTrafficCounts (Integer trafficCounts) {
        this.trafficCounts = trafficCounts;
    }

    public Integer getErrorCode () {
        return errorCode;
    }

    public void setErrorCode (Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage () {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getApiMoneyId () {
        return apiMoneyId;
    }

    public void setApiMoneyId (Integer apiMoneyId) {
        this.apiMoneyId = apiMoneyId;
    }

    public Integer getApiMoneyType () {
        return apiMoneyType;
    }

    public void setApiMoneyType (Integer apiMoneyType) {
        this.apiMoneyType = apiMoneyType;
    }

    public Float getApiMoneyValue () {
        return apiMoneyValue;
    }

    public void setApiMoneyValue (Float apiMoneyValue) {
        this.apiMoneyValue = apiMoneyValue;
    }

    public Integer getSpiMoneyId () {
        return spiMoneyId;
    }

    public void setSpiMoneyId (Integer spiMoneyId) {
        this.spiMoneyId = spiMoneyId;
    }

    public Integer getSpiMoneyType () {
        return spiMoneyType;
    }

    public void setSpiMoneyType (Integer spiMoneyType) {
        this.spiMoneyType = spiMoneyType;
    }

    public Float getSpiMoneyValue () {
        return spiMoneyValue;
    }

    public void setSpiMoneyValue (Float spiMoneyValue) {
        this.spiMoneyValue = spiMoneyValue;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}