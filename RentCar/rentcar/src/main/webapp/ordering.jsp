
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
	request.getSession().setAttribute("prevUrl",request.getServletPath().toString());
	String locale = request.getSession().getAttribute("language") != null ? request.getSession().getAttribute("language").toString(): "ru";
	OrderData orderData = (OrderData) session.getAttribute("orderData");
	CarData carData = orderData != null ? orderData.getCarData() : null;

	boolean carVis = carData != null ? true : false;
	String carVisibility = carVis ? "block" : "none";
	String selectVisibility = carVis ? "none" : "block";
%>
<html>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="i18n">

	<head>
<link rel="stylesheet" type="text/css" href="rentcar.css">
<link rel="stylesheet" type="text/css" href="jquery-ui.css">
<script src="./js/jquery-1.10.2.min.js"></script>
<script src="./js/jquery-ui-1.10.3.datepicker.min.js"></script>
<script src="./js/jquery.ui.datepicker-ru.js" type="text/javascript"></script>
<script src="./js/jquery.ui.datepicker-en-GB.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/rentcar.js"></script>
<title><fmt:message key="ordering.ordering" /></title>
<script type="text/javascript">
	$(function() {
		$.datepicker
				.setDefaults($.extend($.datepicker.regional['<%=locale%>']));
		$("#datepickerfrom").datepicker({
			minDate : "-0d",
			maxDate : "+3d"
		});
		$("#datepickerto").datepicker();

	});
	function setDateInerval() {
		var mindate = $("#datepickerfrom").datepicker("getDate");
		mindate.setDate(mindate.getDate() + 1);
		$("#datepickerto").datepicker("option", "minDate", mindate);
		var maxdate = new Date();
		maxdate.setDate(mindate.getDate() + 30);
		$("#datepickerto").datepicker("option", "maxDate", maxdate);
	}
	function checkAndPost() {
		if (checkEmpty(OrderingForm.datepickerfrom.value) && checkEmpty(OrderingForm.datepickerto.value))
 		{
			OrderingForm.submit();
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

</script>
	</head>
	<body style="height: 100%">
		<jsp:include page="/WEB-INF/header.jsp" />
		<div class="content" style="display: table">
			<div class="ordering">
				<fieldset style="height: 100%">
				<div id="form">
					<form style="vertical-align: middle; text-align: center"
						name="OrderingForm" action="prepareorder.controller" method="post">
						<a style="display:<%=selectVisibility%>" href="cars.jsp"><fmt:message key="ordering.selectcar" /></a>
						<table  style="display:<%=carVisibility%>">
							<tr>
								<td>
									<table>
										<tr>
											<td><fmt:message key="cars.table.name" /></td>
											<td><fmt:message key="cars.table.description" /></td>
											<td><fmt:message key="cars.table.cost" /></td>
										</tr>
										<tr>
											<td><hr /></td>
											<td><hr /></td>
											<td><hr /></td>
											<td><hr /></td>
										</tr>
										<tr>
											<td><%=carData != null ? carData.getModelName() : ""%></td>
											<td><%=carData != null ? carData.getDescription() != null ? carData.getDescription() : "":""%></td>
											<td><%=carData != null ? carData.getCost().toString() : ""%></td>
											<td>
												<div class='button' onclick="post_to_url('clearcurrentcar.controller','POST')">
													<img src='./Images/lightbulb.png' height='100%' align="left"> &nbsp
													<fmt:message key="clear" />
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td><hr/></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr id="datefrom">
											<td style="text-align: right;"><fmt:message
													key="ordering.datefrom" />*</td>
											<td style="text-align: left;"><input name="datepickerfrom" type="text"
												id="datepickerfrom" onchange="setDateInerval()" /></td>
										</tr>
										<tr>
											<td style="text-align: right;"><fmt:message
													key="ordering.dateto" />*</td>
											<td style="text-align: left;"><input name="datepickerto" type="text"
												id="datepickerto"/></td>
										</tr>
									</table>
										<div class='button' onclick="checkAndPost()">
											<img src='./Images/check.png' height='100%' align="left">
												&nbsp 
											<fmt:message key="cars.filter.action.button" />
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