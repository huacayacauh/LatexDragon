package libreDragon.api;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import libreDragon.model.Configuration;
import libreDragon.model.Rule;


@Path("/test")
public class Test {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		Iterator<Rule> iterator;
		if(Data.getTree() == null)
			Data.setDefault();
		System.out.println("config"+Data.getConfig().getConfiguration("ROOT").getOperators().toString());
		
		return getJSon();
			}
	
	public String getJSon () {
		Object temp1 =  Data.getTree().getRoot().generateExpression("0");
		String temp = Data.getexpr();
		Data.cleanexpr();
		return 	"{"
				+ "\"math\": \"$$"+temp1+"$$\","
				+ "\"ids\":["+temp+"],"
				+ "\"list\":[]"
				+"}";
	}
	//public String get
	
}
