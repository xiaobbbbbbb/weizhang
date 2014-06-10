package com.ecarinfo.traffic.persist.po;
import com.ecarinfo.traffic.persist.tables.TAdminInfo;
import com.ecarinfo.db4j.annotation.Entity;
import java.io.Serializable;
import java.util.Date;

/**
*  大运营管理员
*
**/
@Entity(table=TAdminInfo.class)
public class AdminInfo implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;
	private String name;//用户姓名
	private String nickName;//昵称
	private String password;
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

    public String getNickName () {
        return nickName;
    }

    public void setNickName (String nickName) {
        this.nickName = nickName;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
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