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
 * Cette classe regroupe toutes les informations associé à une sessions de jeu.
 * @author malo
 *
 */
public class Session {

	/**
	 * Historique des transformation de la formule.
	 */
	private ArrayList<KrakenTree> trees = new ArrayList();

	/**
	 * Indice de la formule courante dans l'historique (par défaut le dernier élement de la liste)
	 */
	private int currentTree = 0;

	/**
	 * Id de la session de jeu.
	 */
	UUID gameId;

	/**
	 * @return L'historique des transformations de la formule.
	 */
	public ArrayList<KrakenTree> getTrees(){
		return trees;
	}

	/**
	 * @return La taille de l'historique.
	 */
	public int getTreesSize(){
		return trees.size();
	}

	/**
	 * @return la formule courante.
	 */
	public KrakenTree getTree() {
		return trees.get(currentTree);
	}

	/**
	 * @return la formule suivante
	 * dans l'historique si elle existe.
	 */
	public KrakenTree getNext(){
		if(trees.size() <= currentTree +1)
			currentTree = trees.size()-1;
		else
			currentTree++;
		return trees.get(currentTree);
	}

	/**
	 * @return la formule précédente
	 * dans l'historique si elle existe.
	 */
	public KrakenTree getPrevious(){
		if(currentTree > 0)
			currentTree--;
		return trees.get(currentTree);
	}

	/**
	 *
	 * @param  int index        indice de la formule dans l'historique.
	 * @return la formule d'indice index
	 * dans l'historique si elle existe.
	 */
		public KrakenTree getStateFromTimeline (int index) {
			if ((index < 0) || (index >= trees.size())) {
				System.out.println("null:" + index);
				return null;
			}
			currentTree = index;
			return trees.get(currentTree);
		}

	/**
	 * @return l'indice de la formule
	 * courante dans l'historique.
	 */
	public int getCurrentTree () {
		return currentTree;
	}

/**
 * Constructeur de l'objet session.
 * @param  Boolean    custom        Indique si l'on dois charger les règles custom.
 * @param  Expression expression    Expression initiale de la session de jeu.
 * @param  Expression victory       Expression finale de la session de jeu.
 * @return            [description]
 */
	public Session(Boolean custom, Expression expression, Expression victory){
		gameId = UUID.randomUUID();
		RulesConfiguration globalRules = new RulesConfiguration();
		readRules(globalRules,"/rules.cfg");
		if(custom)
			readRules(globalRules,"/customRules.cfg");
		trees.add(new KrakenTree(globalRules));
		trees.get(currentTree).setRoot(expression);
		trees.get(currentTree).setVictory(victory);
	}

/**
 * Permet de créer une règle custom et la sauvegarder
 * dans config/customRules.cfg.
 * @param int start indice dans l'historique de la formule d'entrée de la règle.
 * @param int end   indice dans l'historique de la formule de sortie de la règle.
 */
	public void createTheorem (int start, int end) {
		if ((start < trees.size()) && (end < trees.size())) {
			getTree().addRule("Custom", trees.get(start).getRoot(), trees.get(end).getRoot());
			RuleParser.writeRule(new Rule(trees.get(start).getRoot(), trees.get(end).getRoot()));
		}
	}

	/**
	 * Application d'une règle sur une expression de
	 * la formule courante.
	 * @param String exprid  id de l'expression sur laquelle appliquer la règle.
	 * @param int    idrule  id de la règle à appliquer.
	 * @param String contexe contexe d'application de la règle.
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

/**
 * Supprime toutes les transformations de l'historique
 * ayant un indice supérieur a currentTree.
 */
	public void menage(){
		for(int i = trees.size() - 1 ; i > currentTree ; i--)
			trees.remove(i);
	}

/**
 * Lecture d'un fichier de règles.
 * @param RulesConfiguration config liste des règles.
 * @param String             file   nonm du fichier de règles.
 */
	private void readRules(RulesConfiguration config, String file) {
	    String configPath = "config";
		try {
			RuleParser.readRules(new FileInputStream(new File(configPath + file)), config);
		} catch (FileNotFoundException | libreDragon.ruleParser.ParseException e) {
			e.printStackTrace();
		}
	}

}
