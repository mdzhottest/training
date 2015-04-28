package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements Serializable{

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}



	private static final long serialVersionUID = -8417878625349721996L;
	
	public void addPublisher(Publisher pub)throws SQLException{
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?)", new Object[]{pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone()});
		
	}
	
	public void updatePublisher(Publisher pub) throws SQLException{
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?;", 
				new Object[]{pub.getPublisherName(), pub.getPublisherAddress(), pub.getPublisherPhone(), pub.getPublisherId()});
	}
	
	public void delPublisher(Publisher pub) throws SQLException{
		save("delete from tbl_publisher where publisherId = ?", new Object[]{pub.getPublisherId()});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Publisher> readAll() throws SQLException{
		return (List<Publisher>)read("select * from tbl_publisher", null);
	}
	
	@SuppressWarnings("unchecked")
	public Publisher readOne(int publisherId) throws SQLException{
		List<Publisher> pubs = (List<Publisher>) read("SELECT * FROM tbl_publisher where publisherId = ?", 
				new Object[]{publisherId});
		
		if (pubs != null && pubs.size()>0){
			return (Publisher) pubs.get(0);
			
		}else{
			return null;
		}
		
	}
	
	@Override
	protected List<Publisher> mapResults(ResultSet rs) throws SQLException{
		List<Publisher> pubs = new ArrayList<Publisher>();
		while (rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			
			pubs.add(a);
		}
		return pubs;
	}


	@Override
	protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
