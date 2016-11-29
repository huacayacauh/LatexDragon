package libreDragon.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import libreDragon.latexParser.LatexConfiguration;
import libreDragon.model.BinaryExpression;
import libreDragon.model.Configuration;
import libreDragon.model.Expression;
import libreDragon.model.KrakenTree;
import libreDragon.model.PrimaryExpression;
import libreDragon.model.Rule;
import libreDragon.model.UnaryExpression;

public class Session {
	private static LatexConfiguration config = new LatexConfiguration();
	private  KrakenTree tree;
	private  HashMap<String,Expression> ids = new HashMap<>();
	private  HashMap<String,ArrayList<String>> rules = new HashMap<>();
	UUID gameId;
	
	public Session(){
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
		gameId = UUID.randomUUID();
	}
	public Session(String id){
		tree = new KrakenTree();
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		tree.setRoot(egal_ABC_D);
		gameId = UUID.randomUUID();
	}
	public void applicRule(String exprid,int idrule, String contexe){
		Expression expression = ids.get("\""+exprid+"\"");
		Rule rule = Configuration.rules.getRules().get(contexe).get(idrule);
		tree.applicRule(expression, rule);
	}
	
	
	public void setTree(KrakenTree t) {
		tree = t;
	}

	public void addexpr(String id,Expression expression){
		ids.put(id,expression);
	}
	
	public String getexpr(){
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
	
	public void addrules(String exp,ArrayList<String> rule){
		rules.put(exp, rule);
	}
	
	public ArrayList<String> addrules(Expression expression){
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
	
	public String getrules(){
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
	
	public void cleanexpr(){
		ids.clear();
	}

	public KrakenTree getTree() {
		return tree;
	}

}
