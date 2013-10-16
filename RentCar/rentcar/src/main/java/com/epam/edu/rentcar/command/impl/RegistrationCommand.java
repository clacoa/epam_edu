package com.epam.edu.rentcar.command.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.service.LoginChecker;
import com.epam.edu.rentcar.util.CommonBundle;

public class RegistrationCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(RegistrationCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String redirect = "/registration.jsp";
		String msg = null;
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String passport = request.getParameter("passport");
		Connection conn = null;
		String language = request.getSession().getAttribute("language")!=null ? request.getSession().getAttribute("language").toString():"ru";
		Locale locale = new Locale(language);
		
		try {
			conn = ConnectionPool.getInstance().getConnection();
			int result = LoginChecker.userRegister(conn, email, nickName, password,
					passwordRepeat, firstName, lastName, passport);
			switch (result) {
			case 0:
				msg = CommonBundle.getProperty("registration.msg.password",	locale);
				break;
			case 1:
				msg = CommonBundle.getProperty("registration.msg.email",	locale);
				break;
			case 2:
				msg = CommonBundle.getProperty("registration.msg.passport",	locale);
				break;
			case 3:
				request.getSession().setAttribute("user", LoginChecker.checkUser(conn,email));
				LOG.info(LoginChecker.checkUser(conn,email)+" registred");
				redirect ="/index.jsp";
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
