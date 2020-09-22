import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class StandaloneClient {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Getting the Remote");

    SumRemote remote = (SumRemote) getInitialContext().lookup("ejb:/EjbSum/Sum!SumRemote");

    System.out.println("Got the remote Interface" + remote);
    System.out.println(remote.add(10,10));

	}
	public static Context getInitialContext() throws NamingException{
	   Properties props = new Properties();
	   props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	   props.put(Context.PROVIDER_URL,"remote+http://localhost:8180");
	   final Context context = new InitialContext(props);
	   return context;
	}
}
