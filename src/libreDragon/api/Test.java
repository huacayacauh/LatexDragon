package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/test")
public class Test {
	//@Path("/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		Reponse myjaxbean = new Reponse();
		if(Data.getSession("1") == null)
			Data.addSession("1");
		return myjaxbean.getMyJaxBean("1");
		}
}
