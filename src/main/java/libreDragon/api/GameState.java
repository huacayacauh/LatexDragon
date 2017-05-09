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
 * Class use by the client to request the game state
 * @author malo
 *
 */
@Path("/")
public class GameState {
	/**
	 * if the session exist return the formula state and the rule we can applique
	 * @param gameid
	 * @return
	 */
	@Path("/gamestate/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnstate (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status, gameStatus;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"",-1);
		}

	@Path("/previous/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getPrevious (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status, gameStatus = "RUNNING";
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"PREVIOUS",-1);
	}

	@Path("/next/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getNext (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status, gameStatus;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId,"NEXT",-1);
	}

	@Path("/timeline/{gameid}/{index}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStateFromTimeline (@PathParam("gameid") String gameId, @PathParam("index") String index) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status, gameStatus;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		else if(Integer.parseInt(index) < 0 || Integer.parseInt(index) > Data.getSession(gameId).getTreesSize()){
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Timeline introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		System.out.println("Game "+gameId);
		return reponse.formula(gameId, "", Integer.parseInt(index));
	}

	@Path("/delete/{gameid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String deleteGame (@PathParam("gameid") String gameId) {
		if (!Data.isIn(gameId)) {
			Reponse reponse = new Reponse();
	    String complementaryInfo, status, gameStatus;
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		Data.closeSession(gameId);
		System.out.println("Delete " + gameId);
		return "cool";
	}
}
