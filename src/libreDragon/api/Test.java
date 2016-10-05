package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/test")
public class Test {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		if(Data.getTree() == null)
			Data.setDefault();
		return "$"+Data.getTree().getRoot().generateExpression("")+"$";
		//return "$\\cssId{exp}{a+b}$";
	}
	
	public String getFormule () {
		return "";
	}
	
}
