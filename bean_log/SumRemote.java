import javax.ejb.Remote;
@Remote
public interface SumRemote 
{
	int add(int a, int b);
	void print();
	void multi_print(int n);
}