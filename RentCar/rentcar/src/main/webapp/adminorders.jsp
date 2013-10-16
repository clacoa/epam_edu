<%@page import="com.epam.edu.rentcar.model.OrderData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@page import="com.epam.edu.rentcar.model.CarFilter"%>
<%@page import="com.epam.edu.rentcar.model.PrintElement"%>
<%@page import="com.epam.edu.rentcar.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	CarFilter cf = null;

	if (request.getAttribute("filter") != null) {
		cf = (CarFilter) request.getAttribute("filter");
	}
	String language = request.getSession().getAttribute("language")!=null ? request.getSession().getAttribute("language").toString():"ru";
	User user = (User) session.getAttribute("user");
	request.getSession().setAttribute("prevUrl",
			request.getServletPath().toString());
%>
<html>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="i18n">
	<head>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<script type="text/javascript" src="./js/rentcar.js">	
</script>
<script type="text/javascript">
	function fillFilterValue() {
		setSelect('modelSelect','<%=cf == null ? "" : cf.getModelName()%>');
		setSelect('costSelect','<%=cf == null ? "" : cf.getCost()%>');
		setInputValue('descriptionFilter','<%=cf == null ? "" : cf.getDescription()%>');
	}
	function clearFilterValue() {
		setSelect('modelSelect', '');
		setSelect('costSelect', '');
		setInputValue('descriptionFilter', '');
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="orders.title" /></title>
	</head>
	<body style="height: 100%" onload="fillFilterValue()">
		<jsp:include page="/WEB-INF/header.jsp" />
		<div class="content" style="display: table; width: 100%;">
			<div id="table"
				style="display: list-item; block; text-align: center;"></div>
			<fieldset>
				<mytag:OrdersTable user='<%=user%>'></mytag:OrdersTable>
			</fieldset>
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</fmt:bundle>
</html>