import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
import java.io.IOException;

import java.util.Properties;

import java.util.*;

import java.io.FileInputStream;

public class StandaloneClient {

	public static void main(String[]args) throws NamingException {
   	
    System.out.println("Getting the Remote");
	LibraryStatefulSessionBeanRemote libraryBean = null;

    try{
    	libraryBean = (LibraryStatefulSessionBeanRemote) getInitialContext().lookup("ejb:/EjbStateful/LibraryStatefulSessionBean!LibraryStatefulSessionBeanRemote?stateful");
    //Results: Got the remote InterfaceProxy for remote EJB StatelessEJBLocator for "/EjbRemote/Sum", view is interface SumRemote, affinity is None
    }catch (Exception e){
        System.out.println("Something went wrong ");
    }

    System.out.println("Got the remote Interface" + libraryBean);
    libraryBean.addBook("Florence");
    List<String> books = libraryBean.getBooks();
    for (String eachB : books) {
    	System.out.println(eachB);
    	}
	}
	//getInitialContext:
	public static Context getInitialContext() throws NamingException, IOException{
	   Properties props = new Properties();
	   props.load(new FileInputStream("jndi.properties"));
	   final Context context = new InitialContext(props);
	   return context;
	}
}
