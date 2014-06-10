package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TSpiInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  第三方配置表
*
**/
@Entity(table=TSpiInfo.class)
public class SpiInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//spi名称
	private String enName;//英文名称描述
	private String nodeUrl;//spi节点url
	private String ruleUrl;//查询规则url
	private String trafficUrl;//获取违章数据url
	private Integer status;//spi状态
	private String spiKey;//key
	private String userName;//登录名
	private String password;//密码
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

    public String getEnName () {
        return enName;
    }

    public void setEnName (String enName) {
        this.enName = enName;
    }

    public String getNodeUrl () {
        return nodeUrl;
    }

    public void setNodeUrl (String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getRuleUrl () {
        return ruleUrl;
    }

    public void setRuleUrl (String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public String getTrafficUrl () {
        return trafficUrl;
    }

    public void setTrafficUrl (String trafficUrl) {
        this.trafficUrl = trafficUrl;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public String getSpiKey () {
        return spiKey;
    }

    public void setSpiKey (String spiKey) {
        this.spiKey = spiKey;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
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