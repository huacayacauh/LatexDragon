package libredragon.model;

import libredragon.model.expressionapi.term.types.*;
import tom.library.sl.*;
import java.util.*;
public  class ExpressionApi {
  %include { sl.tom }
    %gom {
      module Term
      imports int String
      abstract syntax
      Expr = Plus(e1:Expr, e2:Expr)
           | Mult(e1:Expr, e2:Expr)
           | Eq(e1:Expr, e2:Expr)
           | Parenthesis(e:Expr)
           | Sqrt(e:Expr)
           | MoinsU(e:Expr)
           | MoinsB(e1:Expr, e2:Expr)
           | Non (e:Expr)
           | Factorial(e:Expr)
           | Or(e1:Expr, e2:Expr)
           | And(e1:Expr, e2:Expr)
           | Diff(e1:Expr, e2:Expr)
           | Inf(e1:Expr, e2:Expr)
           | Sup(e1:Expr, e2:Expr)
           | Infegal(e1:Expr, e2:Expr)
           | Supegal(e1:Expr, e2:Expr)
           | Divide(e1:Expr, e2:Expr)
           | Power (e1:Expr, e2:Expr)
           | Epsilon()
           | Nombre(i:int)
           | Litteral(name:String)
           | ListPlus(Expr *)
           | ListMult(Expr *)

	// Associaticvité et commutativité des opérateur.
    ListPlus : ACU(){}
    ListMult : ACU(){} 
    }
	
