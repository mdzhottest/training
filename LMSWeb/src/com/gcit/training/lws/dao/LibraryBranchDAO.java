package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> implements Serializable{

	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 6965176401043670282L;

	public void addBranch(LibraryBranch branch) throws SQLException{

		String upQuer = "insert into tbl_library_branch (branchName,branchAddress) values (?,?)";
        save(upQuer, new Object[]{branch.getLibraryName(),branch.getLibraryAddress()});
	}
	
	//Updates the author name in specified address
	public void updateBranch(LibraryBranch a) throws SQLException{
		String upQuer = "update tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?";
		save(upQuer, new Object []{a.getLibraryName(),a.getLibraryAddress(),a.getLibraryId()});
	}
	
	public void deleteBranch(LibraryBranch a) throws SQLException{
		String rQ = "delete from tbl_library_branch where branchId=?";
		save(rQ,new Object[]{a.getLibraryId()});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<LibraryBranch> readAll() throws SQLException {
		String select = "select * from tbl_library_branch";
		return (List<LibraryBranch>) read(select,null);
		
	}
	
	public LibraryBranch getBranchByName(String bName) throws SQLException{
		String select = "select * from tbl_library_branch where branchName=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libBran =(List<LibraryBranch>) read(select, new Object[]{bName});
          if(libBran!=null&&libBran.size()>0){
      		return libBran.get(0);	

          }
          else return null;
		
	}
	
	public LibraryBranch getBranchById(int branchId) throws SQLException{
		String select = "select * from tbl_library_branch where branchId=?";
		@SuppressWarnings("unchecked")
		List<LibraryBranch> libraryBranchs =(List<LibraryBranch>) read(select, new Object[]{branchId});
          if(libraryBranchs!=null&&libraryBranchs.size()>0){
      		return libraryBranchs.get(0);	

          }
          else return null;
		
	}

	@Override
	protected List<LibraryBranch> mapResults(ResultSet rs) throws SQLException {
		List<LibraryBranch> b = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch lb = new LibraryBranch();
			lb.setLibraryId(rs.getInt("branchId"));
			lb.setLibraryName(rs.getString("branchName"));
			lb.setLibraryAddress(rs.getString("branchAddress"));
			b.add(lb);
		}

		return b;
	}

	@Override
	protected List<LibraryBranch> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
