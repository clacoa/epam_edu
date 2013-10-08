package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.impl.CarDao;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.Status;

public class PostgreCarDao extends PostgreEntityDao<Car> implements CarDao<Car> {

	private static Logger LOG = Logger.getLogger(PostgreCarDao.class);

	public final static String UPDATE = "Update cars Set carcost=?, mark=?, model=?, description=?, statusid=? where id=?";
	public final static String INSERT = "Insert into cars (carcost, mark, model, description, statusid) values (?,?,?,?,?)";

	public final static String TABLE_NAME = "cars";
	public final static String ID = "id";
	public final static String MARK = "mark";
	public final static String MODEL = "model";
	public final static String DESCRIPTION = "description";
	public final static String CAR_COST = "carcost";
	public final static String STATUS_ID = "statusid";

	private PostgreCarStatusDao pCarStatusDao = new PostgreCarStatusDao();
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public Car get(Connection conn, Long id) {
		Car car = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_BY_ID, getTableName()));
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Status carStatus = pCarStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				car = new Car(Long.valueOf(rs.getString(ID)),
						rs.getString(MARK), rs.getString(MODEL),
						Double.valueOf(rs.getString(CAR_COST)), carStatus);
				car.setDescription(rs.getString(DESCRIPTION));
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {
		}
		return car;
	}

	public List<Car> getAll(Connection conn) {
		List<Car> carList = null;
		Car car = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_ALL, getTableName()));
			ResultSet rs = pst.executeQuery();
			carList = new ArrayList<Car>();
			while (rs.next()) {
				Status carStatus = pCarStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				car = new Car(Long.valueOf(rs.getString(ID)),
						rs.getString(MARK), rs.getString(MODEL),
						Double.valueOf(rs.getString(CAR_COST)), carStatus);
				car.setDescription(rs.getString(DESCRIPTION));
				carList.add(car);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
		}
		return carList;
	}

	public void saveOrUpdate(Connection conn, Car entity) {
		int updateResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, entity.getId())) {
				pst = conn.prepareStatement(UPDATE);
				pst.setDouble(1, entity.getCost());
				pst.setString(2, entity.getMark());
				pst.setString(3, entity.getModel());
				pst.setString(4, entity.getDescription());
				pst.setLong(5, entity.getStatus().getId());
				pst.setLong(6, entity.getId());
				updateResult = pst.executeUpdate();
				pst.close();
			} else {
				pst = conn.prepareStatement(INSERT);
				pst.setDouble(1, entity.getCost());
				pst.setString(2, entity.getMark());
				pst.setString(3, entity.getModel());
				pst.setString(4, entity.getDescription());
				pst.setLong(5, entity.getStatus().getId());
				updateResult = pst.executeUpdate();
				pst.close();
			}
		} catch (Exception ignore) {
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
				Status carStatus = pCarStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				car = new Car(Long.valueOf(rs.getString(ID)),
						rs.getString(MARK), rs.getString(MODEL),
						Double.valueOf(rs.getString(CAR_COST)), carStatus);
				car.setDescription(rs.getString(DESCRIPTION));
				carList.add(car);
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {

		}
		return carList;
	}

	public List<String> getDistinct(Connection conn, String colName) {
		List<String> catalog = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_DISTINCT, colName, getTableName()));
			ResultSet rs = pst.executeQuery();
			catalog = new ArrayList<String>();
			while (rs.next()) {
				catalog.add(rs.getString(colName));
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {

		}
		return catalog;
	}

	public List<String> getDistinctWhere(Connection conn, String colName, String likeColName, String likeValue ) {
		List<String> catalog = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_DISTINCT_WHERE, colName, getTableName(), likeColName));
			pst.setString(1,likeValue);
			ResultSet rs = pst.executeQuery();
			catalog = new ArrayList<String>();
			while (rs.next()) {
				catalog.add(rs.getString(colName));
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {

		}
		return catalog;
	}
}
