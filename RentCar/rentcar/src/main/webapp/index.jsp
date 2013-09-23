<%@page import="java.util.ArrayList"%>
<%@page import="com.epam.edu.rentcar.db.ConnectionPool"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.epam.edu.rentcar.db.dao.impl.UserDao"%>
<%@page import="com.epam.edu.rentcar.db.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<html>
<body>
	<h2>Hello World!</h2>
	<%String s="";%>
	<%
		try{
		Connection conn=ConnectionPool.getInstance().getConnection();	
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from car_state");
		int cnt = 1;
		while (rs.next()) {
			s+=(cnt++) + " id:" + rs.getLong("id")
					+ " carState:" + rs.getString("carstate") + "<hr/><br/>";					
		}
		rs.close();
		st.close();
		conn.close();
	}
	catch(Exception e) {
		Connection conn=null;
		if (conn != null)
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	%>
	<%=s%>
	
	<%--<%UserDao ud= new UserDao();
	User user=ud.get(Long.valueOf(1));
	s=user.toString();--%>

	<% s="";
	UserDao ud= new UserDao();
	List<User> userList = ud.getAll();
	for (User user1: userList){
		s+= user1.toString()+"<hr/><br/>";
	}
	%>
	<%= s%>
	
</body>
</html>
