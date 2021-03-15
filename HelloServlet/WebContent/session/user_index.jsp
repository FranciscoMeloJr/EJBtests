<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>  
    	<c:out value="Using Session" />
    
        <pre>
        <form action="session/session.jsp">  
            Name  : <input type="text" name="uname">
            
            Email : <input type="text" name="email">

                    <input type="submit" value="Click"><br/>  
        </pre>
   	    </form>  
</body>  
</html> 