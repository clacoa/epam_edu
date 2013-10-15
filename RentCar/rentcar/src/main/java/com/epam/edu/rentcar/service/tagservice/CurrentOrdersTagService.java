package com.epam.edu.rentcar.service.tagservice;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserDao;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.model.CarData;
import com.epam.edu.rentcar.model.CarFilter;

public class CurrentOrdersTagService {
	private static Logger LOG = Logger.getLogger(CurrentOrdersTagService.class);
	
	private PostgreUserDao carDao = new PostgreUserDao();
	private PostgreOrderDao orderDao = new PostgreOrderDao();
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar calendar = Calendar.getInstance();
	
	public List<Order> createOrdersTableData(Connection conn, User user, Locale locale) {
		
		List<Order> orders = null;
		List<Order> filterList = null;
		
		try {
			if (user.getRole().getId()==2L){
				orders = orderDao.getCurrentOrders(conn);
			} else {
				orders = orderDao.getCurrentOrders(conn,user);
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return orders;
		
	}
}
