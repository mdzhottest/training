package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.training.entities.domain.BookCopies;

public class BookCopiesDAO implements Serializable{

	private static final long serialVersionUID = 3815926817355723832L;
	
	
	public void addCopies(BookCopies bc) throws SQLException{
		Connection conn = getConnection();
		
		String addQuer = "Declare count int; Set count = 1; Update tbl_book_copies set noCopies = (noCopies+count) where bookId =?";
		
		PreparedStatement stmt = conn.prepareStatement(addQuer);
		
		stmt.setInt(1, bc.getBookId());
		stmt.executeUpdate();
		
	}
	
	
	public Connection getConnection()throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return c;
	}
	

}
