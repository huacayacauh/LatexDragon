package libreDragon.api;

import java.util.ArrayList;
import java.util.Iterator;

import latexConfigurationParser.LatexConfiguration;
import libreDragon.model.BinaryExpression;
import libreDragon.model.KrakenTree;
import libreDragon.model.PrimaryExpression;
import libreDragon.model.UnaryExpression;

public class Data {
	private static LatexConfiguration config = new LatexConfiguration();
	private static KrakenTree tree;
	private static ArrayList<String> ids = new ArrayList<>();
	private static ArrayList<String> rules = new ArrayList<>();
	
	public static void addexpr(String id){
		ids.add(id);
	}
	
	public static String getexpr(){
		String temp ="[";
		Iterator<String> iterator = ids.iterator();
		if(iterator.hasNext()){
			temp = temp+iterator.next();
			while (iterator.hasNext()) {
				temp = temp+","+iterator.next();
			}
		}
		return temp+"]";
	}
	
	public static void addrules(String rule){
		rules.add(rule);
	}
	
	public static String getrules(){
		String temp ="[";
		Iterator<String> iterator = rules.iterator();
		if(iterator.hasNext()){
			temp = temp+iterator.next();
			while (iterator.hasNext()) {
				temp = temp+","+iterator.next();
			}
		}
		return temp+"]";
	}

	public static LatexConfiguration getConfig() {
		return config;
	}

	public static KrakenTree getTree() {
		return tree;
	}
	
	public static void setDefault(){
		tree = new KrakenTree(config);
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		//PrimaryExpression expr_B = new PrimaryExpression("LITTERAL", "b");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		//BinaryExpression expr_A_plus_B = new BinaryExpression("PLUS", expr_A, expr_B);
		
		//Expression parenthese_A = new UnaryExpression("PARENTHESIS", expr_A.cloneExpression());
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		tree.setRoot(egal_ABC_D );
	}

}
