package libreDragon.generateRuleParser;

public class SpecificationPrimaire implements Specification {

	private String name;
	String regEx;
	//TODO: Voir comment faire pour les valeurs possibles, ne pas oublier de modifier le constructeur

	public SpecificationPrimaire(String name, String regEx)
	{
		this.name = name;
		this.regEx = regEx;
	}

	public String toString(){
		return "Primaire " + name + " " + regEx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}





}
