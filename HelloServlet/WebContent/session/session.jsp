<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            String name = request.getParameter("uname");
            String email=request.getParameter("email");
            out.print("Welcome " + name+ " Email :"+email);
            

            session.setAttribute("user", name);      //set attribute in session
            session.setAttribute("email", email);
            
        
        %> 
        <a href = "session/session_second.jsp">Next Page</a>
    </body>
</html>