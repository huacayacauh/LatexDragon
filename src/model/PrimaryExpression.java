package model;

import java.util.LinkedList;
import java.util.List;

public class PrimaryExpression implements Expression{
	
	public static final String general_expression_type = "EXPRESSION";
	
	String type;
	String name;
	
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
	public String generateExpression(String id) {
		return Configuration.graphic.generatePrimaryExpression(this, type, name, id);
	}

	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof PrimaryExpression) ) return false;
		
		PrimaryExpression primary_expression = (PrimaryExpression) expression;
		if( primary_expression.getType() != getType() ) return false;
		if( primary_expression.getName() != getName() ) return false;
		
		return true;
	}

	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType() == general_expression_type ) return true;
		if( ! (model instanceof PrimaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;
		
		PrimaryExpression primary_model = (PrimaryExpression) model;
		return primary_model.getName() == getName();
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


}
