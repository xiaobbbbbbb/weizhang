package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryTaskCar;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  任务车辆信息
*
**/
@Entity(table=TQueryTaskCar.class)
public class QueryTaskCar implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Integer queryTaskId;//查询任务id
	private Long orgCarId;//机构车辆id
	private Integer status;//状态(0:为查询，1：已经查询)
	private Date createTime;//创建时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Integer getQueryTaskId () {
        return queryTaskId;
    }

    public void setQueryTaskId (Integer queryTaskId) {
        this.queryTaskId = queryTaskId;
    }

    public Long getOrgCarId () {
        return orgCarId;
    }

    public void setOrgCarId (Long orgCarId) {
        this.orgCarId = orgCarId;
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
}