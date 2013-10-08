<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>

	<fmt:bundle basename="i18n_en" prefix="mainmenu.">
			<table class="menu" height="100%" width="100%" align="left" cellpadding="5px">
				<tr>
					<td><a href="cars.jsp"><fmt:message key="about" /></a></td>
					<td><fmt:message key="info" /></td>
					<td><fmt:message key="auto" /></td>
					<td><fmt:message key="reserv" /></td>
					<td><fmt:message key="orders" /></td>
				</tr>
			</table>
	</fmt:bundle>
