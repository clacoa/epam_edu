package com.epam.edu.rentcar.service;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreCarStatusDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderStatusDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserRoleDao;
import com.epam.edu.rentcar.db.ConnectionPool;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.entity.User;

public class Test {

	public static String isExists() {
		boolean f = false;
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		List<Order> orderList = new ArrayList<Order>();
		List<Car> carList = new ArrayList<Car>();
		String s = "";
		User user = null;
		Status carStatus = null;

		PostgreUserDao postgreUserDao = new PostgreUserDao();
		PostgreOrderDao postgreOrderDao = new PostgreOrderDao();
		PostgreCarDao postgreCarDao = new PostgreCarDao();
		PostgreCarStatusDao carStatusDao = new PostgreCarStatusDao();
		PostgreUserRoleDao userRoleDao = new PostgreUserRoleDao();
		PostgreOrderStatusDao orderStatusDao = new PostgreOrderStatusDao();

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date dateFrom;
		Date dateTo;
		try {
			dateFrom = format.parse("25.10.2013 00:00");
			dateTo = format.parse("29.10.2013 00:00");
		} catch (ParseException e1) {
			dateFrom = null;
			dateTo = null;
		}

		try {
			conn = ConnectionPool.getInstance().getConnection();

			User user1 = postgreUserDao.get(conn, (postgreUserDao.getByPassportNumber(conn, "NP3721495947")).getId());
			Car car = postgreCarDao.get(conn, 4L);
			
			Status orderStatus = orderStatusDao.get(conn, 1L);
			
			Order order = new Order(Long.valueOf(7), user1,	car, dateFrom, dateTo, 44.95, orderStatus);
			postgreOrderDao.saveOrUpdate(conn, order);
			

			carStatus = carStatusDao.get(conn, 1L);
			// user = postgreUserDao.get(conn, Long.valueOf(11));
			// if (postgreCarDao.isExists(conn, 1L)){
			// car = postgreCarDao.get(conn, 1L);
			//
			// }
			//
			// postgreUserDao.delete(conn, Long.valueOf(11));
			//
			// postgreCarDao.saveOrUpdate(conn, car1);
			// postgreCarDao.delete(conn, 5L);
			//
			// postgreOrderDao.saveOrUpdate(conn, order);
			//
			// if (postgreUserDao.isExistsPassportNumber(conn,
			// user1.getPassport())) {
			// user = postgreUserDao.getByPassportNumber(conn,
			// user1.getPassport());
			// }
			// userList = postgreUserDao.getAll(conn);
			 orderList = postgreOrderDao.getAll(conn);
			// carList = postgreCarDao.getAll(conn);
			// userList.get(1).setNickName("petrov");
			// //user1=postgreUserDao.getByEmail(conn,
			// userList.get(2).getEmail());
			// //user = postgreUserDao.getByPassportNumber(conn,
			// userList.get(3).getPassport());
			// postgreUserDao.saveOrUpdate(conn, user1);
			// postgreUserDao.saveOrUpdateAll(conn, userList);
			conn.close();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}

		// s = user.toString();

//		for (User entity : userList) {
//			s += entity.toString() + "<hr/><br/>";
//		}
//
//		for (Car entity : carList) {
//			s += entity.toString() + "<hr/><br/>";
//		}

		for (Order entity : orderList) {
			s += entity.toString() + "<hr/><br/>";
		}


		return s;

	}
}
