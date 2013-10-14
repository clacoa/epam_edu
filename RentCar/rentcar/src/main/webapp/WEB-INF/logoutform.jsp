<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.entity.User"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<script type="text/javascript" src="./js/rentcar.js"></script>
<fmt:setLocale value="${language}" /> 
<fmt:bundle basename="i18n" prefix="logout.">
<%
	request.setAttribute("command", "logout");
	User user = (User) session.getAttribute("user");
%>
<div class="login">
	<fieldset style="height: 100%">
		<jsp:include page="language.jsp" />
		<table>
			<tr>
				<td><fmt:message key="hello" />&nbsp<%=user.getNickName()%></td>
			</tr>
			<tr>
				<td>
					<div class="button"
						onclick="post_to_url('logout.controller','GET')">
						<img src="./Images/logout.png" height="100%" align="left" />
						&nbsp<fmt:message key="logout" />
					</div>
				</td>
			</tr>
		</table>
	</fieldset>
</div>
</fmt:bundle>