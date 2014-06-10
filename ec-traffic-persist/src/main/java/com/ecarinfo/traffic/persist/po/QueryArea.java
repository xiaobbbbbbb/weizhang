package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryArea;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  查询区域表
*
**/
@Entity(table=TQueryArea.class)
public class QueryArea implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String orgCode;//机构编码
	private Integer provinceId;//省份id
	private Integer cityId;//城市id
	private String provinceName;//省份名称
	private Date updateTime;
	private String cityName;//城市名称
	private Date createTime;//创建时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getProvinceId () {
        return provinceId;
    }

    public void setProvinceId (Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId () {
        return cityId;
    }

    public void setCityId (Integer cityId) {
        this.cityId = cityId;
    }

    public String getProvinceName () {
        return provinceName;
    }

    public void setProvinceName (String provinceName) {
        this.provinceName = provinceName;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityName () {
        return cityName;
    }

    public void setCityName (String cityName) {
        this.cityName = cityName;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}