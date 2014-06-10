package com.ecarinfo.traffic.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.utils.WebUtils;

public class LoginStatusCheckFilter implements Filter{
	private static Logger logger = Logger.getLogger(LoginStatusCheckFilter.class);
	private static Set<String> staticPosfixs = new HashSet<String>();
	static  {
		staticPosfixs.addAll(Arrays.asList("js", "css", "ico", "png", "jpg", "jpeg", "gif", "PGN", "JPG", "JPEG"));
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) resp;
	    String requestURI = request.getRequestURI();
	    logger.debug("[REQUEST_URI]:" + requestURI + " ,[PARAMAS]:" + getRequestParams(request));
	    
        if(requestURI.contains("/admin/login") || requestURI.contains("/admin/dologin") || isStaticResources(requestURI)){
            chain.doFilter(request, response);
            return;
        }
        
	    OrgUserInfo user = (OrgUserInfo) request.getSession().getAttribute(WebUtils.SESSION_KEY_USER);
	    if (user==null) {
	    	if (!"XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
	    		logger.debug("[=========to login page=========]");
		    	request.getRequestDispatcher("/admin/login").forward(request,response);
	    	} else {
	    		WebUtils.printAjax(response, "<div id='ajaxErrorStatus' succ='N' msg='会话超时'></div>");
	    	}	    	
	    	return;
	    }
	    chain.doFilter( req , resp );  
	}
	
	private boolean isStaticResources(String uri) {
		String posfix = null;
		int posDot = uri.lastIndexOf('.');
		if (posDot>0 && posDot+1<uri.length()) {
			posfix = uri.substring(posDot+1);
		}		
		if (posfix!=null && staticPosfixs.contains(posfix)) {
			return true;
		} 
		return false;
	}
	
	private String getRequestParams(HttpServletRequest request) { 
		@SuppressWarnings("rawtypes")
		Enumeration en = request.getParameterNames();
		StringBuffer buf = new StringBuffer();
		while(en.hasMoreElements()){
			String key = en.nextElement().toString();
			buf.append(String.format("|param[%s]:=%s", key,request.getParameter(key)));
		}
		return buf.toString();
	}
	
	@Override
	public void destroy() {
		
	}

}
