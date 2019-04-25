package com.Revature.service;

import java.util.List;

import com.Revature.beans.Employee;
import com.Revature.model.Credentials;
import com.Revature.model.User;

public class ManagerAuthService {

		public boolean isAManager(Credentials creds) {
			
		
			List managerIdList; 
		
			//set of credentials from login
			String username = creds.getUsername(); 
			String password = creds.getPassword(); 
		
			//
			System.out.println(username);
			System.out.println(password);
			
			//instance of employee if valid credentials
			EmployeeService es = new EmployeeServiceImpl(); 
			Employee emp = es.getEmployeeByUnameAndPword(username, password); 
		
			managerIdList = es.getAllManagersIds(); 
			
			// 
			System.out.println(emp);
			
			// 
			if (managerIdList.contains(emp.getId())) {
				return true; 
			}
			return false; 
		}

}
