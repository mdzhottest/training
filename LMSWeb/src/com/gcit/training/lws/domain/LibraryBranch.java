package com.gcit.training.lws.domain;

import java.io.Serializable;

public class LibraryBranch implements Serializable{
	
	private static final long serialVersionUID = 2391848005754822374L;
	
	private int libraryId;
	private String libraryName;
	private String libraryAddress;
	
	
	/**
	 * @return the libraryId
	 */
	public int getLibraryId() {
		return libraryId;
	}
	/**
	 * @param libraryId the libraryId to set
	 */
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	/**
	 * @return the libraryName
	 */
	public String getLibraryName() {
		return libraryName;
	}
	/**
	 * @param libraryName the libraryName to set
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	/**
	 * @return the libraryAddress
	 */
	public String getLibraryAddress() {
		return libraryAddress;
	}
	/**
	 * @param libraryAddress the libraryAddress to set
	 */
	public void setLibraryAddress(String libraryAddress) {
		this.libraryAddress = libraryAddress;
	}
	
	
}
