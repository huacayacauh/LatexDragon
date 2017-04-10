package libreDragon.latexParser;

import libreDragon.model.BinaryExpression;
import libreDragon.model.UnaryExpression;
import libreDragon.model.PrimaryExpression;
import libreDragon.model.Expression;

public class ReverseParser{
  private static int indice = 0;

  private static void miamCssId(String expression){
    while (expression.charAt(indice) != '}')
      indice++;
    indice++;
  }

  private static boolean isPrimary(String expression){
    if(expression.charAt(indice) == '{' && expression.charAt(indice + 2) == '}'){
      indice ++;
      return true;
    }

    return false;
  }

  private static boolean isLitteral(String expression){
    return Character.isLetter(expression.charAt(indice));
  }

  private static int isUnary(){
    return 0;
  }


  private static boolean isUnaryType(String type){
    if(type == "PARENTHESE" || type == "MOINS_U" || type == "NOT")
      return true;
    return false;
  }

  private static String getType(String expression){
    switch (expression.charAt(indice)) {
			case '+': indice +=2;return "PLUS";
			case '|': indice +=2;return "OR";
			case '&': indice +=2;return "AND";
			case '=': indice +=2;return "EGAL";
			case '<': indice +=2;return "INF";
			case '>': indice +=2;return "SUP";
			case '-': indice +=2;return "MOINS";
			case '*': indice +=2;return "FOIS";
			case '/': indice +=2;return "DIVIDE";
			case '^': indice +=2;return"POWER";
      case '(': indice +=2;return "PARENTHESE";
      case '!': indice +=2;return "NOT";
		default:
			break;
		}
    if(indice +6 < expression.length() && expression.substring(indice, indice+5).compareTo("\\over") == 0){
      indice += 6;
      return "DIVIDE";
    }
    else if(indice +5 < expression.length() && expression.substring(indice, indice+4).compareTo("sqrt") == 0){
        indice += 5;
        return "SQRT";
      }
    else if (indice +4 < expression.length()) {
      if(expression.substring(indice, indice+1).compareTo("!=") == 0){
        indice += 4;
        return "DIFF";
      }
      else if(expression.substring(indice, indice+1).compareTo("<=") == 0){
        indice += 4;
        return "INFEGAL";
      }
      else if(expression.substring(indice,indice+1).compareTo(">=") == 0){
        indice += 4;
        return "SUPEGAL";
      }
    }
    return "UNDEFINE";
  }

  public static Expression parse (String expression){
    String type;
    Expression first_expression, second_expression;
    int debut, fin;
    miamCssId(expression);
    if(isPrimary(expression)){
      if(isLitteral(expression)) {
        System.out.println("New PrimaryExpression LITTERAL: "+expression.charAt(indice));
        debut = indice;
        indice ++;
        return new PrimaryExpression("LITTERAL", ""+expression.charAt(debut));
      }
      else {
        System.out.println("New PrimaryExpression NOMBRE: "+expression.charAt(indice));
        debut = indice;
        indice ++;
        return new PrimaryExpression("NOMBRE", ""+expression.charAt(debut));
      }
    }
    else {
      indice++;
      debut = indice;
      type = getType(expression);
      if(isUnaryType(type)){
        System.out.println("Create UnaryExpression");
        first_expression = parse(expression);
        System.out.println("New UnaryExpression : "+first_expression.expressionToString());
        indice += 2;
        return new UnaryExpression(type,first_expression);
      }
      else {
        indice = debut;
        System.out.println("Create BinaryExpression");
        first_expression = parse(expression);
        indice ++;
        type = getType(expression);
        second_expression = parse(expression);
        System.out.println("New BinaryExpression : "+first_expression.expressionToString() +" "+type+" "+ second_expression.expressionToString());
        indice ++;
        return new BinaryExpression(type, first_expression, second_expression);
      }

    }

  }
}
