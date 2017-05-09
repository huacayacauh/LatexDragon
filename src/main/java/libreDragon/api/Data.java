package libreDragon.api;

import java.util.ArrayList;
import java.util.HashMap;

import libreDragon.latexParser.LatexConfiguration;
import libreDragon.model.Configuration;
import libreDragon.model.Expression;
import libreDragon.model.Pair;
import libreDragon.model.RulesConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import libreDragon.ruleParser.RuleParser;
import java.nio.charset.StandardCharsets;


/**
 * The data server
 * @author malo
 *
 */
public class Data {
	private static boolean init = false;
	private static LatexConfiguration config = null;
	private static HashMap<String, Session> sessions = new HashMap<>();
	private static ArrayList<Pair<Expression,Expression>> expressionJouable = new ArrayList();

	public static void initialize(){
		config = new LatexConfiguration();
		Configuration.init(config);
		readExpressions();
		init = true;
	}

	/**
	 * This function add a new session and init configuration
	 * if this the first session.
	 * @return the session id
	 */
	public static String addSession(Boolean customRules, int indice){
		Session session;
		if(!init){
			initialize();
		}
		session = new Session(customRules, indice);
		sessions.put(session.gameId.toString(), session);
		return session.gameId.toString();
	}

	public static int getNbExpressions(){
		if(!init){
			initialize();
		}
		return expressionJouable.size();
	}

	public static Expression getExpression(int i){
		if(!init){
			initialize();
		}
		if(i < expressionJouable.size())
			return expressionJouable.get(i).first;
		return expressionJouable.get(0).first;
	}

	public static Expression getExpressionVictory(int i){
		if(!init){
			initialize();
		}
		if(i < expressionJouable.size())
			return expressionJouable.get(i).second;
		return expressionJouable.get(0).second;
	}

	/**
	 * This function close a session if she exist
	 * @param id session id to close
	 */
	public static void closeSession(String id){
		if(sessions.containsKey(id))
			sessions.remove(id);
	}
	/**
	 * return the session
	 * @param id the session id to return
	 * @return the session
	 */
	public static Session getSession(String id){
		if(!init){
			initialize();
		}
		return sessions.get(id);
	}

	/**
	 * This function return true if the session id is open in the server
	 * @param id the session id
	 * @return True if contain session id
	 */
	public static boolean isIn(String id){
		if(!init){
			initialize();
		}
		return sessions.containsKey(id);
	}

	/**
	 * @return
	 */
	public static LatexConfiguration getConfig() {
		if(!init){
			initialize();
		}
		return config;
	}

	public static ArrayList<Pair<Expression,Expression>> getExpressionsList(){
		if(!init){
			initialize();
		}
		return expressionJouable;
	}

	private static void readExpressions(){
		ArrayList<Pair<Expression,Expression>> liste = new ArrayList();
		String configPath = "config";
		BufferedReader lecteurAvecBuffer = null;
		String ligne,expression,resultat;
		int i;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(configPath + "/formula.cfg"));
		}
		catch(FileNotFoundException exc){
			System.out.println("Erreur d'ouverture");
		}
		try {
			while ((ligne = lecteurAvecBuffer.readLine()) != null){
				i = 0;
				while (i+2 < ligne.length() && ligne.substring(i,i+2).compareTo("=>") != 0){
					i++;
				}
				expression = ligne.substring(0, i-1);
				resultat = ligne.substring(i+2);
				try {
					InputStream expInitial = new ByteArrayInputStream(expression.getBytes(StandardCharsets.UTF_8));
					InputStream expFinal = new ByteArrayInputStream(resultat.getBytes(StandardCharsets.UTF_8));
					expressionJouable.add(new Pair (RuleParser.readExpression(expInitial),RuleParser.readExpression(expFinal)));
				} catch (libreDragon.ruleParser.ParseException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
