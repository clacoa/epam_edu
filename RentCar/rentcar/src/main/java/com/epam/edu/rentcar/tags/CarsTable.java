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
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.CarFilter;
import com.epam.edu.rentcar.model.PrintElement;
import com.epam.edu.rentcar.service.tagservice.CarsTagService;
import com.epam.edu.rentcar.util.CommonBundle;

public class CarsTable extends SimpleTagSupport {
	
	private static Logger LOG = Logger.getLogger(CarsTable.class);

	private CarFilter filter;
	private String id;

	public void setFilter(CarFilter filter) {
		this.filter = filter;
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
		TagPrinter printer = new TagPrinter();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			List<CarData> carDataList = cts.createCarsTableData(conn,filter, locale);
			printer.printCarTable(out, carDataList);
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
