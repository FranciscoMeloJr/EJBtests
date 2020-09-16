// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

import java.io.StringWriter;
import java.io.PrintWriter;


// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
   private String message;

   // Do required initialization
   public void init() throws ServletException {
      message = printMessage();
   }

   //Print message:
   public String printMessage(){

   return "Hallo Mars";
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
   }

   public void destroy() {
      // do nothing.
   }

   //POST
   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

      try{    
         message = getEJB();
      }catch(NamingException e) {
         StringWriter sw = new StringWriter();
         e.printStackTrace(new PrintWriter(sw));
         message = sw.toString();
      }
 
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
   }

   //getInitialContext:
   public static Context getInitialContext() throws NamingException{
      Properties props = new Properties();
      props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
      props.put(Context.PROVIDER_URL,"remote+http://localhost:8180");
      final Context context = new InitialContext(props);
      return context;
   }

   //Call Initial Context
   public static String getEJB() throws NamingException {
      
    System.out.println("Getting the Remote");
    
    //ejb:/EjbSum/Sum!SumRemote
    AdderImplRemote remote = (AdderImplRemote) getInitialContext().lookup("ejb:/EjbRemote/AdderImpl!AdderImplRemote");

    System.out.println("Got the remote Interface" + remote);
    return String.valueOf(remote.add(10,10));

   }
}