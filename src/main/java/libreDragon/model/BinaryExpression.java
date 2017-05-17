package libreDragon.model;

import java.util.LinkedList;
import java.util.List;

import libreDragon.api.Data;
import libreDragon.latexParser.OperatorParser;
import libreDragon.api.Session;

/**
 * Cette classe représente un noeud à deux fils.
 * Exemple : + dans 1 + 2
 *
 * @author Pacôme
 * @see Expression
 *
 */
public class BinaryExpression implements Expression {

	String type;
	String id;
	Expression first_expression;
	Expression second_expression;

	Expression father;

	public BinaryExpression(String type, Expression first_expression, Expression second_expression) {
		setFirstExpression(first_expression);
		setSecondExpression(second_expression);
		this.type = type;
	}

	@Override
	public BinaryExpression cloneExpression() {
		return new BinaryExpression(type, first_expression.cloneExpression(), second_expression.cloneExpression());
	}

	/**
	 * Génère un équivalent graphique en fonction de la factory de la configuration
	 */
	@Override
	public String generateExpression(String id) {
		this.id = id;
		return Configuration.graphic.generateBinaryExpression(
					this,
					type,
					first_expression,
					second_expression,
					id);
	}

	@Override
	public String generateSimpleExpression() {
		return Configuration.graphic.generateSimpleBinaryExpression(
					this,
					type,
					first_expression,
					second_expression);
	}

	@Override
	public void generateRulesAndIdExpression(String id,KrakenTree tree) {
		this.id = id;
		Configuration.graphic.generateRulesAndIdBinaryExpression(
					this,
					type,
					first_expression,
					second_expression,
					id, tree);
	}

	/**
	 * Compare ce noeud avec le noeud en paramètre.
	 * Tente d'abord de comparer le type du noeud (qui doit être binaire)
	 * puis essaye de comparer son nom de type ( PLUS == FOIS ? )
	 * puis essaye de comparer les sous arbres récursivement.
	 * @param expression : l'expression à comparer
	 */
	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof BinaryExpression) ) return false;

		BinaryExpression binary_expression = (BinaryExpression) expression;
		if( binary_expression.getType() != getType() ) return false;

		return binary_expression.firstExpression().compare(firstExpression()) && binary_expression.secondExpression().compare(secondExpression());
	}

	/**
	 * Compare l'expression au modèle en argument.
	 * Semblable à la fonction compare, à l'exception près qu'une
	 * expression correspond toujours à une expression primaire générale,
	 * même si elle ne s'y compare pas.
	 * @param model : l'expression modèle
	 */
	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType() == PrimaryExpression.general_expression_type ) return true;
		if( ! (model instanceof BinaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;

		BinaryExpression binary_model = (BinaryExpression) model;
		return firstExpression().doesMatchModel(binary_model.firstExpression()) && secondExpression().doesMatchModel(binary_model.secondExpression());
	}

	public Expression firstExpression() {
		return first_expression;
	}

	public Expression secondExpression() {
		return second_expression;
	}

	public void setFirstExpression(Expression expression) {
		first_expression = expression;
		first_expression.setFather(this);
	}

	public void setSecondExpression(Expression expression) {
		second_expression = expression;
		second_expression.setFather(this);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return expressionToString();
	}

	@Override
	public String expressionToString() {
		return "( " + firstExpression().expressionToString() + " " + getType() + " " + secondExpression().expressionToString() + " )";
	}

	@Override
	public void setFather(Expression father) {
		this.father = father;
	}

	@Override
	public Expression getFather() {
		return father;
	}

	/**
	 * Génère le path de ce noeud.
	 * @see Expression
	 */
	@Override
	public List<Expression> generatePathList() {
		if( father == null ) {
			List<Expression> list = new LinkedList<Expression>();
			list.add(this);
			return list;
		}

		List<Expression> list = father.generatePathList();
		list.add(this);
		return list;
	}

	public String getId(){
		return this.id;
	}

	@Override
	public String getExpr() {
		return first_expression.getExpr() +" "+ Operator.convert(type)  +" "+  second_expression.getExpr();
	}
}
