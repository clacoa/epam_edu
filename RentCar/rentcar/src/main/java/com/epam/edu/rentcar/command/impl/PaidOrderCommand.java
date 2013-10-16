package com.epam.edu.rentcar.command.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.Status;

public class PaidOrderCommand implements Command {

	private static Logger LOG = Logger.getLogger(PaidOrderCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String redirect = "/error.jsp";
		if (request.getParameter("orderid") != null) {
			redirect = "/orders.jsp";
			Long orderId = Long.valueOf((request.getParameter("orderid")));

			Connection conn = null;
			try {
				conn = ConnectionPool.getInstance().getConnection();
				conn.setAutoCommit(false);
				PostgreOrderDao orderDao = new PostgreOrderDao();
				Order order = orderDao.get(conn, orderId);
				order.setStatus(new Status(2L));
				orderDao.saveOrUpdate(conn, order);
				conn.commit();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				try {
					conn.rollback();
				} catch (SQLException e1) {
					LOG.error(e1.getMessage(), e1);
				}
			} finally {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e1) {
					LOG.error(e1.getMessage(), e1);
				}
			}
		}
		return redirect;
	}

}
