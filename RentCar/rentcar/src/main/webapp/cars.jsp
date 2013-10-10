<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<%@page import="com.epam.edu.rentcar.model.CarFilter"%>
<html>
<head>
<script type="text/javascript" src="./js/rentcar.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Our cars</title>
</head>
<body style="height: 100%">
	<jsp:include page="/WEB-INF/header.jsp" />
	<div class="content" style="display: table; width: 100%;">
		<div id="table"></div>
		<%
			String s = "BMW";
		%>
		<%
			CarFilter cf = null;

			if (request.getAttribute("carFilter") != null) {
				cf =(CarFilter) request.getAttribute("carFilter");
			}
		%>
		<div class='button' onclick="post_to_url_params('searchcar.controller',{'modelSelect':getSelect('modelSelect'),'costSelect':getSelect('costSelect'),'descriptionFilter':getInput('descriptionFilter')},'GET')"> <img src='./Images/search.png' height='100%' align='left'> Search</div>
		<mytag:CarsTable filter='<%=cf%>'></mytag:CarsTable>
	</div>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>