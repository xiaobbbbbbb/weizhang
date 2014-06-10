package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TLogInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  
*
**/
@Entity(table=TLogInfo.class)
public class LogInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Long id;
	private Integer operatorId;//操作员id
	private String operatorName;//操作员名称
	private String operatorType;//操作员类型（能确定operator_id所在的表）
	private String content;//日志内容
	private String remark;//备注
	private Date createTime;//创建时间

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Integer getOperatorId () {
        return operatorId;
    }

    public void setOperatorId (Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName () {
        return operatorName;
    }

    public void setOperatorName (String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorType () {
        return operatorType;
    }

    public void setOperatorType (String operatorType) {
        this.operatorType = operatorType;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }
}