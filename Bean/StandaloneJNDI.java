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

public class StandaloneJNDI {
  //Main
  public static void  main(String [] rgstring){
    try {
      // Create the initial context.  The environment
      // information specifies the JNDI provider to use
      // and the initial URL to use (in our case, a
      // directory in URL form -- file:///...).

      Context context = getContext();
      //Add and Get environment
      addEnvironment(context);
      getEnvironment(context);
      // If you provide no other command line arguments,
      // list all of the names in the specified context and
      // the objects they are bound to.
      	System.out.println("Listing all names");
        NamingEnumeration namingenumeration = context.listBindings("");
        while (namingenumeration.hasMore()) {
          Binding binding = (Binding)namingenumeration.next();
          System.out.println(binding.getName() + " === " + binding.getObject()
          );
        }

		NameClassPair ncp = listNames(context);
  		//context.unbind(ncp.getName());    
    listFields(ncp);
    }
    catch (NamingException namingexception) {
         System.out.println("Name not here");
    }
    catch (NoSuchFieldException nosuchfield) {
         System.out.println("Field not here");
    }
  }

  //Create Context hard coded:
  public static Context getContext() throws NamingException{
      Hashtable hashtableEnvironment = new Hashtable();
      hashtableEnvironment.put( Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
      hashtableEnvironment.put(Context.PROVIDER_URL,"remote+http://localhost:8180");//(Context.PROVIDER_URL,"remote+http://localhost:8080");
      Context context = new InitialContext(hashtableEnvironment);
      return context;

  }
  //Get Environment:
  public static void getEnvironment(Context context) throws NamingException{
  	System.out.println(context.getEnvironment());
  }

  //Add to the Environment
  public static void addEnvironment(Context context)  throws NamingException{
  	context.addToEnvironment("com.wiz.jndi.wizProp", "wizards");
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

  //Get field objects form object
  public static void listFields(Object unk) throws NoSuchFieldException {
    try{
        Class<?> clazz = unk.getClass();
        System.out.println(unk.getClass().toString());
        Field field = clazz.getField("fieldName"); //Note, this can throw an exception if the field doesn't exist.
        Object fieldValue = field.get(unk);
    }catch(IllegalAccessException illegalaccess){
      illegalaccess.printStackTrace();
    }

  }
}