package com.Revature.beans;

public class EmployeeType {
	
	//instance variables 
	private int id; 
	private String employeeTypeName;
	
	
	//constructors
	public EmployeeType() {
		super();
	}
	public EmployeeType(int id, String employeeTypeName) {
		super();
		this.id = id;
		this.employeeTypeName = employeeTypeName;
	}
	
	//getters and setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	
	//toString method
	@Override
	public String toString() {
		return "EmployeeType [id=" + id + ", employeeTypeName=" + employeeTypeName + "]";
	}

}
