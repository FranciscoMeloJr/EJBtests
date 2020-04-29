import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class SingletonClient {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Getting the Remote");
    SingletonX remote = (SingletonX) getInitialContext().lookup("java:global/ejb/josias");

    System.out.println("Got the remote" + remote);
    remote.add(1);
    System.out.println(remote.getTotal());

	}
	//getInitialContext:
	public static Context getInitialContext() throws NamingException{
	   Properties props = new Properties();
	   props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	   props.put(Context.PROVIDER_URL,"remote+http://localhost:8080");//"remote+http://localhost:8080,remote+http://localhost:8180");
	   final Context context = new InitialContext(props);
	   return context;
	}
}
