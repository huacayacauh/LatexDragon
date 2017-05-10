package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.lang.NumberFormatException;

/**
 * Cette classe implémente la requête permettant au client de demander
 * la création d'une nouvelle session de jeu.
 * @author malo
 *
 */
@Path("/start")
public class Start {

	@GET
	@Path("/{mode}/{gameid}/{formulaid}/{reglecustom}/")
	@Produces()
	/**
	 * Créé une nouvelle session si elle n'existe pas.
	 * @param mode id du mode de jeu
	 * @param gameid id de la session
	 * @param formulaid id de la formule jouable
	 * @param reglecustom booleen
	 * @return the connection status and if it's a success the client id
	 */
	public String connection (@PathParam("mode") String mode, @PathParam("gameid") String gameid, @PathParam("formulaid") String formulaId, @PathParam("reglecustom") Boolean regleCustom) {
		Reponse reponse = new Reponse();
		String status,complementaryInfo;
		if(!Data.isIn(gameid)){ // on test si la session de jeu existe
				try{
					if(formulaId != null && Integer.valueOf(formulaId) >= 0 && Integer.valueOf(formulaId) < Data.getNbExpressions())
						gameid = Data.addSession(regleCustom,Integer.valueOf(formulaId));
					else
						gameid = Data.addSession(regleCustom,0);
				}
				catch(NumberFormatException e){
					status = "FAILURE";
					complementaryInfo = "L'Id de la formule doit être un entier.";
				}
		}
		if (gameid == null) { // on test si la création a réussi.
			status = "FAILURE";
			complementaryInfo = "Couldn't create a new session, server might be full.";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "New session created with id : " + gameid + ".";
			System.out.println("Start "+gameid+" Expr : "+Data.getSession(gameid).getTree().getRoot().getExpr());
		}

		return reponse.info(gameid, status, complementaryInfo);

		}
}
