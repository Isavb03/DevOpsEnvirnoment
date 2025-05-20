
<%@page import="java.sql.*" %>
<%@ page import="java.sql.Connection,java.sql.Statement,java.sql.ResultSet" %>
<%@page import="project.DatabaseConfig" %>
<%
String faculty=request.getParameter("faculty");
String degree=request.getParameter("degree");
String RegNo=request.getParameter("RegNo");
String name=request.getParameter("name");
String NICno=request.getParameter("NICno");
String gender=request.getParameter("gender");



try{
	
	Connection con=DatabaseConfig.getCon();
	Statement st=con.createStatement();
	st.executeUpdate("insert into student values('"+faculty+"','"+degree+"','"+RegNo+"','"+name+"','"+NICno+"','"+gender+"')");
	response.sendRedirect("adminHome.jsp");
	
}catch(Exception e)
{
	
	System.out.println(e);
}




%>