package com.training.lws.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.training.entities.domain.Book;
import com.training.lws.service.AdminServices;

public class UpBook {
	AdminServices adServ = new AdminServices();
	Scanner input = new Scanner(System.in);
	
//	public void upBookMenu(){
//		int sel;
//		System.out.println("1) Add Book\n2)Update Book\n3)Delete Book"+
//				"\n4) Main menu");
//		System.out.println("Please make a selection from 1-4");
//		sel = input.nextInt();
//		input.next();
//		switch(sel){
//		case 1:
//			try{
//				this.addBook();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			break;
//		case 2:
//			try{
//				this.updateBook();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void updateBook() throws SQLException{
//		System.out.println("Updating books");
//		Book book = null;
//		System.out.println("Search for boooks to update by title or id \n 1. Title \n2.Title");
//		int choice = input.nextInt();
//		if (choice == 1){
//			System.out.println("enter title");
//			String t = input.next();
//			List<Book> bk = adServ.readAllTitles(t);
//			int num = 1;
//			for(Book a: book){
//				
//			}
//		}
//	}
}
