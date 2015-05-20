package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import javax.swing.text.AbstractDocument.BranchElement;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.LibraryBranch;

@Service
public class LibrarianService {
	
	@Autowired
	private BasicDataSource ds;
	
	@Autowired
	private AuthorDAO aDAO;
	
	@Autowired
	private BookDAO bkDAO;
	
	@Autowired
	private BorrowerDAO brDAO;
	
	@Autowired
	private LibraryBranchDAO lbDAO;
	
	@Autowired
	private BookLoanDAO bklDAO;
	
	@Autowired
	private BookCopyDAO bkcDAO;
	
	
	//______________________________Display all Branches_________________//
	public List<LibraryBranch> getBranchs(int pageNo, int pageSize) throws Exception{
		LibraryBranchDAO ldao = lbDAO;
		return ldao.readAll(pageNo, pageSize);
	}
	
	@Transactional
	public void addBkCopies(LibraryBranch branch){
		lbDAO.addCopies(branch);
	}
	

}
