package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.CarDao;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.exception.DaoException;

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

	public Car get(Connection conn, Long id) throws DaoException {
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
		} catch (Exception e) {
			throw new DaoException(this.getTableName(),e.getCause());
		}
		return car;
	}

	public List<Car> getAll(Connection conn) throws DaoException {
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
			throw new DaoException(this.getTableName(),e.getCause());
		}
		return carList;
	}

	public void saveOrUpdate(Connection conn, Car entity) throws DaoException {
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
		} catch (Exception e) {
			throw new DaoException(this.getTableName(),e.getCause());
		}

	}

	public List<Car> findByNamedQuery(Connection conn, String queryName) throws DaoException {
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
		} catch (Exception e) {
			throw new DaoException(this.getTableName(),e.getCause());
		}
		return carList;
	}

	public List<String> getDistinct(Connection conn, String getColName) throws DaoException {
		List<String> catalog = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_DISTINCT, getColName, getTableName()));
			ResultSet rs = pst.executeQuery();
			catalog = new ArrayList<String>();
			while (rs.next()) {
				catalog.add(rs.getString(getColName));
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(),e.getCause());
		}
		return catalog;
	}

	public List<String> getDistinct(Connection conn, String getColName, String addColName, String splitter) throws DaoException {
		List<String> catalog = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_DISTINCT,getColName+","+addColName, getTableName()));
			ResultSet rs = pst.executeQuery();
			catalog = new ArrayList<String>();
			while (rs.next()) {
				catalog.add(rs.getString(getColName)+splitter+rs.getString(addColName));
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(),e.getCause());
		}
		return catalog;
	}
}
