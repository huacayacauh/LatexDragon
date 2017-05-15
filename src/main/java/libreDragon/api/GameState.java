package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.Throwable;


/**
 * Cette classe implémente la requête permettant au client de demander
 * l'état du jeu.
 * @author malo
 *
 */
@Path("/")
public class GameState {

	@Path("/gamestate/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Retourne la formule courante de la session de jeu ayant comme id gameid si elle existe.
	 * @param gameid
	 * @return
	 */
	public String returnstate (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"",-1);
		}

		/**
		 * Retourne la formule précédente de la session de jeu ayant comme id gameid si elle existe.
		 * @param gameid
		 * @return
		 */
	@Path("/previous/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPrevious (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"PREVIOUS",-1);
	}

	/**
	 * Retourne la formule suivante de la session de jeu ayant comme id gameid si elle existe.
	 * @param gameid
	 * @return
	 */
	@Path("/next/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getNext (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"NEXT",-1);
	}

	/**
	 * Retourne la formule ayant comme indice index dans la session de jeu ayant comme id gameid si elle existe.
	 * @param gameid
	 * @return
	 */
	@Path("/timeline/{gameid}/{index}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStateFromTimeline (@PathParam("gameid") String gameId, @PathParam("index") String index) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(Integer.parseInt(index) < 0 || Integer.parseInt(index) > Data.getSession(gameId).getTreesSize()){
			status = "FAILURE";
			complementaryInfo = "Timeline introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId, "", Integer.parseInt(index));
	}

	/**
	 * Cette fonction vérifie si la session de jeu correspondant a l'id
	 * gameId existe et si elle existe la ferme ce qui correpond a effacer
	 * toutes ses données.
	 * @param gameId id de la session
	 * @return
	 */
	@Path("/delete/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String deleteGame (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status;
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		Data.closeSession(gameId);
		System.out.println("Delete " + gameId);
		status = "SUCCESS";
		complementaryInfo = "Session ferme !";
		return reponse.info(gameId, status, complementaryInfo);
	}
}
