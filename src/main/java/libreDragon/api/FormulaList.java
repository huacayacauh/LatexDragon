package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author malo
 *
 */
@Path("/formula")
public class FormulaList {
	/**
	 */
	@GET
	@Produces()
	public String answer () {
    Reponse reponse = new Reponse();
		return reponse.expressionList();
	}
}
