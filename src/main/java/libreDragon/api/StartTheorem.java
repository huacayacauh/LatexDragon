package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/starttheorem")
public class StartTheorem {
  @GET
	@Path("/{gameid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		System.out.println("New Theorem "+gameId);
		Data.getSession(gameId).startTheorem();
    String status,complementaryInfo;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			complementaryInfo = "Session doesn't exist !";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "New theorem created with id : " + gameId + ".";
		}
		return reponse.info(gameId, status, complementaryInfo);
		}
}
