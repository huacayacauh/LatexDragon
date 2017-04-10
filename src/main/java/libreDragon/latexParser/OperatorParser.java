package libreDragon.latexParser;

public class OperatorParser {
	
	public static String parseOperator(String string) {
		if ( string == null ) return null;
		return string.substring(1, string.length() - 1);
	}
	

}
