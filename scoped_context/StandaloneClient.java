import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class StandaloneClient {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Two initial context");

    SumRemote remote = (SumRemote) getInitialContext().lookup("ejb:/EjbSum/Sum!SumRemote");

    System.out.println("Got the remote Interface" + remote);
    System.out.println(remote.add(10,10));

    remote = (SumRemote) getInitialContext().lookup("ejb:/EjbSum/Sum!SumRemote");
    System.out.println("Got the remote Interface 2" + remote);
    System.out.println(remote.add(20,20));

    
    remote = (SumRemote) getInitialContext().lookup("ejb:/EjbPrint/Sum!SumRemote");
    System.out.println("Got the remote Interface 3" + remote);
    System.out.println(remote.add(30,20));


	}
	//getInitialContext:
	public static Context getInitialContext() throws NamingException{
	   Properties props = new Properties();
	   props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	   props.put(Context.PROVIDER_URL,"remote+http://localhost:8080");//"remote+http://localhost:8080,remote+http://localhost:8180");
	   final Context context = new InitialContext(props);
	   return context;
	}

}

/*
  Steps:
     1. Deploy EJB 1
     2. Deploy EJB 2
     3. Run test

/*WFLYEJB0473: JNDI bindings for session bean named 'Sum' in deployment unit 'deployment "EjbSum.jar"' are as follows:

	java:global/EjbSum/Sum!SumRemote
	java:app/EjbSum/Sum!SumRemote
	java:module/Sum!SumRemote
	java:jboss/exported/EjbSum/Sum!SumRemote
	ejb:/EjbSum/Sum!SumRemote
	java:global/EjbSum/Sum
	java:app/EjbSum/Sum
	java:module/Sum


21:17:15,227 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-7) WFLYEJB0473: JNDI bindings for session bean named 'Sum' in deployment unit 'deployment "EjbPrint.jar"' are as follows:

	java:global/EjbPrint/Sum!SumRemote
	java:app/EjbPrint/Sum!SumRemote
	java:module/Sum!SumRemote
	java:jboss/exported/EjbPrint/Sum!SumRemote
	ejb:/EjbPrint/Sum!SumRemote
	java:global/EjbPrint/Sum
	java:app/EjbPrint/Sum
	java:module/Sum
*/
