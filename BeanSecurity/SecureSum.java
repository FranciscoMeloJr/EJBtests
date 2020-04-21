import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;

import org.jboss.annotation.security.SecurityDomain;


@Stateless
@RolesAllowed({ "guest" }) //Added
@SecurityDomain("other") //Added
public class SecureSum implements SecureSumRemote 
{
	public int add(int a, int b){
		return a+b;
	}
	@RolesAllowed("admin")
	public int minus(int a, int b){ return a - b; }
}