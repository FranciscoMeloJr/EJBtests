import java.security.MessageDigest;
import java.math.BigInteger;
import org.jboss.crypto.CryptoUtil;
 
public class EncryptPassword
{
    public static void main(String ar[]) throws Exception
     {
       /*
       You will need the following JAFRs in your classpath in order to compile & run this program 
       export CLASSPATH=$JBOSS_HOME/modules/org/picketbox/main/picketbox-4.0.7.Final.jar:$JBOSS_HOME/bin/client/jboss-client.jar:$CLASSPATH:.:
       */
 
       /*
           JBossAS7 encrypts passwords in the following format:
              HEX( MD5( username ':' realm ':' password))
       */
 
       String userName=ar[0];
       String realmName=ar[1];
       String password=ar[2];
 
       String clearTextPassword=userName+":"+realmName+":"+password; 
 
       String hashedPassword=CryptoUtil.createPasswordHash("MD5", "hex", null, null, clearTextPassword);
       System.out.println("clearTextPassword: "+clearTextPassword);
       System.out.println("hashedPassword: "+hashedPassword);
       System.out.println("If you will create user using add-user.sh script then you will see the same Hash Value of Password.nn");
     }
}