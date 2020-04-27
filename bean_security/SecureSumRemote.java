import javax.ejb.Remote;
//Someone based on @author <a href="mailto:sguilhen@redhat.com">Stefan Guilhen</a>

@Remote
public interface SecureSumRemote 
{
	int add(int a, int b);
	int minus(int a, int b);
}