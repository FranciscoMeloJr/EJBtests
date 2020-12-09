import java.util.concurrent.TimeUnit;
public class DefaultTimeZone {
    public static void main(String[] args) throws Exception{
        System.out.println(java.util.TimeZone.getDefault().getID());
        TimeUnit.MINUTES.sleep(1);
    }
}