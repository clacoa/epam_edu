package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;

public class ClearCurrentCarCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("orderData", null);
		String redirect = "/ordering.jsp";
		return redirect;
	}

}
