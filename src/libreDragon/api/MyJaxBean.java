package libreDragon.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import libreDragon.model.Configuration;
import libreDragon.model.Expression;
import libreDragon.model.Rule;

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
		List<Rule> liste;
		Expression resultat = Data.getTree().getRoot();
		Map<String, List<Rule>> rules = Configuration.rules.getRules();
		Set<String> listKeys=rules.keySet();  // Obtenir la liste des clés
		Iterator<String> iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		/*while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			liste = rules.get(key);
			for(int i = 0; i < liste.size(); i++){
				System.out.println("Rule "+liste.get(i));
				if (liste.get(i).canApplic(resultat)){
					resultat = liste.get(i).applic(resultat);
					System.out.println(key+" Applic "+liste.get(i)+" "+resultat.expressionToString());
				}
			}
		}*/
		setMath((String) resultat.generateExpression("0"));
		setIds(Data.getexpr());
		list = Data.getrules();
		Data.cleanexpr();
		return 	"{"
				+ "\"math\": \"$$"+math+"$$\","
				+ "\"ids\":["+ids+"],"
				+ "\"list\":["+list
				+"}";
	}
}