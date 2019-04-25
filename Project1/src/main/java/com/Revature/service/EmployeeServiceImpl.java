package com.Revature.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.Revature.beans.Employee;
import com.Revature.dao.EmployeeDAO;
import com.Revature.dao.EmployeeDAOimpl;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO ed = new EmployeeDAOimpl();

	@Override
	public List<Employee> getEmployees() {
		return ed.getEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}

	@Override
	public Employee getEmployeeByUnameAndPword(String uName, String pWord) {
		return ed.getEmployeeByUnameAndPword(uName, pWord);
	}

	@Override
	public void updateEmployeeFName(String valIn, int empId) {
		ed.updateEmployeeFName(valIn, empId);
	}

	@Override
	public void updateEmployeeLName(String valIn, int empId) {
		ed.updateEmployeeLName(valIn, empId);

	}

	@Override
	public void updateEmployeeEmail(String valIn, int empId) {
		ed.updateEmployeeEmail(valIn, empId);
	}

	@Override
	public List<Integer> getAllManagersIds() {
		
		List<Integer> managerIds = new ArrayList<Integer>(); 
		EmployeeService es = new EmployeeServiceImpl(); 
		List<Employee> empList = es.getEmployees(); //employee manager pairs
		
		for(int i = 0; i < empList.size(); i ++) {
			if (i%2 == 0) {
			//System.out.println(empList.get(i).getReportsTo()); 
			managerIds.add(empList.get(i).getReportsTo()); 
			}
		}
		
		return managerIds;
	}

	
	// * * * this prints all who are employees and not nessecarily managers 
	

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> employeeList = ed.getEmployees(); 
		
		for(int i = 0; i < employeeList.size(); i ++) {
			if (i%2 == 0) {
			System.out.println(employeeList.get(i)); 
			
			}
		}
		return employeeList;
	}

	@Override
	public void updateEmployee(int empId, String fName, String lName, String email) {
		updateEmployeeFName(fName, empId); 
		updateEmployeeLName(lName, empId); 
		updateEmployeeEmail(email, empId); 
		
	}


	
	
}
