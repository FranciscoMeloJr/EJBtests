all:
	@echo "Generating Shared Library"
	cc -I. -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -fPIC -shared -o libHelloWorld.so HelloWorld.c HelloWorld.h

clean:
	@echo "Cleaning the generated SH files"
	rm *.so