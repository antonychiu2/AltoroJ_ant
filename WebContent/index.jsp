<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="header.jspf"/>

<div id="wrapper" style="width: 99%;">
	<jsp:include page="toc.jspf"/>
    <td valign="top" colspan="3" class="bb">
		<%		
		java.lang.String content = request.getParameter("content");
		if (content == null)
			content = "default.htm";
		else
			content = request.getParameter("content");

		content = "static/"+content;
		%>
		<jsp:include page="<%= content %>"/>
    </td>
	
</div>

<jsp:include page="footer.jspf"/>