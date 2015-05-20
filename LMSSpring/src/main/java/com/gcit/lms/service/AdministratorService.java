package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;

@Service
public class AdministratorService {

	@Autowired
	private BasicDataSource ds;

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
	

	//__________Start Authors___________________________//
	@Transactional
	public void addAuthor(Author author) throws Exception {
		authorDAO.addAuthor(author);
	}

	@Transactional
	public void deleteAuthor(Author author) throws Exception {
		authorDAO.removeAuthor(author);
	}
	
	public Author getAuthor(int authorId) throws Exception {
		return authorDAO.readOne(authorId);
	}

	@Transactional
	public void editAuthor(Author author) throws Exception {
		authorDAO.updateAuthor(author);
	}

	public List<Author> getAuthors(int pageNo, int pageSize) throws Exception {
		AuthorDAO aDAO = authorDAO;
		return aDAO.readAll(pageNo, pageSize);
	}
	
	public List<Author> searchAuthors(String searchString, int pageNo, int pageSize)
			throws Exception {
		return authorDAO.searchAuthorByName(searchString, pageNo, pageSize);
	}
	
	public int searchAuthorsCount(String searchString) throws Exception {
		return authorDAO.searchAuthorByNameCount(searchString);
	}
	
	//*********************End Author***********************//
	
	
	//_______________________Start Books_______________________//
	@Transactional
	public void addBook(Book b) throws Exception {
		bookDAO.addBook(b);
	}
	
	@Transactional
	public void editBook(Book b) throws Exception{
		bookDAO.editBook(b);
	}
	
	@Transactional
	public void deleteBook(Book b) throws Exception{
		bookDAO.deleteBook(b);
	}
	
	public Book getBook(int bookId) throws Exception {
		return bookDAO.readOne(bookId);
	}
	
	public List<Book> getBooks(int pageNo, int pageSize) throws Exception {
		BookDAO bDAO = bookDAO;
		return bDAO.readAll(pageNo, pageSize);
	}

	public int getBooksCount() throws Exception {
		return bookDAO.readAllCount();
	}
	
	//Searching books part 1
	public List<Book> searchBooks(String searchString, int pageNo, int pageSize)
			throws Exception {
		return bookDAO.searchBookByTitle(searchString, pageNo, pageSize);
	}
	//searching books part 2
	public int searchBooksCount(String searchString) throws Exception {
		return bookDAO.searchBookByTitleCount(searchString);
	}
	
	//_______________________End of Book methods_________________//

	
	//_______________________Start publisher methods_____________//
	@Transactional
	public void addPublisher(Publisher p) throws Exception {
		pubDAO.addPublisher(p);
	}
	
	@Transactional
	public void editPublisher(Publisher p) throws Exception{
		pubDAO.updatePublisher(p);
	}
	
	@Transactional
	public void deletePublisher(Publisher p) throws Exception{
		pubDAO.removePublisher(p);
	}
	
	public Publisher getPublisher(int pubId) throws Exception{
		return pubDAO.readOne(pubId);
	}
	

	public List<Publisher> getPublishers(int pageNo, int pageSize) throws Exception {
		return pubDAO.readAll(pageNo, pageSize);
	}
	
	public List<Publisher> searchPublishers(String searchString, int pageNo, int pageSize)
			throws Exception {
		return pubDAO.searchPublisherByName(searchString, pageNo, pageSize);
	}
	
	public int searchPublishersCount(String searchString)
			throws Exception {
		return pubDAO.searchPublisherByNameCount(searchString);
	}

	//________________________End of publisher_______________________//
	
	//-----------------Start Library Branches----------------------//
	@Transactional
	public void addLibraryBranch(LibraryBranch lb) throws Exception{
		libDAO.addBranch(lb);
	}
	
	@Transactional
	public void editLibraryBranch(LibraryBranch lb) throws Exception{
		libDAO.updateBranch(lb);
	}
	
	@Transactional
	public void deleteLibraryBranch(LibraryBranch lb) throws Exception{
		libDAO.removeBranch(lb);
	}
	
	public LibraryBranch getBranch(int branchId)throws Exception{
		return libDAO.getBranchById(branchId);
	}
	
	public List<LibraryBranch> getBranches(int pageNo, int pageSize)throws Exception{
		return libDAO.readAll(pageNo, pageSize);
	}
	
	public List<LibraryBranch> searchBranches(String searchString, int pageNo, int pageSize)throws Exception{
		
		return libDAO.searchBranchByName(searchString, pageNo, pageSize);
	}
	
	public int seachBranchesCount(String searchString) throws Exception{
		return libDAO.searchBranchByNameCount(searchString);
	}

	//____________________________End of Library Branches____________________________//
	
	//-----------------------------Start Genre---------------------------//
	@Transactional
	public void addGenre(Genre g)throws Exception{
		gDAO.addGenre(g);
	}
	@Transactional
	public void editGenre(Genre g)throws Exception{
		gDAO.updateGenre(g);
	}
	@Transactional
	public void deleteGenre(Genre g)throws Exception{
		gDAO.removeGenre(g);
	}
	
	public Genre getGenre(int genreId)throws Exception{
		return gDAO.getGenreById(genreId);
	}
	
	public List<Genre> getGenres(int pageNo, int pageSize) throws Exception{
		return gDAO.readAll(pageNo, pageSize);
	}
	
	public List<Genre> searchGenres(String searchString, int pageNo, int pageSize) throws Exception{
		return gDAO.searchGenreByName(searchString, pageNo, pageSize);
		
	}
	
	public int searchGenreCount(String searchString) throws Exception{
		return gDAO.searchGenreByNameCount(searchString);
	}
	
	//____________________________________End of Genre____________________________________//
	
	//----------------------------Start  of Borrower----------------------------//
	@Transactional
	public void addBorrower(Borrower br) throws Exception{
		brDAO.addBorrower(br);
	}
	
	@Transactional
	public void editBorrower(Borrower br) throws Exception{
		brDAO.updateBorrower(br);
	}
	
	@Transactional
	public void deleteBorrower(Borrower br) throws Exception{
		brDAO.removeBorrower(br);
	}
	
	public Borrower getBorrower(int bId) throws Exception{
		return brDAO.getBorrowerByCardNo(bId);
	}
	
	public List<Borrower> getBorrowers(int pageNo, int pageSize) throws Exception{
		return brDAO.readAll(pageNo, pageSize);
	}
	
	public List<Borrower> searchBorrower(String searchString, int pageNo, int pageSize) throws Exception{
		return brDAO.searchBorrowerByName(searchString, pageNo, pageSize);
	}
	
	public int searchBorrowerCount(String searchString) throws Exception{
		return brDAO.searchBorrowerByNameCount(searchString);
	}
	
	//_________________________________End of Borrower_______________________________///

	
	//--------------------------------Start BookLoans------------------------------//
	public void addBookLoans(BookLoan bl) throws SQLException{
		bkLoanDAO.addBookLoan(bl);
	}
	
	//public void editDate()
	
	//-------------------------------End of Bookloans----------------------------------//
	
	//***********************End of file
}
