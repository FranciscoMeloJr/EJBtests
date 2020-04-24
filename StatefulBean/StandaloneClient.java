import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

import java.util.*;

public class StandaloneClient {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Getting the Remote");

    LibraryStatefulSessionBeanRemote libraryBean = (LibraryStatefulSessionBeanRemote) getInitialContext().lookup("ejb:/EjbStateful/LibraryStatefulSessionBean!LibraryStatefulSessionBeanRemote?stateful");
    //Results: Got the remote InterfaceProxy for remote EJB StatelessEJBLocator for "/EjbRemote/Sum", view is interface SumRemote, affinity is None

    System.out.println("Got the remote Interface" + libraryBean);
    libraryBean.addBook("Florence");
    List<String> books = libraryBean.getBooks();
    for (String eachB : books) {
    	System.out.println(eachB);
    	}
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
