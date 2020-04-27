//CalculatorRemote.java

package calculator;

import javax.ejb.*;

@Remote

public interface CallerRemote

{
     public String testMethod();
   	 public String callEJBOne(int a, int b);
}
 

