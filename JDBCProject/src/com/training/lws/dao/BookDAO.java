package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.entities.domain.Book;

public class BookDAO implements Serializable {

	private static final long serialVersionUID = -5675634058437817697L;
	
	public void addBook(Book book) throws SQLException{
		Connection conn = getConnection();
		
		String addQuer = "insert into tbl_book values ?";
		PreparedStatement stmt = conn.prepareStatement(addQuer);
		
		stmt.setString(1, book.getTitle());
		stmt.executeUpdate();
		
	}
	
	//updating the books
	public void updateBook(Book a) throws SQLException{
		Connection conn = getConnection();
		String upQuer = "Update tbl_book set title = ? where bookId = ?";
		
		PreparedStatement stmt = conn.prepareStatement(upQuer);
		
		stmt.setString(1, a.getTitle());
		stmt.setInt(2, a.getBookid());
		stmt.executeUpdate();		
		
	}
	
	public void delBook (Book a) throws SQLException{
		Connection conn = getConnection();
		String delQuer = "Delete from tbl_book where bookId = ?";
		
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, a.getBookid());
		
		stmt.executeUpdate();
		
	}
	
	public List<Book> readAllBooks() throws SQLException{
		String sel = " Select * from tbl_author";
		List<Book> bl = new ArrayList<Book>();
		PreparedStatement stmt = getConnection().prepareStatement(sel);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()){
			Book b2 = new Book();
			b2.setBookid((rs.getInt("bookId")));
			b2.setTitle(rs.getString("title"));
			
			bl.add(b2);
		}
		
		return bl;
		
	}
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		
		return conn;
	}
	
}
