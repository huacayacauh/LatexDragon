package libreDragon.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import libreDragon.latexParser.LatexConfiguration;
import libreDragon.latexParser.ParseException;
import libreDragon.model.BinaryExpression;
import libreDragon.model.Configuration;
import libreDragon.model.Expression;
import libreDragon.model.KrakenTree;
import libreDragon.model.PrimaryExpression;
import libreDragon.model.Rule;
import libreDragon.model.RulesConfiguration;
import libreDragon.model.UnaryExpression;
import libreDragon.ruleParser.RuleParser;

public class Data {
	private static LatexConfiguration config = new LatexConfiguration();
	private static KrakenTree tree;
	private static HashMap<String,Expression> ids = new HashMap<>();
	private static HashMap<String,ArrayList<String>> rules = new HashMap<>();
	
	public static void applicRule(String exprid,int idrule, String contexe){
		Expression expression = ids.get("\""+exprid+"\"");
		Rule rule = Configuration.rules.getRules().get(contexe).get(idrule);
		tree.applicRule(expression, rule);
	}
	
	
	public static void setTree(Expression tree) {
		Data.tree.setRoot(tree);
	}

	public static void addexpr(String id,Expression expression){
		ids.put(id,expression);
	}
	
	public static String getexpr(){
		String temp ="[";
		Set<String> listKeys=ids.keySet();
		Iterator<String> iterator=listKeys.iterator();
		if(iterator.hasNext()){
			temp = temp+iterator.next();
			while (iterator.hasNext()) {
				temp = temp+","+iterator.next();
			}
		}
		return temp+"]";
	}
	
	public static void addrules(String exp,ArrayList<String> rule){
		rules.put(exp, rule);
	}
	
	public static ArrayList<String> addrules(Expression expression){
		List<Rule> liste;
		ArrayList<String> res = new ArrayList<>();
		Map<String, List<Rule>> rules = Configuration.rules.getRules();
		Set<String> listKeys=rules.keySet();  // Obtenir la liste des clés
		Iterator<String> iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			liste = rules.get(key);
			for(int i = 0; i < liste.size(); i++){
				if (liste.get(i).canApplic(expression)){
					//System.out.println(expression.expressionToString());
					res.add("{\"text\": "+"\""+expression.getExpr() + " => "+liste.get(i).applic(expression).getExpr()+"\","+"\"ruleId\":"+i+",\"type\":"+"\""+key+"\"}");
				}
			}
		}
		return res;
	}
	
	public static String getrules(){
		String temp ="";
		Set<String> listKeys=rules.keySet();  // Obtenir la liste des clés
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			String key= iterateur.next();
			temp += "{"+key +":[";
			ArrayList<String> liste = rules.get(key);
			if(liste.size() > 0)
				temp += liste.get(0);;
			for(int i = 1; i < liste.size(); i++){
				temp += ","+liste.get(i);
			}
			if(iterateur.hasNext())
				temp+="]},";
			else
				temp+="]}";
		}
		return temp+"]";
	}

	public static LatexConfiguration getConfig() {
		return config;
	}
	
	public static void cleanexpr(){
		ids.clear();
	}

	public static KrakenTree getTree() {
		return tree;
	}
	
	public static void setDefault() {
		tree = new KrakenTree(config);
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		tree.setRoot(egal_ABC_D);
		readRules(tree);
	}
	public static void readRules(KrakenTree tree) {
	     String configPath = "config";
			Configuration.rules = new RulesConfiguration();
			try {
				RuleParser.readRules(new FileInputStream(new File(configPath + "/rules.cfg")));
				//tree.setRoot(RuleParser.readExpression(new FileInputStream(new File(configPath + "/formula.cfg"))));
			} catch (FileNotFoundException | libreDragon.ruleParser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
}
}
