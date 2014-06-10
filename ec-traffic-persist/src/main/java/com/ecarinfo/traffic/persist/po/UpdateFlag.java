package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TUpdateFlag;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TUpdateFlag.class)
public class UpdateFlag implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//名称
	private Long maxId;//最大id
	private Date lastUpdateTime;//最后更新时间
	private Date updateTime;//更新时间
	private Date createTime;//创建时间

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

    public Long getMaxId () {
        return maxId;
    }

    public void setMaxId (Long maxId) {
        this.maxId = maxId;
    }

    public Date getLastUpdateTime () {
        return lastUpdateTime;
    }

    public void setLastUpdateTime (Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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