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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.security.appscan.Log4AltoroJ;
import com.ibm.security.appscan.altoromutual.model.Account;
import com.ibm.security.appscan.altoromutual.model.User;
import com.ibm.security.appscan.altoromutual.util.DBUtil;
import com.ibm.security.appscan.altoromutual.util.ServletUtil;

/**
 * This servlet processes user's login and logout operations
 * Servlet implementation class LoginServlet
 * @author Alexei
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//log out
		try {
			HttpSession session = request.getSession(false);
			session.removeAttribute(ServletUtil.SESSION_ATTR_USER);
		} catch (Exception e){
			// do nothing
		} finally {
			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//log in
		// Create session if there isn't one:
		HttpSession session = request.getSession(true);

		String username = null;
		
		try {
			username = request.getParameter("uid");
			if (username != null)
				username = username.trim().toLowerCase();
			
			String password = request.getParameter("passw");
			password = password.trim().toLowerCase(); //in real life the password usually is case sensitive and this cast would not be done
			
			if (!DBUtil.isValidUser(username, password)){
				Log4AltoroJ.getInstance().logError("Login failed >>> User: " +username + " >>> Password: " + password);
				throw new Exception("Login Failed: We're sorry, but this username or password was not found in our system. Please try again.");
			}
			
		} catch (Exception ex) {
			request.getSession(true).setAttribute("loginError", ex.getLocalizedMessage());
			response.sendRedirect("login.jsp");
			return;
		}

	
		try {
			User user = DBUtil.getUserInfo(username);
			Account[] accounts = user.getAccounts();
			String accountStringList = Account.toBase64List(accounts);
			Cookie accountCookie = new Cookie(ServletUtil.ALTORO_COOKIE, accountStringList);
			response.addCookie(accountCookie);
			session.setAttribute(ServletUtil.SESSION_ATTR_USER, user);
			response.sendRedirect(request.getContextPath()+"/bank/main.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
		return;
	}

}
