package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TOrgType;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  机构类型
*
**/
@Entity(table=TOrgType.class)
public class OrgType implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//类型名称
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private Integer status;//状态

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

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}