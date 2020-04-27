import calculator.*;
import javax.naming.*;
import java.util.*;
public class TestClientEjb3 {
	public static void main(String[] args)  throws Exception {
		  String  result="";
		  Context context = null;
		  System.out.println("\n\n\t Hello ...");
		   try {
                        Properties props = new Properties();
                        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); 
                        props.put(Context.SECURITY_PRINCIPAL, "ejbUserOne");
                        props.put(Context.SECURITY_CREDENTIALS, "ejbPasswordOne@123");
                        props.put("jboss.naming.client.ejb.context", true);
                        context = new InitialContext(props);	
                        System.out.println("\n\tGot initial Context: "+context);	

                        CallerRemote remote=(CallerRemote)context.lookup("EJBs_App/EJB-Two/CallerName!calculator.CallerRemote");
                        
                        System.out.println("\n\n\t Got CallerRemote : "+remote);
                        result=remote.callEJBOne(1000,2000);
		      }
		   catch(Exception e){ e.printStackTrace(); }
		    System.out.println("ONE----result = "+result);
	 }
}
