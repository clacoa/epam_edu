package com.epam.edu.rentcar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet Filter implementation class LocalFilter
 */
public class LocalFilter implements Filter {

	public class CustomHttpServletRequest extends HttpServletRequestWrapper {

		private Map<String, String> customHeaderMap = null;

		public CustomHttpServletRequest(HttpServletRequest request) {
			super(request);
			customHeaderMap = new HashMap<String, String>();
		}

		public void addHeader(String name, String value) {
			customHeaderMap.put(name, value);
		}

		@Override
		public String getParameter(String name) {
			String paramValue = super.getParameter(name); // query Strings
			if (paramValue == null) {
				paramValue = customHeaderMap.get(name);
			}
			return paramValue;
		}

	}

	/**
	 * Default constructor.
	 */
	public LocalFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		CustomHttpServletRequest req = new CustomHttpServletRequest(
				(HttpServletRequest) request);
		req.addHeader("Accept-Language", "ru");
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
