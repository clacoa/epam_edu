package com.epam.edu.rentcar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.model.OrderData;

/**
 * Servlet Filter implementation class OrderFilter
 */
public class OrderFilter implements Filter {

    /**
     * Default constructor. 
     */
    public OrderFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = ((HttpServletResponse) response);
		OrderData orderData = (OrderData) req.getSession().getAttribute("orderData");
		if (orderData == null)  {
			res.sendRedirect("./ordering.jsp");
		} else if (!orderData.isFilled()){
			res.sendRedirect("./ordering.jsp");
		}
		else{
			chain.doFilter(request, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
