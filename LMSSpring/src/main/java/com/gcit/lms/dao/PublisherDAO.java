package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Publisher;


@Repository
public class PublisherDAO extends BaseDAO<Publisher> implements Serializable, ResultSetExtractor <List<Publisher>>{
	
	private static final long serialVersionUID = 1619700647002508164L;

	
	public void addPublisher(Publisher publisher) throws SQLException {
		template.update("insert into tbl_publisher (publisherName) values (?)",
				new Object[] { publisher.getName() });

	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		template.update("update tbl_publisher set publisherName = ? where publisherId = ?",
				new Object[] { publisher.getName(), publisher.getId() });
	}

	public void removePublisher(Publisher publisher) throws SQLException {
		template.update("delete from tbl_publisher where publisherId=?",
				new Object[] { publisher.getId() });
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Publisher> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Publisher>) template.query("select * from tbl_publisher", this);
	}

	public Publisher readOne(int publisherId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Publisher> publishers = (List<Publisher>) template.query(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId }, this);
		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setId(rs.getInt("publisherId"));
			a.setName(rs.getString("publisherName"));

			publishers.add(a);
		}
		return publishers;
	}
	
	public List<Publisher> searchPublisherByName(String searchString, int pageNo,
			int pageSize) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString == null || searchString.trim().length() == 0) {
			return readAll(pageNo, pageSize);
		} else {
			searchString = "%"+searchString+"%";
			return template.query("select * from tbl_publisher where publisherName like ?",
					new Object[] {searchString}, this);
		}
	}
	
	public int searchPublisherByNameCount(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (Integer) template.queryForObject(
				"select count(1) from tbl_publisher where publisherName like ?",
				new Object[] { searchString }, Integer.class);
	}
	
	//---------------------- End of Code -------------------------------//
}
