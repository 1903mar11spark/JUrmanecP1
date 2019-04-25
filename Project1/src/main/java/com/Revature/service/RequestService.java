package com.Revature.service;

import java.util.List;

import com.Revature.beans.Request;

public interface RequestService {
		
		//for employees 
		public List<Request> getRequestById(int empId, int reqStateId);
		//public void createRequest(Double cost, String description, String receiptImage, int employeeId, int managerId); 
		public boolean createRequest(Request request); 
		//public Request getRequestById(int id); 
		//public void updateRequest(Request request); 

		//for managers 
		public List<Request> getRequestByMgrId(int mgrId); //gets requests to the manager 
		public List<Request> mgrGetReqbyEmpId(int mgrId, int empId); //gets request to the manager by a specific employee
		public List<Request> mgrGetResolvedReqs(); // pulls all requests that are either approved or denied
		
		public void updateRequest(int reqId, int reqStateId);
		
}
