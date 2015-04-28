package com.gcit.training.lws.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.lws.domain.Author;
import com.gcit.training.lws.domain.Book;
import com.gcit.training.lws.domain.Borrower;
import com.gcit.training.lws.domain.Genre;
import com.gcit.training.lws.domain.LibraryBranch;
import com.gcit.training.lws.domain.Publisher;
import com.gcit.training.lws.service.AdministratorService;

/**
 * Servlet implementation class ManagementSystemServlet
 */
@WebServlet({"/addAuthor", "/addPublisher", "/deleteAuthor", "/addBook", "/editAuthor",
	"/deleteBook", "/editBook", "/editBorrower", "/addBorrower", "/deleteBranch", "/deleteBorrower",
	"/addBranch", "/editGenre"})
public class ManagementSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagementSystemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**********************************************************************************************************
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 **********************************************************************************************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String func = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		
		switch(func){
			case "/deleteAuthor":{
				deleteAuthor(request);
				break;
			}
			case "/deleteBook":{
				deleteBook(request);
				break;
			}
			case "/deleteBranch":{
				deleteBranch(request);
				break;
			}
			case "deleteBorrower":{
				deleteBorrower(request);
				break;
			}
			case "editAuthor":{
				editAuthor(request);
				break;
			}
			case "editBook":{
				editBook(request);
				break;
			}
			default:{
				
				break;
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		rd.forward(request, response);
		
	}

	private void editBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String pubId = request.getParameter("pubId");
		Publisher b = null;
		Book a = new Book();
		try{
			b = new AdministratorService().readOne(Integer.parseInt(pubId));
		}catch (Exception e){
			e.printStackTrace();
		}
		a.setBookid(Integer.parseInt(bookId));
		a.setTitle(title);
		a.setPublisher(b);
		try{
			new AdministratorService().updateBook(a);
			request.setAttribute("result", "Editing books passed!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Books could not be edited: "+e.getMessage());
		}
	}

	private void deleteBranch(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String branchId = request.getParameter("branchId");
		LibraryBranch b = new LibraryBranch();
		b.setLibraryId(Integer.parseInt(branchId));
		
		try{
			new AdministratorService().deleteBranch(b);
			request.setAttribute("result", "Library branch removed sucessfully!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Library branch removal failed!: "+ e.getMessage());
		}
	}

	private void deleteBorrower(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cardNo = request.getParameter("cardNo");
		Borrower b = new Borrower();
		b.setCardNo(Integer.parseInt(cardNo));
		
		try{
			new AdministratorService().deleteBorrower(b);
			request.setAttribute("result", "Borrower removed sucessfully!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Borrower remove failed!: "+ e.getMessage());
		}
	}

	private void editAuthor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authorId = request.getParameter("authorId");
		String authorName = request.getParameter("authorName");
		
		Author a = new Author();
		a.setAuthorId(Integer.parseInt(authorId));
		a.setAuthorName(authorName);
		try{
			new AdministratorService().updateAuthor(a);
			request.setAttribute("result", "Editing books passed!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Author could not be eddited: "+e.getMessage());
		}
	}

	private void deleteBook(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bkid = request.getParameter("bookId");
		Book b = new Book();
		b.setBookid(Integer.parseInt(bkid));
		
		try{
			new AdministratorService().deleteBook(b);
			request.setAttribute("result", "Book removed sucessfully!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Book remove failed!: "+ e.getMessage());
		}
	}

	private void deleteAuthor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authorId = request.getParameter("authorId");
		Author a = new Author();
		a.setAuthorId(Integer.parseInt(authorId));
		
		try{
			new AdministratorService().deleteAuthor(a);
			request.setAttribute("result", "Author removed sucessfully!");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Author remove failed!: "+ e.getMessage());
		}
	}
	
	
	

	/***********************************************************************************************************
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 ***********************************************************************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String func = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		
		switch(func){
			case "/addAuthor":{
				addAuthor(request);
				break;
			}
			case "/addPublisher":{
				addPublisher(request);
				break;
			}
			case "/addBook":{
				addBook(request);
				break;
			}
			case "/addBranch":{
				addLibrary(request);
				break;
			}
			case "/addBorrower":{
				addBorrower(request);
				break;
			}
			case "/addGenre":{
				addGenre(request);
				break;
			}
			case"/editBorrower":{
				editBorrower(request);
				break;
			}
			case "/editGenre":{
				editGenre(request);
				break;
			}
			default:{
				break;
			}
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
		rd.forward(request, response);
		//-----------------------------------------------------
	}

		
	
	
		private void editBorrower(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String borrowerId = request.getParameter("borrowerId");
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		
		Borrower b = new Borrower();
		
		b.setCardNo(Integer.parseInt(borrowerId));
		b.setBorrowerName(borrowerName);
		b.setBorrowerAddress(borrowerAddress);
		b.setBorrowerPhone(borrowerPhone);
		try{
			new AdministratorService().updateBorrower(b);
			request.setAttribute("result", "Editing Borrower passed");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Genre could not be edited: "+e.getMessage());
		}
		
	}

		private void editGenre(HttpServletRequest request) {
		// TODO Auto-generated method stub
			String genreId = request.getParameter("genreId");
			String genreName = request.getParameter("genreName");
			Genre a = new Genre();
		
			a.setGenreId(Integer.parseInt(genreId));
			a.setName(genreName);
			try{
				new AdministratorService().updateGenre(a);
				request.setAttribute("result", "Editing genre passed!");
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("result", "Genre could not be edited: "+e.getMessage());
			}
	}

		private void addBorrower(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bName = request.getParameter("borrowerName");
		String bPhone = request.getParameter("borrowerPhone");
		String bAddress = request.getParameter("borrowerAddress");
		
		Borrower b = new Borrower();
		b.setBorrowerName(bName);
		b.setBorrowerPhone(bPhone);
		b.setBorrowerAddress(bAddress);
		
		try{
			new AdministratorService().addBorrower(b);
			request.setAttribute("result", "Borrower added successfully");
		}catch( Exception e){
			e.printStackTrace();
			request.setAttribute("result", "adding borrower failed!: "+ e.getMessage());
		}
	}

		private void addLibrary(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		
		LibraryBranch l = new LibraryBranch();
		l.setLibraryName(branchName);
		l.setLibraryAddress(branchAddress);
		
		try{
			new AdministratorService().addLibrary(l);;
			request.setAttribute("result", "Branch added successfully");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Adding Branch failed!: "+ e.getMessage());
		}
	}

		private void addPublisher(HttpServletRequest req){
			String publisherName = req.getParameter("publisherName");
			String publisherAddress = req.getParameter("publisherAddress");
			String publisherPhone = req.getParameter("publisherPhone");
			
			Publisher p = new Publisher();
			p.setPublisherName(publisherName);
			p.setPublisherAddress(publisherAddress);
			p.setPublisherPhone(publisherPhone);
			
			try{
				new AdministratorService().addPub(p);
				req.setAttribute("result", "Publisher added successfully");
			}catch(Exception e){
				e.printStackTrace();
				req.setAttribute("result", "Adding publisher failed!: "+ e.getMessage());
			}
			
		}
		
		
		private void addBook(HttpServletRequest req){
			String title = req.getParameter("publisherName");
			String pubid = req.getParameter("publisherId");
			
			Publisher p = new Publisher();
			p.setPublisherId(Integer.parseInt(pubid));
			Book b = new Book();
			b.setTitle(title);
			b.setPublisher(p);
						
			try{
				new AdministratorService().addBook(b);
				req.setAttribute("result", "Book added successfully");
			}catch(Exception e){
				e.printStackTrace();
				req.setAttribute("result", "Adding Book failed!: "+ e.getMessage());
			}
				
		}
		
		
		private void addAuthor(HttpServletRequest request) {
			String authorName = request.getParameter("authorName");

			
			Author author = new Author();
			author.setAuthorName(authorName);

			try {
				new AdministratorService().addAuthor(author);
				request.setAttribute("result", "Author added succesfully!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("result",
						"Author add failed!: " + e.getMessage());
			}
		}
		
//		private void addBranch(HttpServletRequest request){
//			String branchName = request.getParameter("branchName");
//			String branchAddress = request.getParameter("branchAddress");
//			
//			LibraryBranch lb = new LibraryBranch();
//			lb.setLibraryName(branchName);
//			lb.setLibraryAddress(branchAddress);
//			
//			try{
//				new AdministratorService().addLibrary(lb);
//				request.setAttribute("Result", "Branch added successfully!");
//				
//			}catch(Exception e){
//				e.printStackTrace();
//				request.setAttribute("result",
//						"Library add failed!: " + e.getMessage());
//			}
//			
	//}
		
		private void addGenre(HttpServletRequest request){
			String gName = request.getParameter("genreName");
			
			Genre gn = new Genre();
			
			gn.setName(gName);
			
			try{
				new AdministratorService().addGenre(gn);
				request.setAttribute("result", "Genre added sucessfully!");
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("result", "Genre failed to add!: "+e.getMessage());
			}
		}
}
