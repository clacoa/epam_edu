package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.service.LoginChecker;

public class RegistrationCommand implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String redirect = "Registration.jsp";
		String msg = null;
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String passport = request.getParameter("passport");

		int result = LoginChecker.userRegister(email, nickName, password,
				passwordRepeat, firstName, lastName, passport);

		switch (result) {
		case 0:
			msg = "Введенные пароли не совпадают";
			break;
		case 1:
			msg = "Данный email уже зарегистрирован в базе";
			break;
		case 2:
			msg = "Пользователь с такими паспортными данными уже зарегистрирован в базе";
			break;
		case 3:
			request.setAttribute("user", LoginChecker.checkUser(email));
			redirect = "index.jsp";
			break;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("sendRedirect", redirect);

	}

}
