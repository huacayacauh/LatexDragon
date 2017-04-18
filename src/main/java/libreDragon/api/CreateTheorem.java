package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/createtheorem")
public class CreateTheorem {
  @GET
	@Path("/{gameid}/{start}/{end}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId, @PathParam("start") String start, @PathParam("end") String end) {
		Reponse reponse = new Reponse();

		System.out.println("New Theorem : " + gameId);

		Data.getSession(gameId).createTheorem(Integer.parseInt(start), Integer.parseInt(end));

    String status, complementaryInfo;

		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			complementaryInfo = "Session doesn't exist !";
		}
		else {
			status = "SUCCESS";
			complementaryInfo = "New theorem created with id : " + gameId + ".";
		}

		return reponse.info(gameId, status, complementaryInfo);
	}
}
