package com.ecarinfo.traffic.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;

public class WebUtils {
	private static Logger logger = Logger.getLogger(WebUtils.class);
	public static final String SESSION_KEY_USER = "user";
	public static final String SESSION_KEY_ORG = "org";
	private static final String PARAM_CURRENT_PAGE = "page";
	private static final String PARAM_PAGE_SIZE = "rows";
	private static final String PAGE_HOLDER = "pageHolder";
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	
	public static OrgUserInfo getUser(HttpServletRequest request) {
		return (OrgUserInfo) request.getSession().getAttribute(SESSION_KEY_USER);
	}
	
	public static String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	       }      
	       return ip;      
	}  
	
	public static List<Integer> getPages(ECPage<?> ecpage) {
		return getPages((int)ecpage.getCurrentPage(), (int)ecpage.getTotalPage());
	}
	
	public static List<Integer> getPages(int currentPage, int pageCount) {
		List<Integer> pages = new ArrayList<Integer>();
		
		List<Integer> pagesBefore = new ArrayList<Integer>();
		List<Integer> pagesAfter = new ArrayList<Integer>();
		
		int indexBefore = currentPage-1;
		int step = 0;
		while (indexBefore>1 && step<3) {
			pagesBefore.add(indexBefore);
			indexBefore--;
			step++;
		}
		int indexAfter = currentPage+1;
		step = 0;
		while (indexAfter<pageCount && step<4) {
			pagesAfter.add(indexAfter);
			indexAfter++;
			step++;
		}
		while ((pagesBefore.size() + pagesAfter.size() < 7) && indexBefore>1) {
			pagesBefore.add(indexBefore);
			indexBefore--;
		}
		while ((pagesBefore.size() + pagesAfter.size() < 7) && indexAfter<pageCount) {
			pagesAfter.add(indexAfter);
			indexAfter++;
		}		
		Collections.reverse(pagesBefore);
		pages.addAll(pagesBefore);
		if (currentPage>1 && currentPage<pageCount) {
			pages.add(currentPage);
		}		
		pages.addAll(pagesAfter);		
		return pages;
	}
	
	public static String getParam(HttpServletRequest request, String key, String defaultValue) {
		String value = (String) request.getParameter(key);
		if (StringUtils.isEmpty(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public static int getParamAsInt(HttpServletRequest request, String key, int defaultValue) {
		String value = (String) request.getParameter(key);
		if (StringUtils.isEmpty(value)) {
			return defaultValue;
		} else {
			int intValue = defaultValue;
			try {
				intValue = Integer.parseInt(value);
			} catch (Exception e) {
				logger.error("getParamAsInt error:" + e.getMessage());
			}
			return intValue;
		}
	}
	
	public static Float getParamAsFloat(HttpServletRequest request, String key, Float defaultValue) {
		String value = (String) request.getParameter(key);
		if (StringUtils.isEmpty(value)) {
			return defaultValue;
		} else {
			Float intValue = defaultValue;
			try {
				intValue = Float.parseFloat(value);
			} catch (Exception e) {
				logger.error("getParamAsFloat error:" + e.getMessage());
			}
			return intValue;
		}
	}
	
	public static Long getParamAsLong(HttpServletRequest request, String key, Long defaultValue) {
		String value = (String) request.getParameter(key);
		if (StringUtils.isEmpty(value)) {
			return defaultValue;
		} else {
			Long intValue = defaultValue;
			try {
				intValue = Long.parseLong(value);
			} catch (Exception e) {
				logger.error("getParamAsInt error:" + e.getMessage());
			}
			return intValue;
		}
	}
	
	public static Boolean getParamAsBoolean(HttpServletRequest request, String key, Boolean defaultValue) {
		Boolean value = defaultValue;
		if (request.getParameter(key)!=null) {
			String v = request.getParameter(key);
			if (v.equals("Y") || v.equals("yes") || v.equals("YES") || v.equals("true") || v.equals("TRUE")) {
				value = true;
			} else if (v.equals("N") || v.equals("no") || v.equals("NO") || v.equals("false") || v.equals("FALSE")){
				value = false;
			}
		}
		return value;		
	}
	
	public static final String getParamCh(HttpServletRequest request,String parameterName, String defaultValue) {
		String value = request.getParameter(parameterName);
		if(StringUtils.isEmpty(value)){
			return defaultValue;
		}
		value  = encodingChars(request, value);
		//else 默认为utf8
		return value;
	}
	public static final String getValueCh(HttpServletRequest request,String parameterName) {
		String value = request.getParameter(parameterName);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		value  = encodingChars(request, value);
		//else 默认为utf8
		return value;
	}
	
	public static final String getValueChParam(HttpServletRequest request, String parameter) {
		String value = parameter;
		if(StringUtils.isEmpty(value)){
			return null;
		}
		value  = encodingChars(request, value);
		//else 默认为utf8
		return value;
	}
	
	public static String encodingChars(HttpServletRequest request,String value) {
		String result =value;			
		try {
			result = new String(value.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}	
		return result;
	}
	
	public static void printAjax(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public static void printAjax(HttpServletResponse response, AjaxResponse resp) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(JsonUtils.Object2JsonString(resp));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public static void setSession(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
		
	}
	
	
	
	
	public static void setPageHolder(HttpServletRequest request, ECPage<?> pageHolder) {
		request.setAttribute(PAGE_HOLDER, pageHolder);
		List<Integer> pages = getPages(pageHolder);
		request.setAttribute("pageList", pages);
		if (pages.size()>0 && pages.get(0)>2) {
			request.setAttribute("showDotsBefore", true);
		}
		if (pages.size()>0 && pages.get(pages.size()-1) < pageHolder.getTotalPage()-1) {
			request.setAttribute("showDotsAfter", true);
		}
	}
}
