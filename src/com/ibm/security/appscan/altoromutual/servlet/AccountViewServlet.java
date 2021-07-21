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
 * This servlet allows the users to view account and transaction information.
 * Servlet implementation class AccountServlet
 * @author Alexei
 *
 */
public class AccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//show account balance for a particular account
		if (request.getRequestURL().toString().endsWith("showAccount")){
			String accountName = request.getParameter("listAccounts");
			if (accountName == null){
				response.sendRedirect(request.getContextPath()+"/bank/main.jsp");
				return;
			}
//			response.sendRedirect("/bank/balance.jsp&acctId=" + accountName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bank/balance.jsp?acctId=" + accountName);
			dispatcher.forward(request, response);
			return;
		}
		//this shouldn't happen
		else if (request.getRequestURL().toString().endsWith("showTransactions"))
			doPost(request,response);
		else
			super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//show transactions within the specified date range (if any)
		if (request.getRequestURL().toString().endsWith("showTransactions")){
			String startTime = request.getParameter("startDate");
			String endTime = request.getParameter("endDate");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bank/transaction.jsp?" + ((startTime!=null)?"&startTime="+startTime:"") + ((endTime!=null)?"&endTime="+endTime:""));
			dispatcher.forward(request, response);
		}
	}
}
