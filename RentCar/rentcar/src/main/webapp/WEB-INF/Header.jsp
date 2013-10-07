<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
			String logPageName = "/WEB-INF/LoginForm.jsp";
			if (session.getAttribute("user") != null) {
				logPageName = "/WEB-INF/UserInfo.jsp";
			}
		%>
		<jsp:include page="<%=logPageName%>" />
		<jsp:include page="MainMenu.jsp" />
</body>
</html>