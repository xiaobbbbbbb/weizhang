package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TRalRoleResources;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;

/**
*  
*
**/
@Entity(table=TRalRoleResources.class)
public class RalRoleResources implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer roleId = 0;//角色id
	private Integer resourcesId = 0;//资源id

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId () {
        return resourcesId;
    }

    public void setResourcesId (Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}