package com.epam.edu.rentcar.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import com.epam.edu.rentcar.command.Command;
import com.epam.edu.rentcar.model.CarFilter;
import com.epam.edu.rentcar.service.tagservice.CarsTagService;
import com.epam.edu.rentcar.tags.CarsTable;

public class SearchCarCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(SearchCarCommand.class);
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String redirect = "/cars.jsp";
		String msg = null;
		String modelName = request.getParameter("modelSelect");
		String cost = request.getParameter("costSelect");
		String description = request.getParameter("descriptionFilter");

		if ((!StringUtils.isEmpty(modelName)) || (!StringUtils.isEmpty(cost))
				|| (!StringUtils.isEmpty(description))) {
			CarFilter carFilter = new CarFilter();
			if(!StringUtils.isEmpty(modelName)){
				carFilter.setMark(modelName.substring(0,
						modelName.indexOf(CarsTagService.SPLITTER)));
				carFilter.setModel(modelName.substring(modelName
						.indexOf(CarsTagService.SPLITTER) + 1));
			}
			if(!StringUtils.isEmpty(cost)){
				carFilter.setMinCost(Double.valueOf(cost.substring(0,
						cost.indexOf(CarsTagService.SPLITTER))));
				carFilter.setMaxCost(Double.valueOf(cost.substring(cost
						.indexOf(CarsTagService.SPLITTER) + 1)));
			}
			if(!StringUtils.isEmpty(description)){
				carFilter.setDescription(description);
			}
			request.setAttribute("filter", carFilter);
		}

		return redirect;
	}

}
