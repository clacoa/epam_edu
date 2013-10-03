package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.service.LoginChecker;

public class LogoutCommand implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String redirect = "Error.jsp";
		request.getSession().setAttribute("user", null);
		redirect="index.jsp";
		request.setAttribute("sendRedirect", redirect);
	}

}
