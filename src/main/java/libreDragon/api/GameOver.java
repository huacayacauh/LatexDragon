package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Class use to close a client session
 * @author malo
 *
 */
@Path("/over")
public class GameOver {
	/**
	 * Close the session if the client id exist in the data server
	 * @param gameId 
	 * @return
	 */
	@GET
	@Path("/{gameid}")
	@Produces()
	public String closeGame(@PathParam("gameid") String gameId){
		Data.closeSession(gameId);
		return "";
	}

}
