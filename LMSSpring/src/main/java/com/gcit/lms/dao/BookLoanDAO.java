/**
 * BookLoanDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;


public class BookLoanDAO extends BaseDAO<BookLoan> implements Serializable, ResultSetExtractor<List<BookLoan>>{
	/**
	 * @param conn
	 */
		@Autowired
		BorrowerDAO brDAO;
		BookDAO bkDAO;
		LibraryBranchDAO lDAO;
	/**
	 * 
	 */
	private static final long serialVersionUID = -631970332837238218L;

	
	public void  addBookLoan(BookLoan bookLoan) throws SQLException{
			String updateQuery = "insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)";
            template.update(updateQuery, new Object [] {bookLoan.getBook().getBookId(),bookLoan.getLibraryBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),
            	 bookLoan.getDateDue(),bookLoan.getDateDue()});		
	}
	
	public void overideDueDate(BookLoan bookLoan) throws SQLException{
		String updateQuery = "update tbl_book_loans SET dueDate = ? where bookId =? and branchId=? and cardNo=?";
         template.update(updateQuery,new Object[] {bookLoan.getDateDue(), bookLoan.getBook().getBookId(),bookLoan.getBorrower().getCardNo()});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<BookLoan> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return  (List<BookLoan>) template.query("select * from tbl_book_loan", this);
	}

	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	
	protected List<BookLoan> extractData(ResultSet rs) throws SQLException {
      List<BookLoan> bookLoans = new ArrayList<BookLoan>();
      
      while(rs.next()){
    	 BookLoan b = new BookLoan();
    	 b.setBook(bkDAO.readOne(rs.getInt("bookId")));
    	 b.setLibraryBranch(lDAO.getBranchById(rs.getInt("branchId")));
    	 b.setBorrower(brDAO.getBorrowerByCardNo(rs.getInt("cardNo")));
    	 b.setDateOut(rs.getDate("dateOut"));
    	 b.setDateDue(rs.getDate("dateIn"));
    	 bookLoans.add(b);
      }
		
		return bookLoans;
	}
	
	public List<BookLoan> searchBookLoanByCardNo(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {;
			return template.query("select * from tbl_book_loan where cardNo = ?",
					new Object[] { searchString }, this);
		}
	}
	
	public List<BookLoan> searchBookLoanByBranchId(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			return template.query("select * from tbl_book_loan where branchId = ?",
					new Object[] {searchString}, this);
		}
	}
	
	public List<BookLoan> searchBookLoanByBookId(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			return template.query("select * from tbl_book_loan where bookId = ?",
					new Object[] { searchString }, this);
		}
	}

	public int searchBookLoanByBookIdCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_book_loan where bookId = ?",
				new Object[] { searchString }, Integer.class);
	}
	
	public int searchBookLoanByCardNoCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_book_loan where cardNo = ?",
				new Object[] { searchString }, Integer.class);
	}
	
	public int searchBookLoanByBranchIdCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_book_loan where branchId = ?",
				new Object[] { searchString }, Integer.class);
	}
	

	
	//---------------------- End of Code -------------------------------//
}



/* (non-Javadoc)
 * @see com.gcit.lms.dao.BaseDAO#mapResultsFirstLevel(java.sql.ResultSet)
 */
//@Override
//protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
//	// TODO Auto-generated method stub
//	return null;
//}
//

	//return bookLoans;
	
//}
