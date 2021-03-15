<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.logging.Logger" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	The telenovela makes no sense
	
    <%
         out.println("Your IP address is " + request.getRemoteAddr());
    %>
    <% System.out.println( "printing stuff");%>
	<p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p> </BR>
	
	<% Logger logger = Logger.getLogger(this.getClass().getName());
	logger.info( "logger" );%>
</body>
</html>