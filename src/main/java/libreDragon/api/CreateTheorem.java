package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * la création d'une règle à partir de l'historique des transformations
 * de la formule de la session de jeu.
 * @author malo
 *
 */
@Path("/createtheorem")
public class CreateTheorem {
  /**
   * Le client va demander la création d'une règle de réécriture ayant
   * comme model d'entrée la formule correspondant a l'indice start dans
   * l'historique et comme model de sortie la formule correspondant a l'indice end dans
   * l'historique.
   * @param gameId session id
   * @param start indice de début
   * @param end indice de fin
   * @return
   */
  @GET
	@Path("/{gameid}/{start}/{end}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId, @PathParam("start") String start, @PathParam("end") String end) {
		Reponse reponse = new Reponse();
    String complementaryInfo, status = "FAILURE";
		if (!Data.isIn(gameId)) { // on test si la session de jeu existe
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(start.compareTo("null") == 0 || Integer.valueOf(start) >= Data.getSession(gameId).getTreesSize() || Integer.valueOf(start) < 0){ // on test si lindice de début est plausible
			complementaryInfo = "Expression de debut de theoreme introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(end.compareTo("null") == 0 || Integer.valueOf(end) < Integer.valueOf(start)){ // on test si lindice de fin est plausible
			complementaryInfo = "Expression de fin de theoreme introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}

		System.out.println("New Theorem : " + gameId);
		Data.getSession(gameId).createTheorem(Integer.parseInt(start), Integer.parseInt(end)); // on créer la règle
		status = "SUCCESS";
		complementaryInfo = "New theorem created with id : " + gameId + ".";
		return reponse.info(gameId, status, complementaryInfo); // on informe le client du sucés de l'opperation
	}
}
