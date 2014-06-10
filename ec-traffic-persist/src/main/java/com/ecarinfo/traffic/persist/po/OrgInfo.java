package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TOrgInfo.class)
public class OrgInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String code;//机构代号(机构编码)
	private String name;//名称
	private String address;//地址
	private String contacts;//联系人
	private String tel;//电话
	private String appKey;//密钥
	private Date expiredTime;//到期时间
	private Integer status = 1;//状态
	private Date joinTime;//加入时间
	private Integer queryType;//查询类型(1:实时查询,2:指查询)
	private Integer maxCarCounts;//授权车辆数
	private Integer leftCarCounts;//剩余车辆数
	private Integer maxQueryCounts;//最大查询次数
	private Integer leftQueryCounts;//剩余查询次数
	private Integer provinceId;//省份id
	private Integer cityId;//城市id
	private String salesman;//业务员名称
	private Integer tackId;//策略id
	private String belongTo;//所属集团
	private Integer carFrameLen = -1;//车架号长度
	private Integer carEngineLen = -1;//发动机号长度
	private Integer ownerNameLen = -1;//车辆拥有者姓名长度
	private Integer carCertificateLen = -1;//车辆登记证书长度
	private Integer tackId;//策略id
	private Boolean isDelete = false;//是否删除(1为删除)
	private Date updateTime;//更新时间
	private Date createTime;//创建时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getContacts () {
        return contacts;
    }

    public void setContacts (String contacts) {
        this.contacts = contacts;
    }

    public String getTel () {
        return tel;
    }

    public void setTel (String tel) {
        this.tel = tel;
    }

    public String getAppKey () {
        return appKey;
    }

    public void setAppKey (String appKey) {
        this.appKey = appKey;
    }

    public Date getExpiredTime () {
        return expiredTime;
    }

    public void setExpiredTime (Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Date getJoinTime () {
        return joinTime;
    }

    public void setJoinTime (Date joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getQueryType () {
        return queryType;
    }

    public void setQueryType (Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getMaxCarCounts () {
        return maxCarCounts;
    }

    public void setMaxCarCounts (Integer maxCarCounts) {
        this.maxCarCounts = maxCarCounts;
    }

    public Integer getLeftCarCounts () {
        return leftCarCounts;
    }

    public void setLeftCarCounts (Integer leftCarCounts) {
        this.leftCarCounts = leftCarCounts;
    }

    public Integer getMaxQueryCounts () {
        return maxQueryCounts;
    }

    public void setMaxQueryCounts (Integer maxQueryCounts) {
        this.maxQueryCounts = maxQueryCounts;
    }

    public Integer getLeftQueryCounts () {
        return leftQueryCounts;
    }

    public void setLeftQueryCounts (Integer leftQueryCounts) {
        this.leftQueryCounts = leftQueryCounts;
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

    public String getSalesman () {
        return salesman;
    }

    public void setSalesman (String salesman) {
        this.salesman = salesman;
    }

    public Integer getTackId () {
        return tackId;
    }

    public void setTackId (Integer tackId) {
        this.tackId = tackId;
    }

    public String getBelongTo () {
        return belongTo;
    }

    public void setBelongTo (String belongTo) {
        this.belongTo = belongTo;
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

    public Integer getTackId () {
        return tackId;
    }

    public void setTackId (Integer tackId) {
        this.tackId = tackId;
    }

    public Boolean getIsDelete () {
        return isDelete;
    }

    public void setIsDelete (Boolean isDelete) {
        this.isDelete = isDelete;
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