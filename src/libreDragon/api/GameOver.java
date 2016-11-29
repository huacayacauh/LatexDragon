package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/gameover")
public class GameOver {
	@GET
	@Path("/{gameid}")
	@Produces()
	public String closeGame(@PathParam("gameid") String gameId){
		Data.closeSession(gameId);
		return "";
	}

}
