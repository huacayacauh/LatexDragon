package libreDragon.api;

import java.util.ArrayList;
import java.util.HashMap;

import libreDragon.latexParser.LatexConfiguration;
import libreDragon.model.Configuration;
import libreDragon.model.Expression;
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
	private static LatexConfiguration config = null;
	private static HashMap<String, Session> sessions = new HashMap<>();
	private static ArrayList<Expression> expressionJouable = new ArrayList();

	/**
	 * This function add a new session and init configuration
	 * if this the first session.
	 * @return the session id
	 */
	public static String addSession(Boolean customRules, int indice){
		Session session;
		if(config == null){
			config = new LatexConfiguration();
			Configuration.init(config);
			readExpressions();
		}
		session = new Session(customRules, indice);
		sessions.put(session.gameId.toString(), session);
		return session.gameId.toString();
	}

	public static int getNbExpressions(){
		return expressionJouable.size();
	}

	public static Expression getExpression(int i){
		if(i < expressionJouable.size())
			return expressionJouable.get(i);
		return expressionJouable.get(0);
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
		return sessions.get(id);
	}

	/**
	 * This function return true if the session id is open in the server
	 * @param id the session id
	 * @return True if contain session id
	 */
	public static boolean isIn(String id){
		return sessions.containsKey(id);
	}

	/**
	 * @return
	 */
	public static LatexConfiguration getConfig() {
		return config;
	}

	public static ArrayList<Expression> getExpressionsList(){
		return expressionJouable;
	}

	private static void readExpressions(){
		String configPath = "config";
		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(configPath + "/formula.cfg"));
		}
		catch(FileNotFoundException exc){
			System.out.println("Erreur d'ouverture");
		}
		try {
			while ((ligne = lecteurAvecBuffer.readLine()) != null){
				try {
					InputStream stream = new ByteArrayInputStream(ligne.getBytes(StandardCharsets.UTF_8));
					expressionJouable.add(RuleParser.readExpression(stream));
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
