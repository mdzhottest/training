package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

@Repository
public class BookDAO extends BaseDAO<Book> implements Serializable,
		ResultSetExtractor<List<Book>> {

	@Autowired
	PublisherDAO pDAO;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addBook(Book bk) throws SQLException {

		Integer pubId = null;
		if (bk.getPublisher() != null)
			pubId = bk.getPublisher().getId();

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update("insert into tbl_book (title, pubId) values (?,?)",
				new Object[] { bk.getTitle(), pubId }, keyHolder);

		int bookId = keyHolder.getKey().intValue();

		for (Author a : bk.getAuthors()) {
			template.update(
					"insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}
	}
	
	public void editBook(Book bk) throws Exception{
		template.update("Update tbl_book set title=? where bookId =?", new Object[]{bk.getTitle(), bk.getBookId()});
	}
	
	
	public void deleteBook(Book bk) throws Exception{
		template.update("Delete From tbl_book where bookId = ?", new Object[]{bk.getBookId()});
	}
//	public void updateAuthor(Book book) throws SQLException {
//	}
//
//	public void removeAuthor(Book book) throws SQLException {
//	}

	public List<Book> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Book>) template.query("select * from tbl_book", this);
	}

	public int readAllCount() throws SQLException {
		return (Integer) template.queryForObject("select count(1) from tbl_book",
				Integer.class);
	}

	public Book readOne(int bookId) throws SQLException {
		List<Book> books = (List<Book>) template.query(
				"select * from tbl_book where bookId = ?",
				new Object[] { bookId }, this);
		if (books != null && books.size() > 0) {
			return books.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();

		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			books.add(b);
		}
		return books;
	}

	public List<Book> searchBookByTitle(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%" + searchString + "%";
			return template.query("select * from tbl_book where title like ?",
					new Object[] { searchString }, this);
		}
	}

	public int searchBookByTitleCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_book where title like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	//---------------------- End of Code -------------------------------//
}
