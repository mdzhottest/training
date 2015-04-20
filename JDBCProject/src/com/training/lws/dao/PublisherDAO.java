package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.training.entities.domain.Publisher;

public class PublisherDAO implements Serializable{

	private static final long serialVersionUID = -8417878625349721996L;
	
	public void addPublisher(Publisher pub) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "Insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		
		stmt.setString(1, pub.getPublisherName());
		stmt.setString(2, pub.getPublisherAddress());
		stmt.setInt(3, pub.getPublisherPhone());
		
		stmt.executeUpdate();
		
	}
	
	//Updates the publisher name in specified address
	public void updatePublisher(Publisher pub) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "update tbl_publisher set publisherName = ?, publisherAddress=?, publisherPhone =? where publisherId = ?";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		
		stmt.setString(1, pub.getPublisherName());
		stmt.setString(2, pub.getPublisherAddress());
		stmt.setInt(3, pub.getPublisherPhone());
		stmt.setInt(4, pub.getPublisherId());
		
		stmt.executeUpdate();
	}
	
	public void delPublisher(Publisher pub) throws SQLException{
		Connection conn = getConnection();
		
		String delQuer = "Delete from tbl_publisher where publisherId = ?;";
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, pub.getPublisherId());
		stmt.executeUpdate();
		
	}
	
	
	
	public Connection getConnection() throws SQLException{
		
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return conn;
	}
	
}
