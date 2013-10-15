package com.epam.edu.rentcar.dao;

import com.epam.edu.rentcar.entity.Car;


public interface CarDao <T extends Car> extends GenericRentCarDao<Car>{

}
