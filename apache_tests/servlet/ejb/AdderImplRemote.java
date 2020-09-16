import javax.ejb.Remote;

@Remote
public interface AdderImplRemote{
  public int add(int a, int b);
}
