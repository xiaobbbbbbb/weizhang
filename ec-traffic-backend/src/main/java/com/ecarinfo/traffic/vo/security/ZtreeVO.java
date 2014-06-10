package com.ecarinfo.traffic.vo.security;

/**
 * @Description: 树的数据传输对象
 * @Date 2012-11-6
 * @Version V1.0
 */

public class ZtreeVO {
	private Object id;

	private String name;

	private Boolean isParent = false;

	private String url;

	private String target;

	private String iconSkin;

	private Object pId; // 资源父节点
	private boolean open; // 是否是有父节点,有则为true
	private boolean checked = false;// 当前的checkbox是否是选中状态

	private String type; // 自定义属性

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public Object getpId() {
		return pId;
	}

	public void setpId(Object pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ZtreeDto [id=" + id + ", name=" + name + ", isParent=" + isParent + ", url=" + url + ", target=" + target + ", iconSkin=" + iconSkin
				+ ", pId=" + pId + ", open=" + open + ", checked=" + checked + ", type=" + type + "]";
	}
}
