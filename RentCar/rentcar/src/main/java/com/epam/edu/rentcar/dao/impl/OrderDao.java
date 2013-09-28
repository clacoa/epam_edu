package com.epam.edu.rentcar.dao.impl;

import com.epam.edu.rentcar.dao.GenericRentCarDao;
import com.epam.edu.rentcar.entity.Order;

public interface OrderDao<T extends Order> extends GenericRentCarDao<Order> {

}
