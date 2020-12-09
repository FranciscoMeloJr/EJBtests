#!/bin/bash
if [ -z "$1" ]
then  
    echo "Running"
    export JAVAWS_VM_ARGS="-verbose -J-Xdebug -J-Xnoagent -J-Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y -Xnofork"
    echo $JAVAWS_VM_ARGS 

	javaws Test.jnlp $JAVAWS_VM_ARGS
else
    echo "Compiling"
    #export 
	export CLASSPATH=/home/fdemeloj/Downloads/cases/EJBtests/JNLP/jnlp.jar:.:

	#Debug:
	#export JAVAWS_VM_ARGS="-Xdebug -Xrunjdwp:transport=dt_socket,server=n,suspend=y,address=8200"
	#set JAVAWS_TRACE_NATIVE=1
	#set JAVAWS_VM_ARGS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8989,server=y,suspend=n"
	#Compile
	javac TestJnlp.java 

	#create jar
	jar -cfe TestJnlp.jar TestJnlp *.*

	#Sign the jar
	jarsigner -keystore ../eap7console.jks TestJnlp.jar jboss
fi

