package sampleejbservice.ejb;

import javax.ejb.Remote;
import java.io.Serializable;


public interface HelloWorldRemote extends Serializable {

    public String sayHello(String username);
}
