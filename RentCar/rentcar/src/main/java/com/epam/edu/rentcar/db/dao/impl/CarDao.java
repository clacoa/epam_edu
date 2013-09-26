package com.epam.edu.rentcar.db.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.epam.edu.rentcar.db.entity.Car;

public class CarDao extends EntityDao<Car>{

	@Override
	public String getTableName() {
		return Car.TABLE_NAME;
	}
	public Car get(Connection conn, Long id) {
		Car car = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Car.GET_BY_ID,
					Car.TABLE_NAME, id));
			if (rs.next()) {
				car = new Car(Long.valueOf(rs.getString(Car.ID)),
						rs.getString(Car.MARK),
						rs.getString(Car.MODEL),
						Double.valueOf(rs.getString(Car.CAR_COST)),
						Long.valueOf(rs.getString(Car.STATUS_ID)));
				car.setDescription(rs.getString(Car.DESCRIPTION));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		return car;
	}

	public List<Car> getAll(Connection conn) {
		List<Car> carList = null;
		Car car = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Car.GET_ALL, Car.TABLE_NAME));
			carList = new ArrayList<Car>();
			while (rs.next()) {
				car = new Car(Long.valueOf(rs.getString(Car.ID)),
						rs.getString(Car.MARK),
						rs.getString(Car.MODEL),
						Double.valueOf(rs.getString(Car.CAR_COST)),
						Long.valueOf(rs.getString(Car.STATUS_ID)));
				car.setDescription(rs.getString(Car.DESCRIPTION));
				carList.add(car);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return carList;
	}

	public void saveOrUpdate(Connection conn, Car entity) {
		int updateResult;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Car.GET_BY_ID,
					Car.TABLE_NAME, entity.getId()));
			if (rs.next()) {
				updateResult = st.executeUpdate(String.format(Car.UPDATE,
						entity.getCost(), entity.getMark(),
						entity.getModel(), entity.getDescription(),
						entity.getStatusId(), entity.getId()));

			} else {
				updateResult = st.executeUpdate(String.format(Car.INSERT,
						entity.getCost(), entity.getMark(),
						entity.getModel(), entity.getDescription(),
						entity.getStatusId(), entity.getId()));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		
	}

	public void saveOrUpdateAll(Connection conn, Collection<Car> entities) {
		for (Car entity : entities) {
			saveOrUpdate(conn, entity);
		}
		
	}

	public List<Car> findByNamedQuery(Connection conn, String queryName) {
		List<Car> carList = null;
		Car car = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryName);
			carList = new ArrayList<Car>();
			while (rs.next()) {
				car = new Car(Long.valueOf(rs.getString(Car.ID)),
						rs.getString(Car.MARK),
						rs.getString(Car.MODEL),
						Double.valueOf(rs.getString(Car.CAR_COST)),
						Long.valueOf(rs.getString(Car.STATUS_ID)));
				car.setDescription(rs.getString(Car.DESCRIPTION));
				carList.add(car);
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {

		}
		return carList;
	}

}
