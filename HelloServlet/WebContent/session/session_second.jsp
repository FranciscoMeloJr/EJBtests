<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
<body>  
<%   
  
String name=(String)session.getAttribute("user");    //Getting Session Attribute
String email=(String)session.getAttribute("email");
out.print("Welcome to Session Page: Name "+name+ " Email: "+email);  
  
%>  
</body>  
</html>