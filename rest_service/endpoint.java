import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Path("/testejb")
public class endpoint {

    private static Logger logger = Logger.getLogger(TestEJbEndpoint.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@QueryParam("callsize") Integer number) {

        if(cacheejb == null ){
            cacheejb = Boolean.TRUE;
        }

        HelloWorldRemote worldRemote = null;

        List<String> response = new ArrayList<>();
        response.add("Hello World");

        return Response.ok(response).build();

    }

}