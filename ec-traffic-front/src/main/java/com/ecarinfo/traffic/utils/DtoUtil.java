package com.ecarinfo.traffic.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DtoUtil {
	public static final String decode(String value) throws UnsupportedEncodingException {
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		value = value.trim();
		return URLDecoder.decode(value, "utf-8");
	}

	/**
	 * 中文的转码
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String incode(String value) throws UnsupportedEncodingException {
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		return new String(value.trim().getBytes("ISO8859-1"), "UTF-8");
	}

	public static final Integer getIntParam(String value) {
		if (value == null) {
			return null;
		}
		if (value.trim().length() == 0) {
			return null;
		}
		try {
			Integer intVal = Integer.parseInt(value);
			return intVal;
		} catch (NumberFormatException e) {
			throw new RuntimeException(value + "不是一个数字。");
		}
	}
}
