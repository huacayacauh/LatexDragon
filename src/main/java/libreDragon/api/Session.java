package libreDragon.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import libreDragon.latexParser.LatexConfiguration;
import libreDragon.latexParser.ReverseParser;
import libreDragon.model.BinaryExpression;
import libreDragon.model.Configuration;
import libreDragon.model.Expression;
import libreDragon.model.KrakenTree;
import libreDragon.model.PrimaryExpression;
import libreDragon.model.Rule;
import libreDragon.model.UnaryExpression;
import libreDragon.model.RulesConfiguration;
import libreDragon.ruleParser.RuleParser;

/**
 * The class session store every information associate to a client and his formula
 * @author malo
 *
 */
public class Session {
	private ArrayList<KrakenTree> trees = new ArrayList();
	private int currentTree = 0;
	private RulesConfiguration globalRules;
	UUID gameId;

	public KrakenTree getNext(){
		if(trees.size() <= currentTree +1)
			currentTree = trees.size()-1;
		else
			currentTree++;
		return trees.get(currentTree);
	}

	public KrakenTree getPrevious(){
		if(currentTree > 0)
			currentTree--;
		return trees.get(currentTree);
	}

	public String getGlobalRules(){
		String temp ="";
		Set<String> listKeys=globalRules.getRules().keySet();
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			String key= iterateur.next();
			temp += "{\""+key +"\":[";
			List<Rule> liste = globalRules.getRules().get(key);
			if(liste.size() > 0)
				temp += ("\"" + liste.get(0).toString() + "\"");
			for(int i = 1; i < liste.size(); i++){
				temp += ",\"" + liste.get(i).toString() + "\"";
			}
			if(iterateur.hasNext())
				temp+="]},";
			else
				temp+="]}";
		}
		return temp+"]";
	}

	/**
	 *
	 */
	public Session(Boolean customRules){
		trees.add(new KrakenTree());
		trees.get(currentTree).setRoot(defaultFormula());
		gameId = UUID.randomUUID();
		globalRules = new RulesConfiguration();
		readRules("/rules.cfg");
		if(customRules)
			readRules("/customRules.cfg");
	}

	public void addRuleSession(String input_type, Expression input_model, Expression output_model){
		globalRules.addRule(input_type,new Rule(input_model, output_model));
	}

	public void createTheorem (int start, int end) {
		if ((start < trees.size()) && (end < trees.size())) {
			addRuleSession("contextMenu", trees.get(start).getRoot(), trees.get(end).getRoot());
			RuleParser.writeRule(new Rule(trees.get(start).getRoot(), trees.get(end).getRoot()));
		}
	}

	/**
	 * @param exprid
	 * @param idrule
	 * @param contexe
	 */
	public void applicRule(String exprid,int idrule, String contexe){
		getTree().cleanRules();
		cleanexpr();
		menage();
		trees.add(getTree().cloneKrakenTree());
		currentTree++;
		getTree().getRoot().generateExpression("0",this);
		Expression expression = getTree().getIds("\""+exprid+"\"");
		Rule rule = globalRules.getRules().get(contexe).get(idrule);
		getTree().applicRule(expression, rule);
	}

	public void menage(){
		for(int i = currentTree + 1; i < trees.size(); i++)
			trees.remove(i);
	}

	/**
	 * A default formula
	 * @return
	 */
	private Expression defaultFormula(){
		PrimaryExpression expr_A = new PrimaryExpression("LITTERAL", "a");
		PrimaryExpression expr_B = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_C = new PrimaryExpression("NOMBRE", "2");
		PrimaryExpression expr_D = new PrimaryExpression("NOMBRE", "3");
		BinaryExpression divide_A_B = new BinaryExpression("DIVIDE", expr_A.cloneExpression(), expr_B.cloneExpression());
		UnaryExpression parenthese_A_plus_B = new UnaryExpression("PARENTHESIS", divide_A_B.cloneExpression());
		BinaryExpression fois_AB_C = new BinaryExpression("FOIS", parenthese_A_plus_B.cloneExpression(), expr_C.cloneExpression());
		BinaryExpression egal_ABC_D = new BinaryExpression("EGAL", fois_AB_C.cloneExpression(), expr_D.cloneExpression());
		return egal_ABC_D;
	}


	/**
	 * @param t
	 */
	public void setTree(KrakenTree t) {
		trees.set(currentTree, t);
	}

	/**
	 * @param id
	 * @param expression
	 */
	public void addexpr(String id,Expression expression){
		getTree().addIds(id, expression);
	}

	/**
	 *  return the list of expressions in a string
	 * @return
	 */
	public String getexpr(){
		String temp ="[";
		Set<String> listKeys=getTree().getKeyIds();
		Iterator<String> iterator=listKeys.iterator();
		if(iterator.hasNext()){
			temp = temp+iterator.next();
			while (iterator.hasNext()) {
				temp = temp+","+iterator.next();
			}
		}
		return temp+"]";
	}

	/**
	 * @param exp
	 * @param rule
	 */
	public void addrules(String exp,ArrayList<String> rule){
		getTree().addRules(exp, rule);
	}

	/**
	 * Generate all the rule can applique in a session
	 * @param expression
	 * @return
	 */
	public ArrayList<String> addrules(Expression expression){
		List<Rule> liste;
		ArrayList<String> res = new ArrayList<>();
		Set<String> listKeys= globalRules.getRules().keySet();
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			liste = globalRules.getRules().get(key);
			for(int i = 0; i < liste.size(); i++){
				if (liste.get(i).canApplic(expression)){
					res.add("{\"text\": "+"\""+expression.getExpr() + " => "+liste.get(i).applic(expression).getExpr()+"\","+"\"ruleId\":"+i+",\"type\":"+"\""+key+"\"}");
				}
			}
		}
		return res;
	}

	/**
	 * return the list of rules in a string
	 * @return
	 */
	public String getrules(){
		String temp ="";
		Set<String> listKeys= getTree().getKeyRules();
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			String key= iterateur.next();
			temp += "{"+key +":[";
			ArrayList<String> liste = getTree().getRules(key);
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

	/**
	 * Clear the expressions hashmap
	 */
	public void cleanexpr(){
		getTree().cleanIds();
	}

	/**
	 * return the expression representation
	 * @return
	 */
	public KrakenTree getTree() {
		return trees.get(currentTree);
	}

	private void readRules(String file) {
	    String configPath = "config";
		try {
			RuleParser.readRules(new FileInputStream(new File(configPath + file)), globalRules);
		} catch (FileNotFoundException | libreDragon.ruleParser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTimeline () {
		String timeline = "";
		for (int i = 0 ; i < trees.size() ; i++) {
			String tmp = (String) trees.get(i).getRoot().generateSimpleExpression();
			timeline += "{\"index\":" + i + ",";
			timeline += "\"text\":\"$$" + tmp +"$$\"}";
			if (i != trees.size() - 1)
				timeline += ",";
		}
		timeline += "]}";

		return timeline;
	}

	public int getCurrentTree () {
		return currentTree;
	}

	public KrakenTree getStateFromTimeline (int index) {
		if ((index < 0) || (index >= trees.size())) {
			System.out.println("null:" + index);
			return null;
		}

		currentTree = index;
		return trees.get(currentTree);
	}

}
