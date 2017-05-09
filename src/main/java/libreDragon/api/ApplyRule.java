package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Class use by the client to applique a rule in his formula
 * @author malo
 *
 */
@Path("/rule")
public class ApplyRule {
	/**
	 * The client can request a rule application with this function
	 * @param gameId session id
	 * @param expId expression id
	 * @param ruleId rule id
	 * @param context if it's a drag and drop rule or a context menu rule
	 * @return
	 */
	@GET
	@Path("/{gameid}/{exprid}/{ruleid}/{contexid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId, @PathParam("exprid") String expId, @PathParam("ruleid") String ruleId, @PathParam("contexid") String context) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status, gameStatus;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		else if(!Data.getSession(gameId).getTree().idIsIn(expId)){
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Id de l'expression introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		else if(!Data.getSession(gameId).getTree().ruleIsIn(expId,Integer.valueOf(ruleId),context)){
			status = "FAILURE";
			gameStatus = "RUNNING";
			complementaryInfo = "Id de la règle introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}


		System.out.println("Game "+gameId);
		Data.getSession(gameId).applicRule(expId,Integer.valueOf(ruleId),context);
		return reponse.formula(gameId,"",-1);
		}
}
