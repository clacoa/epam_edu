<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.entity.User" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	request.getSession().setAttribute("prevUrl",request.getServletPath().toString());
	String locale = request.getSession().getAttribute("language") != null ? request.getSession().getAttribute("language").toString() : "ru";
	boolean languageVis = locale.equals("ru");
	String ruVisibility = languageVis ? "block" : "none";
	String enVisibility = languageVis ? "none" : "block";
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
			<div style="display:<%=ruVisibility%>">
				<h1 align="center">Здесь отображается информация об услугодателе</h1>
				<p>Адрес</p>
				<p>История</p>
				<p>Схема проезда</p>
			</div>
			<div style="display:<%=enVisibility%>">
				<h1 align="center">Service provider information displayed here</h1>
				<p>Address</p>
				<p>History</p>
				<p>Location map</p>
			</div>
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</fmt:bundle>
</html>
