package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ruleslist")
public class RulesList {

	@Path("{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();

		return reponse.rulesList(gameId);
	}
}
