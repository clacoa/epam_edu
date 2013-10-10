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
import com.epam.edu.rentcar.model.CarFilter;

public class CarsTable extends SimpleTagSupport {

	private CarFilter filter;
	private String id;
	private PostgreCarDao carDao = new PostgreCarDao();
	private PostgreOrderDao orderDao = new PostgreOrderDao();
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar calendar = Calendar.getInstance();
	public final static String SPLITTER = "~";


	public void setFilter(CarFilter filter) {
		this.filter = filter;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void doTag() throws JspException, IOException {
		try {
			Connection conn = ConnectionPool.getInstance().getConnection();
			printFilter(conn);
			printTable(createTableData(conn));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<Car> createTableData(Connection conn) {

		carDao = new PostgreCarDao();
		orderDao = new PostgreOrderDao();

		List<Car> cars = null;
		List<Car> filterList = null;

		cars = carDao.getAll(conn);
		for (Car car : cars) {
			Order order = orderDao.getCurrentOrder(conn, car);
			if (order != null) {
				calendar.setTime(order.getDateTo());
				calendar.add(Calendar.DATE, 1);
				car.setAvailableDate(calendar.getTime());
			}
		}

		if ((cars != null) && (filter != null)) {
			if (!filter.getModel().equals("")) {
				filterList = new ArrayList<Car>();
				for (Car car : cars) {
					if (car.getModel().equals(filter.getModel())) {
						filterList.add(car);
					}
				}
			}
			if (filterList != null) {
				cars = filterList;
			}
		}
		return cars;

	}

	private List<String> ModelFilter(Connection conn) {
		List<String> models = new ArrayList<String>();

		models = carDao.getDistinct(conn, PostgreCarDao.MARK,
				PostgreCarDao.MODEL, SPLITTER);
		return models;

	}

	private void printTable(List<Car> cars) throws IOException {
		JspWriter out = getJspContext().getOut();
		if (cars != null) {
			out.println("<table style='width:100%'>");
			int i = 0;
			for (Car car : cars) {
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
				String action = printCarAction(car);
				if (action != null) {
					out.print(action);
				}
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

		}
	}

	private String printCarAction(Car car) {
		String action = null;
		if (car.getStatus().getId() == 2L) {
			action = "<div class='button' onclick='checkAndPost()'>"
					+ " <img src='./Images/donate.png' height='100%' align='left' />"
					+ " Reserve </div>";
		} else if (car.getStatus().getId() == 1L) {
			if (car.getAvailableDate() != null) {
				action = "Available from "
						+ format.format(car.getAvailableDate()).toString();
			}
		} else if (car.getStatus().getId() == 3L) {
			action = "Sorry, car is not available";
		}
		return action;
	}

	private void printModelFilter(List<String> models) throws IOException {

		JspWriter out = getJspContext().getOut();
		out.println("<select id='modelSelect' name='modelSelect'>");
		out.println("<option value=''>Select model</option>");
		if (models != null) {
			for (String model : models) {
				out.println("<option value='" + model + "'>"
						+ model.replace(SPLITTER, " ") + "</option>");
			}
		}
		out.println("</select>");
	}

	private void printCostFilter() throws IOException {

		JspWriter out = getJspContext().getOut();
		out.println("<select name='costSelect'>");
		out.println("<option value=''>Select cost</option>");
		out.println("<option value='0"+SPLITTER+"10000'>0-10000</option>");
		out.println("<option value='10000"+SPLITTER+"30000'>10000-30000</option>");
		out.println("<option value='30000"+SPLITTER+"50000'>30000-50000</option>");
		out.println("<option value='50000"+SPLITTER+"10000000'>more then 50000</option>");
		out.println("</select>");
	}

	private void printDescriptionFilter() throws IOException {

		JspWriter out = getJspContext().getOut();
		out.println("<input type='text' name='descriptionFilter' value='Enter any description'>");
	}

	private void printSearchButton() throws IOException {
		JspWriter out = getJspContext().getOut();
		String params="";
		//out.println("<div class='button' onclick=\"post_to_url_params('searchcar.controller','GET')\"> <img src='./Images/search.png' height='100%' align='left'> Search</div>");
	}

	private void printFilter(Connection conn) throws IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<div style='display: inline-block;'>");
		printModelFilter(ModelFilter(conn));
		printCostFilter();
		printDescriptionFilter();
		printSearchButton();
		out.println("</div>");

	}
}
