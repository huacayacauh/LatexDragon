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
 * Class use to create a answer to the client
 * @author malo
 *
 */
@XmlRootElement
public class Reponse {

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
				temp += ("{\"text\":\"$$"+exp.generateExpression("") + " => "+liste.get(0).first.applic(exp).generateExpression("")+"$$\","+"\"ruleId\":"+0+",\"type\":"+"\""+liste.get(0).second+"\"}");
			}
			for(int i = 1; i < liste.size(); i++){
				temp += ","+("{\"text\":\"$$"+exp.generateExpression("") + " => "+liste.get(i).first.applic(exp).generateExpression("")+"$$\","+"\"ruleId\":"+i+",\"type\":"+"\""+liste.get(i).second+"\"}");
			}
			if(iterateur.hasNext())
				temp+="]},";
			else
				temp+="]}";
		}
		return temp+"]";
	}

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
	 *  return the list of expressions in a string
	 * @return
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

	public String generateJsonExpressionList(ArrayList<Pair<Expression,Expression>> list){
		String temp ="[";
		if(list.size() > 0)
			temp += ("\"$$"+ list.get(0).first.generateExpression("")+"$$\"");
		for(int i = 1; i < list.size(); i++){
			temp += ",\"$$" + list.get(i).first.generateExpression("") +"$$\"";
		}
		return temp+"]";
	}

	public String getJsonTimeline (ArrayList<KrakenTree> trees) {
		String timeline = "";
		for (int i = 0 ; i < trees.size() ; i++) {
			String tmp = (String) trees.get(i).getRoot().generateExpression("");
			timeline += "{\"index\":" + i + ",";
			timeline += "\"text\":\"$$" + tmp +"$$\"}";
			if (i != trees.size() - 1)
				timeline += ",";
		}
		timeline += "]}";
		return timeline;
	}


	/**
	 * return a JSON implementation of the formula and the rules we can applique
	 * @param gameId
	 * @return
	 */
  public String formula (String gameId, String mode, int index) {
		String gameStatus = "RUNNING";
		if(	Data.getSession(gameId).getTree().victoryTest()){
			gameStatus = "END";
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
	 * return a JSON implementation of the connection state
	 * @param newPlayerId
	 * @param status
	 * @param complementaryInfo
	 * @return
	 */
	public String info (String playerId, String status, String complementaryInfo) {

		return "{ \"id\": \"" + playerId + "\","
				+ "\"status\": \"" + status + "\","
				+ "\"complementaryInfo\": \"" + complementaryInfo + "\"}";
	}

	public String rulesList (String gameId) {
		return "{"
			+ "\"status\": \"" + "SUCCESS" + "\","
			+	"\"rules\":[" + generateJsonRulesList(Data.getSession(gameId).getTree())
			+ "}";
	}

	public String expressionList () {
		return "{"
			+	"\"expression\":" + generateJsonExpressionList(Data.getExpressionsList())
			+ "}";
	}
}
