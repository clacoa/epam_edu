package com.epam.edu.rentcar.command.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.model.OrderData;

public class PrepareOrderCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(PrepareOrderCommand.class);
		
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String redirect = "/orders.jsp";
		String language = request.getSession().getAttribute("language")!=null ? request.getSession().getAttribute("language").toString():"ru";

		String datePickerFrom = request.getParameter("datepickerfrom");
		String datePickerTo = request.getParameter("datepickerto");
		
		OrderData orderData = (OrderData) request.getSession().getAttribute("orderData");

		orderData.setFormatedDateFrom(language, datePickerFrom);
		orderData.setFormatedDateTo(language, datePickerTo);

	    Calendar c = Calendar.getInstance();
	    if (orderData.getDateFrom()!=null && orderData.getDateTo()!=null && orderData.getCarData()!=null ){
	    	orderData.setOrderCost(((orderData.getDateTo().getTime()-orderData.getDateFrom().getTime())/1000/3600/24)*orderData.getCarData().getCost());
	    }
	    request.getSession().setAttribute("orderData", orderData);
		
		return redirect;
	}

}
