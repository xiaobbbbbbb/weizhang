package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TRalUserRole;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;

/**
*  
*
**/
@Entity(table=TRalUserRole.class)
public class RalUserRole implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer userId = 0;//用户id
	private Integer roleId = 0;//角色id

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }
}