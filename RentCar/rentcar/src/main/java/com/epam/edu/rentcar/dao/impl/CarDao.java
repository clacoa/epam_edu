package com.epam.edu.rentcar.dao.impl;

import com.epam.edu.rentcar.dao.GenericRentCarDao;
import com.epam.edu.rentcar.entity.Car;


public interface CarDao <T extends Car> extends GenericRentCarDao<Car>{

}
