1. Deploy the attached "EJBs_App.ear" on WildFly10

2. Open a terminal and Set the classpath to include the "wildfly-10.0.0.CR3-SNAPSHOT/bin/client/jboss-client.jar"

3. Run the client as following:


Server Output
==============

21:37:43,769 INFO  [org.jboss.weld.deployer] (MSC service thread 1-7) WFLYWELD0003: Processing weld deployment EJB-Two.jar
21:37:43,769 INFO  [org.jboss.weld.deployer] (MSC service thread 1-5) WFLYWELD0003: Processing weld deployment EJB-One.jar
21:37:43,782 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-5) WFLYEJB0473: JNDI bindings for session bean named 'CalculatorBean' in deployment unit 'subdeployment "EJB-One.jar" of deployment "EJBs_App.ear"' are as follows:

	java:global/EJBs_App/EJB-One/CalculatorBean!calculator.CalculatorLocal
	java:app/EJB-One/CalculatorBean!calculator.CalculatorLocal
	java:module/CalculatorBean!calculator.CalculatorLocal
	java:global/EJBs_App/EJB-One/CalculatorBean
	java:app/EJB-One/CalculatorBean
	java:module/CalculatorBean

21:37:43,783 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-7) WFLYEJB0473: JNDI bindings for session bean named 'CallerName' in deployment unit 'subdeployment "EJB-Two.jar" of deployment "EJBs_App.ear"' are as follows:

	java:global/EJBs_App/EJB-Two/CallerName!calculator.CallerRemote
	java:app/EJB-Two/CallerName!calculator.CallerRemote
	java:module/CallerName!calculator.CallerRemote
	java:jboss/exported/EJBs_App/EJB-Two/CallerName!calculator.CallerRemote
	java:global/EJBs_App/EJB-Two/CallerName
	java:app/EJB-Two/CallerName
	java:module/CallerName

21:37:43,795 INFO  [org.jboss.weld.deployer] (MSC service thread 1-2) WFLYWELD0006: Starting Services for CDI deployment: EJBs_App.ear
21:37:43,825 INFO  [org.jboss.weld.Version] (MSC service thread 1-2) WELD-000900: 2.3.0 (Final)
21:37:43,839 INFO  [org.jboss.weld.deployer] (MSC service thread 1-3) WFLYWELD0009: Starting weld service for deployment EJBs_App.ear
21:37:44,476 INFO  [org.jboss.as.server] (ServerService Thread Pool -- 34) WFLYSRV0010: Deployed "EJBs_App.ear" (runtime-name : "EJBs_App.ear")
21:37:44,526 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
21:37:44,526 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
21:37:44,526 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: WildFly Full 10.0.0.CR3-SNAPSHOT (WildFly Core 2.0.0.CR5) started in 3461ms - Started 400 of 684 services (339 services are lazy, passive or on-demand)




21:37:52,845 INFO  [org.jboss.ejb.client] (pool-1-thread-1) JBoss EJB Client version 2.1.2.Final
21:37:52,969 INFO  [stdout] (EJB default - 1) 
21:37:52,969 INFO  [stdout] (EJB default - 1) 
21:37:52,970 INFO  [stdout] (EJB default - 1) 	 Bean callEJBOne(a,b) called....
21:37:52,970 INFO  [stdout] (EJB default - 1) 
21:37:52,970 INFO  [stdout] (EJB default - 1) 
21:37:52,970 INFO  [stdout] (EJB default - 1) 	 Addition is : 3000
21:37:52,971 INFO  [stdout] (EJB default - 1) 
21:37:52,971 INFO  [stdout] (EJB default - 1) 
21:37:52,971 INFO  [stdout] (EJB default - 1) 	 [CallerBean] Got the Resule from CalculatorBean ---Sum = 3000

Client Output:
==============
$ java TestClientEjb3


	 Hello ...
Dec 10, 2015 9:37:00 PM org.xnio.Xnio <clinit>
INFO: XNIO version 3.3.2.Final
Dec 10, 2015 9:37:01 PM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.3.2.Final
Dec 10, 2015 9:37:01 PM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 4.0.14.Final

	Got initial Context: javax.naming.InitialContext@7a450ea8
Dec 10, 2015 9:37:01 PM org.jboss.ejb.client.remoting.VersionReceiver handleMessage
INFO: EJBCLIENT000017: Received server version 2 and marshalling strategies [river]
Dec 10, 2015 9:37:01 PM org.jboss.ejb.client.remoting.RemotingConnectionEJBReceiver associate
INFO: EJBCLIENT000013: Successful version handshake completed for receiver context EJBReceiverContext{clientContext=org.jboss.ejb.client.EJBClientContext@6c3a6465, receiver=Remoting connection EJB receiver [connection=Remoting connection <505d7a8a>,channel=jboss.ejb,nodename=banl13bca644a-5]} on channel Channel ID d523d1ff (outbound) of Remoting connection 2f478e10 to localhost/127.0.0.1:8080
Dec 10, 2015 9:37:01 PM org.jboss.ejb.client.EJBClient <clinit>
INFO: JBoss EJB Client version 2.1.2.Final


	 Got CallerRemote : Proxy for remote EJB StatelessEJBLocator for "EJBs_App/EJB-Two/CallerName", view is interface calculator.CallerRemote, affinity is None
ONE----result = DONE----result = 3000
