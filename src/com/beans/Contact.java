package com.beans;

import java.io.Serializable;

//Contact class, used to encapsulate DB data
public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787561683450325331L;
	
	
	private int IDX;
	private String timeStamp;
	private String firstName;
	private String lastName;
	private String eMail;
	private String phoneNumber;
	
	public Contact() {
		
	}
	public Contact(int iDX, String timeStamp, String firstName, String lastName, String eMail, String phoneNumber) {
		super();
		IDX = iDX;
		this.timeStamp = timeStamp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
	}
	public int getIDX() {
		return IDX;
	}
	public void setIDX(int iDX) {
		IDX = iDX;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return this.IDX + " " + this.timeStamp + " " + this.firstName  + " " + this.lastName + " " + this.eMail + " " + this.phoneNumber;
	}
		
}

