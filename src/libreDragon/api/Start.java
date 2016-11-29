package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/start")
public class Start {
	@GET
	@Path("/{gameid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameid) {
		Reponse reponse = new Reponse();
		String status,complementaryInfo;
		if(!Data.isIn(gameid))
			gameid = Data.addSession();
		if (gameid == null) {
			status = "FAILURE";
			complementaryInfo = "Couldn't create a new session, server might be full.";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "New session created with id : " + gameid + ".";
		}
		return reponse.authentification(gameid, status, complementaryInfo);
		
		}
}
