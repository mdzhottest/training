package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.Author;

public class AuthorDAO extends BaseDAO<Author> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356102358186501116L;
	
	//establishes connection
	public AuthorDAO(Connection conn){
		super(conn);
	}
	
	
	//adding authors to table
	public void addAuthor(Author a) throws SQLException{
		save("insert into tbl_author (authorName) values (?)",
				new Object[]{a.getAuthorName()});
	}
	
	//updating a specific author
	public void updateAuthor(Author a) throws SQLException{
		save("Update tbl_author set authorName = ? where authorId = ?",
				new Object[]{a.getAuthorName(), a.getAuthorId()});
	}
	
	//deleting author
	public void removeAuthor(Author a) throws SQLException{
		save("Delete from tbl_author where authorId = ?",
				new Object[]{a.getAuthorId()});
	}
	
	//creates a list of all authors in the database
	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws SQLException{
		return (List<Author>) read("select * from tbl_author", null);
	}
	
	//searches and displayes only one author by Id
	public Author readOneByID(int authorId) throws SQLException{
		@SuppressWarnings("unchecked")
		List<Author> auth = (List<Author>) read("select * from tbl_author where authorId =?", new Object[]{authorId});
		if (auth != null && auth.size()>0){
			return auth.get(0);
					
		}else{
			return null;
		}
	}
	
	//searches and displayes by author name
	public Author readOneByAuthorName(String authorName)throws SQLException{
		@SuppressWarnings("unchecked")
		List<Author> a = (List<Author>) 
				read("selecct * from tbl_author where authorName = ?", 
						new Object[]{authorName});
		if(a !=null && a.size()>0){
			return a.get(0);
		}else{
			return null;
		}
	}
	
	
	
	//gets results from query using base DAO
	@Override
	protected List<Author> mapResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Author> authors = new ArrayList<Author>();
		BookDAO bD = new BookDAO(conn);
		
		while(rs.next()){
			Author a =  new Author();
			a.getAuthorId(rs.getInt(arg0));
		}
	}

	@Override
	protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}