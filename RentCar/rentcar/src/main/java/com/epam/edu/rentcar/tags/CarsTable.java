package com.epam.edu.rentcar.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.CarFilter;
import com.epam.edu.rentcar.service.tagservice.CarsTagService;

public class CarsTable extends SimpleTagSupport {

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
		List<CarData> carDataList = cts.createTableData(filter, locale);
		printer.printTable(out, carDataList);
	}

}
