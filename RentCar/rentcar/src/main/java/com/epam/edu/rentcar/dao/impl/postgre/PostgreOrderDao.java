package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.edu.rentcar.dao.OrderDao;
import com.epam.edu.rentcar.entity.Car;
import com.epam.edu.rentcar.entity.Order;
import com.epam.edu.rentcar.entity.Status;
import com.epam.edu.rentcar.entity.User;
import com.epam.edu.rentcar.exception.DaoException;

public class PostgreOrderDao extends PostgreEntityDao<Order> implements
		OrderDao<Order> {

	private static Logger LOG = Logger.getLogger(PostgreOrderDao.class);

	public final static String UPDATE = "Update orders Set userid=?, carid=?, datefrom=?, dateto=?, ordercost=?, statusid=? where id=?";
	public final static String ADD_COST_UPDATE = "Update orders Set addcost=? where id=?";
	public final static String INSERT = "Insert into orders (userid, carid, datefrom, dateto, ordercost, statusid) values (?,?,?,?,?,?)";
	public final static String GET_CURRENT_FOR_CAR = "Select * from orders where carid=? and (statusid=1 or statusid=2)";
	public final static String GET_CURRENT_FOR_USER = "Select * from orders where userid=? and statusid!=4 and statusid!=7 order by datefrom desc";
	public final static String GET_CURRENT = "Select * from orders where statusid!=4 and statusid!=7 order by datefrom desc";

	public final static String TABLE_NAME = "orders";
	public final static String ID = "id";
	public final static String USER_ID = "userid";
	public final static String CAR_ID = "carid";
	public final static String DATE_FROM = "datefrom";
	public final static String DATE_TO = "dateto";
	public final static String ORDER_COST = "ordercost";
	public final static String STATUS_ID = "statusid";
	public final static String ADD_COST = "addcost";

	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private PostgreOrderStatusDao pOrderStatusDao = new PostgreOrderStatusDao();
	private PostgreCarDao pCarDao = new PostgreCarDao();
	private PostgreUserDao pUserDao = new PostgreUserDao();

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public Order get(Connection conn, Long id) throws DaoException {
		Order order = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_BY_ID, getTableName()));
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				User user = pUserDao.get(conn,
						Long.valueOf(rs.getString(USER_ID)));
				Car car = pCarDao.get(conn, Long.valueOf(rs.getString(CAR_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return order;
	}

	public List<Order> getAll(Connection conn) throws DaoException {
		List<Order> orderList = null;
		Order order = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_ALL, getTableName()));
			ResultSet rs = pst.executeQuery();
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				Car car = pCarDao.get(conn, Long.valueOf(rs.getString(CAR_ID)));
				User user = pUserDao.get(conn,
						Long.valueOf(rs.getString(USER_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
				orderList.add(order);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return orderList;
	}

	public void saveOrUpdate(Connection conn, Order entity) throws DaoException {
		int updateResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, entity.getId())) {
				pst = conn.prepareStatement(UPDATE);
				pst.setLong(1, entity.getUser().getId());
				pst.setLong(2, entity.getCar().getId());
				pst.setDate(3,
						new java.sql.Date(entity.getDateFrom().getTime()));
				pst.setDate(4, new java.sql.Date(entity.getDateTo().getTime()));
				pst.setDouble(5, entity.getOrderCost());
				pst.setLong(6, entity.getStatus().getId());
				pst.setLong(7, entity.getId());
				updateResult = pst.executeUpdate();
				pst.close();
			} else {
				pst = conn.prepareStatement(INSERT);
				pst.setLong(1, entity.getUser().getId());
				pst.setLong(2, entity.getCar().getId());
				pst.setDate(3,
						new java.sql.Date(entity.getDateFrom().getTime()));
				pst.setDate(4, new java.sql.Date(entity.getDateTo().getTime()));
				pst.setDouble(5, entity.getOrderCost());
				pst.setLong(6, entity.getStatus().getId());
				updateResult = pst.executeUpdate();
				pst.close();
			}
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
	}

	public void addCostUpdate(Connection conn, Order entity) throws DaoException{
		int updateResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, entity.getId()) && entity.getAddCost()!=null) {
				pst = conn.prepareStatement(ADD_COST_UPDATE);
				pst.setDouble(1, entity.getAddCost());
				pst.setLong(2, entity.getId());
				updateResult = pst.executeUpdate();
				pst.close();
			} 
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
	}

	public List<Order> findByNamedQuery(Connection conn, String queryName)
			throws DaoException {
		List<Order> orderList = null;
		Order order = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryName);
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				User user = pUserDao.get(conn,
						Long.valueOf(rs.getString(USER_ID)));
				Car car = pCarDao.get(conn, Long.valueOf(rs.getString(CAR_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
				orderList.add(order);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return orderList;
	}
	

	public Order getCurrentOrder(Connection conn, Car car) throws DaoException {
		Order order = null;
		try {
			PreparedStatement pst = conn.prepareStatement(GET_CURRENT_FOR_CAR);
			pst.setLong(1, car.getId());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				User user = pUserDao.get(conn,
						Long.valueOf(rs.getString(USER_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return order;
	}

	public List<Order> getCurrentOrders(Connection conn, User user)
			throws DaoException {

		List<Order> orderList = null;
		Order order = null;
		try {
			PreparedStatement pst = conn.prepareStatement(GET_CURRENT_FOR_USER);
			pst.setLong(1, user.getId());
			ResultSet rs = pst.executeQuery();
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				Car car = pCarDao.get(conn, Long.valueOf(rs.getString(CAR_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
				if (rs.getString(ADD_COST)!=null){
					order.setAddCost(Double.valueOf(rs.getString(ADD_COST)));
				}
				orderList.add(order);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return orderList;
	}
	public List<Order> getCurrentOrders(Connection conn)
			throws DaoException {

		List<Order> orderList = null;
		Order order = null;
		try {
			PreparedStatement pst = conn.prepareStatement(GET_CURRENT);
			ResultSet rs = pst.executeQuery();
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				Status orderStatus = pOrderStatusDao.get(conn,
						Long.valueOf(rs.getString(STATUS_ID)));
				Car car = pCarDao.get(conn, Long.valueOf(rs.getString(CAR_ID)));
				User user = pUserDao.get(conn,Long.valueOf(rs.getString(USER_ID)));
				order = new Order(Long.valueOf(rs.getString(ID)), user, car,
						format.parse(rs.getString(DATE_FROM)), format.parse(rs
								.getString(DATE_TO)), Double.valueOf(rs
								.getString(ORDER_COST)), orderStatus);
				if (rs.getString(ADD_COST)!=null){
					order.setAddCost(Double.valueOf(rs.getString(ADD_COST)));
				}
				orderList.add(order);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DaoException(this.getTableName(), e.getCause());
		}
		return orderList;
	}

}
