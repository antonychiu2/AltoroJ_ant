<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@page import="com.ibm.security.appscan.altoromutual.util.ServletUtil" errorPage="notfound.jsp"%>
    <%@page import="org.apache.commons.lang.StringEscapeUtils" errorPage="notfound.jsp"%>


<jsp:include page="header.jspf"/>

<div id="wrapper" style="width: 99%;">
	<jsp:include page="toc.jspf"/>
    <td valign="top" colspan="3" class="bb">
    
    
		
		<div class="fl" style="width: 99%;">
		
		 <h1>Thank You</h1>
		 
		 <p>Thank you for your comments<%= (request.getAttribute("message_feedback")!=null)?", "+request.getAttribute("message_feedback"):"" %>.  They will be reviewed by our Customer Service staff and given the full attention that they deserve. 
		 <% String email = (String) request.getParameter("email_addr"); 
		 	boolean regExMatch = email!=null && email.matches(ServletUtil.EMAIL_REGEXP);
		 	if (email != null && email.trim().length() != 0 && regExMatch) {%> 
			 Our reply will be sent to your email: <%= ServletUtil.sanitizeBasic(email.toLowerCase()) /*email.toLowerCase()*/ %>
		<% } else {%>
			However, the email you gave is incorrect (<%=email.toLowerCase() /*ServletUtil.sanitizeWeb(email.toLowerCase())*/%>) and you will not receive a response.
		<% }%>
		
		</p>
		
		</div>
    </td>	
</div>

<jsp:include page="footer.jspf"/>