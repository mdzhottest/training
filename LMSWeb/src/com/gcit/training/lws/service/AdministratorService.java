package com.gcit.training.lws.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.lws.dao.AuthorDAO;
import com.gcit.training.lws.dao.BookDAO;
import com.gcit.training.lws.dao.BorrowerDAO;
import com.gcit.training.lws.dao.GenreDAO;
import com.gcit.training.lws.dao.LibraryBranchDAO;
import com.gcit.training.lws.dao.PublisherDAO;
import com.gcit.training.lws.domain.Author;
import com.gcit.training.lws.domain.Book;
import com.gcit.training.lws.domain.Borrower;
import com.gcit.training.lws.domain.Genre;
import com.gcit.training.lws.domain.LibraryBranch;
import com.gcit.training.lws.domain.Publisher;

public class AdministratorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779531377552718891L;
	/**
	 * 
	 */


	public void addAuthor(Author author) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		try {
			new AuthorDAO(conn).addAuthor(author);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	
	public List<Publisher> getPublishers() throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new PublisherDAO(conn).readAll();
		
	}
	
	public List<Author> getAuthors() throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new AuthorDAO(conn).readAll();
	}
	
	
	public Author getAuthor(int authorId)throws Exception{
		return new AuthorDAO(ConnectionUtil.getConnection()).readOne(authorId);
	}
	
	public List<Book> getBooks() throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new BookDAO(conn).readAll();
	}
	
	public Book getBook(int bookId) throws Exception{
		return new BookDAO(ConnectionUtil.getConnection()).readOne(bookId);
	}
	
	public List<Borrower>getBorrowers() throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new BorrowerDAO(conn).readAll();
		
	}
	public Borrower getBorrower(int cardNo) throws Exception{
		return new BorrowerDAO(ConnectionUtil.getConnection()).readOne(cardNo);
	}
	
	
	public void updateAuthor(Author a)throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		try{
			new AuthorDAO(conn).updateAuthor(a);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}
	}
	
	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException{
		BookDAO bkD = new BookDAO(ConnectionUtil.getConnection());
		List<Book> bks = bkD.readAll();
		return bks;
	}
	
	public Author readOneAuth(int authId) throws SQLException, ClassNotFoundException{
		Author a = new AuthorDAO(ConnectionUtil.getConnection()).readOne(authId);
		
		return a;
	}
	
	public Author readByNameOnce(String authName) throws SQLException, ClassNotFoundException{
		Author a = new AuthorDAO(ConnectionUtil.getConnection()).readByName(authName);
		return a;
		
	}
	
	public Book readByBookId(int bkId) throws SQLException, ClassNotFoundException{
		BookDAO bkD = new BookDAO(ConnectionUtil.getConnection());
		Book bk = bkD.readOne(bkId);
		return bk;
		
	}
	
	public List<Book> readAllByTitle(String title)throws SQLException, ClassNotFoundException{
		BookDAO bkD = new BookDAO(ConnectionUtil.getConnection());
		List<Book> bks = bkD.readAllByTitle(title);
		return bks;
		
	}
	
	public void deleteBook(Book book) throws SQLException, ClassNotFoundException{
		BookDAO bkD = new BookDAO(ConnectionUtil.getConnection());
		bkD.delBook(book);
	}
	
	public List<Author> readAllAuth() throws SQLException, ClassNotFoundException{
		Connection conn = ConnectionUtil.getConnection();
		List<Author> a = new AuthorDAO(conn).readAll();
		return a;
	}
	
	public Publisher readPubOnceById(int pubId) throws SQLException, ClassNotFoundException{
		Publisher pub = new PublisherDAO(ConnectionUtil.getConnection()).readOne(pubId);
		return pub;
	}
	
	public void addPub(Publisher Pub) throws SQLException, ClassNotFoundException{
		new PublisherDAO(ConnectionUtil.getConnection()).addPublisher(Pub);
	}

	public void addBook(Book bk) throws SQLException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		new BookDAO(ConnectionUtil.getConnection()).addBook(bk);
	}

	public void addLibrary(LibraryBranch lb)throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		new LibraryBranchDAO(ConnectionUtil.getConnection()).addBranch(lb);
		
	}
	
	public void addBorrower(Borrower br)throws SQLException, ClassNotFoundException{
		new BorrowerDAO(ConnectionUtil.getConnection()).addBorrower(br);
	}

	public void addGenre(Genre gn) throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		new GenreDAO(ConnectionUtil.getConnection()).addGenre(gn);
	}

	public void deleteAuthor(Author a) throws SQLException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		new AuthorDAO(ConnectionUtil.getConnection()).delAuthor(a);
	}

	public void deleteBorrower(Borrower b)throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		new BorrowerDAO(ConnectionUtil.getConnection()).delBorrower(b);
	}

	public void deleteBranch(LibraryBranch b) throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		new LibraryBranchDAO(ConnectionUtil.getConnection()).deleteBranch(b);
	}

	public void updateBook(Book a) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		try{
			new BookDAO(conn).updateBook(a);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}
	}
	
	public Publisher readOne(int publisherId) throws Exception{
		Connection conn = ConnectionUtil.getConnection();
		return new PublisherDAO(conn).readOne(publisherId);
	}

	public void updateBorrower(Borrower b) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		try{
			new BorrowerDAO(conn).upBorrower(b);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}
	}

	public void updateGenre(Genre a) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		try{
			new GenreDAO(conn).updateGenre(a);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
		}
	}




}
