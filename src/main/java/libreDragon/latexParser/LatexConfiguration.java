package libreDragon.latexParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import libreDragon.api.Data;
import libreDragon.model.BinaryExpression;
import libreDragon.model.Expression;
import libreDragon.model.Pair;
import libreDragon.model.Rule;
import libreDragon.model.KrakenTree;

public class LatexConfiguration implements GraphicExpressionFactory {

	public static final String config_file_path = "config/graphics.cfg";

	private Map<String, LatexExpressionConfiguration > configurations;

	public LatexConfiguration() {
		configurations = new HashMap<String, LatexExpressionConfiguration >();
		addConfiguration("ROOT",
					new LatexExpressionConfiguration(new Pair<String, String>("", "")));
	}


	public void addConfiguration(String string, LatexExpressionConfiguration value) {
		configurations.put(string, value);
	}

	public LatexExpressionConfiguration getConfiguration(String string) {
		return configurations.get(string);
	}

	@Override
	public String generateBinaryExpression(Expression expression, String type, Expression first, Expression second, String id) {
		BinaryExpression bexpression = (BinaryExpression) expression;
		String operator = getConfiguration(expression.getType()).getOperators().first;
		return "\\\\cssId{exp"+id +"}" +"{" +bexpression.firstExpression().generateExpression(id+"0") + operator.substring(1, operator.length()-1)+bexpression.secondExpression().generateExpression(id+"1")+ "}";
	}

	@Override
	public void generateRulesAndIdBinaryExpression(Expression expression, String type, Expression first, Expression second, String id,KrakenTree tree) {
		BinaryExpression bexpression = (BinaryExpression) expression;
		tree.addIds("exp"+id,expression);
		tree.addRules("exp"+id, tree.generateRules(bexpression));

		bexpression.firstExpression().generateRulesAndIdExpression(id+"0",tree);
		bexpression.secondExpression().generateRulesAndIdExpression(id+"1",tree);
	}


	@Override
	public String generateUnaryExpression(Expression expression, String type, Expression sub, String id) {
		String firstOperator = getConfiguration(expression.getType()).getOperators().first;
		String secondOperator = getConfiguration(expression.getType()).getOperators().second;
		return "\\\\cssId{exp"+id+"}{"+ firstOperator.substring(1, firstOperator.length()-1)  + sub.generateExpression(id+"0") + secondOperator.substring(1, secondOperator.length()-1)+"}";
	}

	@Override
	public void generateRulesAndIdUnaryExpression(Expression expression, String type, Expression sub, String id, KrakenTree tree) {
		tree.addIds("exp"+id,expression);
		tree.addRules("exp"+id, tree.generateRules(expression));
		sub.generateRulesAndIdExpression(id+"0",tree);
	}


	@Override
	public String generatePrimaryExpression(Expression expression, String type, String name, String id) {
		return "\\\\cssId{exp"+id+"}{"+ name +"}";
	}

	@Override
	public String generateSimpleBinaryExpression(Expression expression, String type, Expression first, Expression second) {
		BinaryExpression bexpression = (BinaryExpression) expression;
		String operator = getConfiguration(expression.getType()).getOperators().first;
		return "{" +bexpression.firstExpression().generateSimpleExpression() + operator.substring(1, operator.length()-1)+bexpression.secondExpression().generateSimpleExpression()+ "}";
	}


	@Override
	public String generateSimpleUnaryExpression(Expression expression, String type, Expression sub) {
		String firstOperator = getConfiguration(expression.getType()).getOperators().first;
		String secondOperator = getConfiguration(expression.getType()).getOperators().second;
		return "{"+ firstOperator.substring(1, firstOperator.length()-1)  + sub.generateSimpleExpression() + secondOperator.substring(1, secondOperator.length()-1)+"}";
	}

	@Override
	public String generateSimplePrimaryExpression(Expression expression, String type, String name) {
		return "{"+ name +"}";
	}

	@Override
	public void generateRulesAndIdPrimaryExpression(Expression expression, String type, String name, String id, KrakenTree tree) {
		tree.addIds("exp"+id,expression);
		tree.addRules("exp"+id, tree.generateRules(expression));
	}

	@Override
	public void init() {
		File config_file = new File(config_file_path);
		System.out.println("Init Latex Configuration "+config_file_path);
		System.out.println(config_file.getAbsolutePath());
		try {
			LatexConfigurationParser.read(config_file);
		} catch (ParseException e) {
			System.out.println("Erreur. Le fichier de configuration contient une erreur syntaxique.");
			e.printStackTrace();
		}

		System.out.println(configurations.size() + " configuration(s) graphique(s) ont ete chargees.");
	}

	@Override
	public String generateRuleExpression(Rule rule) {
		return rule.getInputModel().generateSimpleExpression() + "=>" + rule.getResultModel().generateSimpleExpression();
	}


}
