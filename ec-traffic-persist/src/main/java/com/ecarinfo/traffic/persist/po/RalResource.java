package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TRalResource;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;

/**
*  
*
**/
@Entity(table=TRalResource.class)
public class RalResource implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer resourceId;//资源id
	private String name;//资源名称
	private String url;//资源地址
	private String icon;//图标
	private Integer parentId;//父资源id
	private Integer level;//资源排序级别
	private Integer isLeaf;//是否是叶子
	private String message;//描述
	private Integer type;//0为系统资源，1未用户资源，2即是系统资源又是用户资源

    public Integer getResourceId () {
        return resourceId;
    }

    public void setResourceId (Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getIcon () {
        return icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public Integer getParentId () {
        return parentId;
    }

    public void setParentId (Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel () {
        return level;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }

    public Integer getIsLeaf () {
        return isLeaf;
    }

    public void setIsLeaf (Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }
}