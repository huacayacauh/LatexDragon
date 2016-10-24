package libreDragon.api;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Configuration;
import model.Rule;


@Path("/test")
public class Test {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		Iterator<Rule> iterator;
		if(Data.getTree() == null)
			Data.setDefault();
		for (String mapKey : Configuration.rules.getRules().keySet()) {
			iterator =  Configuration.rules.getRules().get(mapKey).iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		}
		
		return getJSon();
			}
	
	public String getJSon () {
		return 	"{"
				+ "\"math\": \"$$"+Data.getTree().getRoot().generateExpression("0")+"$$\","
				+ "\"ids\":["+Data.getexpr()+"],"
				+ "\"list\":[]"
				+"}";
	}
	
}
