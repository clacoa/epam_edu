<%@page import="java.util.ArrayList"%>
<%@page import="com.epam.edu.rentcar.db.ConnectionPool"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.epam.edu.rentcar.db.ConnectionPool"%>
<%@page import="com.epam.edu.rentcar.db.entity.User"%>
<%@page import="com.epam.edu.rentcar.db.dao.impl.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.epam.edu.rentcar.service.Test"%>


<html>
<body>
	<h2>Hello World!</h2>
	<%
		String s = "";
	s= Test.isExists();
	%>
	<%=s%>
</body>
</html>
 