	/** 
	 * Transformation d'une expression binaire pour qu'elle 
	 * soit compatible avec la grammaire du programme
	 */
    public static Expr binaryExpressionToExpr (Expression expression, String type, Expression first, Expression second) {
      BinaryExpression bexpression = (BinaryExpression) expression;
      if(type.compareTo("PLUS") == 0)
        return `Plus(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("FOIS") == 0)
        return `Mult(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("EGAL") == 0)
        return `Eq(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("OR") == 0)
        return `Or(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("AND") == 0)
        return `And(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("DIFF") == 0)
        return `Diff(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("INF") == 0)
        return `Inf(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("SUP") == 0)
        return `Sup(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("Infegal") == 0)
        return `Infegal(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("Supegal") == 0)
        return `Supegal(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("MOINS_B") == 0)
        return `MoinsB(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("DIVIDE") == 0)
        return `Divide(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      else if(type.compareTo("POWER") == 0)
        return `Power(bexpression.firstExpression().expressionToExpr(), bexpression.secondExpression().expressionToExpr());
      return `Epsilon();
    }

	/** 
	 * Transformation d'une expression unaire pour qu'elle 
	 * soit compatible avec la grammaire du programme
	 */
    public static Expr unaryExpressionToExpr(Expression expression, String type, Expression sub) {
      if(type.compareTo("PARENTHESIS") == 0)
        return `Parenthesis(sub.expressionToExpr());
      else if(type.compareTo("NOT") == 0)
        return `Non(sub.expressionToExpr());
      else if(type.compareTo("SQRT") == 0)
        return `Sqrt(sub.expressionToExpr());
      else if(type.compareTo("FACTORIAL") == 0)
        return `Factorial(sub.expressionToExpr());
      else if(type.compareTo("MOINS_U") == 0)
        return `MoinsU(sub.expressionToExpr());
      return sub.expressionToExpr();
    }
	
	/** 
	 * Transformation d'une expression primaire pour qu'elle 
	 * soit compatible avec la grammaire du programme
	 */
    public static Expr primaryExpressionToExpr(Expression expression, String type, String name) {
      if(type.compareTo("NOMBRE") == 0)
        return `Nombre(Integer.valueOf(name));
      return `Litteral(name);
    }

	/** 
	 * Transformation d'une expression sous la forme de la 
	 * grammaire du programme vers une expression compréhensible 
	 * par le reste du serveur
	 */
    public Expression exprToExpression(Expr e){
      %match(e){
        Plus(e1, e2) -> { return new BinaryExpression("PLUS",exprToExpression(`e1), exprToExpression(`e2)); }
        Mult(e1, e2) -> { return new BinaryExpression("FOIS",exprToExpression(`e1), exprToExpression(`e2)); }
        Eq(e1, e2) -> { return new BinaryExpression("EGAL",exprToExpression(`e1), exprToExpression(`e2)); }
        Parenthesis(e1) ->{ return new UnaryExpression("PARENTHESIS", exprToExpression(`e1)); }
        Sqrt(e1) -> {return new UnaryExpression("SQRT", exprToExpression(`e1)); }
        MoinsU(e1) -> {return new UnaryExpression("MOINS_U", exprToExpression(`e1)); }
        Non (e1) -> {return new UnaryExpression("NOT", exprToExpression(`e1)); }
        Factorial(e1) -> {return new UnaryExpression("FACTORIAL", exprToExpression(`e1)); }
        MoinsB(e1, e2) -> { return new BinaryExpression("MOINS_B",exprToExpression(`e1), exprToExpression(`e2)); }
        Or(e1, e2) -> { return new BinaryExpression("OR",exprToExpression(`e1), exprToExpression(`e2)); }
        And(e1, e2) -> { return new BinaryExpression("AND",exprToExpression(`e1), exprToExpression(`e2)); }
        Diff(e1, e2) -> { return new BinaryExpression("DIFF",exprToExpression(`e1), exprToExpression(`e2)); }
        Inf(e1, e2) -> { return new BinaryExpression("INF",exprToExpression(`e1), exprToExpression(`e2)); }
        Sup(e1, e2) -> { return new BinaryExpression("SUP",exprToExpression(`e1), exprToExpression(`e2)); }
        Infegal(e1, e2) -> { return new BinaryExpression("INFEGAL",exprToExpression(`e1), exprToExpression(`e2)); }
        Supegal(e1, e2) -> { return new BinaryExpression("SUPEGAL",exprToExpression(`e1), exprToExpression(`e2)); }
        Divide(e1, e2) -> { return new BinaryExpression("DIVIDE",exprToExpression(`e1), exprToExpression(`e2)); }
        Power(e1, e2) -> { return new BinaryExpression("POWER",exprToExpression(`e1), exprToExpression(`e2)); }
        Nombre(i) -> { return new PrimaryExpression("NOMBRE",""+`i);}
        Litteral(a) -> { return new PrimaryExpression("LITTERAL",`a);}
        Epsilon() -> {return null;}
      }
      throw new RuntimeException("should not be there exprToExpression : " + e);
    }
	
	/** 
	 * Génération de la liste d'élement associé à
	 * l'opérateur *
	 */
    private static Expr generateListMult(Expr e){
      %match(e){
        Mult(e1,e2) -> { return `ListMult(generateListMult(e1), generateListMult(e2) ); }
        x -> { return `x; }
      }
      throw new RuntimeException("should not be there generateListMult : " + e);
    }

	/** 
	 * Génération de la liste d'élement associé à
	 * l'opérateur +
	 */
    private static Expr generateListPlus(Expr e){
      %match(e){
        Plus(e1,e2) -> { return `ListPlus(generateListPlus(e1), generateListPlus(e2) ); }
        x -> { return `x; }
      }
      throw new RuntimeException("should not be there generateListPlus: " + e);
    }

	/** 
	 * Remise en sous forme d'expression les listes associé 
	 * au different opérateur.
	 */
    private Expr listToOp(Expr e){
      %match(e){
        ListPlus() -> { return `Epsilon(); }
        ListMult() -> { return `Epsilon(); }
        ListPlus(a,b) -> {return `Plus(a,b); }
        ListPlus(a, after*) -> { return `Plus (a, listToOp(ListPlus(after*))); }
        ListMult(a,b) -> {return `Mult(a,b); }
        ListMult(a, after*) -> { return `Mult (a, listToOp(ListMult(after*))); }
        x -> { return `x;}
      }
      throw new RuntimeException("should not be there listToOp : " + e);
    }

    %strategy Com() extends Fail() {
      visit Expr {
        Plus(e1, e2) -> Plus(e2, e1)
        Mult(e1, e2) -> Mult(e2, e1)
      }
    }

	/** 
	 * Fonction utilisée pour la génération a partir de liste 
	 * des éléments associé au différent opérateur des variantes
	 * de l'expression traité.
	 */
    public void eval (Expr e, ArrayList list){
      Expr e1, e2, e3;
      %match(e) {
          ListPlus?(T1*,T2*) -> {
              e1 = listToOp(`T1);
              e2 = listToOp(`T2);
              if( `e1 != `Epsilon() && `e2 != `Epsilon()){
                list.add(exprToExpression(`Plus(e1, e2)));
                try {
                  list.add(exprToExpression(`Com().visit(`Plus(e1, e2))));
                } catch(VisitFailure f) {
                  System.out.println("the strategy failed");
                }
              }
          }
        ListMult?(T1*,T2*) -> {
          e1 = listToOp(`T1);
          e2 = listToOp(`T2);
          if( `e1 != `Epsilon() && `e2 != `Epsilon()){
            list.add(exprToExpression(`Mult(e1, e2)));
            try {
              list.add(exprToExpression(`Com().visit(`Mult(e1, e2))));
            } catch(VisitFailure f) {
              System.out.println("the strategy failed");
            }
          }
        }
      }
    }


    public ArrayList<Expression> run(Expression exp) {
      ArrayList<Expression> list = new ArrayList();
      Expr e = exp.expressionToExpr();
      Expr plus = generateListPlus(e);
      Expr fois = generateListMult(e);

      eval(plus, list);
      eval(fois, list);
      return list;
  }
}
