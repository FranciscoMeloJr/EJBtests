import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class StandaloneSecure {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Getting the Remote");
    //SumRemote remote = (SumRemote) getInitialContext().lookup("ejb:/EjbSum/Sum!" + SumRemote.class.getName());
    //Results:Got the remote InterfaceProxy for remote EJB StatelessEJBLocator for "/EjbRemote/Sum", view is interface SumRemote, affinity is None

	//SumRemote remote = (SumRemote) getInitialContext().lookup("ejb:/EjbSum/Sum!Sum");
    //Results: Exception in thread "main" java.lang.IllegalArgumentException: Sum is not an interface

    String username = "quickstartUser";
    String password = "quickstartPwd1!";
    SecureSumRemote remote = (SecureSumRemote) getInitialContext(username, password).lookup("ejb:/EjbSecure/SecureSum!SecureSumRemote");
    //Results: Got the remote InterfaceProxy for remote EJB StatelessEJBLocator for "/EjbRemote/Sum", view is interface SumRemote, affinity is None

    System.out.println("Got the remote Interface" + remote);
    System.out.println(remote.add(10,10));
    System.out.println(remote.minus(10,10));
	}
	//getInitialContext:
	public static Context getInitialContext(String username, String password) throws NamingException{
	   Properties props = new Properties();
	   props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	   props.put(Context.PROVIDER_URL,"remote+http://localhost:8080");//"remote+http://localhost:8080,remote+http://localhost:8180");
   	   if(username != null && password != null) {
      		props.put(Context.SECURITY_PRINCIPAL, username);
      		props.put(Context.SECURITY_CREDENTIALS, password);
   	   }
	   final Context context = new InitialContext(props);
	   return context;
	}
}