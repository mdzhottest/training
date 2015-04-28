package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.Book;
import com.gcit.training.lws.domain.Genre;

public class GenreDAO extends BaseDAO<Genre> implements Serializable{

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 7489462531280152524L;
	
	public void addGenre(Genre ge) throws SQLException{
		String upQuer = "insert into tbl_genre (genre_name) values (?)";
		   save(upQuer, new Object[] {ge.getName()});
		
	}
	
	//Updates the author name in specified address
	public void updateGenre(Genre ge) throws SQLException{
		String upQuer = "update tbl_genre set genre_name = ? where genre_id = ?";
		save(upQuer,new Object[]{ ge.getGenreId(), ge.getName()});
	}
	
	public void deleteGenre(Genre ge) throws SQLException{
		String remQuer = "delete from tbl_genre where genre_id=?";
		save(remQuer, new Object[]{ge.getGenreId()});
		
	}
	
	public List<Genre> readAllGenre() throws SQLException {
		String select = "select * from tbl_genre";
		 @SuppressWarnings("unchecked")
		List<Genre> genres =(List<Genre>) read(select,null);

		return genres;
	}

	public Genre getGenreByName(String gName) throws SQLException{
		String select = "select * from tbl_genre where genre_name =?";
		@SuppressWarnings("unchecked")
		List<Genre> g =(List<Genre>) read(select,new Object[] {gName});
		if (g!=null&&g.size()>0){
		return g.get(0);
		}
		else return null;
	}
	
	public Genre getGenreById(int gId) throws SQLException{
		String select = "select * from tbl_genre where genre_id =?";
		@SuppressWarnings("unchecked")
		List<Genre> g =(List<Genre>) read(select,new Object[] {gId});
		if (g!=null&&g.size()>0){
		return g.get(0);
		}
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Genre> mapResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			Genre g = new Genre();
			int genreId = rs.getInt("genre_id");
			g.setGenreId(genreId);
			
			g.setName(rs.getString("genre_name"));
			
         	g.setBooks((List<Book>) new BookDAO(conn).read("select * from tbl_book where bookId in (select bookId from tbl_book_genre where genreId=?",
         			new Object[] {genreId} ));

			genres.add(g);
		}		
		return genres;
	}

	@Override
	protected List<Genre> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
