import javax.ejb.Stateless;
import org.jboss.logging.Logger;

@Stateless
public class Sum implements SumRemote 
{
	private static final Logger LOGGER = Logger.getLogger(Sum.class);
	public int add(int a, int b){
		return a+b;
	}
	public void print(){
		LOGGER.info("Print Test");
		System.out.print("Print");
	}

	public void multi_print(int n){
		for (int i = 0; i < n; i++) {
			LOGGER.info("Print Test ");
		}
	}
}