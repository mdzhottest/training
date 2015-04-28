package com.training.lws.client;

import java.util.Scanner;

import com.training.lws.service.AdminServices;

public class Admin {

		Scanner input = new Scanner(System.in);
		
		public Admin(){
			System.out.println("Administrator Menu");
		}
		
		public void adminSelect(){
			int sel = 0;
			System.out.println("\n1)Add/Update/Delete Book and Author\n"
					+ "2)Add/Update/Delete Publishers \n"
					+ "3)Add/Update/Delete Library Branches\n"
					+ "4)Add/Update/Delete Borrowers\n"
					+ "5)Over-ride Due Date for a Book Loan");
			System.out.println("Enter a number from menu");
			sel=input.nextInt();
			input.next();
			
			switch(sel){
			case 1:{
				UpBook ubk = new UpBook();
				//ubk.upBookMenu();
			}
			}
		}
		
}
