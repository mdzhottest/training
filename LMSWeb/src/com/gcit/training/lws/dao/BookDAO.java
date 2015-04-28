package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.Author;
import com.gcit.training.lws.domain.Book;

public class BookDAO extends BaseDAO<Book> implements Serializable {

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = -5675634058437817697L;
	
	
	//adds book to table
	public void addBook(Book bk) throws SQLException{
		Integer pubId = null;
		
		if (bk.getPublisher() != null){
			pubId = bk.getPublisher().getPublisherId();
		}
		int bkId = saveWithId("insert Into tbl_book (title, pubId) values (?,?)", 
				new Object[] {bk.getTitle(), pubId});
		
		
		for (Author a: bk.getAuthors()){
			save("INSERT INTO tbl_book_authors (bookId, authorId) values (?,?)", new Object[]{bkId, a.getAuthorId()});
		}
	}
	
	//updating the books
	public void updateTitle(Book a) throws SQLException{
//		Connection conn = getConnection();
//		String upQuer = "Update tbl_book set title = ? where bookId = ?";
//		
//		PreparedStatement stmt = conn.prepareStatement(upQuer);
//		
//		stmt.setString(1, a.getTitle());
//		stmt.setInt(2, a.getBookid());
//		stmt.executeUpdate();
		
		String quer = "Update tbl_book set title=? where bookId = ?";
		save(quer, new Object[]{a.getTitle()});
	}
	
	
	public void updatePublisher (Book a) throws SQLException{
		String quer = "Update tbl_book set pubId =? where bookId = ?";
		save(quer, new Object[]{a.getPublisher().getPublisherId(), a.getBookid()});
	}
	
	public void delBook (Book a) throws SQLException{
		save("DELETE FROM tbl_book where bookId = ?",new Object[]{a.getBookid()});
	}
	
	
	public Book readOne(int bookId) throws SQLException{
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) read("Select from tbl_book where bookId = ?",
				new Object[]{bookId});
		
		if (books != null && books.size()>0){
			return books.get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readAllByTitle(String title) throws SQLException {
		return (List<Book>) read("select * from tbl_book where title=?",new Object[]{title});
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws SQLException{
		return (List<Book>) read("Select * FROM tbl_book", null);
	}
	
	@Override
	protected List<Book> mapResults(ResultSet rs)throws SQLException{
		List <Book> books = new ArrayList<Book>();
		PublisherDAO pD = new PublisherDAO(conn);
		AuthorDAO aD = new AuthorDAO(conn);
		
		while(rs.next()){
			Book b = new Book();
			b.setBookid(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pD.readOne(rs.getInt("pubId")));
			
			@SuppressWarnings("unchecked")
			List<Author> a = (List<Author>) aD.read("Select * from tbl_author where authorId in (SELECT authorId from tbl_book_authors where bookId = ?)", 
					new Object[]{b.getBookid()});
			
			b.setAuthors(a);
			
			books.add(b);	
		}
		
		return books;
		 
	}
	
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
		
		return conn;
	}



	@Override
	protected List<Book> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pDAO = new PublisherDAO(conn);
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookid(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			
			books.add(b);
		}
		return books;
		// TODO Auto-generated method stub
		
	}

	public void updateBook(Book a) throws SQLException{
		// TODO Auto-generated method stub
		String quer = "Update tbl_book set title=?, pubId=? where bookId = ?";
		save(quer, new Object[]{a.getTitle(), a.getPublisher().getPublisherId(), a.getBookid()});
	}
	
}
