package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TInputRule;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  输入规则表
*
**/
@Entity(table=TInputRule.class)
public class InputRule implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private Integer provinceId;//车信城市id
	private String provinceName;//省份名称
	private String provincePinyin;//省份拼音
	private String provincePrefix;//省份前缀
	private Integer cityId;//城市id
	private String cityPinyin;//城市拼音
	private String cityName;//城市名称
	private String carNoPrefix;//车牌前缀
	private Integer carFrameLen = -1;//车架号长度
	private Integer carEngineLen = -1;//发动机号长度
	private Integer ownerNameLen = -1;//车辆拥有者姓名长度
	private Integer carCertificateLen = -1;//车辆登记证书长度
	private Date updateTime;//更新时间
	private Date createTime;//创建时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getProvinceId () {
        return provinceId;
    }

    public void setProvinceId (Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName () {
        return provinceName;
    }

    public void setProvinceName (String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvincePinyin () {
        return provincePinyin;
    }

    public void setProvincePinyin (String provincePinyin) {
        this.provincePinyin = provincePinyin;
    }

    public String getProvincePrefix () {
        return provincePrefix;
    }

    public void setProvincePrefix (String provincePrefix) {
        this.provincePrefix = provincePrefix;
    }

    public Integer getCityId () {
        return cityId;
    }

    public void setCityId (Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityPinyin () {
        return cityPinyin;
    }

    public void setCityPinyin (String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public String getCityName () {
        return cityName;
    }

    public void setCityName (String cityName) {
        this.cityName = cityName;
    }

    public String getCarNoPrefix () {
        return carNoPrefix;
    }

    public void setCarNoPrefix (String carNoPrefix) {
        this.carNoPrefix = carNoPrefix;
    }

    public Integer getCarFrameLen () {
        return carFrameLen;
    }

    public void setCarFrameLen (Integer carFrameLen) {
        this.carFrameLen = carFrameLen;
    }

    public Integer getCarEngineLen () {
        return carEngineLen;
    }

    public void setCarEngineLen (Integer carEngineLen) {
        this.carEngineLen = carEngineLen;
    }

    public Integer getOwnerNameLen () {
        return ownerNameLen;
    }

    public void setOwnerNameLen (Integer ownerNameLen) {
        this.ownerNameLen = ownerNameLen;
    }

    public Integer getCarCertificateLen () {
        return carCertificateLen;
    }

    public void setCarCertificateLen (Integer carCertificateLen) {
        this.carCertificateLen = carCertificateLen;
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