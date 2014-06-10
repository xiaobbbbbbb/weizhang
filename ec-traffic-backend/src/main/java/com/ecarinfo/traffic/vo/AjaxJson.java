package com.ecarinfo.traffic.vo;

import org.apache.commons.lang.StringUtils;

/**
 * @Description: JSON对象
 * @Date 2012-11-6
 * @Version V1.0
 */

public class AjaxJson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否成功
	 */
	private boolean success = true;

	/**
	 * 提示信息
	 */
	private String msg;

	/**
	 * 其他信息
	 */
	private Object obj = null;

	public String getMsg() {
		if (this.success) {
			return "操作成功！";
		} else {
			if (StringUtils.isEmpty(msg)) {
				return "操作失败！";
			} else {
				return msg;
			}
		}
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}