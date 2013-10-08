<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<%
			String logPageName = "/WEB-INF/loginform.jsp";
			if (session.getAttribute("user") != null) {
				logPageName = "/WEB-INF/logoutform.jsp";
			}
		%>
		<jsp:include page="<%=logPageName%>" />
		<jsp:include page="mainmenu.jsp" />
