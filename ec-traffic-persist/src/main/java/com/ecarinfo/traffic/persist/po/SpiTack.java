package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TSpiTack;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  第三方策略表
*
**/
@Entity(table=TSpiTack.class)
public class SpiTack implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private Integer tackId;//策略id
	private Integer spiId;//spi的id
	private Date createTime;//创建时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getTackId () {
        return tackId;
    }

    public void setTackId (Integer tackId) {
        this.tackId = tackId;
    }

    public Integer getSpiId () {
        return spiId;
    }

    public void setSpiId (Integer spiId) {
        this.spiId = spiId;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}