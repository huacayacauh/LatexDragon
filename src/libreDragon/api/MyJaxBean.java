package libreDragon.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import libreDragon.model.Expression;

@XmlRootElement
public class MyJaxBean {
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
    
	public String getMyJaxBean () {
		Expression resultat = Data.getTree().getRoot();
		setMath((String) resultat.generateExpression("0"));
		setIds(Data.getexpr());
		list = Data.getrules();
		Data.cleanexpr();
		Data.setTree(resultat);
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":"+ids+","
				+ "\"rules\":["+list
				+"}";
	}
}