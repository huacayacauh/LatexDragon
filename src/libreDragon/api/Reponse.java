package libreDragon.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import libreDragon.model.Expression;

@XmlRootElement
public class Reponse {
    @XmlElement private String math;
    @XmlElement private String ids;
    @XmlElement private String list="";
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
    
	public String getMyJaxBean (String gameId) {
		Data.getSession(gameId).cleanexpr();
		Expression resultat = Data.getSession(gameId).getTree().getRoot();
		setMath((String) resultat.generateExpression("0",gameId));
		setIds(Data.getSession(gameId).getexpr());
		list = Data.getSession(gameId).getrules();
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list
				+"}";
	}
	public String authentification (String newPlayerId, String status, String complementaryInfo) {
		
		return "{ \"id\": \"" + newPlayerId + "\","
				+ "\"status\": \"" + status + "\","
				+ "\"complementaryInfo\": \"" + complementaryInfo + "\"}";
	}
}