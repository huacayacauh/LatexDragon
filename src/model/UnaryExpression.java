package model;

import java.util.LinkedList;
import java.util.List;

public class UnaryExpression implements Expression {

	String type;
	Expression sub_expression;
	
	Expression father;
	
	public UnaryExpression(String type, Expression sub_expression) {
		setSubExpression(sub_expression);
		this.type = type;
	}
	
	@Override
	public UnaryExpression cloneExpression() {
		return new UnaryExpression(type, sub_expression.cloneExpression() );
	}

	@Override
	public String generateExpression(String id) {
		return Configuration.graphic.generateUnaryExpression(
					this,
					type,
					sub_expression,
					id);
	}
	
	public Expression subExpression() {
		return sub_expression;
	}
	
	public void setSubExpression(Expression expression) {
		sub_expression = expression;
		sub_expression.setFather(this);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean compare(Expression expression) {
		if( ! (expression instanceof UnaryExpression) ) return false;
		
		UnaryExpression unary_expression = (UnaryExpression) expression;
		if( unary_expression.getType() != getType() ) return false;
		
		return unary_expression.subExpression().compare(subExpression());
	}

	@Override
	public boolean doesMatchModel(Expression model) {
		if( model instanceof PrimaryExpression && model.getType() == PrimaryExpression.general_expression_type ) return true;
		if( ! (model instanceof UnaryExpression) ) return false;
		if( ! (model.getType() == getType()) ) return false;
		
		UnaryExpression unary_model = (UnaryExpression) model;
		return subExpression().doesMatchModel(unary_model.subExpression());
	}

	@Override
	public String toString() {
		return expressionToString();
	}

	@Override
	public String expressionToString() {
		return getType() + "( " + subExpression().expressionToString() + " )";
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
