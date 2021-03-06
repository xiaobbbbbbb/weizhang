package com.ecarinfo.traffic.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ecarinfo.common.utils.PropUtil;
import com.ecarinfo.db4j.ctx.DB;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.logic.LogUtil;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.tables.TOrgUserInfo;

/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 */

public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	@Resource
	public DB db;
	
	@Resource
	public LogUtil logUtil;
	
	public static int PAGE_SIZE = 20;

	public static int PAGE_MAX_SIZE = 30;

	// 重定向
	public static String REDIRECT = "redirect:";

	// 请求转发
	public static String FORWARD = "forward:";
	private static TOrgUserInfo tuser = Tables.get(TOrgUserInfo.class);
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	public OrgUserInfo getUser(HttpServletRequest request) {
		OrgUserInfo orgUserInfo = (OrgUserInfo)request.getSession().getAttribute("user");
		return orgUserInfo;
	}
	/**
	 * 内容输出
	 */
	public static void printAjax(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error("printAjax内容输出异常");
		}
	}

	public HttpServletResponse download(String path, String fileName, HttpServletResponse response) {
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			if (toClient != null) {
				toClient.close();
			}
		} catch (Exception ex) {
			logger.error("文件下载异常", ex);
		}
		return response;
	}
}
