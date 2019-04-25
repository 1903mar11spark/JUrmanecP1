package com.Revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/ProfileServlet")
//@WebServlet("profile")
public class ProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	
	//constructor
    public ProfileServlet() {
        super();
    }

    //methods
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pass the request from the root URL to Profiles.html
		

		request.getRequestDispatcher("Profile.html").forward(request, response); 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
