//CalculatorRemote.java
package calculator;
import javax.ejb.*;
@Local
public interface CalculatorLocal
{
  public int add(int x,int y);
  public int sub(int x,int y);
  public int mult(int x,int y);
  public int div(int x,int y);
}    
 

