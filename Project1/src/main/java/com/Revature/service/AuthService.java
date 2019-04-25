package com.Revature.service;

import com.Revature.beans.Employee;
import com.Revature.model.Credentials;
import com.Revature.model.User;

public class AuthService {

	public User isValidUser(Credentials creds) {
		

		// from presentation tier (login servlet)
		User u = null;  
		
		//set of credentials from login
		String username = creds.getUsername(); 
		String password = creds.getPassword(); 
	
		//
		System.out.println(username);
		System.out.println(password);
		
		//instance of employee if valid credentials
		EmployeeService es = new EmployeeServiceImpl(); 
		Employee emp = es.getEmployeeByUnameAndPword(username, password); 
		//
		System.out.println(emp);
	
		//if valid credentials create user
		if (emp != null) {
			
			u = new User(emp.getId(), emp.getLoginCreds().getuName(), emp.getFirstName(), emp.getLastName(), emp.getEmail()); 
		}
		
		return u; 
	}
}
