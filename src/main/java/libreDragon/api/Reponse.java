package libreDragon.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import libreDragon.model.Expression;
import libreDragon.api.Session;

/**
 * Class use to create a answer to the client
 * @author malo
 *
 */
@XmlRootElement
public class Reponse {
	@XmlElement private String math;
	@XmlElement private String ids;
	@XmlElement private String list="";
	private String timeline;

	public String getMath() {
		return math;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public void setTimeline (String timeline) {
		this.timeline = timeline;
	}
	public String getTimeline () {
		return timeline;
	}

	/**
	 * return a JSON implementation of the formula and the rules we can applique
	 * @param gameId
	 * @return
	 */
	public String formula (String gameId) {
		Data.getSession(gameId).cleanexpr();
		Expression resultat = Data.getSession(gameId).getTree().getRoot();
		setMath((String) resultat.generateExpression("0",Data.getSession(gameId)));
		setIds(Data.getSession(gameId).getexpr());
		list = Data.getSession(gameId).getrules();
		setTimeline(Data.getSession(gameId).getTimeline());
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list+","
				+ "\"timeline\":{"
				+ "\"current\":"+Data.getSession(gameId).getCurrentTree()+","
				+ "\"elements\":["+timeline
				+ "}";
	}

  public String formula (String gameId, String mode) {
		Data.getSession(gameId).cleanexpr();
		Expression resultat = null;

		if (mode.compareTo("NEXT") == 0)
			  resultat = Data.getSession(gameId).getNext().getRoot();
		else if (mode.compareTo("PREVIOUS") == 0)
			  resultat = Data.getSession(gameId).getPrevious().getRoot();

		setMath((String) resultat.generateExpression("0",Data.getSession(gameId)));
		setIds(Data.getSession(gameId).getexpr());
		list = Data.getSession(gameId).getrules();
		setTimeline(Data.getSession(gameId).getTimeline());
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list+","
				+ "\"timeline\":{"
				+ "\"current\":"+Data.getSession(gameId).getCurrentTree()+","
				+ "\"elements\":["+timeline
				+ "}";
	}

	public String formula (String gameId, int index) {
		Data.getSession(gameId).cleanexpr();

		Expression resultat = Data.getSession(gameId).getStateFromTimeline(index).getRoot();

		setMath((String) resultat.generateExpression("0",Data.getSession(gameId)));

		setIds(Data.getSession(gameId).getexpr());

		list = Data.getSession(gameId).getrules();

		setTimeline(Data.getSession(gameId).getTimeline());

		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list+","
				+ "\"timeline\":{"
				+ "\"current\":"+Data.getSession(gameId).getCurrentTree()+","
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
			+	"\"rules\":[" + Data.getSession(gameId).getGlobalRules()
			+ "}";
	}
}
