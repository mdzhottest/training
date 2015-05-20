/**
 * GenreDAO.java
 */
package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO<Genre> implements Serializable, ResultSetExtractor<List<Genre>>{

	private static final long serialVersionUID = 2688793748275673863L;
	

	public void addGenre(Genre genre) throws SQLException {
		String updateQuery = "insert into tbl_genre (genre_name) values (?)";
		   template.update(updateQuery, new Object[] {genre.getName()});

	}
	
	public void updateGenre(Genre genre) throws SQLException {

		String updateQuery = "update tbl_genre set genre_name = ? where genre_id = ?";
		template.update(updateQuery,new Object[]{ genre.getGenreId(), genre.getName()});

	}
	public void removeGenre(Genre genre) throws SQLException {
		String removeQuery = "delete from tbl_genre where genre_id=?";
		template.update(removeQuery, new Object[]{genre.getGenreId()});
	}
	
	
	public List<Genre> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		String select = "select * from tbl_genre";
		 List<Genre> genres =(List<Genre>) template.query(select,this);

		return genres;
	}

	
	
	public Genre getGenreByName(String genreName) throws SQLException{
		String select = "select * from tbl_genre where genre_name =?";
		@SuppressWarnings("unchecked")
		List<Genre> genres =(List<Genre>) template.query(select,new Object[] {genreName}, this);
		if (genres!=null&&genres.size()>0){
		return genres.get(0);
		}
		else return null;
	}
	
	
	public Genre getGenreById(int genreId) throws SQLException{
		String select = "select * from tbl_genre where genre_id =?";
		@SuppressWarnings("unchecked")
		List<Genre> genres = template.query(select,new Object[] {genreId}, this);
		if (genres!=null&&genres.size()>0){
		return genres.get(0);
		}
		else return null;
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			Genre genre = new Genre();
			int genreId = rs.getInt("genre_id");
			genre.setGenreId(genreId);
			
			genre.setName(rs.getString("genre_name"));
			
//			Book b = new Book();
//			b.setBookId(template.query(("select bookId from tbl_book_genre where genreId=?"), 
//					new  Object[]{rs.getInt("genreId"),);
//         	genre.setBooks(template.query("select * from tbl_book where bookId in ?",
//         			new Object[] {genreId}, this));

			genres.add(genre);
		}		
		return genres;
	}
	
	public List<Genre> searchGenreByName(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%" + searchString + "%";
			return template.query("select * from tbl_genre where genre_name like ?",
					new Object[] { searchString }, this);
		}
	}
	
	public int searchGenreByNameCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_genre where genre_name like ?",
				new Object[] {searchString}, Integer.class);
	}

}
