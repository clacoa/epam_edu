
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.service.Test"
	contentType="text/html;charset=utf-8"%>
<%@page import="com.epam.edu.rentcar.entity.User"%>
<html>
<body>
	<jsp:useBean id="CarDao"
		class="com.epam.edu.rentcar.dao.impl.postgre.PostgreCarDao"
		scope="page"></jsp:useBean>

	<%
		String logPageName = "LoginForm.jsp";
		if (session.getAttribute("user") != null) {
			logPageName = "UserInfo.jsp";
		}
	%>
	<jsp:include page="<%=logPageName%>" />
	<%
		String s = "";
		s = Test.isExists();
	%>
	<DIV>
		<%=s%>
	</DIV>

	<h1>
		<jsp:getProperty name="CarDao" property="tableName" /></h1>
	<!--${CarDao.tableName}-->
</body>
</html>
