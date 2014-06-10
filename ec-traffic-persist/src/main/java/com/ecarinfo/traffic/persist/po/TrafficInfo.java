package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TTrafficInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TTrafficInfo.class)
public class TrafficInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String carNo;//车牌号
	private Integer carType;//车型
	private String trafficDetail;//违章描述
	private String trafficCity;//违章城市
	private String trafficLocation;//违章地点
	private Date trafficTime;//违章时间
	private String archive;//文书号
	private String code;//违章代号
	private String uniqueKey;//唯一标识一条违章记录=md5(base64(carNo+carType+detail+happenTime))
	private Integer scores;//违章扣分
	private Integer status = 0;//处理状态(0 未处理  1 己处理)
	private Float money;//违章罚款
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

    public Integer getCarType () {
        return carType;
    }

    public void setCarType (Integer carType) {
        this.carType = carType;
    }

    public String getTrafficDetail () {
        return trafficDetail;
    }

    public void setTrafficDetail (String trafficDetail) {
        this.trafficDetail = trafficDetail;
    }

    public String getTrafficCity () {
        return trafficCity;
    }

    public void setTrafficCity (String trafficCity) {
        this.trafficCity = trafficCity;
    }

    public String getTrafficLocation () {
        return trafficLocation;
    }

    public void setTrafficLocation (String trafficLocation) {
        this.trafficLocation = trafficLocation;
    }

    public Date getTrafficTime () {
        return trafficTime;
    }

    public void setTrafficTime (Date trafficTime) {
        this.trafficTime = trafficTime;
    }

    public String getArchive () {
        return archive;
    }

    public void setArchive (String archive) {
        this.archive = archive;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getUniqueKey () {
        return uniqueKey;
    }

    public void setUniqueKey (String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Integer getScores () {
        return scores;
    }

    public void setScores (Integer scores) {
        this.scores = scores;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Float getMoney () {
        return money;
    }

    public void setMoney (Float money) {
        this.money = money;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}