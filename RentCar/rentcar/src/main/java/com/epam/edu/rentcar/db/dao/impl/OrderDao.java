package com.epam.edu.rentcar.db.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.epam.edu.rentcar.db.entity.Order;

public class OrderDao extends EntityDao<Order> {
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public String getTableName() {
		return Order.TABLE_NAME;
	}

	public Order get(Connection conn, Long id) {
		Order order = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Order.GET_BY_ID,
					Order.TABLE_NAME, id));
			if (rs.next()) {
				order = new Order(Long.valueOf(rs.getString(Order.ID)),
						Long.valueOf(rs.getString(Order.USER_ID)),
						Long.valueOf(rs.getString(Order.CAR_ID)),
						format.parse(rs.getString(Order.DATE_FROM)),
						format.parse(rs.getString(Order.DATE_TO)),
						Double.valueOf(rs.getString(Order.ORDER_COST)),
						Long.valueOf(rs.getString(Order.STATUS_ID)));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
		}
		return order;
	}

	public List<Order> getAll(Connection conn) {
		List<Order> orderList = null;
		Order order = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Order.GET_ALL, Order.TABLE_NAME));
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				order = new Order(Long.valueOf(rs.getString(Order.ID)),
						Long.valueOf(rs.getString(Order.USER_ID)),
						Long.valueOf(rs.getString(Order.CAR_ID)),
						format.parse(rs.getString(Order.DATE_FROM)),
						format.parse(rs.getString(Order.DATE_TO)),
						Double.valueOf(rs.getString(Order.ORDER_COST)),
						Long.valueOf(rs.getString(Order.STATUS_ID)));
				orderList.add(order);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return orderList;
	}

	public void saveOrUpdate(Connection conn, Order entity) {
		int updateResult;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(String.format(Order.GET_BY_ID,
					Order.TABLE_NAME, entity.getId()));
			if (rs.next()) {
				updateResult = st.executeUpdate(String.format(Order.UPDATE,
						entity.getUserId(), entity.getCarID(),
						entity.getDateFrom(), entity.getDateTo(),
						entity.getOrderCost(), entity.getStatusId(), entity.getId()));

			} else {
				updateResult = st.executeUpdate(String.format(Order.INSERT,
						entity.getUserId(), entity.getCarID(),
						entity.getDateFrom(), entity.getDateTo(),
						entity.getOrderCost(), entity.getStatusId()));
			}
			rs.close();
			st.close();
		} catch (Exception ignore) {
			System.out.println(ignore);
		}
	}

	public void saveOrUpdateAll(Connection conn, Collection<Order> entities) {
		for (Order order: entities){
			saveOrUpdate(conn, order);
		}
	}

	public List<Order> findByNamedQuery(Connection conn, String queryName) {
		List<Order> orderList = null;
		Order order = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryName);
			orderList = new ArrayList<Order>();
			while (rs.next()) {
				order = new Order(Long.valueOf(rs.getString(Order.ID)),
						Long.valueOf(rs.getString(Order.USER_ID)),
						Long.valueOf(rs.getString(Order.CAR_ID)),
						format.parse(rs.getString(Order.DATE_FROM)),
						format.parse(rs.getString(Order.DATE_TO)),
						Double.valueOf(rs.getString(Order.ORDER_COST)),
						Long.valueOf(rs.getString(Order.STATUS_ID)));
				orderList.add(order);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
		}
		return orderList;
	}
	
	

}
