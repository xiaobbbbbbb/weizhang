package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TSpiMoney;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  第三方价钱配置表
*
**/
@Entity(table=TSpiMoney.class)
public class SpiMoney implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private Integer type = 1;//收费类型(1:按单个请求，2.按年，3：按月，4：按日)
	private String name;//收费描述
	private Integer spiId;//spi_info的id
	private Float money;//收费(元)
	private Integer status;//状态
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getSpiId () {
        return spiId;
    }

    public void setSpiId (Integer spiId) {
        this.spiId = spiId;
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