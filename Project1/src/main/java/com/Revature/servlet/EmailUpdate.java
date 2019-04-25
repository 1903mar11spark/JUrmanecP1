package com.Revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Revature.beans.Request;
import com.Revature.service.EmployeeService;
import com.Revature.service.EmployeeServiceImpl;
import com.Revature.service.RequestService;
import com.Revature.service.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class EmailUpdate extends HttpServlet {

	int empId;
	int userId;
	private ObjectMapper om;
	private EmployeeService es;

	public EmailUpdate() {
		super();
		es = new EmployeeServiceImpl();
		om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// pull in user/ employee id from session
		HttpSession session = request.getSession(false);

		int empId = (int) session.getAttribute("userId");
		System.out.println(userId);


		String firstName = request.getParameter("firstName");
		System.out.println(firstName);
		
		es.updateEmployeeEmail(firstName, empId);
		
		

		// es.updateEmployeeFName(firstName, empId);
		// es.updateEmployeeFName(firstName, empId);

		/*
		 * System.out.println(om.readValue(request.getReader(), Request.class));
		 * 
		 * if (requestService.createRequest(om.readValue(request.getReader(),
		 * Request.class))) { response.setStatus(200); } else { response.setStatus(400);
		 */
		PrintWriter pw = response.getWriter();

		request.getRequestDispatcher("base2.html").include(request, response);

		pw.println("<div class=\"display\"><p>Your request has been submitted</p></div>");

		// pw.println("<a href=\"requestForm.html\">make another request</a>");
		pw.println("</body></html>"); // this is server side templating with the print writer

		if ((boolean) session.getAttribute("manager")) {

			pw.println("<a href=\"managerProfile\">return to your profile page</a>");
			pw.println("</body></html>");

		} else {

			pw.println("<a href=\"profile\">return to your profile page</a>");
			pw.println("</body></html>"); // this is server side templating with the print writer

		}

	}

}
