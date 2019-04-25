package com.Revature.beans;

public class RequestState {

	//instance variables 
	private int id; 
	private String requestStateName;
	
	//constructors
	public RequestState() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestState(int id, String requestStateName) {
		super();
		this.id = id;
		this.requestStateName = requestStateName;
	}
	
	//getters and setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRequestStateName() {
		return requestStateName;
	}
	public void setRequestStateName(String requestStateName) {
		this.requestStateName = requestStateName;
	}
	
	//toString 
	@Override
	public String toString() {
		return "RequestState [id=" + id + ", requestStateName=" + requestStateName + "]";
	} 
	
}
