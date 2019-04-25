package com.Revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.RequestService;
import com.Revature.service.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


//@WebServlet("/employee")
public class PullReqsByEmp extends HttpServlet {
	
	//serial version UID
	private static final long serialVersionUID = 7334524652907509005L;
	
	//variables 
	private ObjectMapper om; 
	private EmployeeService es;
	private RequestService rs; 
	int mngrId; 
	int id;


	//constructor 
    public PullReqsByEmp() {
		es = new EmployeeServiceImpl();
		rs = new RequestServiceImpl(); 
		om = new ObjectMapper(); 
		om.registerModule(new JavaTimeModule()); 
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id"); 
		String mngrIdString = request.getParameter("mngrId"); 
		
		System.out.println(id);
		
		if(idString != null) {
			try {
				
				int id = Integer.parseInt(idString);
				int mngrId = Integer.parseInt(mngrIdString);
				
				String employeeJSON = om.writeValueAsString(es.getEmployeeById(id)); 
				
				if (employeeJSON != null) {
					
					// 
					response.getWriter().write(om.writeValueAsString(rs.mgrGetReqbyEmpId(mngrId, id)));

				} else {
					response.sendError(400);
				}	
			} catch (Exception e) {
				response.sendError(400); 
			}
		} else {
			
			response.sendError(400); 
		}
		
	}
	
	/*

	String idString = request.getParameter("id"); 
	
	if(idString != null) {
		
		try {
			
			int id = Integer.parseInt(idString); 
			String employeeJSON = om.writeValueAsString(employeeService.getEmployeeById(id)); 
			if (employeeJSON != null) {
				int state = 1; 
				response.getWriter().write(om.writeValueAsString(requestService.getRequestById(id, state)));
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
