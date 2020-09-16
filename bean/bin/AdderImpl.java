import javax.ejb.Stateless;

@Stateless
public class AdderImpl implements AdderImplRemote{
  public int add(int a, int b)
  { 
    return a+b;
  }
}
