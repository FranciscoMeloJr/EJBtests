import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Binding;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.Hashtable;

import java.lang.reflect.Field;

import java.util.Properties;

import java.io.*;

//This class gets the property from another file
public class StandaloneFile {
  //Main
  public static void  main(String [] rgstring){
    try {
      
      //this returns java.naming.factory.initial
      System.out.println(Context.INITIAL_CONTEXT_FACTORY.toString());
      //this returns java.naming.provider.url
	  System.out.println(Context.PROVIDER_URL.toString());

      Context context = getContextDirectly();
    }
    catch (NamingException namingexception) {
         System.out.println("Name not here");
    }

  }

  //Create Context from file: jndi.properties
  public static Context getContextFile() throws NamingException{
  	 System.out.println("Reading from file");

    try{
      Properties props = new Properties();
      InputStream input = new FileInputStream("jndi.properties");
      props.load(input);  
      Context context = new InitialContext(props);
      return context;
    }
    catch (FileNotFoundException nofile) {
         System.out.println("File not here");
         return null;
    } 
    catch (IOException io) {
         System.out.println("IO error");
         return null;
    } 
  }
  //Create Context hard coded:
  public static Context getContext() throws NamingException{
      Hashtable hashtableEnvironment = new Hashtable();
      hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
      hashtableEnvironment.put(Context.PROVIDER_URL,"remote+http://localhost:8180");//(Context.PROVIDER_URL,"remote+http://localhost:8080");
      Context context = new InitialContext(hashtableEnvironment);
      return context;

  }

  //Create Context hard coded:
  public static Context getContextDirectly() throws NamingException{
  	  System.out.println("Directly");
      String jndiName="java:app/EjbSum/Sum!SumRemote";//ctx.lookup("java:global/EjbSum/Sum!SumRemote");
      SumRemote remote = (SumRemote) InitialContext.doLookup(jndiName); 
      System.out.println("Got the remote Interface" + remote);
      System.out.println(remote.add(10,10));
      return null;

  }

  //Get list Names
  public static NameClassPair listNames(Context context) throws NamingException{

        NamingEnumeration ctxlst =   context.list("");  
        NameClassPair ncp = null;
        while (ctxlst.hasMore()) {  
            ncp = (NameClassPair)ctxlst.next();  
            System.out.println( ncp.getClassName() + " : " + ncp.isRelative() +" : " + ncp.getName()  +" : " + ncp.toString() + " : " + ncp.getNameInNamespace());        
        }  
  		return ncp;
  }

}