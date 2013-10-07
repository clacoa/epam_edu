<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="registration"
	class="com.epam.edu.rentcar.bean.RegistrationBean" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
			alert("Submit");
			RegistrationForm.submit();
		}
	}

	function checkEmail() {
		var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
		if (pattern.test(RegistrationForm.email.value)) {
			return true;
		} else {
			alert("Enter your email");
			return false;
		}
	}
	function checkEmpty(inputvalue) {
		if (!inputvalue) {
			alert("Empty field " + inputvalue);
			return false;
		} else {
			return true;
		}
	}
	function chekPassword() {
		if (RegistrationForm.password.value != RegistrationForm.passwordRepeat.value) {
			alert("The passwords do not match");
			return false;
		} else {
			return true;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<jsp:getProperty property="regMessage" name="registration" />

	<H1 id="error">
		<%
			registration.getRegMessage();
		%>
	</H1>
	<div class="registration">
		<center>
			<fieldset>
				<form name="RegistrationForm" action="ControllerServlet"
					method="post">
					<h3 id="error">
						<%
							if (request.getAttribute("msg") != null) {
								out.println(request.getAttribute("msg"));
							} else {
								out.println("Register Page");
							}
						%>
					</h3>

					<legend>
						<h2>Create you personal account</h2>
					</legend>
					<table>
						<tr>
							<td>Email:</td>
							<td><input type="text" name="email"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>NickName:</td>
							<td><input type="text" name="nickName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>Repeat Password:</td>
							<td><input type="password" name="passwordRepeat"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>FirstName:</td>
							<td><input type="text" name="firstName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>LastName:</td>
							<td><input type="text" name="lastName"></td>
							<td>*</td>
						</tr>
						<tr>
							<td>Passport:</td>
							<td><input type="text" name="passport"></td>
							<td>*</td>
						</tr>
					</table>
					<input name="command" type="hidden" value="registration" />
				</form>
				<div class="button" onclick="checkAndPost()">
					<img src="./Images/plus.png" height="100%"" align="left" /> Register
				</div>
			</fieldset>
		</center>
	</div>
</body>
</html>