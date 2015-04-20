package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDAO implements Serializable {

	private static final long serialVersionUID = -5675634058437817697L;
	
	public void addBook() throws SQLException{
		Connection conn = getConnection();
		
		String addQuer = "insert into tbl_book values ?";
		
		
	}
	
	
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		
		return conn;
	}
	
}
