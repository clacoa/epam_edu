
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.service.Test"
	contentType="text/html;charset=utf-8"%>
<%@page import="com.epam.edu.rentcar.entity.User"%>
<%@page import="java.util.*"%>
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	request.getSession().setAttribute("prevUrl",
			request.getServletPath().toString());
%>
<html>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="i18n">
	<head>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<title><fmt:message key="hello" /></title>
	</head>
	<body style="height: 100%">
		<jsp:include page="/WEB-INF/header.jsp" />
		<div class="content" style="display: table;">

		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</fmt:bundle>
</html>
