package com.epam.edu.rentcar.command.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.Status;

public class CommitDamageOrderCommand implements Command {

	private static Logger LOG = Logger.getLogger(CommitDamageOrderCommand.class);
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String redirect = "/error.jsp";
		if (request.getSession().getAttribute("orderId") != null && request.getParameter("addcost")!=null) {
			redirect = "/orders.jsp";
			Long orderId=Long.valueOf(request.getSession().getAttribute("orderId").toString());
			Double addCost = Double.valueOf((request.getParameter("addcost")));
			Connection conn = null;
			try {
				conn = ConnectionPool.getInstance().getConnection();
				conn.setAutoCommit(false);
				PostgreOrderDao orderDao = new PostgreOrderDao();
				PostgreCarDao carDao = new PostgreCarDao();
				Order order = orderDao.get(conn, orderId);
				order.setAddCost(addCost);
				Car car=carDao.get(conn, order.getCar().getId());
				order.setStatus(new Status(3L));
				car.setStatus(new Status(3L));
				orderDao.saveOrUpdate(conn, order);
				orderDao.addCostUpdate(conn, order);
				carDao.saveOrUpdate(conn, car);
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
			request.getSession().setAttribute("orderId", null);
		}
		return redirect;
	}

}
