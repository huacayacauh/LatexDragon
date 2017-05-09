package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.lang.NumberFormatException;

/**
 * Class use to create or restore a session with a client
 * @author malo
 *
 */
@Path("/start")
public class Start {
	/**
	 * If client is not in the data server: create a new session
	 * and return his id.
	 * If the client id is in the data server just return a success answer
	 * @param gameid client id
	 * @return the connection status and if it's a success the client id
	 */
	@GET
	@Path("/{mode}/{gameid}/{formulaid}/{reglecustom}/")
	@Produces()
	public String connection (@PathParam("mode") String mode, @PathParam("gameid") String gameid, @PathParam("formulaid") String formulaId, @PathParam("reglecustom") Boolean regleCustom) {
		Reponse reponse = new Reponse();
		String status,complementaryInfo;
		if(!Data.isIn(gameid))
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
		if (gameid == null) {
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
