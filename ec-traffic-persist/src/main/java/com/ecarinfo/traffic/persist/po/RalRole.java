package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TRalRole;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TRalRole.class)
public class RalRole implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer roleId;//角色id
	private String name;//角色名称
	private String message;// 角色描述
	private Integer status = 1;//是否有效
	private Date createTime;
	private Date updateTime;

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
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