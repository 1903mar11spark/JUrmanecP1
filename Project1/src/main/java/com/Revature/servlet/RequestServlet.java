package com.Revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Revature.beans.Request;
import com.Revature.beans.RequestState;
import com.Revature.model.Credentials;
import com.Revature.service.AuthService;
import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.ManagerAuthService;
import com.Revature.service.RequestService;
import com.Revature.service.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



//@WebServlet("/employee")
public class RequestServlet extends HttpServlet {
	
	//serial version UI
	private static final long serialVersionUID = 1L;
	
	//variables 
	private ObjectMapper om; 
	private RequestService requestService; 
	private EmployeeService employeeService;  
	int id; 
	RequestState initReqState = new RequestState(1, "pending");  
	private ManagerAuthService mas = new ManagerAuthService(); 
	private AuthService as = new AuthService(); 

	
	//constructor 
    public RequestServlet() {
    	employeeService = new EmployeeServiceImpl();
		requestService = new RequestServiceImpl(); 
		om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
	}


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//this below works to deliver index.html to client 
		//request.getRequestDispatcher("index.html").forward(request, response);
		
		//working for print to browser
		String idString = request.getParameter("id"); 
		
		if(idString != null) {
			
			try {
				
				int id = Integer.parseInt(idString); 
				String employeeJSON = om.writeValueAsString(employeeService.getEmployeeById(id)); 
				if (employeeJSON != null) {
					int state = 1; 
					response.getWriter().write(om.writeValueAsString(requestService.getRequestById(id, state)));
					System.out.println(om.writeValueAsString(requestService.getRequestById(id, state)));
					//response.getWriter().write(employeeJSON);
				} else {
					response.sendError(400);
				}	
			} catch (Exception e) {
				response.sendError(400); 
			}
		} else {
			response.sendError(400); 
		}
		/*
		//only for managers 
		String mngrIdString=request.getParameter("mngrId"); 
		if(idString != null) {
			try {
				int mngrId = Integer.parseInt(mngrIdString); 
				
				String employeeJSON = om.writeValueAsString(employeeService.getEmployeeById(mngrId)); 
				
				if (employeeJSON != null) {
	 
					response.getWriter().write(om.writeValueAsString(requestService.getRequestByMgrId(mngrId)));
					//response.getWriter().write(employeeJSON);
					
				} else {
					response.sendError(400);
				}	
			} catch (Exception e) {
				response.sendError(400); 
			}
		} else {
			response.sendError(400); 
		}	
		*/
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
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
		
		
		rmbRequest = new Request(id, cost, description, receiptImage, initReqState, employeeId, managerId); 
		
		System.out.println(rmbRequest);
		
		requestService.createRequest(rmbRequest); 
		
		/*
		System.out.println(om.readValue(request.getReader(), Request.class));
		
		if (requestService.createRequest(om.readValue(request.getReader(), Request.class))) {
			response.setStatus(200);
		} else {
			response.setStatus(400); 
		*/
		PrintWriter pw = response.getWriter(); 
		
		request.getRequestDispatcher("base2.html").include(request, response);
		
		pw.println("<div class=\"display\"><p>Your request has been submitted</p></div>"); 
		
		pw.println("<a href=\"requestForm.html\">make another request</a>");
		pw.println("</body></html>"); //this is server side templating with the print writer
		
	
		HttpSession session = request.getSession(false); 
		
		if ((boolean) session.getAttribute("manager")) {
			
			pw.println("<a href=\"managerProfile\">return to your profile page</a>");
			pw.println("</body></html>");
			
		} else {
		
		pw.println("<a href=\"profile\">return to your profile page</a>");
		pw.println("</body></html>"); //this is server side templating with the print writer
		
		}
		
		
		
		}	
	
}
