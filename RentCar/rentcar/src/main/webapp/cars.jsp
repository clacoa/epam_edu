<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js">
	function refreshModels() {
		alert("refresh models");
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Our cars</title>
</head>
<body style="height: 100%">
	<jsp:include page="/WEB-INF/header.jsp" />
	<div class="content" style="display: table; width: 100%;">
		<div id="table"></div>
		<%String s="BMW"; %>
		<mytag:CarsTable></mytag:CarsTable>
		<!--<jsp:include page="/WEB-INF/carstable.jsp" />-->
	</div>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>