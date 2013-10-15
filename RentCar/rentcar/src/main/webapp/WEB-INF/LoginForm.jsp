<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@page import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<script type="text/javascript">
	function submitLoginForm() {
		LoginForm.submit();
	}
</script>
<fmt:setLocale value="${language}" /> 
<fmt:bundle basename="i18n" prefix="login.">
<div class="login">
	<fieldset style="height: 100%">
		<jsp:include page="language.jsp" />
		<form name="LoginForm" action="login.controller" method="post">
			<table>
				<tr>
					<td><fmt:message key="email" /></td>
					<td><input type="text" name="email"></td>
					<td><fmt:message key="password" /></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr
					style="color:#ff0000; visibility:<%=request.getAttribute("msg") != null ? "visible"
					: "hidden"%>">
					<td colspan=4>
						<%
							out.print(request.getAttribute("msg"));
						%>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="button" onclick="submitLoginForm()">
							<img src="./Images/login.png" height="100%" align="left" />
							&nbsp<fmt:message key="login" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><a href="registration.jsp"><fmt:message key="register" /></a></td>
				</tr>
			</table>
			
		</form>
	</fieldset>
</div>
</fmt:bundle>
