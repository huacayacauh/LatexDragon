package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/test")
public class GameState {
	@Path("/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle (@PathParam("gameid") String gameid) {
		Reponse myjaxbean = new Reponse();
		System.out.println("Game "+gameid);
		if(Data.getSession(gameid) == null)
			getItem();
		return myjaxbean.getMyJaxBean(gameid);
		}
	public String getItem() {
		   // An unauthorized user tries to enter
		   throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	public class NotAuthorizedException extends WebApplicationException {
	     public NotAuthorizedException(String message) {
	         super(Response.status(Response.Status.UNAUTHORIZED)
	             .entity(message).type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
