package com.Revature.service;

import java.util.HashSet;
import java.util.List;

import com.Revature.beans.Employee;

public interface EmployeeService {

	//getting employees 
	
	public List<Employee> getEmployees(); 
	
	public List<Employee> getAllEmployees(); 
	
	public List<Integer> getAllManagersIds(); 
	
	public Employee getEmployeeById(int id); //gets an instance of an employee from their id
	
	public Employee getEmployeeByUnameAndPword(String uName, String pWord); //for employee login 
	
	//updating employees 
	
	public void updateEmployeeFName(String valIn, int empId);
	public void updateEmployeeLName(String valIn, int empId);
	public void updateEmployeeEmail(String valIn, int empId);
	
	public void updateEmployee(int empId, String fName,String lName, String email); 
	
}
