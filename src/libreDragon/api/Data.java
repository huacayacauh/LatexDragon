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
	private static HashMap<String, Session> sessions = new HashMap<>();
	
	public static void addSession(String id){
		Session session;
		if(sessions.isEmpty()){
			session = new Session();
			setDefault();
		}
		else
			session = new Session(id);
		sessions.put(id, session);
	}
	
	public static Session getSession(String id){
		return sessions.get(id);
	}
	
	public static LatexConfiguration getConfig() {
		return config;
	}
	
	public static void setDefault() {
		readRules();
	}
	public static void readRules() {
	     String configPath = "config";
			Configuration.rules = new RulesConfiguration();
			try {
				RuleParser.readRules(new FileInputStream(new File(configPath + "/rules.cfg")));
			} catch (FileNotFoundException | libreDragon.ruleParser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
}
}
