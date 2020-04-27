//CalculatorBean.java
package calculator;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;


@Stateless(name = "CallerName", mappedName = "CallerMappedName")

public class CallerBean implements CallerRemote {
    
    // @EJB(mappedName = "CalculatorMappedName")
     
     //private CalculatorLocal cb;

     
    CalculatorLocal calculatorLocal;
    
    public String testMethod() {
		   System.out.println("\n\n\t Bean testMethod() called....");
		   return "DONE----returned";
	}

	public String callEJBOne(int a, int b) {
		   int result=0;
		   try{
		           String jndiName="java:app/EJB-One/CalculatorBean!calculator.CalculatorLocal";
		           calculatorLocal = (CalculatorLocal) InitialContext.doLookup(jndiName);
		           System.out.println("\n\n\t Bean callEJBOne(a,b) called....");                
                   result=calculatorLocal.add(a,b);
                   
                   System.out.println("\n\n\t [CallerBean] Got the Resule from CalculatorBean ---Sum = "+result);
		   }
		   catch(Exception e){ e.printStackTrace(); }
		   return "DONE----result = "+result;
	}
 }
