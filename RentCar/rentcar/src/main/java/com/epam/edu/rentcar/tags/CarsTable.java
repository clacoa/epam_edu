package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;

public class CarsTable extends SimpleTagSupport {
	private String mark;
	private String model;
	private String id;
	private PostgreCarDao carDao = new PostgreCarDao();
	private PostgreOrderDao orderDao = new PostgreOrderDao();
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar calendar = Calendar.getInstance();

	public void setId(String id) {
		this.id = id;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void doTag() throws JspException, IOException {
		carDao = new PostgreCarDao();
		orderDao = new PostgreOrderDao();

		List<Car> cars = null;
		List<Car> filterCar = null;
		try {
			Connection conn = ConnectionPool.getInstance().getConnection();
			cars = carDao.getAll(conn);
			for (Car car : cars) {
				Order order = orderDao.getCurrentOrder(conn, car);
				if (order != null) {
					calendar.setTime(order.getDateTo());
					calendar.add(Calendar.DATE, 1);
					car.setAvailableDate(calendar.getTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cars != null) {
			if (!mark.equals("")) {
				filterCar = new ArrayList<Car>();
				for (Car car : cars) {
					if (car.getMark().equals(mark)) {
						filterCar.add(car);
					}
				}
				if (filterCar != null) {
					cars = filterCar;
					filterCar = null;
				}
			}
			if (!model.equals("")) {
				filterCar = new ArrayList<Car>();
				for (Car car : cars) {
					if (car.getModel().equals(model)) {
						filterCar.add(car);
					}
				}
			}
			if (filterCar != null) {
				cars = filterCar;
				filterCar = null;
			}
		}
		print(cars);
	}

	private void print(List<Car> cars) throws IOException {
		JspWriter out = getJspContext().getOut();
		if (cars != null) {
			out.println("<table style='width:100%'>");
			int i = 0;
			for (Car car : cars) {
				String action = null;
				if (car.getStatus().getId() == 2L) {
					action = "<div class='button' onclick='checkAndPost()'>"
							+ " <img src='./Images/plus.png' height='100%' align='left' />"
							+ " Reserve </div>";
				} else if (car.getStatus().getId() == 1L) {
					if (car.getAvailableDate() != null) {
						action = "Available from "
								+ format.format(car.getAvailableDate())
										.toString();
					}
				} else if (car.getStatus().getId() == 3L) {
					action = "Sorry, car is not available";
				}
				i++;
				out.println((i % 2) == 0 ? "<tr class='row2'>" : "<tr>");
				out.println("<td>");
				out.println(i);
				out.println("</td>");
				out.println("<td>");
				out.println(car.getMark());
				out.println("</td>");
				out.println("<td>");
				out.println(car.getModel());
				out.println("</td>");
				out.println("<td>");
				out.println(car.getDescription());
				out.println("</td>");
				out.println("<td>");
				out.println(car.getCost());
				out.println("</td>");
				out.println("<td>");
				if (action != null) {
					out.print(action);
				}
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

		}
	}
}
