package com.DatabaseConnection.Library;

import java.sql.*;
import java.util.*;

public class Connections {

	protected Connection conn;
	protected Statement stmt;
	protected Scanner input = new Scanner(System.in);
	public ResultSet rs;
	
	//adding data to table
	public String addBook = "Insert INTO tbl_book (title) values (?);", addAut = "Insert INTO tbl_author (authorName) values (?);", 
			addPub = "Insert INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?);", 
			addBranch = "Insert INTO tbl_library_branch values (branchName, branchAddress) values(?,?);", 
			addBorr = "Insert INTO tbl_borrower (name, address, phone) values (?,?,?)";
	
	//updating data in table by their soecified ID
	public String upTitleQuer="Update tbl_book set title = ? where bookId = ?";
	public String upAuthorQuer = "Update tbl_author set authorName = ? where authorId = ?",
			upPub = "Update tbl_publisher set publisherName = ? where publisherId = ?", upBran = "Update tbl_library_branch set branchName = ? where branchId = ?",
			upBorr = "Update tbl_borrower set borrowerName = ?, borrowerAddress = ?, borrowerPhone = ? where cardNo = ?";
	
	//deleting rows from specified tables. prepared statememnts
	public String delbook = "Delete from tbl_book where bookId = ?", delAuthor = "Delete from tbl_author where authorId = ?", 
			delPub = "Delete from tbl_publisher where publisherId = ?", delBorr = "Delete From tbl_borrower where cardNo = ?";
	
	PreparedStatement upPrStmt;
	
	public Connections() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "Eminem12#");
			stmt = conn.createStatement();
			ResultSet rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public void adminMode() {
		int c;
		boolean con = false;
		do {
			System.out.println("1. Add/Update/Delete book and author");
			System.out.println("2. Add/Update/Delete publishers");
			System.out.println("3. Add/Update/Delete library branch");
			System.out.println("4. Add/Update/Delete borrowers");
			System.out.println("5. Over-ride due date for a book loan");
			System.out
					.println("Please input the number index for your choice: ");
			try{
				c = input.nextInt();
				switch (c){
				case 1:{
					int ch;
					System.out.println("1: Add book and author");
					System.out.println("2: Update book and author");
					System.out.println("3: Delete book and author");
					System.out.println("Please input number index for your choice: ");
					ch = input.nextInt();
					//book and author
					switch(ch){
						//adding books and authors
						case 1:{
							int ch1;
							System.out.println("1. Add book");
							System.out.println("2. Add author");
							System.out.println("Please input number index for your choice: ");
							ch1 = input.nextInt();
							switch (ch1){
								case 1:{
									System.out.println("Please input a new book");
									String value = input.nextLine();
									try {
										stmt.executeUpdate("Insert into tbl_book (title) values('"+ value+"')");
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								}
								case 2:{
								System.out.println("Please input a new author name");
								String value = input.nextLine();
								
								try {
									stmt.executeUpdate("Insert into tbl_author (authorName) values('"+value+"')");
									
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
								
								}
								default:{
									System.out.println("Please input value from 1-2");
												break;
								}
							}
						}
						//updating books and authors
						case 2:{
						int ch2;
						System.out.println("1. Update book");
						System.out.println("2. Update Author");
						System.out.println("Please input number index for your choice: ");
						ch2 = input.nextInt();
						
							switch (ch2){
								case 1:{
								int index=0;
								String bookName= "";
								String nullHolder;
								
								try {
									System.out.println("Select chosen index");
									index = input.nextInt();
									nullHolder = input.nextLine();
									System.out.println("Select new book name");
									bookName = input.next();
									nullHolder = input.nextLine();
									
									upPrStmt = conn.prepareStatement(upTitleQuer);
									
									upPrStmt.setString(1, bookName);
									upPrStmt.setInt(2, index);
									
									upPrStmt.executeUpdate(upTitleQuer);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								break;
							
								}
								case 2:{
									int index;
									String authorName;
									System.out.println("Select chosen index");
									index = input.nextInt();
									
									System.out.println("Select new author name");
									authorName = input.nextLine();
									
									try {
										upPrStmt.setString(1, authorName);
										upPrStmt.setInt(2, index);
										
										upPrStmt.executeUpdate(upAuthorQuer);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									break;
							
								}
								default:{
									System.out.println("Please input a number within the range");
									break;
								}
							}
						}
						//deleting books and authors
						case 3:{
							int ch3;
									System.out.println("What do you want to delete");
									System.out.println("1. Books");
									System.out.println("2. Author");
									ch3 = input.nextInt();
							switch(ch3){
								case 1:{
									int bookID;
									System.out.println("Enter book id to be deleted");
									bookID = input.nextInt();
									
									try{
										upPrStmt.setInt(1, bookID);
										upPrStmt.executeUpdate(delbook);
									}catch(SQLException e){
										e.printStackTrace();
									}
									break;
							
								}
								case 2:{
									int authorId;
									System.out.println("Enter id of author to be deleted");
									authorId = input.nextInt();
									
									try{
										upPrStmt.setInt(1, authorId);
										upPrStmt.executeUpdate(delAuthor);
									}catch(SQLException e){
										e.printStackTrace();
									}
									break;
								
								}
								default:{
									System.out.println("Please input choice 1 or 2");
											break;
								}
							}
						break;
						}
						default:{
							System.out.println("Please choose the correct index");
							break;
						}
					}
					
				}
				
				//publishers
				case 2:{
				
				}
				case 3:{
				
				}
				case 4:{
				
				}
				case 5:{
				
				}
				default:{
				
				}
			
				
			}}catch(NumberFormatException e){
				
			}
		} while (con);
	
}}
	