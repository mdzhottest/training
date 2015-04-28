package com.gcit.training.lws.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lws.domain.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> implements Serializable{

	private static final long serialVersionUID = -8591950361166071450L;

	public BorrowerDAO(Connection conn){
		super(conn);
	}

	public void addBorrower(Borrower bor) throws SQLException{

		String updateQuery = "insert into tbl_borrower (name,address,phone) values (?,?,?)";
		save(updateQuery,new Object []{ bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone()});

	}

	public void delBorrower(Borrower bor) throws SQLException{
		String remQuery = "delete from tbl_borrower where cardNo=?";
		save (remQuery, new Object []{bor.getCardNo()});
	}


	public void upBorrower(Borrower bor) throws SQLException{
		String upQuer = "Update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?";
		save(upQuer, new Object []{bor.getBorrowerName(), bor.getBorrowerAddress(),  bor.getBorrowerPhone(), bor.getCardNo()});

	}


	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws SQLException {
		String select = "select * from tbl_borrower";
		return    (List<Borrower>) read(select,null);

	}

	public Borrower getBorrowerByName(String bName) throws SQLException{
		String select = "select * from tbl_borrower where name=?";
		@SuppressWarnings("unchecked")
		List<Borrower> borrowers=(List<Borrower>) read(select,new Object[]{bName});
		if(borrowers!=null&&borrowers.size()>0)
		{
			return borrowers.get(0);	
		}
		else 
			return null;
	}


	public Borrower getBorrowerByCardNo(int cardNo) throws SQLException{
		String select = "select * from tbl_borrower where cardNo=?";
		@SuppressWarnings("unchecked")
		List<Borrower> borrowers=(List<Borrower>) read(select,new Object[]{cardNo});
		if(borrowers!=null&&borrowers.size()>0)
		{
			return borrowers.get(0);	
		}
		else return null;
	}

	@Override
	protected List<Borrower> mapResults(ResultSet rs) throws SQLException {
		List<Borrower> bor = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			borrower.setBorrowerAddress(rs.getString("address"));
			borrower.setBorrowerPhone(rs.getString("phone"));
			bor.add(borrower);
		}

		return bor;
	}


	@Override
	protected List<Borrower> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> b = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower bda = new Borrower();
			bda.setCardNo(rs.getInt("cardNo"));
			bda.setBorrowerName(rs.getNString("borrowerName"));
			bda.setBorrowerAddress(rs.getString("borrowerAddress"));
			bda.setBorrowerPhone(rs.getString("borrowerPhone"));

			b.add(bda);
		}
		return b;

	}

	public Borrower readOne(int cardNo) throws SQLException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Borrower> b = (List<Borrower>) read("SELECT * FROM tbl_borrower where cardNo = ?", new Object[]{cardNo});

		if(b != null && b.size()>0){
			return b.get(0);
		}else{
			return null;
		}

	}




}
