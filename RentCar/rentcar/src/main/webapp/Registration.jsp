<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="registration"
	class="com.epam.edu.rentcar.bean.RegistrationBean" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
		function checkAndPost() {
			if (RegistrationForm.password.value!=RegistrationForm.passwordRepeat.value){
				document.getElementById("error").innerText="Не совпадают пароли";				
			} else {
				RegistrationForm.submit();
			}
		}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <style type="text/css">
   .registration { 
    width: 500px; 
    background: #ccc;
    padding: 5px;
    padding-right: 20px; 
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
	<h1>Registration Page</h1>
	<div class="registration ">
	<center>		
		<form name="RegistrationForm" action="ControllerServlet" method="post">
		<h3 id="error">
							<%
						if (request.getAttribute("msg") != null) {
							out.println(request.getAttribute("msg"));
						} else {
							out.println("Register Page");
						}
					%>
		</h3>
			<fieldset>
			<legend><h2>Create you personal account</h2></legend>
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>NickName:</td><td><input type="text" name="nickName"></td>
				</tr>
				<tr>
					<td>Password:</td><td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>Repeat Password:</td><td><input type="password" name="passwordRepeat"></td>
				</tr>
				<tr>
					<td>FirstName:</td><td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<td>LastName:</td><td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Passport:</td><td><input type="text" name="passport"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"></td>
				</tr>
			</table>
			<input name="command" type="hidden" value="registration" />
			</fieldset>
		</form>
	</center>
	</div>
</body>
</html>