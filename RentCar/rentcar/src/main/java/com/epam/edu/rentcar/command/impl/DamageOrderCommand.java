package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;

public class DamageOrderCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(DamageOrderCommand.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String redirect = "/error.jsp";
		Object prevUrl = request.getSession().getAttribute("prevUrl");
		if (prevUrl != null) {
			redirect = prevUrl.toString();
		}
		if (request.getParameter("orderid") != null) {
			redirect = "/damage.jsp";
			Long orderId = Long.valueOf((request.getParameter("orderid")));
			request.getSession().setAttribute("orderId", orderId);
		}
		return redirect;
	}

}
