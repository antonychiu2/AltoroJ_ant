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
 * Feedback submission servlet
 * @author Alexei
 */
public class FeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//the feedback is not actually submitted
		if (request.getParameter("comments") == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		String name = request.getParameter("name");
		if (name != null){
			request.setAttribute("message_feedback", name);
		} else {
			request.removeAttribute("name");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/feedbacksuccess.jsp");
		dispatcher.forward(request, response);
	}
}
