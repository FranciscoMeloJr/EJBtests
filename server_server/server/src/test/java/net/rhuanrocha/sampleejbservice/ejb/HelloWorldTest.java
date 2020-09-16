package net.rhuanrocha.sampleejbservice.ejb;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public class HelloWorldTest {

    private static Logger logger = Logger.getLogger(HelloWorldTest.class.getName());
    private Optional<String> callsize;
    private Optional<String> cacheejb;
    private Optional<String> jndi;
    private Optional<String> sayhelloinput;

    @Before
    public void initTestConfigurations(){
        callsize = Optional.ofNullable(System.getProperty("net.rhuanrocha.callsize"));
        cacheejb = Optional.ofNullable(System.getProperty("net.rhuanrocha.cacheejb"));
        jndi = Optional.ofNullable(System.getProperty("net.rhuanrocha.jndi"));
        sayhelloinput = Optional.ofNullable(System.getProperty("net.rhuanrocha.sayhelloinput"));

    }

    public List<? extends Object> test(){

       List<Integer> integers = new ArrayList<Integer>();

        return integers;

    }

    @Test
    public void testEjbRemote() throws NamingException {

        HelloWorldRemote worldRemote = null;

        boolean isCacheejb = Boolean.parseBoolean(cacheejb.orElse("true"));
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = Integer.parseInt(callsize.orElse("1"))-1; i >= 0; i-- ) {

            if(!isCacheejb || worldRemote == null ) {
                logger.info("Looking up EJB...");
                Context context = new InitialContext(getEJBProperties());
                worldRemote = (HelloWorldRemote) context
                        .lookup(jndi.orElse("ejb:/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote"));
            }
            logger.info("Calling EJB "+(i+1)+" ...");
            Assert.assertEquals(worldRemote.sayHello(sayhelloinput.orElse("Anonymous")), "Hello "+sayhelloinput.orElse("Anonymous"));

        }
    }

    private Properties getEJBProperties(){
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");

        jndiProps.put("remote.connections","default");
        jndiProps.put("remote.connection.default.protocol","http-remoting");
        jndiProps.put("remote.connection.default.host","localhost");
        jndiProps.put("remote.connection.default.port","8080");
        jndiProps.put("remote.connection.default.username", "ejbuser");
        jndiProps.put("remote.connection.default.password", "redhat1!");

        jndiProps.put("remote.connection.default.channel.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS","false");
        jndiProps.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.HEARTBEAT_INTERVAL","5000");
        jndiProps.put("remote.connection.default.connect.options.org.xnio.Options.KEEP_ALIVE","true");
        jndiProps.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.MAX_INBOUND_MESSAGES","1");
        jndiProps.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.MAX_OUTBOUND_MESSAGES","1");


        return jndiProps;
    }
}
