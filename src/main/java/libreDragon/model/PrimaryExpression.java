package libreDragon.model;

import java.util.LinkedList;
import java.util.List;
import libreDragon.api.Session;

public class PrimaryExpression implements Expression{

	public static final String general_expression_type = "EXPRESSION";

	String type;
	String name;
	String id;

	Expression father;

	public PrimaryExpression(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public PrimaryExpression cloneExpression() {
		return new PrimaryExpression(type, name);
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public void generateRulesAndIdExpression(String id, KrakenTree tree) {
		this.id = id;
		Configuration.graphic.generateRulesAndIdPrimaryExpression(this, type, name, id, tree);
	}

	@Override
	public String generateExpression(String id) {
		this.id = id;
		return Configuration.graphic.generatePrimaryExpression(this, type, name, id);
	}

	@Override
	public String generateSimpleExpression() {
		return Configuration.graphic.generateSimplePrimaryExpression(this, type, name);
	}

	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof PrimaryExpression) ) return false;

		PrimaryExpression primary_expression = (PrimaryExpression) expression;
		if( primary_expression.getType().compareTo(getType()) != 0 ) return false;
		if( primary_expression.getName().compareTo(getName()) != 0 ) return false;

		return true;
	}

	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType().compareTo(general_expression_type) == 0 ) return true;
		if( ! (model instanceof PrimaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;

		PrimaryExpression primary_model = (PrimaryExpression) model;

		if(primary_model.getName().compareTo(getName()) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return expressionToString();
	}

	@Override
	public String expressionToString() {
		return getName();
	}

	@Override
	public void setFather(Expression father) {
		this.father = father;
	}

	@Override
	public Expression getFather() {
		return father;
	}

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

	public String getExpr(){
		return name;
	}


}
