package libreDragon.latexParser;


import libreDragon.model.Pair;

public class LatexExpressionConfiguration {
	private Pair<String, String> operators;
	
	public LatexExpressionConfiguration(Pair<String, String> operators) {
		this.operators = operators;
	}
	
	public Pair<String, String> getOperators() {
		return operators;
	}
	
}
