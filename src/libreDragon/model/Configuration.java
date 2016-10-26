package libreDragon.model;

import libreDragon.latexParser.GraphicExpressionFactory;

public class Configuration {
	
	public static GraphicExpressionFactory graphic;
	public static RulesConfiguration rules;
	
	public static void init(GraphicExpressionFactory factory) {
		graphic = factory;
		graphic.init();
		rules = new RulesConfiguration();
	}
}
