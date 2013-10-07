
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.service.Test"
	contentType="text/html;charset=utf-8"%>
<%@page import="com.epam.edu.rentcar.entity.User"%>
<%@page import="java.util.*"%>
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="rentcar.css">
</head>
<body style="height: 100%">
	<jsp:useBean id="CarDao"
		class="com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao"
		scope="page"></jsp:useBean>

	<jsp:include page="/WEB-INF/Header.jsp" />

	<div class="content" >

		<%
			String s = "";
			s = Test.isExists();
		%>
	
			<c:out value="${user}" />
	

		<h1>
			<jsp:getProperty name="CarDao" property="tableName" /></h1>
		<!--${CarDao.tableName}-->
		<mytag:Table />
	</div>
	<jsp:include page="/WEB-INF/Cellar.jsp" />
</body>
</html>
