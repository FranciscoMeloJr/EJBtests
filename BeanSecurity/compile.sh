echo "Export"
export CLASSPATH=/home/fdemeloj/jboss-eap-7.2/modules/system/layers/base/javax/annotation/api/main/jboss-annotations-api_1.3_spec-1.0.1.Final-redhat-1.jar:/home/fdemeloj/jboss-eap-7.2/modules/system/layers/base/javax/ejb/api/main/jboss-ejb-api_3.2_spec-1.0.1.Final-redhat-1.jar:/home/fdemeloj/jboss-eap-7.3/bin/client/jboss-client.jar:/home/fdemeloj/Downloads/jboss-annotations-ejb3-4.2.2.GA.jar:.:

echo "Compile interface and remote:"
javac SecureSum.java
javac SecureSumRemote.java

echo "create the jar file:"
jar -cvf EjbSecure.jar SecureSum.class SecureSumRemote.class resources/

echo "Compile client:"
javac StandaloneSecure.java 

echo "Run client:"
java StandaloneSecure
