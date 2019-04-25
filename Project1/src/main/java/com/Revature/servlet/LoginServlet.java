package com.Revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Revature.model.Credentials;
import com.Revature.model.User;
import com.Revature.service.AuthService;
import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.ManagerAuthService;


//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	//serialization ID
	private static final long serialVersionUID = 1L;
       
	//instance variable 
	private AuthService as = new AuthService(); 
	private ManagerAuthService mas = new ManagerAuthService(); 
	
	//constructor
    public LoginServlet() {
        super();
    }

    //web container methods
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("Login.html").forward(request, response); 
		//response.getWriter().write("Hello World!");	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//check whether session exists. And if not, create one. 
		//overloaded version takes a boolean parameter, if false returns null when there is none 
		
		HttpSession session = request.getSession(); 
		
		//grab credentials from the request 
		Credentials creds = new Credentials(request.getParameter("username"), request.getParameter("password")); 
		
		//attempt to authenticate user 
		
		User u = as.isValidUser(creds); 
		
		
		if (u != null) {
			
			
			//session attributes as the user information - session refers to the application session
			session.setAttribute("userId", u.getId());
			session.setAttribute("username", u.getUsername());
			session.setAttribute("firstname", u.getFirstname());
			session.setAttribute("lastname", u.getLastname());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("problem", null);
			

			//check if they are a manager 
			if (mas.isAManager(creds) == true) {
				//redirect to manager page 
				session.setAttribute("manager", true);
				response.sendRedirect("http://localhost:8086/Project1/managerProfile.html");
			} else {
			//redirect user to profile page if authenticated and not a manager 
			session.setAttribute("manager", false);
			response.sendRedirect("profile");
			}
			
			
		} else {
			
			session.setAttribute("problem", "invalid credentials");	
			//otherwise redirect to the login page 
			response.sendRedirect("login");
		}
	}

}
		