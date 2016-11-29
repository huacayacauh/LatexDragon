package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rule")
public class Comunication {
	@GET
	@Path("/{gameid}/{exprid}/{ruleid}/{contexid}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId, @PathParam("exprid") String expId, @PathParam("ruleid") String ruleId, @PathParam("contexid") String context) {
		Reponse myjaxbean = new Reponse();
		System.out.println("Game "+gameId);
		Data.getSession(gameId).applicRule(expId,Integer.valueOf(ruleId),context);
		return myjaxbean.getMyJaxBean(gameId);
		}
}
