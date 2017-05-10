package libreDragon.generateRuleParser;

public class SpecificationBinaire implements Specification {

	String name;
	String symbol;
	int priority;

	public SpecificationBinaire(String name, String symbol, int priority) {
		this.name = name;
		this.symbol = symbol;
		this.priority = priority;
	}

	public String toString(){
		return "Binaire " + name + " " + symbol + " " + priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}




}
