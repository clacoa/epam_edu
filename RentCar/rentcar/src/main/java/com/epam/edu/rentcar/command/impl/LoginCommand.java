package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.service.LoginChecker;

public class LoginCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String redirect = "/error.jsp";

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (LoginChecker.checkUser(email) != null) {
			if (LoginChecker.checkLogin(email, password)) {
				request.getSession().setAttribute("user",
						LoginChecker.checkUser(email));
			} else {
				String msg = "Invalid Email/Password";
				request.setAttribute("msg", msg);
			}
		} else {
			String msg = "Invalid Email/Password";
			request.setAttribute("msg", msg);
		}
		Object prevUrl = request.getSession().getAttribute("prevUrl");
		if (prevUrl != null) {
			redirect = prevUrl.toString();
		}
		return redirect;
	}

}
