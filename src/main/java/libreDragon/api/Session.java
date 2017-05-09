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
	UUID gameId;

	public ArrayList<KrakenTree> getTrees(){
		return trees;
	}

	public int getTreesSize(){
		return trees.size();
	}

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

	public int getCurrentTree () {
		return currentTree;
	}

	/**
	 * @param t
	 */
	public void setTree(KrakenTree t) {
		trees.set(currentTree, t);
	}

	/**
	 *
	 */
	public Session(Boolean custom, int indice){
		gameId = UUID.randomUUID();
		RulesConfiguration globalRules = new RulesConfiguration();
		readRules(globalRules,"/rules.cfg");
		if(custom)
			readRules(globalRules,"/customRules.cfg");
		trees.add(new KrakenTree(globalRules));
		trees.get(currentTree).setRoot(Data.getExpression(indice));
		trees.get(currentTree).setVictory(Data.getExpressionVictory(indice));
	}

	public void createTheorem (int start, int end) {
		if ((start < trees.size()) && (end < trees.size())) {
			getTree().addRule("Custom", trees.get(start).getRoot(), trees.get(end).getRoot());
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
		getTree().cleanIds();
		menage();
		trees.add(getTree().cloneKrakenTree());
		currentTree++;
		getTree().getRoot().generateExpression("0");
		getTree().getRoot().generateRulesAndIdExpression("0",getTree());
		Expression expression = getTree().getIds(exprid);
		Rule rule = getTree().getRuleByidsAndContext(exprid,idrule,contexe);
		getTree().applicRule(expression, rule);
	}

	public void menage(){
		for(int i = trees.size() - 1 ; i > currentTree ; i--)
			trees.remove(i);
	}


	/**
	 * return the expression representation
	 * @return
	 */
	public KrakenTree getTree() {
		return trees.get(currentTree);
	}

	private void readRules(RulesConfiguration config, String file) {
	    String configPath = "config";
		try {
			RuleParser.readRules(new FileInputStream(new File(configPath + file)), config);
		} catch (FileNotFoundException | libreDragon.ruleParser.ParseException e) {
			e.printStackTrace();
		}
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
