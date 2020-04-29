import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
 
import javax.ejb.Stateless;
import javax.ejb.EJB;  

@Singleton
 
@Remote
@Startup
//@EJB(name = "java:jboss/exported/SingletonBean", beanInterface = SingletonBean.class)  

public class SingletonBean implements SingletonX {
    int total;
 
    @PostConstruct
    public void startup() {
        System.out.println("Singleton inited!");
        total = 0;
    }
 
     
    public void add(int i) {        
        total += i;
    }
 
     
    public int getTotal() {        
        return total;
    }
}