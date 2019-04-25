package com.Revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Revature.beans.Request;
import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.RequestService;
import com.Revature.service.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class ApprovOrDenyServlet extends HttpServlet {
	
	
	//serialization ID
	private static final long serialVersionUID = 1L;
       
	//variables 
	private ObjectMapper om; 
	private RequestService rs; 
	private EmployeeService es;  
	int id; 
	int reqId; 
	int reqStateId; 
	
	//constructor 
    public ApprovOrDenyServlet() {
        super();
    	es = new EmployeeServiceImpl();
		rs = new RequestServiceImpl(); 
		om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		System.out.println(request.getParameter("cost")); 
		System.out.println(request.getParameter("description"));
		System.out.println(request.getParameter("receiptImage"));
		System.out.println(request.getParameter("employeeId"));
		System.out.println(request.getParameter("managerId"));
		Request rmbRequest; 
		Double cost = Double.parseDouble(request.getParameter("cost")); 
		String description = request.getParameter("description"); 
		String receiptImage = request.getParameter("receiptImage"); 
		int employeeId = Integer.parseInt(request.getParameter("employeeId")); 
		int managerId = Integer.parseInt(request.getParameter("managerId")); 
		*/
		
		//rmbRequest = new Request(cost, description, receiptImage, employeeId, managerId); 
		
		//System.out.println(rmbRequest);
		
		//requestService.createRequest(rmbRequest); 
		
		//adding this 
		
		//.readValue takes a JASON onbject and maps it into an bject of the specified class 
		//it essentially creates an object from a a JSON string. 
		
		
		
		int reqId = Integer.parseInt(request.getParameter("reqId")); 
		int reqStateId = Integer.parseInt(request.getParameter("reqStateId")); 
		
		System.out.println("in the post request");
		System.out.println("reqStateId: " + reqStateId);
		System.out.println("reqId: "+ reqId); 

		rs.updateRequest(reqId, reqStateId);

		/*
		if (bearService.createBear(om.readValue(req.getReader(), Bear.class))) {
			resp.setStatus(200);
		} else {
			resp.sendError(400);
		}
		*/
	
	}
	
}
