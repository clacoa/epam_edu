package com.epam.edu.rentcar.command.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.OrderData;

public class CarBookingCommand implements Command {

	private static Logger LOG = Logger.getLogger(CarBookingCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		Car car = null;
		OrderData orderData = null;
		String redirect = "/ordering.jsp";
		PostgreCarDao carDao = new PostgreCarDao();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			car = carDao.get(conn, Long.valueOf(request.getParameter("carid")));
			CarData carData = new CarData(car);
			orderData=new OrderData();
			orderData.setCarData(carData);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					LOG.error(e1.getMessage(), e1);
				}
		}
		request.getSession().setAttribute("orderData", orderData);
		return redirect;
	}

}
