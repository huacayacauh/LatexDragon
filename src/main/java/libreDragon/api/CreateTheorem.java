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
    String complementaryInfo, status, gameStatus;
		if (!Data.isIn(gameId)) {
			status = "FAILURE";
      gameStatus = "RUNNING";
			complementaryInfo = "Session introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		else if(start.compareTo("null") == 0 || Integer.valueOf(start) >= Data.getSession(gameId).getTreesSize() || Integer.valueOf(start) < 0){
			status = "FAILURE";
      gameStatus = "RUNNING";
			complementaryInfo = "Expression de début de théorème introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}
		else if(end.compareTo("null") == 0 || Integer.valueOf(end) < Integer.valueOf(start)){
			status = "FAILURE";
      gameStatus = "RUNNING";
			complementaryInfo = "Expression de début de théorème introuvable !";
			return reponse.info(gameId, status, gameStatus, complementaryInfo);
		}

		System.out.println("New Theorem : " + gameId);
		Data.getSession(gameId).createTheorem(Integer.parseInt(start), Integer.parseInt(end));
		status = "SUCCESS";
    gameStatus = "RUNNING";
		complementaryInfo = "New theorem created with id : " + gameId + ".";
		return reponse.info(gameId, status, gameStatus, complementaryInfo);
	}
}
