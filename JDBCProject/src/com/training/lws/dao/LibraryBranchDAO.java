package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.training.entities.domain.LibraryBranch;

public class LibraryBranchDAO implements Serializable{

	private static final long serialVersionUID = 6965176401043670282L;

	public void addBranch(LibraryBranch branch) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "Insert into tbl_library_branch (branchName, branchAddress) values (?)";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		
		stmt.setString(1, branch.getLibraryName());
		stmt.setString(2, branch.getLibraryAddress());
		stmt.executeUpdate();
		
	}
	
	//Updates the author name in specified address
	public void updateBranch(LibraryBranch a) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		stmt.setString(1, a.getLibraryName());
		stmt.setString(2, a.getLibraryAddress());
		stmt.setInt(3, a.getLibraryId());
		stmt.executeUpdate();
	}
	
	public void deleteAuthor(LibraryBranch a) throws SQLException{
		Connection conn = getConnection();
		
		String delQuer = "Delete from tbl_library_branch where branchId = ?;";
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, a.getLibraryId());
		stmt.executeUpdate();
		
	}
	
	public Connection getConnection() throws SQLException{
		
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return conn;
	}
}
