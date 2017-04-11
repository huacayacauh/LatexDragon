package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/endtheorem")
public class EndTheorem {
  @GET
	@Path("/{gameid}/{save}")
	@Produces()
	public String answer (@PathParam("gameid") String gameId) {
		Reponse reponse = new Reponse();
		System.out.println("End Theorem "+gameId);
    //if(save)
		  Data.getSession(gameId).endTheorem();
		return reponse.formula(gameId);
		}
}
