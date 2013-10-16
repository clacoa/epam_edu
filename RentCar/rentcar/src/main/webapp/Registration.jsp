<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.epam.edu.rentcar.util.CommonBundle"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.getSession().setAttribute("prevUrl",request.getServletPath().toString());
	String locale = request.getSession().getAttribute("language") != null ? request.getSession().getAttribute("language").toString(): "ru";
%>
<fmt:setLocale value="${language}" /> 
<fmt:bundle basename="i18n" prefix="registration.">
<html>
<head>
<script type="text/javascript" src="./js/rentcar.js"></script>
<script type="text/javascript">
	function checkAndPost() {
		if (checkEmail() && chekPassword()
				&& checkEmpty(RegistrationForm.email.value)
				&& checkEmpty(RegistrationForm.password.value)
				&& checkEmpty(RegistrationForm.nickName.value)
				&& checkEmpty(RegistrationForm.passwordRepeat.value)
				&& checkEmpty(RegistrationForm.firstName.value)
				&& checkEmpty(RegistrationForm.lastName.value)
				&& checkEmpty(RegistrationForm.passport.value)) {
			RegistrationForm.submit();
		}
	}

	function checkEmail() {
		var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
		if (pattern.test(RegistrationForm.email.value)) {
			return true;
		} else {
			alert('<%=CommonBundle.getProperty("registration.enteremail", new Locale(locale))%>');
			return false;
		}
	}
	function checkEmpty(inputvalue) {
		if (!inputvalue) {
			alert('<%=CommonBundle.getProperty("mandatory", new Locale(locale))%>');
			return false;
		} else {
			return true;
		}
	}
	function chekPassword() {
		if (RegistrationForm.password.value != RegistrationForm.passwordRepeat.value) {
			alert('<%=CommonBundle.getProperty("registration.passwordsfail", new Locale(locale))%>');
			return false;
		} else {
			return true;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="title" /></title>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<style type="text/css">
.registration {
	width: 500px;
	background: #E3E3E3;
	padding: 5px;
	border: solid 1px black;
	float: center;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	<div class=".ordering">
		<center>
			<fieldset>
				<form name="RegistrationForm" action="registration.controller"
					method="post">
					<h3 id="error">
						<%
							if (request.getAttribute("msg") != null) {
								out.println(request.getAttribute("msg"));
							}
						%>
					</h3>

					<legend>
						<h2><fmt:message key="legend" /></h2>
					</legend>
					<table>
						<tr>
							<td><fmt:message key="email" /></td>
							<td><input type="text" name="email"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="nick" /></td>
							<td><input type="text" name="nickName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="password" /></td>
							<td><input type="password" name="password"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="repeatpassword" /></td>
							<td><input type="password" name="passwordRepeat"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="firstname" /></td>
							<td><input type="text" name="firstName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="lastname" /></td>
							<td><input type="text" name="lastName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td><fmt:message key="passport" /></td>
							<td><input type="text" name="passport"></td>
							<td>*</td>
						</tr>
					</table>
				</form>
				<div class="button" onclick="checkAndPost()">
					<img src="./Images/plus.png" height="100%" align="left" />&nbsp<fmt:message key="register" />
				</div>
			</fieldset>
		</center>
	</div>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
</fmt:bundle>