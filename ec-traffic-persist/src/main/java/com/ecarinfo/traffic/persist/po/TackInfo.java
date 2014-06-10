package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TTackInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  策略信息表
*
**/
@Entity(table=TTackInfo.class)
public class TackInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//策略名称
	private Integer operatorId;//操作员
	private Integer queryType;//查询类型(实时|包年)
	private Integer cacheTime = 24;//缓存时间（单位：小时）
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

    public Integer getOperatorId () {
        return operatorId;
    }

    public void setOperatorId (Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getQueryType () {
        return queryType;
    }

    public void setQueryType (Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getCacheTime () {
        return cacheTime;
    }

    public void setCacheTime (Integer cacheTime) {
        this.cacheTime = cacheTime;
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