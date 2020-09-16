package endpoint;

import sampleejbservice.ejb.HelloWorldRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Path("/testejb")
public class TestEJbEndpoint {

    private static Logger logger = Logger.getLogger(TestEJbEndpoint.class.getName());

    //http://localhost:8080/sampleejbclient-1.0-SNAPSHOT/testejb/test?callsize=2&cacheejb=false
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@QueryParam("callsize") Integer callsize, @QueryParam("cacheejb") Boolean cacheejb,  @QueryParam("plain") Boolean plain) throws NamingException {

        if( callsize == null){
            callsize= 1;
        }

        if(cacheejb == null ){
            cacheejb = Boolean.TRUE;
        }

        HelloWorldRemote worldRemote = null;

        List<String> response = new ArrayList<>();

        callsize--;
        for(int i = callsize; i >= 0; i--) {

            if(!cacheejb || worldRemote == null ) {
                logger.info("Looking up EJB...");
                Context context = new InitialContext(i==1? getEJBProperties():getEJBProperties2());
                worldRemote = (HelloWorldRemote) context
                        .lookup("sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote");
                        //.lookup("/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote");
            }
            logger.info("Calling EJB "+(i+1)+" ...");
            response.add(worldRemote.sayHello("Anonymous"));

        }

        return Response.ok(response).build();

    }

    //http://localhost:8080/sampleejbclient-1.0-SNAPSHOT/testejb/hello
    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ejb() throws NamingException {

        HelloWorldRemote worldRemote = null;

        List<String> response = new ArrayList<>();


        //http://localhost:8080/sampleejbclient-1.0-SNAPSHOT/testejb?callsize=2&cacheejb=false
        response.add("Bienvenue a Quebec");
        return Response.ok(response).build();
        
    }


    private Properties getEJBProperties() {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL,"remote://localhost:8180");//"remote://localhost:4447");
        jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS","false");
        jndiProps.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED","false");
        jndiProps.put("jboss.naming.client.ejb.context", "true");
        jndiProps.put("java.naming.security.principal", "ejbuser");
        jndiProps.put("java.naming.security.credentials", "redhat1");

        return jndiProps;
    }


    private Properties getEJBProperties2() {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");

        jndiProps.put("java.naming.provider.url", "http-remoting://localhost:8180");//"remote://localhost:4448");

        jndiProps.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
        jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS","false");
        jndiProps.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED","false");
        jndiProps.put("jboss.naming.client.ejb.context", "true");
        jndiProps.put("java.naming.security.principal", "ejbuser");
        jndiProps.put("java.naming.security.credentials", "redhat1");

        //jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS","JBOSS-LOCAL-USER,DIGEST-MD5");
        //jndiProps.put("remote.connection.default.channel.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS","false");


        return jndiProps;
    }


}
