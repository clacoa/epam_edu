package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;

public class LanguageCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		request.getSession().setAttribute("language", request.getParameter("language"));
		String redirect = "/index.jsp";
		Object prevUrl = request.getSession().getAttribute("prevUrl");
		if (prevUrl != null) {
			redirect = prevUrl.toString();
		}
		return redirect;
	}

}
