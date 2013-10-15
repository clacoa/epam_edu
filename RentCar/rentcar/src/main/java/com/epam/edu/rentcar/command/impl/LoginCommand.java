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

public class LoginCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(LoginCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String redirect = "/error.jsp";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String language = request.getSession().getAttribute("language")!=null ? request.getSession().getAttribute("language").toString():"ru";
		Locale locale = new Locale(language);
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			if (LoginChecker.checkUser(conn, email) != null) {
				if (LoginChecker.checkLogin(conn,email, password)) {
					request.getSession().setAttribute("user",
							LoginChecker.checkUser(conn,email));
				} else {
					String msg = CommonBundle.getProperty("errormsg",	locale);
					request.setAttribute("msg", msg);
				}
			} else {
				String msg = CommonBundle.getProperty("errormsg",	locale);
				request.setAttribute("msg", msg);
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

		Object prevUrl = request.getSession().getAttribute("prevUrl");
		if (prevUrl != null) {
			redirect = prevUrl.toString();
		}
		return redirect;
	}

}
