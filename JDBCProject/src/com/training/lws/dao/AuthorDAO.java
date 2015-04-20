package com.training.lws.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

import com.training.entities.domain.Author;


public class AuthorDAO implements Serializable{

	private static final long serialVersionUID = -4117083832091468714L;
	
	//Adds the a new author value to the table
	public void addAuthor(Author author) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "Insert into tbl_author (authorName) values (?)";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		
		stmt.setString(1, author.getAuthorName());
		stmt.executeUpdate();
		
	}
	
	//Updates the author name in specified address
	public void updateAuthor(Author a) throws SQLException{
		Connection conn = getConnection();
		
		String updateQuery = "update tbl_author set authorName = ? where authorId = ?";
		PreparedStatement stmt = conn.prepareStatement(updateQuery);
		stmt.setString(1, a.getAuthorName());
		stmt.setInt(2, a.getAuthorId());
		stmt.executeUpdate();
	}
	
	public void deleteAuthor(Author a) throws SQLException{
		Connection conn = getConnection();
		
		String delQuer = "Delete from tbl_author where authorId = ?;";
		PreparedStatement stmt = conn.prepareStatement(delQuer);
		
		stmt.setInt(1, a.getAuthorId());
		stmt.executeUpdate();
		
	}
	
	public Connection getConnection() throws SQLException{
		
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		return conn;
	}
	
	public List<Author> readAll() throws SQLException{
		String sel = "Select * from tbl_author";
		PreparedStatement stmt = getConnection().prepareStatement(sel);
		
		ResultSet rs = stmt.executeQuery();
		
		List<Author> authors = new ArrayList<Author>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			
			authors.add(a);
		}
		
		return authors;
		
	}
	
}
