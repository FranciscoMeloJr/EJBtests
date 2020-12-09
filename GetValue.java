public class GetValue {
    public static void main(String[] args) throws Exception{
        System.out.println("Returning value");
        //System.getProperty()
    	//System.getenv()
    	//java -Dfoo="bar" GetValue
    	System.out.println(System.getProperty("user.dir"));
    	System.out.println(System.getProperty("os.name"));
    	System.out.println(System.getProperty("foo"));
    	System.out.println(System.getenv("foo"));
    	System.out.println(System.getenv("JAVA_HOME"));

    	//Getting as String:
    	String sys_info = System.getProperty("foo");
    	System.out.println(sys_info);

    }
}