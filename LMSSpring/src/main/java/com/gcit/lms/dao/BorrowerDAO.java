/**
 * BorrowerDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;



@Repository
public class BorrowerDAO extends BaseDAO<Borrower> implements Serializable,
		ResultSetExtractor<List<Borrower>> {

	/**
	 * @param conn
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -6162620077049608551L;

	public void addBorrower(Borrower borrower) throws SQLException {

		String updateQuery = "insert into tbl_borrower (name,address,phone) values (?,?,?)";
		template.update(updateQuery, new Object[] { borrower.getBorrowerName(),
				borrower.getBorrowerAddress(), borrower.getBorrowerPhone() });
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		String updateQuery = "update tbl_borrower set name = ? , address=?, phone=? where cardNo = ?";
		template.update(updateQuery, new Object[] { borrower.getBorrowerName(),
				borrower.getBorrowerAddress(), borrower.getBorrowerPhone(),
				borrower.getCardNo() });
	}

	public void removeBorrower(Borrower borrower) throws SQLException {
		String removeQuery = "delete from tbl_borrower where cardNo=?";
		template.update(removeQuery, new Object[] { borrower.getCardNo() });
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		String select = "select * from tbl_borrower";
		return (List<Borrower>) template.query(select, this);

	}

	public Borrower getBorrowerByName(String borrowerName) throws SQLException {
		String select = "select * from tbl_borrower where name=?";
		List<Borrower> borrowers = template.query(select,
				new Object[] { borrowerName }, this);
		if (borrowers != null && borrowers.size() > 0) {
			return borrowers.get(0);
		} else
			return null;
	}

	public Borrower getBorrowerByCardNo(int cardNo) throws SQLException {
		String select = "select * from tbl_borrower where cardNo=?";
		List<Borrower> borrowers = template.query(select,
				new Object[] { cardNo }, this);
		if (borrowers != null && borrowers.size() > 0) {
			return borrowers.get(0);
		} else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			borrower.setBorrowerAddress(rs.getString("address"));
			borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}

		return borrowers;
	}
	
	public List<Borrower> searchBorrowerByName(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%"+searchString+"%";
			return template.query("select * from tbl_borrower where name like ?",
					new Object[] {searchString}, this);
		}
	}
	
	public List<Borrower> searchBorrowerByAddress(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%"+searchString+"%";
			return template.query("select * from tbl_borrower where address like ?",
					new Object[] {searchString}, this);
		}
	}
	
	public int searchBorrowerByNameCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_borrower where name like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	public int searchBorrowerByAddressCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_borrower where address like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	//---------------------- End of Code -------------------------------//
}
