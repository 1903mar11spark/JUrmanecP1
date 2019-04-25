package com.Revature.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import com.Revature.beans.Employee;
import com.Revature.beans.Request;
import com.Revature.beans.RequestState;
import com.Revature.dao.EmployeeDAO;
import com.Revature.dao.EmployeeDAOimpl;
import com.Revature.dao.RequestDAO;
import com.Revature.dao.RequestDAOimpl;
import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.RequestService;
import com.Revature.service.RequestServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class Driver {

	public static void main(String[] args) {
		
		
		//session interface implementations  and login work. code is below 
	
		EmployeeService es = new EmployeeServiceImpl(); 
		RequestService rs = new RequestServiceImpl(); 
		RequestDAO rd = new RequestDAOimpl(); 
		
		ObjectMapper om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		
		//logging in 
		/*
		Employee empInstance = ed.getEmployeeByUnameAndPword("JJanson", "juniper"); 
		Employee mgrInstance = ed.getEmployeeById(empInstance.getReportsTo()); 
		*/
		
		
		Employee empInstance = es.getEmployeeByUnameAndPword("FFlower", "fanciful"); 
		System.out.println(empInstance);
		
		es.updateEmployeeFName("Jimmy", 2);

		
		
		
		//es.updateEmployee(2, "Jimmy", "Jones", "JJones@futuretech.com"); 

		
		//Employee mgrInstance = es.getEmployeeById(empInstance.getReportsTo()); 
	
		
		// * * * this prints all who are employees and not nessecarily managers 
		/*
		List<Employee> employeeList = es.getEmployees(); 
		
		for(int i = 0; i < employeeList.size(); i ++) {
			if (i%2 == 0) {
			System.out.println(employeeList.get(i));
			}
		}
		*/
		
		// * * * this prints all who are employees and not nessecarily managers 
		
		List<Employee> employeeList2 = es.getEmployees(); 
				
		for(int i = 0; i < employeeList2.size(); i ++) {
			if (i%2 == 0) {
			System.out.println(employeeList2.get(i)); 
			
			}
		}
		
		
		/*
		List<Integer> managerList = es.getAllManagersIds(); 
		System.out.println(managerList);
		
		boolean answer; 
		if (managerList.contains(empInstance.getId())) {
			answer = true; 
		} else {
			answer = false;  
		}
		System.out.println(answer);
		*/
		
		//test of login 
		/*
		System.out.println("\n");
		System.out.println(empInstance);
		System.out.println(mgrInstance);	
		System.out.println("\n");
		*/
		
		/*
		System.out.println("\n");
		System.out.println(empInstance);
		System.out.println(mgrInstance);	
		System.out.println("\n");
		*/
		
		//some test for converting to JSON . . . 
		
		/*
		ObjectMapper om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false); 
		//what might go in a response . . . 
		//resp.getWriter().write(om.writeValueAsString(bearService.getBearById(1000)));
		try {
			System.out.println(om.writeValueAsString(empInstance));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//for employees updating their record 
		//fields that can be updated are: FIRSTAME, LASTNAME, EMAILADDRESS
		
		/*
		//test to update first name 
		ed.updateEmployeeFName("John", empInstance.getId()); 
		System.out.println("after method1");
		
		//test to update last name
		ed.updateEmployeeLName("Janson", empInstance.getId()); 
		System.out.println("after method2");
		
		ed.updateEmployeeEmail("JJanson@futuretech.com", empInstance.getId()); 
		System.out.println("after method3");
		//System.out.println("test: " + ("FIELD" == "FIELD"));
		*/ 
		
		//for an employee to view their information 
		/*
		System.out.println(empInstance);
		*/
		
		
		
		//printing requests by status for logged in employee works - code is below 
		/*
		ObjectMapper om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false); 
		
		List<Request> empReqList = new ArrayList<Request>(); 
		int reqStateId = 1; 
		
		empReqList = rs.getRequestById(empInstance.getId(), reqStateId); 
		for (int i = 0; i < empReqList.size(); i ++) {
			try {
				System.out.println(om.writeValueAsString(empReqList.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			System.out.println(om.writeValueAsString(rs.getRequestById(empInstance.getId(), reqStateId)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
		/*
		List<Request> empReqList = new ArrayList<Request>(); 
		int reqStateId = 1; 
		empReqList = rs.getRequestById(empInstance.getId(), reqStateId); 
		try {
			om.writeValueAsString(empReqList);
			System.out.println(om);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		*/
		
		/*
		for (int i = 0; i < empReqList.size(); i ++) {
			try {
				System.out.println(om.writeValueAsString(empReqList.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		*/
		
	
		
		/*
		List<Request> mgrReqList = new ArrayList<Request>(); 
		mgrReqList = rs.getRequestByMgrId(empInstance.getId());  
		for (int i = 0; i < mgrReqList.size(); i ++) {
			try {
				System.out.println(om.writeValueAsString(mgrReqList.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		*/
		
	
		//printing other stuff 
		/*
		System.out.println(empInstance.getFirstName()+" has "+empReqList.size()+" pending requests.");
		*/
		/*
		System.out.println("The second request is : "+empReqList.get(1));
		System.out.println("The cost for the 2nd request is: "+empReqList.get(1).getCost());
		System.out.println("The 2nd request was sumbitted to "+ed.getEmployeeById((empReqList.get(1).getManagerId()))); //this way is much faster
 		*/	
		
		
		//sending requests works - code is below
		
		
		
		//sending requests 
		/*
		rd.createRequest(cost, "supplies", "Image", empInstance.getId(), empInstance.getReportsTo());
		*/
		
		//new method for sending request (with request object as input argument) 
		
		/*
		RequestState initReqState = new RequestState(1,"pending"); 
		System.out.println(initReqState);
		
		String description = "bought 626 things"; 
		String image = "rx image 626";
		double cost = 626; 
		
		//form 1 
		
		Request request = new Request(0, cost, description, image, initReqState, empInstance.getId(), empInstance.getReportsTo());
		
		System.out.println("req 1: " + request);
		
		rs.createRequest(request); 
		*/
		
		//form2 
		/*
		Request request2 = new Request(cost, description, image, empInstance.getId(), empInstance.getReportsTo()); 
		System.out.println("req 2: " + request2);
		rs.createRequest(request2); 
		*/
		
		/*
		//test for RequestDAO with method that takes Request object 
		//public Request(int id, double cost, String description, String receiptImage, RequestState requestState, int employeeId, int managerId)
		Request request = new Request(reqId, cost, description, image, (new RequestState(reqState,reqStateName)), empInstance.getId(), empInstance.getReportsTo()); 
		//rd.createRequest(request);
		rd.createRequest(request); 
		*/
		
		//test for createRequest method for RequestService 
		/*
		Request request = new Request(reqId, cost, description, image, (new RequestState(reqState,reqStateName)), empInstance.getId(), empInstance.getReportsTo()); 
		rs.createRequest(request); 
		*/
		//test with underloaded constructor 
		/*
		RequestState defaultRequestState = new RequestState(1, "pending");
		
		System.out.println(defaultRequestState);
		
		Request request = new Request(cost, description, image, empInstance.getId(), empInstance.getReportsTo()); 
		
		System.out.println(request);
		*/
		
		//Request request = new Request(reqId, cost, description, image, (new RequestState(reqState,reqStateName)), empInstance.getId(), empInstance.getReportsTo());
		
		//rs.createRequest(request); 
	
		
		//managers - manager login works - code is below 
		
		//session interface implementations 
		//see above
		
		//logging in 
		/*
		Employee empInstance = ed.getEmployeeByUnameAndPword("FFlower", "fanciful"); 
		Employee mgrInstance = ed.getEmployeeById(empInstance.getReportsTo()); 
		*/
		
		/*
		Employee empInstance = es.getEmployeeByUnameAndPword("FFlower", "fanciful"); 
		Employee mgrInstance = es.getEmployeeById(empInstance.getReportsTo()); 
		*/
		
		//test of login 
		/*
		ObjectMapper om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		*/
		
		//System.out.println(empInstance);
		//System.out.println(mgrInstance);
		/*
		try {
			System.out.println(om.writeValueAsString(empInstance));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//manager log in with RequestService 
		
		//session interface implementations 
		//see above 
		

		//pull up all pending requests for the logged in manager 
		/*
		List<Request> mgrReqList = new ArrayList<Request>(); 
		mgrReqList = rd.getRequestByMgrId(empInstance.getId());  
		for (int i = 0; i < mgrReqList.size(); i ++) {
			System.out.println(mgrReqList.get(i));
		}
		*/
		
		//pulling up all pending requests for logged in manager as JSON objects works!
		/*
		ObjectMapper om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false); 
	
		List<Request> mgrReqList = new ArrayList<Request>(); 
		mgrReqList = rs.getRequestByMgrId(empInstance.getId());  
		for (int i = 0; i < mgrReqList.size(); i ++) {
			try {
				System.out.println(om.writeValueAsString(mgrReqList.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		*/

		//pull up all pending requests from a single employee works 
		//lets loop up requests Francis from James 
		
		/*
		int empId = 2; 
		List<Request> listForOneEmp = new ArrayList<Request>(); 
		listForOneEmp = rd.mgrGetReqbyEmpId(empInstance.getId(), empId);  
		for (int i = 0; i < listForOneEmp.size(); i ++) {
			System.out.println(listForOneEmp.get(i));
		}
 		*/	
		
		
		//approve and deny requests works - the code below will deny or approve 
		//a request by reqId and reqStateId 
		/*
		int reqId = 52; 
		int reqStateId = 3;

		rs.updateRequest(reqId, reqStateId); 
		*/
		
		//resolvedReqs is okay - this code below will print out all resolved requests. 
		/*
		List<Request> resolvedReqs = new ArrayList<Request>(); 
		resolvedReqs = rd.mgrGetResolvedReqs();    
		for (int i = 0; i < resolvedReqs.size(); i ++) {
			try {
				System.out.println(om.writeValueAsString(resolvedReqs.get(i)));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println(om.writeValueAsString(rs.getRequestById(2, 1)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		*/
		//the below code prints out employees and managers as a 
 
		/*
		List <Employee> empManPairsList = new ArrayList<Employee>();  
		
		empManPairsList = es.getEmployees();   
		for (int i = 1; i < empManPairsList.size(); i ++) {
			
			//System.out.println(empManPairsList.get(i));
			
			if ((i+1)%2==0) {
				
				System.out.println(empManPairsList.get(i-1)+",   "+empManPairsList.get(i));
			}
		}
		*/ 
		/*
		List <Employee> empManPairsList = new ArrayList<Employee>();  
		
		empManPairsList = es.getEmployees();   
		for (int i = 1; i < empManPairsList.size(); i ++) {
			
			//System.out.println(empManPairsList.get(i));
			
			if ((i+1)%2==0) {
				
				try {
					System.out.println(om.writeValueAsString(empManPairsList.get(i)));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	try {
		System.out.println(om.writeValueAsString(empInstance));
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		//test w/ object mapping -- doesn't work yet
		
		//ObjectMapper om = new ObjectMapper(); 
		/*
		List <Employee> empManPairsList = new ArrayList<Employee>();  
		
		empManPairsList = es.getEmployees();   
		for (int i = 1; i < empManPairsList.size(); i ++) {
			
			//System.out.println(empManPairsList.get(i));
			
			if ((i+1)%2==0) {
				
				try {
					om.writeValueAsString(empManPairsList.get(i-1)+",   "+empManPairsList.get(i));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} 
				//System.out.println(empManPairsList.get(i-1)+",   "+empManPairsList.get(i));
			}
		}
		*/
		/*
	
		
		List<Request> resolvedRequests = rs.mgrGetResolvedReqs(); 
		
		List<Employee> empList = null; 
		List<Employee> manList = null; 
		
		for(int i = 0 ; i < resolvedRequests.size(); i ++) {
			empList.add(es.getEmployeeById(resolvedRequests.get(i).getEmployeeId())); 
			manList.add(es.getEmployeeById(resolvedRequests.get(i).getEmployeeId())); 
		}
		*/

	
	
	}
}
