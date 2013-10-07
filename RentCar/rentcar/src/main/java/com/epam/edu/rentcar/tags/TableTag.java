package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarStatusDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;

public class TableTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		PostgreCarDao carDao = new PostgreCarDao();
		List<Car> cars;
		try {
			cars = carDao.getAll(ConnectionPool.getInstance().getConnection());
			if (cars != null) {
				out.println("<table>");
				int i=0;
				for (Car car : cars) {
					i++;
					out.println((i % 2)==0 ? "<tr class='row2'>":"<tr>");
					out.println("<td>");
					out.println(car.getId());
					out.println("</td>");
					out.println("<td>");
					out.println(car.getMark());
					out.println("</td>");
					out.println("<td>");
					out.println(car.getModel());
					out.println("</td>");
					out.println("</tr>");
				}
				out.println("</table>");

			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
