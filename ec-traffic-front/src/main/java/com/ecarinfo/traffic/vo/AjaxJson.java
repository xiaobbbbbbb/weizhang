package com.ecarinfo.traffic.vo;

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
	private String msg = "操作成功！";

	/**
	 * 其他信息
	 */
	private Object obj = null;

	public String getMsg() {
		return msg;
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