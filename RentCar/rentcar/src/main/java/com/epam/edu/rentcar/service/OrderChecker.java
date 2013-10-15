package com.epam.edu.rentcar.service;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreOrderStatusDao;
import com.epam.edu.rentcar.dao.impl.postgre.PostgreUserDao;

public class OrderChecker {
	private static Logger LOG = Logger.getLogger(PostgreOrderStatusDao.class);
	private static PostgreUserDao userDao = new PostgreUserDao();
	private static PostgreOrderDao orderDao = new PostgreOrderDao();

}
