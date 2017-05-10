package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * l'application d'une règle sur une expression de la formule
 * @author malo
 *
 */
@Path("/rule")
public class ApplyRule {
	/**
	 * Le client va demander l'application de la règle ayant l'id ruleId
	 * sur l'espression ayant l'id expId dans le contexte context sur la
	 * session de jeu ayant l'id gameId.
	 * Les ids des règles et des expressions dépendent des hashmap de la
	 * classe KrankenTree.
	 * @param gameId session id
	 * @param expId expression id
	 * @param ruleId rule id
	 * @param context contexte d'application de la règle le plus souvent drag_and_drop ou contextMenu.
	 * @return
	 */
	@GET
	@Path("/{gameid}/{exprid}/{ruleid}/{context}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId, @PathParam("exprid") String expId, @PathParam("ruleid") String ruleId, @PathParam("context") String context) {
		Reponse reponse = new Reponse();
		String complementaryInfo, status = "FAILURE";

		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(!Data.getSession(gameId).getTree().idIsIn(expId)){ // on test si l'expression existe
			complementaryInfo = "Id de l'expression introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(!Data.getSession(gameId).getTree().ruleIsIn(expId,Integer.valueOf(ruleId),context)){ //on test si la règle est applicable pour l'expression
			complementaryInfo = "Id de la règle introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}

		System.out.println("Game "+gameId);
		Data.getSession(gameId).applicRule(expId,Integer.valueOf(ruleId),context); // on applique la règle
		return reponse.formula(gameId,"",-1); // on renvoie la nouvelle formule
		}
}
