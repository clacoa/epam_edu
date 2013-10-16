package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspWriter;

import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.PrintElement;
import com.epam.edu.rentcar.util.CommonBundle;

public class TagPrinter {
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

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

	public void printOrderTable(JspWriter out, List<Order> orders, User user,
			Locale locale) throws IOException {
		if (orders != null) {
			out.println("<table style='width:100%'>");
			out.println("<tr style='text-align: center;'>");
			out.println("<td>");
			out.println("</td>");

			if (user.getRole().getId() == 2L) {
				out.println("<td>");
				out.println(CommonBundle.getProperty("orders.user.firstname",locale));
				out.println("</td>");
				out.println("<td>");
				out.println(CommonBundle.getProperty("orders.user.lastname",locale));
				out.println("</td>");
				out.println("<td>");
				out.println(CommonBundle.getProperty("orders.user.passport",locale));
			}

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.car.model",locale));
			out.println("</td>");

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.car.cost",locale));
			out.println("</td>");

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.car.datefrom",locale));
			out.println("</td>");

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.car.dateto",locale));
			out.println("</td>");

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.order.cost",locale));
			out.println("</td>");

			out.println("<td>");
			out.println(CommonBundle.getProperty("orders.order.addcost",locale));
			out.println("</td>");

			if (user.getRole().getId() == 2L) {
				out.println("<td>");
				out.println("</td>");
			}
			out.println("</tr>");
			
			int i = 0;
			for (Order order : orders) {

				i++;
				out.println((i % 2) == 0 ? "<tr class='row2'>" : "<tr>");
				out.println("<td>");
				out.println(i);
				out.println("</td>");

				if (user.getRole().getId() == 2L) {
					out.println("<td>");
					out.println(order.getUser().getFirstName());
					out.println("</td>");
					out.println("<td>");
					out.println(order.getUser().getLastName());
					out.println("</td>");
					out.println("<td>");
					out.println(order.getUser().getPassport());
				}

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

				out.println("<td>");
				if (order.getAddCost() != null) {
					out.println(order.getAddCost().toString());
				}
				out.println("</td>");

				if (user.getRole().getId() == 2L) {
					out.println("<td>");
					out.println(adminControls(order, locale));
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");

		}
	}

	private String adminControls(Order order, Locale locale) {
		String action = "";
		String addAction = "";
		if (order.getStatus().getId() == 1L) {
			action = "<div class='button' onclick=\"post_to_url_params('paidorder.controller',{'orderid':"
					+ order.getId().toString()
					+ "},'POST')\">"
					+ " <img src='./Images/bank.png' height='100%' align='left' />"
					+ "&nbsp"
					+ CommonBundle.getProperty("orders.admin.paid", locale)
					+ "</div>";
			action += "&nbsp&nbsp<div class='button' onclick=\"post_to_url_params('rejectorder.controller',{'orderid':"
					+ order.getId().toString()
					+ "},'POST')\">"
					+ " <img src='./Images/refresh.png' height='100%' align='left' />"
					+ "&nbsp"
					+ CommonBundle.getProperty("orders.admin.reject", locale)
					+ "</div>";
		} else {
			action = "<div class='button' onclick=\"post_to_url_params('executeorder.controller',{'orderid':"
					+ order.getId().toString()
					+ "},'POST')\">"
					+ " <img src='./Images/check.png' height='100%' align='left' />"
					+ "&nbsp"
					+ CommonBundle.getProperty("orders.admin.execute", locale)
					+ "</div>";
			if (order.getStatus().getId() == 2L) {
				addAction = "&nbsp&nbsp<div class='button' onclick=\"post_to_url_params('damageorder.controller',{'orderid':"
						+ order.getId().toString()
						+ "},'POST')\">"
						+ " <img src='./Images/free-for-job.png' height='100%' align='left' />"
						+ "&nbsp"
						+ CommonBundle.getProperty("orders.admin.damaged",
								locale) + "</div>";
			}
		}
		return action + addAction;

	}
}
