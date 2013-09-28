
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<%@page import="com.epam.edu.rentcar.service.Test" contentType="text/html;charset=utf-8" %>
<html>
<body>
	<jsp:useBean id="CarDao" class="com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao" scope="page"></jsp:useBean>
	<h2>Hello World!</h2>
	<%
		String s = "";
	s= Test.isExists();
	%>
	<%=s%>
	
	<%
		String g = (String) request.getParameter("id");		
	%>
	<%=g%>
	<h1> <jsp:getProperty name="CarDao" property="tableName" /></h1>
	${CarDao.tableName}
</body>
</html> 
 