package libreDragon.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import libreDragon.model.Expression;
import libreDragon.api.Session;
import libreDragon.model.KrakenTree;
import libreDragon.model.Configuration;
import libreDragon.model.Pair;
import libreDragon.model.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Cette classe a pour rôle de créer des objet JSON au bon format afin de permettre
 * la communication de données client / serveur.
 * @author malo
 *
 */
@XmlRootElement
public class Reponse {

	/**
	 * Cette fonction génère le champs rules de l'objet JSON formula.
	 * Ce champs est composer d'une liste de couple : id d'une expression
	 * et liste d'id de règles applicables sur cette expression.
	 * @param tree KrakenTree contenant la formule l'id de ses expression
	 * et des règles applicables sur celles-ci.
	 * @return String
	 */
	private String generateJsonRules(KrakenTree tree){
		String temp ="";
		Expression exp = null;
		Set<String> listKeys= tree.getKeyRules();
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			String key = iterateur.next();
			temp += "{\""+key +"\":[";
			exp = tree.getIds(key);
			ArrayList<Pair<Rule,String>> liste = tree.getRules(key);
			if(liste.size() > 0){
				temp += ("{\"text\":\"$$"+exp.generateSimpleExpression() + " => "+liste.get(0).first.applic(exp).generateSimpleExpression()+"$$\","+"\"ruleId\":"+0+",\"type\":"+"\""+liste.get(0).second+"\"}");
			}
			for(int i = 1; i < liste.size(); i++){
				temp += ","+("{\"text\":\"$$"+exp.generateSimpleExpression() + " => "+liste.get(i).first.applic(exp).generateSimpleExpression()+"$$\","+"\"ruleId\":"+i+",\"type\":"+"\""+liste.get(i).second+"\"}");
			}
			if(iterateur.hasNext())
				temp+="]},";
			else
				temp+="]}";
		}
		return temp+"]";
	}

	/**
	 * Cette fonction génère le champs rules de l'objet JSON rulesList.
	 * Ce champs est composer d'une liste de règles : le modèle d'entrée
	 * et le modèle de sortie, le tout au format mathJax.
	 * @param tree KrakenTree contenant les règles de la session de jeu.
	 * @return String
	 */
	public String generateJsonRulesList(KrakenTree tree){
		String temp ="";
		Set<String> listKeys = tree.globalRules.getRules().keySet();
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			String key= iterateur.next();
			temp += "{\""+key +"\":[";
			List<Rule> liste = tree.globalRules.getRules().get(key);
			if(liste.size() > 0)
				temp += ("\"$$"+ Configuration.graphic.generateRuleExpression(liste.get(0))+"$$\"");
			for(int i = 1; i < liste.size(); i++){
				temp += ",\"$$" + Configuration.graphic.generateRuleExpression(liste.get(i)) +"$$\"";
			}
			if(iterateur.hasNext())
				temp+="]},";
			else
				temp+="]}";
		}
		return temp+"]";
	}

	/**
	 * Cette fonction génère le champs ids de l'objet JSON formula.
	 * Ce champs est composer de la liste de d'id des expression de
	 * la formule courante.
	 * @param tree KrakenTree contenant la formule courante et les id de ses expression.
	 * @return String
	 */
	public String generateJsonId(KrakenTree tree){
		String temp ="[";
		Set<String> listKeys = tree.getKeyIds();
		Iterator<String> iterator = listKeys.iterator();
		if(iterator.hasNext()){
			temp = temp+"\""+iterator.next()+"\"";
			while (iterator.hasNext()) {
				temp = temp+","+"\""+iterator.next()+"\"";
			}
		}
		return temp+"]";
	}

	/**
	 * Cette fonction génère le champs expression de l'objet JSON expressionList.
	 * Ce champs est composer de la liste de d'expressions jouables au format mathJax.
	 * @param tree KrakenTree contenant la formule courante et les id de ses expression.
	 * @return String
	 */
	public String generateJsonExpressionList(ArrayList<Pair<Expression,Expression>> list){
		String temp ="[";
		if(list.size() > 0)
			temp += ("\"$$"+ list.get(0).first.generateSimpleExpression()+"$$\"");
		for(int i = 1; i < list.size(); i++){
			temp += ",\"$$" + list.get(i).first.generateSimpleExpression() +"$$\"";
		}
		return temp+"]";
	}

	/**
	 * Cette fonction génère le champs timeline de l'objet JSON formula.
	 * Ce champs est composer de la liste de de formule au format mathJax
	 * correspondant à l'historique des transformations de la formule courante.
	 * @param tree KrakenTree contenant l'historique des transformation de la
	 * formule courante.
	 * @return String
	 */
	public String getJsonTimeline (ArrayList<KrakenTree> trees) {
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


	/**
	 * Cette fonction génère l'objet JSON formula.
	 * Cet objet est composé du champs
	 * 	- math : la formule sous format mathJax
	 * 	- status : si la requête réussi ou non
	 * 	- gameStatus : statut du jeu
	 * 	- ids : id des expression de la formule contenue dans mathJax
	 * 	- rules : id des règle applicable sur les expression de la formule
	 * 	- timeline : historique des transformations de la formule courante
	 * @param gameId : id de la session de jeu.
	 * @param mode : suivant précedent ou rien.
	 * @param index : indice dans l'historique des transformations.
	 * @return String
	 */
  public String formula (String gameId, String mode, int index) {
		String gameStatus = "RUNNING";
		if(	Data.getSession(gameId).getTree().victoryTest()){
			gameStatus = "VICTORY";
		}
		Data.getSession(gameId).getTree().cleanIds();
		Data.getSession(gameId).getTree().cleanRules();
		KrakenTree tree;

		if (mode.compareTo("NEXT") == 0)
			  tree = Data.getSession(gameId).getNext();
		else if (mode.compareTo("PREVIOUS") == 0)
			  tree = Data.getSession(gameId).getPrevious();
		else if(index != -1)
			tree = Data.getSession(gameId).getStateFromTimeline(index);
		else
			tree = Data.getSession(gameId).getTree();

		Expression resultat = tree.getRoot();

		String math = (String) resultat.generateExpression("0");
		resultat.generateRulesAndIdExpression("0",tree);
		String ids = generateJsonId(tree);
		String list = generateJsonRules(tree);
		String timeline = getJsonTimeline(Data.getSession(gameId).getTrees());
		int current = Data.getSession(gameId).getCurrentTree();
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"status\": \"" + "SUCCESS" + "\","
				+ "\"gameStatus\": \"" + gameStatus + "\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list+","
				+ "\"timeline\":{"
				+ "\"current\":"+current+","
				+ "\"elements\":["+timeline
				+ "}";
	}

	/**
	 * Cette fonction génère l'objet JSON info.
	 * Permet d'envoyer des information au client comme des message d'erreur.
	 * @return String
	 */
	public String info (String playerId, String status, String complementaryInfo) {

		return "{ \"id\": \"" + playerId + "\","
				+ "\"status\": \"" + status + "\","
				+ "\"complementaryInfo\": \"" + complementaryInfo + "\"}";
	}

	/**
	 * Cette fonction génère l'objet JSON rulesList.
	 * Permet d'envoyer la liste de toutes les règles de la session
	 * ayant comme id gameId au client.
	 * @param id de la session de jeu.
	 * @return String
	 */
	public String rulesList (String gameId) {
		return "{"
			+ "\"status\": \"" + "SUCCESS" + "\","
			+	"\"rules\":[" + generateJsonRulesList(Data.getSession(gameId).getTree())
			+ "}";
	}

	/**
	 * Cette fonction génère l'objet JSON expressionList.
	 * Permet d'envoyer la liste de toutes les expressions jouables au client.
	 * @param id de la session de jeu.
	 * @return String
	 */
	public String expressionList () {
		return "{"
			+ "\"status\": \"" + "SUCCESS" + "\","
			+	"\"expression\":" + generateJsonExpressionList(Data.getExpressionsList())
			+ "}";
	}
}
