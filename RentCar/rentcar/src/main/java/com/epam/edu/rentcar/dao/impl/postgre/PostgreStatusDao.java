package com.epam.edu.rentcar.dao.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.edu.rentcar.dao.impl.StatusDao;
import com.epam.edu.rentcar.entity.Status;

public abstract class PostgreStatusDao<T extends Status> extends
		PostgreEntityDao<Status> implements StatusDao<T> {

	private final static String GET_BY_STATUS = "Select * from %s where %s=?";
	private final static String UPDATE = "Update %s Set %s=? where id=?";
	private final static String INSERT = "Insert into %s (%s) values (?)";
	
	private final static String ID = "id";
	
	public String getColumnName() {
		return "";
	}
	
	public Status get(Connection conn, Long id) {
		Status entity = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(GET_BY_ID, getTableName()));
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				entity = new Status(Long.valueOf(rs.getString(ID)), rs.getString(getColumnName())); 
			}
			rs.close();
			pst.close();
		} catch (Exception ignore) {
		}
		return entity;
	}
	
	public List<Status> getAll(Connection conn) {
		List<Status> statusList = null;
		Status status = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(
					GET_ALL, getTableName()));
			ResultSet rs = pst.executeQuery();
			statusList = new ArrayList<Status>();
			while (rs.next()) {
				status = new Status(Long.valueOf(rs.getString(ID)), rs.getString(getColumnName())); 
				statusList.add(status);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
		}
		return statusList;
	}
	
	public void saveOrUpdate(Connection conn, Status entity) {
		int updateResult;
		PreparedStatement pst;
		try {
			if (isExists(conn, entity.getId())) {
				pst = conn.prepareStatement(String.format(UPDATE, getTableName(), getColumnName()));
				pst.setString(1, entity.getStatus());
				pst.setLong(2, entity.getId());
				updateResult = pst.executeUpdate();
				pst.close();
			} else {
				pst = conn.prepareStatement(String.format(INSERT, getTableName(), getColumnName()));
				pst.setString(1, entity.getStatus());
				updateResult = pst.executeUpdate();
				pst.close();
			}
		} catch (Exception ignore) {
		}
	}
	
	public boolean isExistsStatus(Connection conn, String status) {
		boolean isExists = false;
		try {	
			PreparedStatement pst = conn.prepareStatement(String.format(GET_BY_STATUS, getTableName(), getColumnName()));
			pst.setString(1, status);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				isExists = true;
				rs.close();
				pst.close();
			}
		} catch (SQLException ignore) {
		}

		return isExists;
	}

	public Status getByStatus(Connection conn, String status) {
		Status entity = null;
		try {
			PreparedStatement pst = conn.prepareStatement(String.format(GET_BY_STATUS, getTableName(), getColumnName()));
			pst.setString(1, status);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				entity = new Status(Long.valueOf(rs.getString(ID)), rs.getString(getColumnName())); 
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
		}
		return entity;
	}
	
	public List<Status> findByNamedQuery(Connection conn,
			String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

}
