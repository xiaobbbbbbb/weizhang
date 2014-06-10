package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TSpiQueryMoney;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  第三方收费统计表
*
**/
@Entity(table=TSpiQueryMoney.class)
public class SpiQueryMoney implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private String orgCode;//机构编号
	private Integer spiMoneyId;//spi_money配置表id
	private Integer operatorId;//操作员id
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Date createTime;//创建时间

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

    public Integer getSpiMoneyId () {
        return spiMoneyId;
    }

    public void setSpiMoneyId (Integer spiMoneyId) {
        this.spiMoneyId = spiMoneyId;
    }

    public Integer getOperatorId () {
        return operatorId;
    }

    public void setOperatorId (Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getStartTime () {
        return startTime;
    }

    public void setStartTime (Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime () {
        return endTime;
    }

    public void setEndTime (Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}