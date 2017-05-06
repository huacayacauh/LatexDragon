package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author malo
 *
 */
@Path("/resume")
public class Resume {
	/**
	 */
	@GET
	@Path("/{gameid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			complementaryInfo = "Session non trouvée, impossible de reprendre la partie !";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "Session trouvée, possibilité de reprendre la partie : " + gameId + ".";
		}
		return reponse.info(gameId, status, complementaryInfo);
	}
}
