package com.gcit.lms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	//***********to be disregarded****//
	@Autowired
	private AdministratorService adminService;
	//**************to be disregarded******//
	
	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private PublisherDAO pubDAO;

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private LibraryBranchDAO libDAO;
	
	@Autowired
	private BookLoanDAO bkLoanDAO;
	
	@Autowired
	private BookCopyDAO bkCpDAO;
	
	@Autowired
	private BorrowerDAO brDAO;
	
	@Autowired
	private GenreDAO gDAO;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//________________________________________________________________//
	//-----------------Start the author methods-----------------------//
	//___________________________________________________________________//
	
	@Transactional
	@RequestMapping(value = "/author/add", method = RequestMethod.POST, 
	consumes = "application/json")
	public String addAuthor(@RequestBody Author author) {
		try {
			authorDAO.addAuthor(author);
			return "Author added successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed: " + e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/author/edit", method = RequestMethod.POST, 
	consumes = "application/json")
	public String editAuthor(@RequestBody Author author) {
		try {
			authorDAO.updateAuthor(author);;
			return "Author edited successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed: " + e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/author/delete", method = RequestMethod.POST, 
	consumes = "application/json")
	public String deleteAuthor(@RequestBody Author author) {
		try {
			authorDAO.removeAuthor(author);
			return "Author deleted successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "failed: " + e.getMessage();
		}
	}
	
	@RequestMapping(value="/author/get/{authorId}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public Author getAuthor(@PathVariable int authorId){
		try {
			return authorDAO.readOne(authorId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/authors/get/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Author> getAllAuthors(@PathVariable int pageNo,
			@PathVariable int pageSize){
		try{
			return authorDAO.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/authors/search/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Author> searchAuthors(@PathVariable String searchString, @PathVariable int pageNo,
			@PathVariable int pageSize){
		try {
			return authorDAO.searchAuthorByName(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	@RequestMapping(value="/author/searchCount/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchAuthorsCount(@PathVariable String searchString) {
		try {
			return authorDAO.searchAuthorByNameCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	//--------------------------End Author Methods---------------------//
	
	@Transactional
	@RequestMapping(value = "/book/add", method = RequestMethod.POST, 
	consumes = "application/json")
	public String addBook(@RequestBody Book b){
		try {
			bookDAO.addBook(b);
			return "Book added successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+ e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/book/edit", method = RequestMethod.POST, 
	consumes = "application/json")
	public String editBook(@RequestBody Book b){
		try {
			bookDAO.editBook(b);
			return "Book edited successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+ e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/book/delete", method = RequestMethod.POST, 
	consumes = "application/json")
	public String deleteBook(@RequestBody Book b){
		try {
			bookDAO.deleteBook(b);
			return "Book deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+ e.getMessage();
		}
	}
	
	@RequestMapping(value="/book/get/{bookId}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public Book getBook(@PathVariable int bookId) throws Exception {
		return bookDAO.readOne(bookId);
	}
	
	@RequestMapping(value="/books/get/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Book> getAllBooks(@PathVariable int pageNo,
			@PathVariable int pageSize){
		try{
			return bookDAO.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping(value="/books/getCount/", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int getBooksCount(){
		try {
			return bookDAO.readAllCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@RequestMapping(value="/books/search/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Book> searchBooks(@PathVariable String searchString,
			@PathVariable int pageNo, @PathVariable int pageSize){
		try {
			return bookDAO.searchBookByTitle(searchString, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/books/searchCount/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchBooksCount(@PathVariable String searchString){
		try {
			return bookDAO.searchBookByTitleCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();// TODO Auto-generated catch block
			return 0;
		}
	}
	
	//_______________________End of Book methods_________________//

	//********************************************************************
	
	//_______________________Start publisher methods_____________//
	@Transactional
	@RequestMapping(value = "/publisher/add", method = RequestMethod.POST, 
	consumes = "application/json")
	public String addPublisher(@RequestBody Publisher p){
		try {
			pubDAO.addPublisher(p);
			return "publisher added successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/publisher/edit", method = RequestMethod.POST, 
	consumes = "application/json")
	public String editPublisher(@RequestBody Publisher p){
		try {
			pubDAO.updatePublisher(p);
			return "publisher edited successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/publisher/delete", method = RequestMethod.POST, 
	consumes = "application/json")
	public String deletePublisher(@RequestBody Publisher p){
		try {
			pubDAO.removePublisher(p);
			return "publisher deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@RequestMapping(value="/publisher/get/{publisherId}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public Publisher getPublisher(@PathVariable int publisherId) throws Exception {
		return pubDAO.readOne(publisherId);
	}
	
	@RequestMapping(value="/publishers/get/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Publisher> getAllPublishers(@PathVariable int pageNo,
			@PathVariable int pageSize){
		try{
			return pubDAO.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
//	@RequestMapping(value="/publishers/getCount/", method ={RequestMethod.GET,
//			RequestMethod.POST}, produces = {"application/json"})
//	public int getpublishersCount(){
//		try {
//			return pubDAO.r();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
	
	@RequestMapping(value="/publishers/search/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Publisher> searchPublisher(@PathVariable String searchString,
			@PathVariable int pageNo, @PathVariable int pageSize){
		try {
			return pubDAO.searchPublisherByName(searchString, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/publishers/searchCount/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchPublishersCount(@PathVariable String searchString){
		try {
			return pubDAO.searchPublisherByNameCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();// TODO Auto-generated catch block
			return 0;
		}
	}
	

	//________________________End of publisher_______________________//
	
	//*********************************************************************//
	
	//-----------------Start Library Branches----------------------//
	@Transactional
	@RequestMapping(value = "/branch/add", method = RequestMethod.POST, 
	consumes = "application/json")
	public String addLibraryBranch(@RequestBody LibraryBranch lb){
		try {
			libDAO.addBranch(lb);
			return "Branch added successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/branch/edit", method = RequestMethod.POST, 
	consumes = "application/json")
	public String editLibraryBranch(@RequestBody LibraryBranch lb){
		try {
			libDAO.updateBranch(lb);
			return "Branch edited successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/branch/delete", method = RequestMethod.POST, 
	consumes = "application/json")
	public String deleteLibraryBranch(@RequestBody LibraryBranch lb){
		try {
			libDAO.removeBranch(lb);
			return "Branch deleted successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@RequestMapping(value="/branch/getId/{branchId}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public LibraryBranch getLiraryBranchById(@PathVariable int branchId) throws Exception {
		return libDAO.getBranchById(branchId);
	}
	
	@RequestMapping(value="/branch/getName/{branchName}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public LibraryBranch getLiraryBranchByName(@PathVariable String branchName) throws Exception {
		return libDAO.getBranchByName(branchName);
	}
	
	
	@RequestMapping(value="/branches/get/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<LibraryBranch> getAllBranches(@PathVariable int pageNo,
			@PathVariable int pageSize){
		try{
			return libDAO.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
//	@RequestMapping(value="/branches/getCount/", method ={RequestMethod.GET,
//			RequestMethod.POST}, produces = {"application/json"})
//	public int getBranchesCount(){
//		try {
//			return libDAO.readAllCount();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
	
	@RequestMapping(value="/branches/searchName/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<LibraryBranch> searchBranches(@PathVariable String searchString,
			@PathVariable int pageNo, @PathVariable int pageSize){
		try {
			return libDAO.searchBranchByName(searchString, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/branches/searchAddress/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<LibraryBranch> searchBranchesByAddress(@PathVariable String searchString,
			@PathVariable int pageNo, @PathVariable int pageSize){
		try {
			return libDAO.searchBranchByAddress(searchString, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/branches/searchCountName/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchBranchesCountName(@PathVariable String searchString){
		try {
			return libDAO.searchBranchByNameCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();// TODO Auto-generated catch block
			return 0;
		}
	}
	
	@RequestMapping(value="/branches/searchCountAddress/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchBranchesCountAddress(@PathVariable String searchString){
		try {
			return libDAO.searchBranchByAddressCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();// TODO Auto-generated catch block
			return 0;
		}
	}
	
	//____________________________End of Library Branches____________________________//
	
	//************************************************************************************//
	
	//-------------------------------------------Start Genre---------------------------//
	
	@Transactional
	@RequestMapping(value = "/genre/add", method = RequestMethod.POST, 
	consumes = "application/json")
	public String addGenre(@PathVariable Genre g){
		try {
			gDAO.addGenre(g);
			return "Genre added successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/genre/edit", method = RequestMethod.POST, 
	consumes = "application/json")
	public String editGenre(@PathVariable Genre g){
		try {
			gDAO.updateGenre(g);
			return "Genre updated successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/genre/delete", method = RequestMethod.POST, 
	consumes = "application/json")
	public String deleteGenre(@PathVariable Genre g){
		try {
			gDAO.removeGenre(g);
			return "Genre deleted successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed: "+e.getMessage();
		}
	}
	
//	@RequestMapping(value="/genre/get/{genreId}", method ={RequestMethod.GET,
//			RequestMethod.POST}, produces = {"application/json"})
//	public Genre getGenre(@PathVariable int genreId){
//		try {
//			return gDAO.readOne(genreId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	@RequestMapping(value="/genres/get/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Genre> getAllGenres(@PathVariable int pageNo,
			@PathVariable int pageSize){
		try{
			return gDAO.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/genres/search/{searchString}/{pageNo}/{pageSize}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public List<Genre> searchGenres(@PathVariable String searchString, @PathVariable int pageNo,
			@PathVariable int pageSize){
		try {
			return gDAO.searchGenreByName(searchString, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	@RequestMapping(value="/genres/searchCount/{searchString}", method ={RequestMethod.GET,
			RequestMethod.POST}, produces = {"application/json"})
	public int searchGenresCount(@PathVariable String searchString) {
		try {
			return gDAO.searchGenreByNameCount(searchString);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
