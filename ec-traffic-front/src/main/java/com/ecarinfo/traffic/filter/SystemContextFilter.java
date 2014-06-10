package com.ecarinfo.traffic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;

public class SystemContextFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try {
			int pageOffset = 1;
			String sort = req.getParameter("sort");
			String order = req.getParameter("order");
			String offset = req.getParameter("page");

			if (StringUtils.isNotEmpty(offset)) {
				pageOffset = Integer.parseInt(offset);
			}

			SystemContext.setOrder(order);
			SystemContext.setSort(sort);
			SystemContext.setPageOffset(pageOffset);
			chain.doFilter(req, resp);
		} finally {
			SystemContext.removePageOffset();
			SystemContext.removeOrder();
			SystemContext.removeSort();
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}
}
