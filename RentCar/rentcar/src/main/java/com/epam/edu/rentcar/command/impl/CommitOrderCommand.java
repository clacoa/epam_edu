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
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.model.OrderData;
import com.epam.edu.rentcar.service.LoginChecker;

public class CommitOrderCommand implements Command {

	private static Logger LOG = Logger.getLogger(CommitOrderCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String redirect = "/index.jsp";

		OrderData orderData = (OrderData) request.getSession().getAttribute("orderData");
		User user = (User)request.getSession().getAttribute("user");
		
		if (orderData != null && orderData.getCarData() != null && user!=null) {
			redirect = "/orders.jsp";
			Connection conn = null;
			try {
				conn = ConnectionPool.getInstance().getConnection();
				conn.setAutoCommit(false);
				
				PostgreCarDao carDao = new PostgreCarDao();
				PostgreOrderDao orderDao = new PostgreOrderDao();

				Car car=carDao.get(conn, orderData.getCarData().getId());
				Order order = new Order();
				order.setCar(car);
				order.setDateFrom(orderData.getDateFrom());
				order.setDateTo(orderData.getDateTo());
				order.setOrderCost(orderData.getOrderCost());
				order.setStatus(new Status(1L));
				order.setUser(user);
				car.setStatus(new Status(1L));
				carDao.saveOrUpdate(conn, car);
				orderDao.saveOrUpdate(conn, order);
				conn.commit();	
				LOG.info(user+" commited order "+order);
				request.getSession().setAttribute("orderData", null);
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
