package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Class use by the client to request the game state
 * @author malo
 *
 */
@Path("/")
public class GameState {
	/**
	 * if the session exist return the formula state and the rule we can applique
	 * @param gameid
	 * @return
	 */
	@Path("/gamestate/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnstate (@PathParam("gameid") String gameid) {
		Reponse myjaxbean = new Reponse();
		System.out.println("Game "+gameid);
		if(Data.getSession(gameid) == null)
			Unauthorized();
		return myjaxbean.formula(gameid);
		}

	/**
	 * return error 401 if the session doesn't exist
	 * @return
	 */
	public String Unauthorized() {
		   // An unauthorized user tries to enter
		   throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	public class NotAuthorizedException extends WebApplicationException {
	     public NotAuthorizedException(String message) {
	         super(Response.status(Response.Status.UNAUTHORIZED)
	             .entity(message).type(MediaType.TEXT_PLAIN).build());
	     }
	}


	@Path("/previous/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPrevious (@PathParam("gameid") String gameid) {
		Reponse myjaxbean = new Reponse();
		System.out.println("Game "+gameid);
		if(Data.getSession(gameid) == null)
			Unauthorized();
		return myjaxbean.formula(gameid,"PREVIOUS");
	}

	@Path("/next/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getNext (@PathParam("gameid") String gameid) {
		Reponse myjaxbean = new Reponse();
		System.out.println("Game "+gameid);
		if(Data.getSession(gameid) == null)
			Unauthorized();
		return myjaxbean.formula(gameid,"NEXT");
	}
}
