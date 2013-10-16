
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.epam.edu.rentcar.util.CommonBundle"%>
<%@page import="com.epam.edu.rentcar.service.Test"
	contentType="text/html;charset=utf-8"%>
<%@page import="com.epam.edu.rentcar.entity.Car"%>
<%@page import="com.epam.edu.rentcar.model.CarData"%>
<%@page import="com.epam.edu.rentcar.model.OrderData"%>
<%@page import="java.util.*"%>
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	request.getSession().setAttribute("prevUrl",
			request.getServletPath().toString());
	String locale = request.getSession().getAttribute("language") != null ? request
			.getSession().getAttribute("language").toString()
			: "ru";
%>
<html>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="i18n">

	<head>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<script type="text/javascript" src="./js/rentcar.js"></script>
<title><fmt:message key="orders.admin.addworks" /></title>
<script type="text/javascript">
	function checkAndPost() {
		if (checkEmpty(DamageForm.addcost.value) && isNumber(DamageForm.addcost.value))
 		{
			DamageForm.submit();
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
	function isNumber(inputvalue) {
		if (!isNaN(parseFloat(inputvalue)))
		  return true;
		} else {
			alert('<%=CommonBundle.getProperty("notnumeric", new Locale(locale))%>');
			return false;
		}
	}
</script>
	</head>
	<body style="height: 100%">
		<jsp:include page="/WEB-INF/header.jsp" />
		<div class="content" style="display: table">
			<div class="ordering">
				<fieldset style="height: 100%">
					<div id="form">
						<form style="vertical-align: middle; text-align: center"
							name="DamageForm" action="commitdamageorder.controller" method="post">
							<table>
								<tr>
									<td><fmt:message key="orders.admin.addworkscost" />
										<br /> <input type='text' name='addcost' value=''>
									</td>
									<td>
										<div class='button'
											onclick="DamageForm.submit()">
											<img src='./Images/check.png' height='100%' align="left">
												&nbsp 
											<fmt:message key="confirm" />
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</fieldset>
			</div>
		</div>
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</fmt:bundle>
</html>