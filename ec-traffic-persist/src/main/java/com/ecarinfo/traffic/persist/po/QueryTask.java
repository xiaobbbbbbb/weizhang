package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryTask;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  查询任务表
*
**/
@Entity(table=TQueryTask.class)
public class QueryTask implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//自定义任务名称
	private Integer operatorId;//操作员
	private String orgCode;//机构编号
	private String taskDetail;//任务描述
	private Integer taskType = 1;//任务类型
	private Integer status = 1;//任务状态
	private Integer orgScheduleId;//机构调度id
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

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

    public Integer getOperatorId () {
        return operatorId;
    }

    public void setOperatorId (Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTaskDetail () {
        return taskDetail;
    }

    public void setTaskDetail (String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public Integer getTaskType () {
        return taskType;
    }

    public void setTaskType (Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Integer getOrgScheduleId () {
        return orgScheduleId;
    }

    public void setOrgScheduleId (Integer orgScheduleId) {
        this.orgScheduleId = orgScheduleId;
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