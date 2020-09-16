import javax.ejb.Stateless;

@Stateless
public class Sum implements SumRemote 
{
	public int add(int a, int b){
		return a+b;
	}
}