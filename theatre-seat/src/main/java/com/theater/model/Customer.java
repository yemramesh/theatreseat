package com.theater.model;

import java.io.Serializable;

public class Customer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private int rowNumber;
    private int sectionNumber;
    private String comments;
    private int requiredSeat;

    
    public Customer(String name, int requiredSeat) {
        this.name = name;
        this.requiredSeat = requiredSeat;
    }

    
    
    

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}





	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}





	/**
	 * @return the rowNumber
	 */
	public int getRowNumber() {
		return rowNumber;
	}





	/**
	 * @param rowNumber the rowNumber to set
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}





	/**
	 * @return the sectionNumber
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}





	/**
	 * @param sectionNumber the sectionNumber to set
	 */
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}





	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}





	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}





	/**
	 * @return the requiredSeat
	 */
	public int getRequiredSeat() {
		return requiredSeat;
	}





	/**
	 * @param requiredSeat the requiredSeat to set
	 */
	public void setRequiredSeat(int requiredSeat) {
		this.requiredSeat = requiredSeat;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + requiredSeat;
		result = prime * result + rowNumber;
		result = prime * result + sectionNumber;
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
		Customer other = (Customer) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (requiredSeat != other.requiredSeat)
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		if (sectionNumber != other.sectionNumber)
			return false;
		return true;
	}

    
}
