package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TQueryTraffic;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  查询和违章记录关联表
*
**/
@Entity(table=TQueryTraffic.class)
public class QueryTraffic implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Long queryId;//查询id
	private Long trafficId;//关联的违章
	private Date createTime;//创建时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getQueryId () {
        return queryId;
    }

    public void setQueryId (Long queryId) {
        this.queryId = queryId;
    }

    public Long getTrafficId () {
        return trafficId;
    }

    public void setTrafficId (Long trafficId) {
        this.trafficId = trafficId;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}