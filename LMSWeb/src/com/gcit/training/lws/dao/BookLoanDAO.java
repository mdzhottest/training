package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2844457541155337594L;

	public BookLoanDAO(Connection conn){
		super(conn);
	}

	public void addBookLoan(BookLoan book) throws SQLException{
		String updateQuery = "insert into tbl_book_loans (bookId, branchId, cardNo, dueOut, dueIn) values (?,?,?,?,?);";
		
		save(updateQuery, new Object[] {
				book.getBook().getBookid(),book.getLibrary().getLibraryId(), book.getBorrower().getCardNo(), book.getDateOut(), book.getDateDue()});
		
	}
	
	public void updateBookLoans (BookLoan book) throws SQLException{
		String upQuer = "Update tbl_book_loans set dateOut = ?, dateDue=? where cardNo = ? And bookId = ? And branchId = ? AND cardNo = ?";
		save(upQuer, new Object[]{book.getDateOut(), book.getDateDue(),book.getBorrower().getCardNo(),book.getBook().getBookid(), book.getLibrary().getLibraryId() });
	}
	
	
	public void overideDueDate(BookLoan bookLoan) throws SQLException{

		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
        save(updateQuery,new Object[] {bookLoan.getDateDue(), bookLoan.getBook().getBookid(),bookLoan.getBorrower().getCardNo()});
	}
	
	
	@Override
	protected List<BookLoan> mapResults(ResultSet rs) throws SQLException{
		BookDAO bkd = new BookDAO(conn);
		
		List<BookLoan> bkds = new ArrayList<BookLoan>();
		
		while(rs.next()){
			BookLoan bkl = new BookLoan();
			String quer = "select * from tbl_book where bookId=?";
			bkl.setBook(null);
		}
		return null;
	}
	
	@Override
	protected List mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
