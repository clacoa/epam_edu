package com.epam.edu.rentcar.service;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.db.dao.impl.CarDao;
import com.epam.edu.rentcar.db.dao.impl.OrderDao;
import com.epam.edu.rentcar.db.dao.impl.UserDao;
import com.epam.edu.rentcar.db.entity.Car;
import com.epam.edu.rentcar.db.entity.Order;
import com.epam.edu.rentcar.db.entity.User;

public class Test {

	public static String isExists() {
		boolean f = false;
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		List<Order> orderList = new ArrayList<Order>();
		List<Car> carList = new ArrayList<Car>();
		String s = "";
		User user = null;
		UserDao userDao = new UserDao();
		OrderDao  orderDao = new OrderDao();
		CarDao carDao = new CarDao();
		User user1 = new User(Long.valueOf(11), "sidorov@rambler.ru",
				"password1", "sid", "Ivan", "Sidorov", "NP2738495947");
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date dateFrom;
        Date dateTo;
		try {
			dateFrom = format.parse("25.09.2013 00:00");
			dateTo = format.parse("29.09.2013 00:00");
		} catch (ParseException e1) {
			dateFrom=null;
	        dateTo=null;
		}
        
		Car car = null;
        
		Order order = new Order(Long.valueOf(3), user1.getId(), Long.valueOf(1),dateFrom,dateTo,55.88,1L);
		Car car1 = new Car(Long.valueOf(2),"BMW","750", 23.56,1L);
		car1.setDescription("черный, 2013 г. 4.4 бензин КПП типтроник");
		
		try {
			conn = ConnectionPool.getInstance().getConnection();
			if (carDao.isExists(conn, 1L)){
				car = carDao.get(conn, 1L);
			}
//			if (userDao.isExists(conn, Long.valueOf(10))) {
//				userDao.delete(conn, Long.valueOf(10));
//			}
//			if (userDao.isExists(conn, Long.valueOf(5))) {
//				user = userDao.get(conn, Long.valueOf(5));
//			}
//			if (userDao.isExistsEmail(conn, user1.getEmail())) {
//				user = userDao.getByEmail(conn, user1.getEmail());
//			}
			orderDao.saveOrUpdate(conn, order);
			carDao.saveOrUpdate(conn, car1);
			if (userDao.isExistsPassportNumber(conn, user1.getPassport())) {
				user = userDao.getByPassportNumber(conn, user1.getPassport());
			}
			userList = userDao.getAll(conn);
			orderList = orderDao.getAll(conn);
			carList = carDao.getAll(conn);
			userDao.saveOrUpdate(conn, user1);
			conn.close();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}

		// s = user.toString();

		for (User entity : userList) {
			s += entity.toString() + "<hr/><br/>";
		}
		s="";
		for (Car entity: carList){
			s += entity.toString() + "<hr/><br/>";
		}

		return s;

	}

}
