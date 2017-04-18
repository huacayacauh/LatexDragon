package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	@Path("/{mode}/{gameid}/{formulaid}/{reglecustom}")
	@Produces()
	public String connection (@PathParam("mode") String mode, @PathParam("gameid") String gameid, @PathParam("formulaid") String formulaId, @PathParam("reglecustom") Boolean regleCustom) {
		Reponse reponse = new Reponse();
		String status,complementaryInfo;
		if(!Data.isIn(gameid))
			gameid = Data.addSession(regleCustom);
		if (gameid == null) {
			status = "FAILURE";
			complementaryInfo = "Couldn't create a new session, server might be full.";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "New session created with id : " + gameid + ".";
		}
		System.out.println("Start "+gameid);
		return reponse.info(gameid, status, complementaryInfo);

		}
}
