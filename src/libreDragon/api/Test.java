package libreDragon.api;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import libreDragon.model.Configuration;


@Path("/test")
public class Test {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		//Iterator<Rule> iterator;
		MyJaxBean myjaxbean = new MyJaxBean();
		if(Data.getTree() == null)
			Data.setDefault();
		System.out.println("config"+Data.getConfig().getConfiguration("ROOT").getOperators().toString());
		return myjaxbean.getMyJaxBean();
			}
	//public String get
	
}
