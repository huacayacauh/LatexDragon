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
    String complementaryInfo, status;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(start.compareTo("null") == 0 || Integer.valueOf(start) >= Data.getSession(gameId).getTreesSize() || Integer.valueOf(start) < 0){
			status = "FAILURE";
			complementaryInfo = "Expression de début de théorème introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}
		else if(end.compareTo("null") == 0 || Integer.valueOf(end) < Integer.valueOf(start)){
			status = "FAILURE";
			complementaryInfo = "Expression de début de théorème introuvable !";
			return reponse.info(gameId, status, complementaryInfo);
		}

		System.out.println("New Theorem : " + gameId);
		Data.getSession(gameId).createTheorem(Integer.parseInt(start), Integer.parseInt(end));
		status = "SUCCESS";
		complementaryInfo = "New theorem created with id : " + gameId + ".";
		return reponse.info(gameId, status, complementaryInfo);
	}
}
