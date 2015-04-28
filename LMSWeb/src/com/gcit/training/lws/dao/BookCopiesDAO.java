package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> implements Serializable{

	private static final long serialVersionUID = 3815926817355723832L;
	
	public BookCopiesDAO(Connection conn){
		super(conn);
	}
	
	public void addCopies(BookCopies bc) throws SQLException{
		String 	Select = "Insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)";
		save(Select,new Object[]{bc.getBookId(),bc.getBranch().getLibraryId(),bc.getNoOfCopies()});
		
	}
	
	public void updateBookCopies(BookCopies bookCopy) throws SQLException{
		String update = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
		save(update, new Object[]{bookCopy.getNoOfCopies(),bookCopy.getBranch().getLibraryId(),bookCopy.getNoOfCopies()});
	}
	
	public void removeCopies(BookCopies bs)throws SQLException{
		int count = 0;
		save("Update tbl_book_copies set noOfCopies = (noCopies-?) where bookId =?", new Object[]{count, bs.getBookId()});
	}


	public Connection getConnection()throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return c;
	}
	
	public BookCopies readOne(int branchId, int bookid) throws SQLException{
		  String select = "Select * from tbl_book_copies where bookId=? and branchId=?";
		  read(select,new Object[]{branchId,bookid});
		
		  return null;
		  
	  }


	@Override
	protected List<BookCopies> mapResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<BookCopies> bc = new ArrayList<BookCopies>();
		while (rs.next()){
			BookCopies bcs = new BookCopies();
			bcs.setBookId(rs.getInt("bookId"));
			//bcs.setBranch(rs.getObject().get));
			bcs.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bc.add(bcs);
		}
		return bc;
	}

	@Override
	protected List<BookCopies> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}
	

}
