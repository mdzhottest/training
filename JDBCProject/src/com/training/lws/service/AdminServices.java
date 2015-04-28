package com.training.lws.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import com.training.entities.domain.Author;
import com.training.entities.domain.Book;
import com.training.entities.domain.Publisher;
import com.training.lws.dao.AuthorDAO;
import com.training.lws.dao.BookDAO;
import com.training.lws.dao.PublisherDAO;

public class AdminServices implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1846211640304694883L;
	
	
	
	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Eminem12#"); 
		return conn;
	}
	
	//reads all books
	public List <Book> readBooks() throws SQLException{
		Connection conn = this.getConnection();
		BookDAO bkDao = new BookDAO(conn);
		List<Book> books = bkDao.readAll();
		
		return books;
	}
	
	//adds a book to the database
	public void addBooks(Book bk) throws Exception{
		Connection conn= this.getConnection();
		BookDAO bkD= new BookDAO(conn);
		
		if(bk.getTitle()==null){
			throw new Exception("Title must not be null");
		}
		bkD.addBook(bk);
		
	}
	
	
	public Author readAuthorOne(int authorId) throws SQLException{
		Connection conn = this.getConnection();
		
		Author a = new AuthorDAO(conn).readOne(authorId);
		return a;
	}
	
	public Author readThruName(String aName) throws SQLException{
		Connection conn = this.getConnection();
		
		Author a = new AuthorDAO(conn).readByName(aName);
		
		return a;
	}
	
	public Publisher readPubOnce(int pubId) throws SQLException{
		Connection conn = this.getConnection();
		Publisher pub = new PublisherDAO(conn).readOne(pubId);
		return pub;
	}
	
	
	public Book readBookbyIdOnce(int bkId) throws SQLException{
		Connection conn = this.getConnection();
		BookDAO bkD = new BookDAO(conn);
		Book bk = bkD.readOne(bkId);
		return bk;
		
	}
	
	public List<Book> readAllTitles(String tit) throws SQLException{
		Connection conn = this.getConnection();
		BookDAO bkD = new BookDAO(conn);
		List<Book> bks = bkD.readAllByTitle(tit);
		
		return bks;
	}
	
	public void delBook(Book book) throws SQLException{
		Connection conn = this.getConnection();
		BookDAO bkd = new BookDAO(conn);
		bkd.delBook(book);
	}

	
	public void addAuthor(Author a) throws SQLException, ClassNotFoundException{
		Connection conn = ConnectionUtil.getConnection();
	}
	
	
	
	//public AdminServices(){
		//int choice;
//		Scanner input = new Scanner(System.in);
//		System.out.println("Welcome to the admin page");
//		System.out.println("Select from the following options");
//		System.out.println("1. Add/Update/Delete book and authors");
//		System.out.println("2. Add/Update/Delete publishers");
//		System.out.println("3. Add/Update/Delete library branch");
//		System.out.println("4. Add/Update/Delete borrowers");
//		System.out.println("5. Override Due date for book loan");
//		choice = input.nextInt();
//		
//		//choosing to add
//		switch(choice){
//		case 1:{
//				System.out.println("Select from the following options");
//				System.out.println("1. Add book");
//				System.out.println("2. Add Author");
//				System.out.println("3. Add book and author");
//				System.out.println("4. Update Book");
//				System.out.println("5. Update Author");
//				System.out.println("6. Update book and author");
//				System.out.println("7. Delete book");
//				System.out.println("8. Delete author");
//				int c;
//				c=input.nextInt();
//				switch (c){
//				case 1:
//				{
//					System.out.println("What is the title? ");
//					BookDAO b = new BookDAO(null);
//					input.nextLine();
//				}
//				//*************end of switch 2 case 1*******
//				}
//			//*************************end of swithc 1 case 1
//			}
//		//---------------------------------------------------//
//		}
//			
//		//choosing to update
//		
//		//choosing to delete
//		
//		//choosing to find specific author
//		
		
		
		
	//}

}
