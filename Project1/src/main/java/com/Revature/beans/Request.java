package com.Revature.beans;

public class Request {
	
	//instance variables 
	private int id;
	private double cost; 
	private String description; 
	private String receiptImage; 
	private RequestState requestState;  
	private int employeeId;
	private int managerId; 
	
	//some default values 
	
	//private int defaultId = 0; 
	//private RequestState defaultRequestState = new RequestState(1, "pending"); 
	//private int defaultRequestStateId = 1; 
	//private String defaulRequestStateName = "pending"; 
	
	//constructors
	public Request() {
		super();
	}

	public Request(int id, double cost, String description, String receiptImage, RequestState requestState, int employeeId, int managerId) {
		super();
		this.id = id; 
		this.cost = cost;
		this.description = description;
		this.receiptImage = receiptImage;
		this.requestState = requestState;
		this.employeeId = employeeId;
		this.managerId = managerId; 
	}
	/*
	//underloaded constructor
	public Request(double cost, String description, String receiptImage, int employeeId, int managerId) {
		super();
		this.id = 0; 
		this.cost = cost;
		this.description = description;
		this.receiptImage = receiptImage;
		this.requestState = new RequestState(1, "pending");
		this.employeeId = employeeId;
		this.managerId = managerId; 
	}
	*/
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReceiptImage() {
		return receiptImage;
	}
	public void setReceiptImage(String receiptImage) {
		this.receiptImage = receiptImage;
	}
	public RequestState getRequestState() {
		return requestState;
	}
	public void setRequestState(RequestState requestState) {
		this.requestState = requestState;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeid(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Request [id=" + id + ", cost=" + cost + ", description=" + description + ", receiptImage="
				+ receiptImage + ", requestState=" + requestState + ", employeeId=" + employeeId + ", managerId="
				+ managerId + "]";
	}
	
}
