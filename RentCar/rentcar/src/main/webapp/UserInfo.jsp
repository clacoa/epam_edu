<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.entity.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="login.css">
<script type="text/javascript" src="./js/rentcar.js"></script>
</head>
<body>
	<%
	    request.setAttribute("command", "login");
		User user = (User) session.getAttribute("user");
	%>
	<div class="login">
		<p>
			You signed as
			<%=user.getNickName()%>
		</p>
		<input type="image" src="./Images/logout.png" onclick="post_to_url('ControllerServlet',{'command':'logout'},'POST')">
	</div>
</body>
</html>