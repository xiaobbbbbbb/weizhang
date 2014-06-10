package com.ecarinfo.traffic.utils;

import java.util.HashMap;

/**
 * 封装前端ajax响应数据
 * @author zhanglu
 *
 */
public class AjaxResponse {
	public static final String SUCCESS = "Y";
	public static final String FAIL = "N";
	private String succ = "Y";
	private String desc;
	private String code;
	private Object jsonData;
	private String html;
	
	
	public AjaxResponse() {
		super();
	}
	
	
	public AjaxResponse(String succ) {
		super();
		this.succ = succ;
	}

	public AjaxResponse(String succ, String[] jsonKeys, Object[] jsonValues) {	
		if (jsonKeys!=null && jsonValues!=null 
				&& jsonKeys.length>0 && jsonValues.length>0 
				&& jsonKeys.length<=jsonValues.length) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < jsonKeys.length; i++) {
				map.put(jsonKeys[i], jsonValues[i]);
			}
			jsonData = map;
		}
		this.succ = succ;
	}

	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getJsonData() {
		return jsonData;
	}
	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	
}
