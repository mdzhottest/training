package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

import com.gcit.training.lws.domain.Author;
import com.gcit.training.lws.domain.Book;


public class AuthorDAO extends BaseDAO<Author> implements Serializable{

	private static final long serialVersionUID = -4117083832091468714L;
	
	//getting the connection
	public AuthorDAO(Connection conn){
		super(conn);
	}
	
	
	//Adds the a new author value to the table
	public void addAuthor(Author author) throws SQLException{
		save("Insert into tbl_author (authorName) values (?)", new Object[]{author.getAuthorName()});
	}
	
	public void updateAuthor(Author a) throws SQLException{
		save("Update tbl_author set authorName = ? where authorId =?", new Object[]{a.getAuthorName(), a.getAuthorId()});
	}
	
	public void delAuthor (Author a) throws SQLException{
		save("Delete from tbl_author where authorId = ?", new Object[] {a.getAuthorId()});
	}
	
	public Author readOne(int authorId) throws SQLException{
		
		@SuppressWarnings("unchecked")
		List<Author> a = (List<Author>) read("Select * from tbl_author where authorId = ?", new Object[]{authorId});
		
		if (a !=null && a.size()>0){
			return a.get(0);
		}else{
			return null;
		}
	}
	
	public Author readByName(String authorName) throws SQLException{
		
		@SuppressWarnings("unchecked")
		List<Author> a = (List<Author>) read("Select * from tbl_author where authorName = ?",new Object[]{authorName} );
		
		if(a != null && a.size()>0){
			return a.get(0);
			
		}else{
			return null;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws SQLException{
		return (List<Author>) read("select * from tbl_author", null);
	}
	




	@Override
	protected List<Author> mapResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Author> a = new ArrayList<Author>();
		BookDAO bd = new BookDAO(conn);
		while (rs.next()){
			Author b = new Author();
			b.setAuthorId(rs.getInt("authorId"));
			b.setAuthorName(rs.getString("authorName"));

			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bd.readFirstLevel("select * from tbl_book where bookId in "
					+ "(select bookId from tbl_book_authors where authorId = ?)", new Object[]{b.getAuthorId()});
			b.setBooks(books);
			
			a.add(b);
		}
		return a;
	}


	@Override
	protected List<Author> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Author> a = new ArrayList<Author>();
		while (rs.next()) {
			Author b = new Author();
			b.setAuthorId(rs.getInt("authorId"));
			b.setAuthorName(rs.getString("authorName"));

			a.add(b);
		}
		
		return a;
	}
	
	
//	@Override
//	protected List<Author> createResultsFirst(ResultSet rs) throws SQLException {
//		List<Author> authors = new ArrayList<Author>();
//		while(rs.next()){
//			Author a = new Author();
//			a.setAuthorId(rs.getInt("authorId"));
//			a.setAuthorName(rs.getString("authorName"));
//			
//			authors.add(a);
//		}
//		return authors;
	//}
}
	

