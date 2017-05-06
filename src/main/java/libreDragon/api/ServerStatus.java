package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author malo
 *
 */
@Path("/status")
public class ServerStatus {
	/**
	 */
	@GET
	@Produces()
	public String answer () {
		return "online";
	}
}
