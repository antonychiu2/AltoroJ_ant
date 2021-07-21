/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.security.appscan.altoromutual.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.security.appscan.altoromutual.model.User;
import com.ibm.security.appscan.altoromutual.util.ServletUtil;

import java.io.IOException;

/**
 * This class ensures that the user is authenticated when they try to access
 * account section of the site, and redirects to the login page if they are not.
 * @author Alexei
 *
 */
public class AuthFilter implements Filter {
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public AuthFilter() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#init(FilterConfig arg0)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest){
			HttpServletRequest request = (HttpServletRequest)req;
			Object user = request.getSession().getAttribute(ServletUtil.SESSION_ATTR_USER);
			if (user == null || !(user instanceof User)){
				((HttpServletResponse)resp).sendRedirect(request.getContextPath()+"/login.jsp");
				return;
			}
			else {
				chain.doFilter(req, resp);
			}
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}