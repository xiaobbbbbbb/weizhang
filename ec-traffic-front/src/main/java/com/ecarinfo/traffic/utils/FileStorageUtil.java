package com.ecarinfo.traffic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ecarinfo.traffic.dto.Constant;


/**
 * @Description: 附件的存储路径类
 * @Date 2012-11-6
 * @Version V1.0
 */

public class FileStorageUtil {

	// 通用的返回路径的方法
	private static final String getFileStorage(HttpServletRequest request, String desc) {
		return request.getSession().getServletContext().getRealPath(Constant.FILE_STORAGE + "/" + desc);
	}

	// 通用的返回路径的方法
	public static final String getBaseFileStorage(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("");
	}

	/**
	 * 获取绝对的带日期路径
	 * 
	 * @param request
	 * @param desc
	 * @param simpleDateFormat
	 * @return
	 */
	public static final String getAbsoluteStorage(HttpServletRequest request, String desc, String simpleDateFormat) {
		return getFileStorage(request, desc + "/" + getDatePath(simpleDateFormat));
	}

	/**
	 * 获取绝对的不带日期路径
	 * 
	 * @param request
	 * @param desc
	 * @param simpleDateFormat
	 * @return
	 */
	public static final String getAbsoluteStorage(HttpServletRequest request, String desc) {
		return getFileStorage(request, desc);
	}

	/**
	 * 获取相对的不带日期路径
	 * 
	 * @param: request
	 * @return
	 */
	public static final String getOppositeStorage(HttpServletRequest request, String desc) {
		return getContextPath(request) + "/" + Constant.FILE_STORAGE + "/" + desc;
	}

	/**
	 * 获取相对的带日期路径
	 * 
	 * @param: request
	 * @return
	 */
	public static final String getOppositeStorage(HttpServletRequest request, String desc, String simpleDateFormat) {
		return getContextPath(request) + "/" + Constant.FILE_STORAGE + "/" + desc + "/" + getDatePath(simpleDateFormat);
	}

	/**
	 * 获取相对的带日期路径
	 * 
	 * @param: request
	 * @return
	 */
	public static final String getWebStorage(String desc, String simpleDateFormat) {
		return Constant.FILE_STORAGE + "/" + desc + "/" + getDatePath(simpleDateFormat);
	}

	public static final String getDatePath(String simpleDateFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(simpleDateFormat);
		return dateFormat.format(new Date());
	}

	/**
	 * 获取上下文路径
	 */
	public static String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
}
