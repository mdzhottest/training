package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.training.entities.domain.BookLoan;

public class BookLoanDAO implements Serializable {
	
	public void addBookLoan(BookLoan book) throws SQLException{
		Connection conn = getConnection();

		String updateQuery = "insert into tbl_book_loans (bookId, branchId, cardNo, dueOut, dueIn) values (?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(updateQuery);
		pstmt.setInt(1, book.getBook().getBookid());
		pstmt.setInt(2, book.getLibrary().getLibraryId());
		pstmt.setInt(3, book.getBorrower().getCardNo());
		pstmt.setDate(4, (Date) book.getDateOut());
		pstmt.setDate(5, (Date)book.getDateDue());
		pstmt.executeUpdate();
	}
	
	public void updateBookLoans (BookLoan book) throws SQLException{
		
		Connection conn = getConnection();
		
		String upQuer = "Update tbl_book_loans set dateOut = ?, dateDue=? where cardNo = ? And bookId = ? And branchId = ? AND cardNo = ?";
		PreparedStatement stmt = conn.prepareStatement(upQuer);
		
		stmt.setDate(1, (Date)book.getDateOut());
		stmt.setDate(2, (Date)book.getDateDue());
		stmt.setInt(3, book.getBorrower().getCardNo());
		stmt.setInt(4, book.getBook().getBookid());
		stmt.setInt(5, book.getLibrary().getLibraryId());
		
		stmt.executeUpdate();
	}
	
	
	public void remBookLoan(BookLoan book) throws SQLException{
		Connection conn = getConnection();
		String delQuer = "Delete from tbl_book_loans where bookId = ? AND branchId =? AND cardNo = ?;";
		
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, book.getBook().getBookid());
		stmt.setInt(2, book.getLibrary().getLibraryId());
		stmt.setInt(3, book.getBorrower().getCardNo());
		
		stmt.executeUpdate();
		
	}
	
	
	
	//getting the connection
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		
		return conn;
	}

}
