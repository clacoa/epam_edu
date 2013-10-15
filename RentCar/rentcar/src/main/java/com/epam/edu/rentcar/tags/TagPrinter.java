package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.PrintElement;

public class TagPrinter {
	private SimpleDateFormat format = new SimpleDateFormat(
			"dd.MM.yyyy");
	public void printSelect(JspWriter out, String name,
			List<PrintElement> elements, String title) throws IOException {

		out.println(title);
		out.println("<br/><select id='" + name + "' name='" + name + "'>");
		out.println("<option value=''></option>");
		if (elements != null) {
			for (PrintElement element : elements) {
				out.println("<option value='" + element.getValue() + "'>"
						+ element.getText() + "</option>");
			}
		}
		out.print("</select>");

	}

	public void printInputText(JspWriter out, String name, String title)
			throws IOException {

		out.println(title);
		out.println("<br/><input type='text' name='" + name + "' value=''>");
	}

	public void printCarTable(JspWriter out, List<CarData> cars)
			throws IOException {
		if (cars != null) {
			out.println("<table style='width:100%'>");
			int i = 0;
			for (CarData car : cars) {
				i++;
				out.println((i % 2) == 0 ? "<tr class='row2'>" : "<tr>");
				out.println("<td>");
				out.println(i);
				out.println("</td>");

				out.println("<td>");
				out.println(car.getModelName());
				out.println("</td>");

				out.println("<td>");
				out.println(car.getDescription());
				out.println("</td>");

				out.println("<td>");
				out.println(car.getCost());
				out.println("</td>");

				out.println("<td>");

				if (car.getAction() != null) {
					out.print(car.getAction());
				}
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

		}
	}

	public void printOrderTable(JspWriter out, List<Order> orders)
			throws IOException {
		if (orders != null) {
			out.println("<table style='width:100%'>");
			int i = 0;
			for (Order order : orders) {

				i++;
				out.println((i % 2) == 0 ? "<tr class='row2'>" : "<tr>");
				out.println("<td>");
				out.println(i);
				out.println("</td>");

				out.println("<td>");
				out.println(order.getCar().getCarInfo());
				out.println("</td>");

				out.println("<td>");
				out.println(order.getCar().getCost().toString());
				out.println("</td>");

				out.println("<td>");
				out.println(format.format(order.getDateFrom()));
				out.println("</td>");

				out.println("<td>");
				out.println(format.format(order.getDateTo()));
				out.println("</td>");

				out.println("<td>");
				out.println(order.getOrderCost().toString());
				out.println("</td>");
				if (order.getAddCost() != null) {
					out.println("<td>");
					out.println(order.getAddCost().toString());
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");

		}
	}
}
