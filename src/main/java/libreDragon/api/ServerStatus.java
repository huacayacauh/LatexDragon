package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * de demander le statut du serveur.
 * @author malo
 *
 */
@Path("/status")
public class ServerStatus {
	/**
	 */
	@GET
	@Produces()
	public String answer () {
		return "online";
	}
}
