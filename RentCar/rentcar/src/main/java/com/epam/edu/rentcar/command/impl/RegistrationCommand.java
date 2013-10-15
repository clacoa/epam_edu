package com.epam.edu.rentcar.command.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.service.LoginChecker;

public class RegistrationCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(RegistrationCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String redirect = "/index.jsp";
		String msg = null;
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String passport = request.getParameter("passport");
		Connection conn = null;
		
		try {
			conn = ConnectionPool.getInstance().getConnection();
			int result = LoginChecker.userRegister(conn, email, nickName, password,
					passwordRepeat, firstName, lastName, passport);
			switch (result) {
			case 0:
				msg = "Введенные пароли не совпадают";
				redirect = "/registration.jsp";
				break;
			case 1:
				msg = "Данный email уже зарегистрирован в базе";
				redirect = "/registration.jsp";
				break;
			case 2:
				msg = "Пользователь с такими паспортными данными уже зарегистрирован в базе";
				redirect = "/registration.jsp";
				break;
			case 3:
				request.getSession().setAttribute("user", LoginChecker.checkUser(conn,email));
				Object prevUrl = request.getSession().getAttribute("prevUrl");
				if (prevUrl != null) {
					redirect = prevUrl.toString();
				}
				break;
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					LOG.error(e1.getMessage(), e1);
				}
		}
		
		request.setAttribute("msg", msg);
		return redirect;

	}

}
