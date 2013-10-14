package com.epam.edu.rentcar.service.tagservice;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.model.CarFilter;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.PrintElement;
import com.epam.edu.rentcar.util.CommonBundle;

public class CarsTagService {

	// private CarFilter filter;
	private String id;
	private PostgreCarDao carDao = new PostgreCarDao();
	private PostgreOrderDao orderDao = new PostgreOrderDao();
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar calendar = Calendar.getInstance();
	private Connection conn;

	public final static String SPLITTER = "~";

	public CarsTagService() {

		carDao = new PostgreCarDao();
		orderDao = new PostgreOrderDao();
		try {
			conn = ConnectionPool.getInstance().getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<CarData> createTableData(CarFilter filter, Locale locale) {

		List<CarData> carDataList = null;
		List<Car> cars = null;
		List<Car> filterList = null;

		cars = carDao.getAll(conn);

		carDataList = new ArrayList<CarData>();
		if (cars != null) {
			if (filter != null) {
				if (!StringUtils.isEmpty(filter.getModel())) {
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

				if ((filter.getMinCost() != null)
						&& (filter.getMaxCost() != null)) {
					filterList = new ArrayList<Car>();
					for (Car car : cars) {
						if ((car.getCost()>=filter.getMinCost() )
								&& (car.getCost()<= filter.getMaxCost())) {
							filterList.add(car);
						}
					}
				}
				if (filterList != null) {
					cars = filterList;
				}

				if (!StringUtils.isEmpty(filter.getDescription())) {
					filterList = new ArrayList<Car>();
					for (Car car : cars) {
						if (StringUtils.containsIgnoreCase(
								car.getDescription(), filter.getDescription()) == true) {
							filterList.add(car);
						}
					}
				}

				if (filterList != null) {
					cars = filterList;
				}
			}
			for (Car car : cars) {
				CarData carData = new CarData();
				carData.setId(car.getId());
				carData.setModelName(car.getMark() + " " + car.getModel());
				carData.setDescription(car.getDescription());
				carData.setCost(car.getCost());
				carData.setStatus(car.getStatus().getId());
				Order order = orderDao.getCurrentOrder(conn, car);
				if (order != null) {
					calendar.setTime(order.getDateTo());
					calendar.add(Calendar.DATE, 1);
					carData.setAvailableDate(calendar.getTime());
				}
				carData.setAction(setCarAction(carData, locale));
				carDataList.add(carData);
			}
		}

		return carDataList;
	}

	public List<PrintElement> ModelFilter() {

		List<String> models = new ArrayList<String>();
		List<PrintElement> printElements = null;
		models = carDao.getDistinct(conn, PostgreCarDao.MARK,
				PostgreCarDao.MODEL, SPLITTER);
		if (models != null) {
			printElements = new ArrayList<PrintElement>();
			for (String model : models) {
				PrintElement element = new PrintElement();
				element.setValue(model);
				element.setText(model.replace(SPLITTER, " "));
				printElements.add(element);
			}
		}

		return printElements;

	}

	public List<PrintElement> CostFilter() {

		List<PrintElement> printElements = new ArrayList<PrintElement>();

		printElements
				.add(new PrintElement("0" + SPLITTER + "10000", "0-10000"));
		printElements.add(new PrintElement("10000" + SPLITTER + "30000",
				"10000-30000"));
		printElements.add(new PrintElement("30000" + SPLITTER + "50000",
				"30000-50000"));
		printElements.add(new PrintElement("50000" + SPLITTER + "1000000",
				"more then 50000"));
		return printElements;
	}

	private String setCarAction(CarData car, Locale locale) {
		String action = null;
		if (car.getStatus() == 2L) {
			action = "<div class='button' onclick='checkAndPost()'>"
					+ " <img src='./Images/check.png' height='100%' align='left' />"
					+"&nbsp" +CommonBundle.getProperty("cars.filter.action.button", locale)+"</div>";
		} else if (car.getStatus() == 1L) {
			if (car.getAvailableDate() != null) {
				action = CommonBundle.getProperty("cars.filter.action.available", locale)+" "
						+ format.format(car.getAvailableDate()).toString();
			}
		} else if (car.getStatus() == 3L) {
			action = CommonBundle.getProperty("cars.filter.action.notavailable", locale);
		}
		return action;
	}
}
