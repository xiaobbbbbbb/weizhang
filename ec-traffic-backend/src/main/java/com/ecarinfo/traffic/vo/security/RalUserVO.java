package com.ecarinfo.traffic.vo.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ecarinfo.traffic.persist.po.RalResource;
import com.ecarinfo.traffic.persist.po.RalRole;
import com.ecarinfo.traffic.persist.po.RalUser;

public class RalUserVO extends RalUser implements Serializable {

	private static final long serialVersionUID = -4521429274869829479L;

	private String roleIds;// 用户角色ID

	private String roleNames;// 角色名称

	private Set<RalRole> roleSet = new HashSet<RalRole>();

	private Set<RalResource> resourceSet = new HashSet<RalResource>();

	// 授权的URL
	public Set<String> permissions = new HashSet<String>();

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public Set<RalRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<RalRole> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<RalResource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<RalResource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	public Set<String> getPermissions() {
		Set<String> urlPermissions = new HashSet<String>();
		for (RalResource res : this.resourceSet) {
			String[] urls = res.getUrl().split(",");
			for (String _url : urls) {
				urlPermissions.add(_url);
			}
		}
		return urlPermissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
}
