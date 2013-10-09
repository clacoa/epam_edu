package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;

public class LogoutCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String redirect = "/error.jsp";
		request.getSession().setAttribute("user", null);
		redirect="/index.jsp";
		return redirect;
	}

}
