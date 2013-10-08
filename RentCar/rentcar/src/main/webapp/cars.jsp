<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="mytag" uri="WEB-INF/mytags.tld"%>
<html>
<head>
<script type="text/javascript">
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
		<div style="display: inline-block;">
			<select id="markSelect" onchange="refreshModels()">
				<option>Select mark</option>
				<option>2</option>
				<option>3</option>
			</select> <select id="modelSelect">
				<option>Select model</option>
				<option>2</option>
				<option>3</option>
			</select>
		</div>
		<jsp:include page="/WEB-INF/carstable.jsp" />
	</div>
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>