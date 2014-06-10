package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TProvince;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;

/**
*  省份信息表
*
**/
@Entity(table=TProvince.class)
public class Province implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//省
	private String simpleName;//省简写
	private String keyTitle;//拼音首字符
	private Integer status;//是否有效，1为有效，0未无效

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

    public String getSimpleName () {
        return simpleName;
    }

    public void setSimpleName (String simpleName) {
        this.simpleName = simpleName;
    }

    public String getKeyTitle () {
        return keyTitle;
    }

    public void setKeyTitle (String keyTitle) {
        this.keyTitle = keyTitle;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }
}