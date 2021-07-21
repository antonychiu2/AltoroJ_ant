/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.security.appscan.altoromutual.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet allows the user to subscribe for the mailing list
 * Servlet implementation class SubscribeServlet
 * 
 * @author Alexei
 */
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LEGAL_EMAIL_ADDRESS = "^[\\w\\d\\.\\%-]+@[\\w\\d\\.\\%-]+\\.\\w{2,4}$";
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("txtEmail");
		if (email == null || !email.matches(LEGAL_EMAIL_ADDRESS)){
			response.sendRedirect("index.jsp");
			return;
		}
		
		//don't actually subscribe the user
		request.setAttribute("message_subscribe", "Thank you. Your email " + email + " has been accepted.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("subscribe.jsp");
		dispatcher.forward(request, response);
	}

}
