package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryTaskDetail;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TQueryTaskDetail.class)
public class QueryTaskDetail implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Integer queryTaskId;
	private Integer totalCount = 0;//总查询数
	private Integer validCount = 0;//有效查询数
	private Integer failCount = 0;//失败查询数
	private Integer trafficCount = 0;//本次任务的违章数
	private Date startTime;//查询开始时间
	private Date endTime;//查询结束时间
	private Date createTime;

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

    public Integer getTotalCount () {
        return totalCount;
    }

    public void setTotalCount (Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getValidCount () {
        return validCount;
    }

    public void setValidCount (Integer validCount) {
        this.validCount = validCount;
    }

    public Integer getFailCount () {
        return failCount;
    }

    public void setFailCount (Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getTrafficCount () {
        return trafficCount;
    }

    public void setTrafficCount (Integer trafficCount) {
        this.trafficCount = trafficCount;
    }

    public Date getStartTime () {
        return startTime;
    }

    public void setStartTime (Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime () {
        return endTime;
    }

    public void setEndTime (Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}