package libreDragon.generateRuleParser;

public class SpecificationUnaire implements Specification {

	String name;
	String rightSymbol;
	String leftSymbol;

	public SpecificationUnaire(String name, String leftSymbol, String rightSymbol) {
		this.name = name;
		this.rightSymbol = rightSymbol;
		this.leftSymbol = leftSymbol;
	}

	public boolean isLeftSymbolEmpty()
	{
		if(leftSymbol.equals("\"\""))
			return true;

		return false;
	}

	public boolean isRightSymbolEmpty()
	{
		if(rightSymbol.equals("\"\""))
			return true;

		return false;
	}

	public String toString(){
		return "Unaire "+ name + " " + leftSymbol + " " + rightSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRightSymbol() {
		return rightSymbol;
	}

	public void setRightSymbol(String rightSymbol) {
		this.rightSymbol = rightSymbol;
	}

	public String getLeftSymbol() {
		return leftSymbol;
	}

	public void setLeftSymbol(String leftSymbol) {
		this.leftSymbol = leftSymbol;
	}




}
