package libreDragon.api;

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

/**
 * The data server
 * @author malo
 *
 */
public class Data {
	private static LatexConfiguration config = null;
	private static HashMap<String, Session> sessions = new HashMap<>();

	/**
	 * This function add a new session and init configuration
	 * if this the first session.
	 * @return the session id
	 */
	public static String addSession(Boolean customRules){
		Session session;
		if(config == null){
			config = new LatexConfiguration();
			Configuration.init(config);
		}
		session = new Session(customRules);
		sessions.put(session.gameId.toString(), session);
		return session.gameId.toString();
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
}
