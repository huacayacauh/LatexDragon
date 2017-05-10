package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * la fermetur d'une session de jeu.
 * @author malo
 *
 */
@Path("/over")
public class GameOver {
	/**
	 * Cette fonction vérifie si la session de jeu correspondant a l'id
	 * gameId existe et si elle existe la ferme ce qui correpond a effacer
	 * toutes ses données.
	 * @param gameId id de la session
	 * @return
	 */
	@GET
	@Path("/{gameid}")
	@Produces()
	public String closeGame(@PathParam("gameid") String gameId){
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			Reponse reponse = new Reponse();
	    String complementaryInfo, status;
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		Data.closeSession(gameId); //on ferme la session de jeu
		return "{\"status\": \"SUCCESS\"}";
	}

}
