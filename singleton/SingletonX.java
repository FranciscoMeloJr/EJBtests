import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
  

public interface SingletonX {

    public void startup();
     
    public void add(int i);
 
    public int getTotal();

}