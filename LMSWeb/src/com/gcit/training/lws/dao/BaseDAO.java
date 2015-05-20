package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public abstract class BaseDAO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2550376202443630390L; 
	
	protected Connection conn = null;
	
	public BaseDAO(Connection conn){
		this.conn = conn;
	}
	
	public List<?> read(String quer, Object[] vals)throws SQLException{
		PreparedStatement stmt = getConnection().prepareStatement(quer);
		int count = 1;
		if(vals != null){
			for(Object obj: vals){
				stmt.setObject(count++, obj);
				
			}
		}
		ResultSet rs = stmt.executeQuery();
		return mapResults(rs);
	}
	
	protected abstract List<?>mapResults(ResultSet rs) throws SQLException;
	
	
	
	protected abstract List<?>mapResultsFirstLevel(ResultSet rs) throws SQLException;

	
	
	public List<?> readFirstLevel(String quer, Object[] vals) throws SQLException{
		PreparedStatement stmt = getConnection().prepareStatement(quer);
		int count = 1;
		if(vals!=null){
			for(Object obj: vals){
				stmt.setObject(count++, obj);
			}
		}
		ResultSet rs = stmt.executeQuery();
		return mapResultsFirstLevel(rs);
	}
	
	
	public void save(String q, Object[] vals) throws SQLException{
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(q);
		int count=1;
		for (Object o:vals){
			stmt.setObject(count++, o);
		}
		stmt.executeUpdate();
		
	}
	
	public int saveWithID(String q, Object[] vals)throws SQLException{
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for(Object o: vals){
			stmt.setObject(count++, o);
		}
		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return -1;
		}
	}
	
	
	private Connection getConnection() throws SQLException{
		return conn;
	}
	
	
	
}