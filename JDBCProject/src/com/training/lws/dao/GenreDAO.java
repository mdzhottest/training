package com.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.training.entities.domain.Genre;

public class GenreDAO implements Serializable{

	private static final long serialVersionUID = 7489462531280152524L;
	
	public void addGenre(Genre ge) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "Insert into tbl_genre (genreName) values (?)";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		
		stmt.setString(1, ge.getName());
		stmt.executeUpdate();
		
	}
	
	//Updates the author name in specified address
	public void updateGenre(Genre ge) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "update tbl_genre set genreName = ? where genreId = ?";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		stmt.setString(1, ge.getName());
		stmt.setInt(2, ge.getGenreId());
		stmt.executeUpdate();
	}
	
	public void deleteGenre(Genre ge) throws SQLException{
		Connection conn = getConnection();
		
		String delQuer = "Delete from tbl_genre where genreId = ?;";
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, ge.getGenreId());
		stmt.executeUpdate();
		
	}
	
	public Connection getConnection() throws SQLException{
		
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return conn;
	}
	
}
