package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TApiMoney;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  API价钱配置表
*
**/
@Entity(table=TApiMoney.class)
public class ApiMoney implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//收费描述
	private Integer type = 1;//收费类型(1:按单个请求，2.按年，3：按月，4：按日)
	private String orgCode = "-1";//机构编号(-1代表所有机构)
	private Float money;//单价（元）
	private Integer status;//状态
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Float getMoney () {
        return money;
    }

    public void setMoney (Float money) {
        this.money = money;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
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