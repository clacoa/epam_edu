package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.PageData;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.impl.LoginCommand;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.model.CarFilter;
import com.epam.edu.rentcar.model.PrintElement;
import com.epam.edu.rentcar.service.LoginChecker;
import com.epam.edu.rentcar.service.tagservice.CarsTagService;
import com.epam.edu.rentcar.util.CommonBundle;

public class CarsFilter extends SimpleTagSupport {
	
	String id;
	private static Logger LOG = Logger.getLogger(CarsFilter.class);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Object language = getJspContext().getAttribute("language",
				PageContext.SESSION_SCOPE);
		Locale locale = language != null ? new Locale(language.toString())
				: new Locale("ru");  
		CarsTagService cts = new CarsTagService();
		TagPrinter printer= new TagPrinter();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			List<PrintElement> modelSelect = cts.ModelFilter(conn);
			List<PrintElement> costSelect = cts.CostFilter();
			out.println("<table>");
			out.println("<tr style='text-align: center'>");
			out.println("<td>");
			printer.printSelect(out,"modelSelect", modelSelect, CommonBundle.getProperty("cars.filter.select.car", locale));
			out.println("</td>");
			out.println("<td>");
			printer.printSelect(out,"costSelect", costSelect, CommonBundle.getProperty("cars.filter.select.cost", locale));
			out.println("</td>");
			out.println("<td>");
			printer.printInputText(out,"descriptionFilter",CommonBundle.getProperty("cars.filter.select.description", locale));
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					LOG.error(e1.getMessage(), e1);
				}
		}
	}

}
