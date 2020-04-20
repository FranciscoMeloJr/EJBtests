import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Binding;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.Hashtable;

public class StandaloneJNDI {
  public static void  main(String [] rgstring) {
    try {
      // Create the initial context.  The environment
      // information specifies the JNDI provider to use
      // and the initial URL to use (in our case, a
      // directory in URL form -- file:///...).
      Hashtable hashtableEnvironment = new Hashtable();
      hashtableEnvironment.put( Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
      hashtableEnvironment.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
      Context context = new InitialContext(hashtableEnvironment);

      addEnvironment(context);
      getEnvironment(context);
      // If you provide no other command line arguments,
      // list all of the names in the specified context and
      // the objects they are bound to.
      	System.out.println("Listing all names");
        NamingEnumeration namingenumeration = context.listBindings("");
        while (namingenumeration.hasMore()) {
          Binding binding = (Binding)namingenumeration.next();
          System.out.println(binding.getName() + " XXX " + binding.getObject()
          );
        }

		NameClassPair ncp = listNames(context);
  		//context.unbind(ncp.getName());    
    }
    catch (NamingException namingexception) {
      namingexception.printStackTrace();
    }
  }

  //Get Environment:
  public static void getEnvironment(Context context) throws NamingException{
  	System.out.println(context.getEnvironment());
  }

  //Add to the Environment
  public static void addEnvironment(Context context)  throws NamingException{
  	context.addToEnvironment("com.wiz.jndi.wizProp", "wizards");
  }

  //Get list:
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