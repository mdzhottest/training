package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.LibraryBranch;

@Repository
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> implements Serializable, ResultSetExtractor<List<LibraryBranch>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7751294459992321796L;
	/**
	 * @param conn
	 */
	
	
	public void addBranch(LibraryBranch libraryBranch) throws SQLException {

		String updateQuery = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
        template.update(updateQuery, new Object[]{libraryBranch.getBranchName(),libraryBranch.getBranchAddress()});
	}


	public void updateBranch(LibraryBranch libraryBranch) throws SQLException {
		String updateQuery = "update tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?";
		template.update(updateQuery, new Object []{libraryBranch.getBranchName(),libraryBranch.getBranchAddress(),libraryBranch.getBranchId()});
	}

	public void removeBranch(LibraryBranch libraryBranch) throws SQLException {
		String removeQuery = "delete from tbl_library_branch where branchId=?";
		template.update(removeQuery,new Object[]{libraryBranch.getBranchId()});
	}

	@SuppressWarnings("unchecked")
	public List<LibraryBranch> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		
		String select = "select * from tbl_library_branch";
		return (List<LibraryBranch>) template.query(select,this);
		
	}

	public LibraryBranch getBranchByName(String branchName) throws SQLException{
		String select = "select * from tbl_library_branch where branchName=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libraryBranchs =(List<LibraryBranch>) template.query(select, new Object[]{branchName}, this);
          if(libraryBranchs!=null&&libraryBranchs.size()>0){
      		return libraryBranchs.get(0);	

          }
          else return null;
		
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		String select = "select * from tbl_library_branch where branchId=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libraryBranchs =(List<LibraryBranch>) template.query(select, new Object[]{branchId}, this);
          if(libraryBranchs!=null&&libraryBranchs.size()>0){
      		return libraryBranchs.get(0);	

          }
          else return null;
		
	}
	/* (non-Javadoc)
	 * @see com.gcit.lms.dao.BaseDAO#mapResults(java.sql.ResultSet)
	 */
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			branches.add(libraryBranch);
		}

		return branches;
	}
	
	public List<LibraryBranch> searchBranchByName(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%" + searchString + "%";
			return template.query("select * from tbl_library_branch where branchName like ?",
					new Object[] { searchString }, this);
		}
	}
	
	public List<LibraryBranch> searchBranchByAddress(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%" + searchString + "%";
			return template.query("select * from tbl_library_branch where branchAddress like ?",
					new Object[] { searchString }, this);
		}
	}
	
	public int searchBranchByAddressCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_library_branch where branchAddress like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	public int searchBranchByNameCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_library_branch where branchName like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	

	//---------------------- End of Code -------------------------------//
}
