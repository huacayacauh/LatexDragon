package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Cette classe implémente la requête permettant au client de demander
 * la liste de toutes les règles de la session de jeu
 * @author malo
 *
 */

@Path("/ruleslist")
public class RulesList {
	/**
	 * Cette fonction appele la classe Reponse pour générer un objet JSON
	 * contenant toutes les règles de la Session de jeu.
	 * @return
	 */
	@Path("{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		return reponse.rulesList(gameId);
	}
}
