package com.Revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	//constructor 
    public LogoutServlet() {
        super();
    }

    //methods
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter(); 
		request.getRequestDispatcher("base.html").include(request, response);
		HttpSession session = request.getSession(false); 
		if (session !=null ) {
			session.invalidate();
		}
		pw.println("<div class=\"display\"><p>You are successfully logged out</p></div>"); 
		pw.println("<a href=\"login\">back to login</a>");
		pw.println("</body></html>"); //this is server side templating with the print writer
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
