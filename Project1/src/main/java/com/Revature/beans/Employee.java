package com.Revature.beans;

public class Employee {

	//instance variables 
	private int id; 
	private String firstName; 
	private String lastName; 
	private String title;  
	private String email; 
	private EmployeeType employeeType; 
	private LoginCreds loginCreds;
	private int reportsTo;


	//no-argument constructor
	public Employee() {
		super();
	}
	
	//constructor w/ fields 
	public Employee(int id, String firstName, String lastName, String title, String email, EmployeeType employeeType,
			LoginCreds loginCreds, int reportsTo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.email = email;
		this.employeeType = employeeType;
		this.loginCreds = loginCreds;
		this.reportsTo = reportsTo;
	}


	//getters and setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public LoginCreds getLoginCreds() {
		return loginCreds;
	}
	public void setLoginCreds(LoginCreds loginCreds) {
		this.loginCreds = loginCreds;
	}
	public int getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}


	//toString method
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", email=" + email + ", employeeType=" + employeeType + ", loginCreds=" + loginCreds + ", reportsTo="
				+ reportsTo + "]";
	}

}
