package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * si le serveur a en mémoire une session de jeu ayant gameId en tant qu'id.
 * @author malo
 *
 */
@Path("/resume")
public class Resume {
	/**
	 * Cette fonction vérifie que la session de jeu correspondant a l'id
	 * gameId existe.
	 * @param gameId id de la session
	 * @return
	 */
	@GET
	@Path("/{gameid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
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
