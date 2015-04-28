package com.gcit.training.lws.domain;

import java.io.Serializable;

import com.gcit.training.lws.domain.LibraryBranch;

public class BookCopies implements Serializable {
	
	
	private static final long serialVersionUID = -1934779906109250691L;
	
	
	private int bookId;
	private LibraryBranch branch;
	private int noOfCopies;
	
	
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	public LibraryBranch getBranch() {
		return branch;
	}
	
	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}
	/**
	 * @return the noOfCopies
	 */
	public int getNoOfCopies() {
		return noOfCopies;
	}
	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + noOfCopies;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopies other = (BookCopies) obj;
		if (bookId != other.bookId)
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (noOfCopies != other.noOfCopies)
			return false;
		return true;
	}

	
}
