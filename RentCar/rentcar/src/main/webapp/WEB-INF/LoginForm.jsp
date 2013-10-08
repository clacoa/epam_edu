<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="rentcar.css">
<script type="text/javascript">
	function submitLoginForm() {
		LoginForm.submit();
	}
</script>
	<div class="login">
		<fieldset  style="height: 100%">
			<form name="LoginForm" action="login.controller" method="post">
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr
						style="color:#ff0000; visibility:<%=request.getAttribute("msg") != null ? "visible"
					: "hidden"%>">
						<td colspan=2>
							<%
								out.print(request.getAttribute("msg"));
							%>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="button" onclick="submitLoginForm()">
								<img src="./Images/login.png" height="100%" align="left" />
								Login
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="registration.jsp">register</a></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
