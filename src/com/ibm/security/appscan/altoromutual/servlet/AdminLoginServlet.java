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

import com.ibm.security.appscan.altoromutual.util.ServletUtil;

/**
 * Administrator login servlet
 * @author Alexei
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		if (password == null){
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
			return ;
		} else if (!password.equals("Altoro1234")){
			request.setAttribute("loginError", "Login failed.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.getSession(true).setAttribute(ServletUtil.SESSION_ATTR_ADMIN_KEY, ServletUtil.SESSION_ATTR_ADMIN_VALUE);
			response.sendRedirect(request.getContextPath()+"/admin/admin.jsp");
		}
	}
}
