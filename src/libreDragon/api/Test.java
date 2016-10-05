package libreDragon.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import latexConfigurationParser.LatexConfiguration;
import model.BinaryExpression;
import model.Configuration;
import model.Expression;
import model.PrimaryExpression;
import model.Rule;
import model.RulesConfiguration;
import model.UnaryExpression;
import model.KrakenTree;

@Path("/test")
public class Test {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle () {
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		
		//Expression parenthese_A = new UnaryExpression("PARENTHESIS", expr_A.cloneExpression());
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		
		LatexConfiguration config = new LatexConfiguration();
		KrakenTree tree = new KrakenTree(config);

		tree.setRoot(egal_ABC_D);
			return ""+tree.getRoot().generateExpression("546984");
	}
	
	public String getFormule () {
		return "";
	}
	
}
