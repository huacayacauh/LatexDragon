package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import latexConfigurationParser.LatexConfiguration;
import model.BinaryExpression;
import model.KrakenTree;
import model.PrimaryExpression;
import model.UnaryExpression;

public class Data {
	private static LatexConfiguration config = new LatexConfiguration();
	private static KrakenTree tree;

	public static LatexConfiguration getConfig() {
		return config;
	}

	public static KrakenTree getTree() {
		return tree;
	}
	
	public static void setDefault(){
		tree = new KrakenTree(config);
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		
		//Expression parenthese_A = new UnaryExpression("PARENTHESIS", expr_A.cloneExpression());
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		tree.setRoot(egal_ABC_D);
	}

}
