package libreDragon.model;

public class Operator {
	public static String convert(String s){
		switch (s) {
			case "PLUS": return "+";
			case "OR": return "|";
			case "AND": return "&";
			case "EGAL": return "=";
			case "DIFF": return "!=";
			case "INF": return "<";
			case "SUP": return ">";
			case "INFEGAL": return "<=";
			case "SUPEGAL": return ">=";
			case "MOINS_B": return "-";
			case "FOIS": return "*";
			case "DIVIDE": return "/";
			case "POWER": return"^";
			case  "SQRT" :  return "sqrt";
			case "MOINS_U": return "-";
			case "FACTORIAL": return "!";
			case  "NOT": return "!";
			case "CONGRAT": return "congrat";


		default:
			break;
		}
		return null;

	}

}
