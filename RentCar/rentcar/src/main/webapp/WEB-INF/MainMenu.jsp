<%@page import="org.apache.commons.lang3.LocaleUtils"%>
<%@page import="java.util.Locale"%>
<%@page import="com.epam.edu.rentcar.util.CommonBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@page import="com.epam.edu.rentcar.entity.User"%>
<%
	User user = (User) session.getAttribute("user");
	boolean adminVis = user != null ? user.getRole().getId() == 2L : false;
	boolean userVis = user != null ? user.getRole().getId() != 2L : false;
	String adminVisibility= adminVis ? "block" : "none";
	String userVisibility= userVis ? "block" : "none";
	
%>

<fmt:setLocale value="${language}" />
<fmt:bundle basename="i18n" prefix="mainmenu.">
	<table class="menu" height="100%" width="100%" align="left"
		cellpadding="5px">
		<tr>
			<td><fmt:message key="info"/></td>
			<td><a href="cars.jsp"><fmt:message key="auto" /></a></td>
			<td><a href="ordering.jsp"><fmt:message key="reserv"/></a></td>
			<td style="display:<%=userVisibility%>"><a href="orders.jsp"><fmt:message key="orders"/></a></td>
			<td style="display:<%=adminVisibility%>"><a href="orders.jsp"><fmt:message key="adminorders"/></a></td>
			<td><fmt:message key="about"/></td>
		</tr>
	</table>
</fmt:bundle>
