package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TOrgSchedule;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  机构调度表（表示机构拥有的调度方式）
*
**/
@Entity(table=TOrgSchedule.class)
public class OrgSchedule implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private Integer scheduleId;//调度id
	private String orgCode;//机构编号
	private Integer status;//状态
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getScheduleId () {
        return scheduleId;
    }

    public void setScheduleId (Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
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