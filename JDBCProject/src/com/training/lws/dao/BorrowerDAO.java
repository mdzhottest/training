package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.entities.domain.Borrower;

public class BorrowerDAO implements Serializable{

	private static final long serialVersionUID = -8591950361166071450L;
	
	public void addBorrower(Borrower bor) throws SQLException{
		Connection conn = getConnection();
		String addQuer = "Insert INTO tbl_borrower (name, address, phone) values (?,?,?) ?";
		PreparedStatement stmt = conn.prepareStatement(addQuer);
		stmt.setString(1,bor.getBorrowerName());
		stmt.setString(2, bor.getBorrowerAddress());
		stmt.setString(3, bor.getBorrowerPhone());
		stmt.executeUpdate();
		
	}
	
	public void delBorrower(Borrower bor) throws SQLException{
		Connection conn = getConnection();
		String remBor = "Delete FROM tbl_borrower where cardNo = ?;";
		PreparedStatement stmt = conn.prepareStatement(remBor);
		
		stmt.setInt(1, bor.getCardNo());
		
		stmt.executeUpdate(remBor);
	}
	
	
	public void upBorrower(Borrower bor) throws SQLException{
		Connection conn = getConnection();
		
		String upQuer = "Update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?";
		PreparedStatement stmt = conn.prepareStatement(upQuer);
		
		stmt.setString(1, bor.getBorrowerName());
		stmt.setString(2, bor.getBorrowerAddress());
		stmt.setString(3, bor.getBorrowerPhone());
		stmt.setInt(4, bor.getCardNo());
		
		stmt.executeUpdate();
	}
	
	public List<Borrower> readBorrowers() throws SQLException{
		Connection conn = getConnection();
		String select = "Select * from tbl_borrower";
		PreparedStatement stmt = conn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		
		List<Borrower> borr = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			
			borr.add(b);
		}
		
		return borr;
		
		
	}
	public Connection getConnection() throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		
		return c;
	}
}
