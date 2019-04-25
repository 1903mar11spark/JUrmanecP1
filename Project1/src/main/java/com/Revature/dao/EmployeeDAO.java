package com.Revature.dao;

import java.util.HashSet;
import java.util.List;

import com.Revature.beans.Employee;

public interface EmployeeDAO {
	
	//getting employees 
	
	public List<Employee> getEmployees(); 
	
	public Employee getEmployeeById(int id); //gets an instance of an employee from their id
	
	public Employee getEmployeeByUnameAndPword(String uName, String pWord); //for employee login 
	
	//updating employees 
	
	public void updateEmployeeFName(String valIn, int empId);
	public void updateEmployeeLName(String valIn, int empId);
	public void updateEmployeeEmail(String valIn, int empId);
	


	//public void createEmployee(Employee employee);
	//public void deleteEmployee(Employee employee); 
	
	// more methods can go here . . . 
	//

}
