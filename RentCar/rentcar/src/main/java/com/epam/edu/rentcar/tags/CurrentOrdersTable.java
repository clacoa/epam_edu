package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.service.tagservice.CarsTagService;
import com.epam.edu.rentcar.service.tagservice.CurrentOrdersTagService;


public class CurrentOrdersTable extends SimpleTagSupport {

	private static Logger LOG = Logger.getLogger(CarsTable.class);

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Object language = getJspContext().getAttribute("language", PageContext.SESSION_SCOPE);
		Locale locale = language != null ? new Locale(language.toString()): new Locale("ru");
		CurrentOrdersTagService tagService = new CurrentOrdersTagService();
		TagPrinter printer = new TagPrinter();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			List<Order> orderDataList = tagService.createOrdersTableData(conn, user, locale);
			printer.printOrderTable(out, orderDataList);
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
