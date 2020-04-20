//CalculatorBean.java
package calculator;
import javax.ejb.*;
//import org.jboss.ejb3.annotation.LocalBinding;
@Stateless(mappedName = "CalculatorMappedName")
//@LocalBinding(jndiBinding="CalculatorMappedName")

public class CalculatorBean implements CalculatorLocal
 {
   public int add(int x,int y)
    {
      System.out.println("\n\n\t Addition is : "+(x+y));
      return (x+y);
    }
   public int sub(int x,int y)
    {
      return(x-y);
    }
  public int mult(int x,int y)
    {
      return (x*y);
    }
  public int div(int x,int y)
    {
      return (x/y);
    }
 } 
