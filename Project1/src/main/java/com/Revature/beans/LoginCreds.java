package com.Revature.beans;

public class LoginCreds {
	
	//instance variables 
	private int id;
	private String uName;
	private String pWord; 
	
	//constructors
	public LoginCreds() {
		super();
	}
	public LoginCreds(int id, String uName, String pWord) {
		super();
		this.id = id;
		this.uName = uName;
		this.pWord = pWord;
	}
	
	
	//getters and setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getpWord() {
		return pWord;
	}
	public void setpWord(String pWord) {
		this.pWord = pWord;
	}
	
	//toString
	@Override
	public String toString() {
		return "LoginCreds [id=" + id + ", uName=" + uName + ", pWord=" + pWord + "]";
	}

}
