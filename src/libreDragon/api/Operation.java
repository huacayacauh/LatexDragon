package libreDragon.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/operation")
public class Operation {
	@POST @Consumes("application/json")
	public void create(final Reponse input) {
	    System.out.println("param1 = " + input.getMath());
	    System.out.println("param2 = " + input.getIds());
	}
}


