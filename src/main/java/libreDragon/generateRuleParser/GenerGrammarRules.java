package libreDragon.generateRuleParser;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import model.Configuration;
//import model.RulesConfiguration;

public class GenerGrammarRules{

	public List<SpecificationUnaire> listLeftUnaryOperators;
	//La liste des specifications unaires gauche
	public List<SpecificationUnaire> listRightUnaryOperators;
	//La liste des specifications unaires droite
	public List<SpecificationUnaire> listUnaryOperators;
	//La liste des specifications unaires gauche et droite
	public List<SpecificationPrimaire> listPrimaryOperators;
	//La liste des specifications primaires
	public List<SpecificationBinaire> listBinaryOperators;
	//La liste des specifications binaires
	HashMap<String, String> codeOfSymbol;
	// Clef : Le symbole, Valeur : Le nom du premier opérateur où le symbole a été vu


    private File file;

	/**
	 * Initialise des listes de specifications différentes (primaires, unaires et binaires)
	 * en triant une liste de spécifications
	 * @param listOperators : La liste contenant toutes les specifications
	 * @param grammarFilePath : The path of the grammar file to produce
	 */
	public GenerGrammarRules(List<Specification> listOperators, String grammarFilePath)
	{
		listLeftUnaryOperators = new ArrayList<SpecificationUnaire>();
		listRightUnaryOperators = new ArrayList<SpecificationUnaire>();
		listUnaryOperators = new ArrayList<SpecificationUnaire>();
		listPrimaryOperators = new ArrayList<SpecificationPrimaire>();
		listBinaryOperators = new ArrayList<SpecificationBinaire>();
		codeOfSymbol = new HashMap<String, String>();

		sort(listOperators);

		file = new File(grammarFilePath);
	}


	/**
	 * Permet de trier la liste des operateurs Binaires en fonction de leur priorité ( + petit au + grand )
	 * @param b
	 */
	private void addBinary(SpecificationBinaire b)
	{
		//Si la liste est vide
		if(listBinaryOperators.isEmpty()) {
			listBinaryOperators.add(b);
			return;
		}

		//Cas classique
		for(int i = 0 ; i < listBinaryOperators.size(); i++)
		{
			if(listBinaryOperators.get(i).getPriority() >= b.getPriority())
			{
				listBinaryOperators.add(i, b);
				return;
			}
		}

		//Si l'élément doit être ajouté en dernier
		listBinaryOperators.add(b);
	}

	/**
	 * Tri des spécifications en fonction de leur type(primaire,unaire,binaire)
	 * puis tri des specifications binaire en fonction de leur priorité
	 * @param listOperators : la liste de toute les specifications
	 */
	private void sort(List<Specification> listOperators)
	{
		for(Specification s : listOperators)
		{
			if( s instanceof SpecificationPrimaire)
			{
				listPrimaryOperators.add((SpecificationPrimaire) s);
			}

			else if(s instanceof SpecificationUnaire)
			{
				SpecificationUnaire u = (SpecificationUnaire) s;
				if(u.isLeftSymbolEmpty())
					listRightUnaryOperators.add(u);
				else if(u.isRightSymbolEmpty())
					listLeftUnaryOperators.add(u);
				else
					listUnaryOperators.add(u);

			}

			else if( s instanceof SpecificationBinaire)
			{
				addBinary((SpecificationBinaire) s);
			}
			else
				System.out.println("Erreur");
		}

		SpecificationUnaire u = new SpecificationUnaire("ROOT", "\"#\"", "\"\"");
		listLeftUnaryOperators.add(u);

		SpecificationPrimaire p = new SpecificationPrimaire("EXPRESSION", "\"[\"A\"-\"Z\"]");
		listPrimaryOperators.add(p);

		u = new SpecificationUnaire("BRACKETS", "\"{\"", "\"}\"" );
		listUnaryOperators.add(u);

	}

	/**
	 * Écrit une spécification binaire dans un fichier de type .jj
	 * @param b : La specifictaion binaire à écrire
	 * @param isFirstTerm : permet d'écrire le symbole OU si ce n'est pas la 1ere specification
	 * @param writer
	 * @param compteur : le compteur de terme
	 * @return : la spécifictaion binaire écrite
	 */
	private String writeABinaryRule(SpecificationBinaire b, boolean isFirstTerm, PrintWriter writer, int compteur)
	{
		String total;
		if(!isFirstTerm)
			writer.println("\t|");

		String value = codeOfSymbol.get(b.getSymbol());
		String name = b.getName();

		String nextSymbol = "Terme"+ (compteur-1);
		total = "\t\tsymbol = <"+value+">\n\t\texp1 = "+nextSymbol+"()\n";
		total += "\t\t{return new BinaryExpression(\""+name+"\", exp, exp1); }";

		return total;
	}

	/**
	 * Écrit une spécification unaire dans un fichier de type .jj
	 * @param u : : La specifictaion unaire à écrire
	 * @param isFirstTerm : permet d'écrire le symbole OU si ce n'est pas la 1ere specification
	 * @param writer
	 * @param compteur : le compteur de terme
	 * @param isListRightUnaryOperators
	 * @param isListLeftUnaryOperators
	 * @return la spécifictaion unaire écrite
	 */
	private String writeAnUnaryRule(SpecificationUnaire u, boolean isFirstTerm, PrintWriter writer, int compteur, Boolean isListRightUnaryOperators, Boolean isListLeftUnaryOperators)
	{
		String total = "\t\t";

		String value, nextSymbol;
		String name = u.getName();
		if(!isListRightUnaryOperators){
			if(u != listLeftUnaryOperators.get(0) )
				writer.println("\t|");

			value = codeOfSymbol.get(u.getLeftSymbol());
			nextSymbol = "Terme"+compteur;
			total = "\t\t"+"symbol = <"+value+">\n\t\texp = "+nextSymbol+"()\n"; //TODO : voir si l'utilisation de value tel quel est correcte
			total += "\t\t{return new UnaryExpression(\""+name+"\", exp);}";
			return total;
		}
		else if(!isListLeftUnaryOperators)
		{

			if(u != listRightUnaryOperators.get(0) )
				writer.println("\t|");

			value = codeOfSymbol.get(u.getRightSymbol());
			total =  "\t\tsymbol = <"+value+">\n";
			total += "\t\t{ exp2 = new UnaryExpression(\""+name+"\",exp);}\n";
			total += "\t\texp3 = UnaireDroitBis(exp2)\n";
			total += "\t\t{return exp3 == null ? exp2 : exp3;}";

			return total;
		}

		if(u != listUnaryOperators.get(0))
			writer.println("\t|");
		value = codeOfSymbol.get(u.getLeftSymbol());
		total = "\t\tsymbolLeft = <"+value+">\n";
		total += "\t\texpression = Terme0()\n";
		value = codeOfSymbol.get(u.getRightSymbol());
		total += "\t\tsymbolRight = <"+value+">\n";

		if(name.equals("BRACKETS"))
			total += "\t\t{return expression;}\n";
		else
			total+= "\t\t{return new UnaryExpression(\""+name+"\",expression);}\n" ;

		return total;
	}

	/**
	 * Écrit une specifictaion primaire dans un fichier de type .jj
	 * @param p : la spécifictaion primaire
	 * @param writer
	 * @return la specification primaire écrite
	 */
	private String writeAPrimaryRule(SpecificationPrimaire p, PrintWriter writer)
	{
		writer.println("\t|");
		String value = codeOfSymbol.get(p.getRegEx());
		String name = p.getName();
		return "\t\tsymbolLeft = <"+value+">\n\t\t{return new PrimaryExpression(\""+name+"\",symbolLeft.image);}";
	}

	/**
	 * Écrit une règle dans un fichier.jj pour n'importe quelle spécification
	 * @param s : la specification
	 * @param isListRightUnaryOperators
	 * @param isListLeftUnaryOperators
	 * @param writer
	 * @param compteur : le compteur de terme
	 * @param isFirstTerm
	 */
	private void writeARule(Specification s, Boolean isListRightUnaryOperators, Boolean isListLeftUnaryOperators, PrintWriter writer, int compteur, boolean isFirstTerm){
		String total = "";

		if( s instanceof SpecificationBinaire){
			total = writeABinaryRule((SpecificationBinaire) s, isFirstTerm, writer,compteur);
		}

		if( s instanceof SpecificationUnaire){
			total = writeAnUnaryRule((SpecificationUnaire)s, isFirstTerm, writer, compteur, isListRightUnaryOperators, isListLeftUnaryOperators);
		}

		if( s instanceof SpecificationPrimaire){
			total = writeAPrimaryRule((SpecificationPrimaire) s, writer);
		}
		writer.println("\t(");
		writer.println(total);
		writer.println("\t)");
	}


	/**
	 * Écrit toutes les spécifications binaire dans un fichier de type.jj
	 * @param writer
	 * @param compteur : le compteur de terme
	 * @param currentIndex : l'index courant de la liste des spécifications binaires (init a 0)
	 * @return : le currentIndex
	 */
	private int writeAllBinaryRules(PrintWriter writer, int compteur, int currentIndex)
	{

		int currentPriority = listBinaryOperators.get(currentIndex).getPriority();

		boolean isFirstTerm = true;
		writer.println("Expression Terme"+ compteur +"() :\n{");
		writer.println("\t\tExpression exp;");
		writer.println("\t\tExpression exp2;");
		writer.println("}\n{");
		compteur++;

		String nextSymbol = "\t\texp = Terme"+ compteur;
		writer.println(nextSymbol+"()\n\t\texp2 = Terme"+(compteur-1)+"Bis(exp)\n");
		writer.println("\t\t{ return exp2 == null ? exp : exp2;}\n}");
		SpecificationBinaire b;

		writer.println("Expression Terme"+ (compteur-1) +"Bis(Expression exp) :\n{");
		writer.println("\tToken symbol;");
		writer.println("\tExpression exp1;");
		writer.println("}\n{");

		for(; currentIndex < listBinaryOperators.size() ; currentIndex++)
		{
			b = listBinaryOperators.get(currentIndex);

			if(b.getPriority() < currentPriority)
				continue;
			if(b.getPriority() > currentPriority)
				break;
			writeARule(b, false, false, writer, compteur, isFirstTerm);
			writer.println("\t|");
		}

		writer.println("\t(");
		writer.println("\t\texp1 = Epsilon()");
		writer.println("\t\t{return exp1;}");
		writer.println("\t)");

		writer.println("}");
		return currentIndex;
	}

	/**
	 * Écrit toutes les règles de la grammaire générée dans un fichier.jj
	 * @param writer
	 */
	private void writeAllRules(PrintWriter writer) {

		int compteur = 0;
		int currentIndex = 0;


		writer.println("void RuleList() :");
		writer.println("{}");
		writer.println("{");
		writer.println("\t( Rule() ) *");
		writer.println("\t<EOF>");
		writer.println("}");

		writer.println("void Rule() :\n{");
		writer.println("\tExpression exp1;");
		writer.println("}");
		writer.println("{");
		writer.println("\texp1 = Terme0()");
		writer.println("\tRuleBis(exp1)");
		writer.println("}");

		writer.println("void RuleBis(Expression exp1) :\n{");
		writer.println("\tExpression exp2;");
		writer.println("\tToken input_type_rule;");
		writer.println("}");
		writer.println("{");
		writer.println("\t(");
		writer.println("\t\t<LEFT_RULE_EQUIVALENT>");
		writer.println("\t\tinput_type_rule = <RULE_INPUT_TYPE>");
		writer.println("\t\t<RIGHT_RULE>");
		writer.println("\t\texp2 = Terme0()");
		writer.println("\t\t{rules.addRule(input_type_rule.image.substring(1), new Rule(exp1, exp2));"
				+ "   \n\t\trules.addRule(input_type_rule.image.substring(1), new Rule(exp2, exp1));"
				+ "   \n\t\tSystem.out.println(rules.getRules());}");
		writer.println("\t) |");
		writer.println("\t(");
		writer.println("\t\t<LEFT_RULE_NOT_EQUIVALENT>");
		writer.println("\t\tinput_type_rule = <RULE_INPUT_TYPE>");
		writer.println("\t\t<RIGHT_RULE>");
		writer.println("\t\texp2 = Terme0()");
		writer.println("\t\t{rules.addRule(input_type_rule.image.substring(1), new Rule(exp1, exp2));}");
		writer.println("\t)");
		writer.println("}");



		writer.println("void Axiome() :\n{");
		writer.println("\tExpression exp;");
		writer.println("}");

		writer.println("{");
		writer.println("\texp = Terme0()");
		writer.println("\t<EOF>\n");
		writer.println("\t{System.out.println(exp.expressionToString());}");
		writer.println("}");

		if(listBinaryOperators == null){
			writer.println("Expression Terme0() :\n{");
			writer.println("}\n{");
			writer.println("\tTerme"+ compteur++ +"();");
		}

		else
		{
			while(listBinaryOperators.size() - (currentIndex + 1) >= 0 ) //TODO : simplifier
			{
				currentIndex = writeAllBinaryRules(writer, compteur, currentIndex);
				compteur++;
			}
		}

		writer.println("Expression Terme"+compteur+"() :\n{"); //UnaireGauche
		writer.println("\tToken symbol;");
		writer.println("\tExpression exp;");
		writer.println("}");

		writer.println("{");

		if(! listLeftUnaryOperators.isEmpty())
		{
			for(SpecificationUnaire u : listLeftUnaryOperators)
				writeARule(u, false, true, writer, compteur, false);

			writer.println("\t|\n\t(");
		}

		writer.println("\t\texp = UnaireDroit()");
		writer.println("\t\t{return exp;}");
		writer.println("\t)");
		writer.println("}");

		writer.println("Expression UnaireDroit() :\n{");
		writer.println("\tExpression primaire;\n\tExpression exp;\n");
		writer.println("}\n{");
		writer.println("\tprimaire = Primaire()");
		writer.println("\texp = UnaireDroitBis(primaire)");
		writer.println("\t{if(exp == null) return primaire; return exp;}");
		writer.println("}");


		writer.println("Expression UnaireDroitBis(Expression exp) :\n{");
		writer.println("\tToken symbol;");
		writer.println("\tExpression exp2;");
		writer.println("\tExpression exp3;");
		writer.println("}\n{");

		if(! listRightUnaryOperators.isEmpty())
		{
			for(SpecificationUnaire u : listRightUnaryOperators)
				writeARule(u, true, false, writer, 0, false);

			writer.println("\t|\n\t(");
		}
		else writer.println("\t(");

		writer.println("\t\texp2 = Epsilon()");
		writer.println("\t\t{return exp2;}");
		writer.println("\t)");
		writer.println("}");

		writer.println("Expression Primaire() :\n{");
		writer.println("\tToken symbolLeft;");
		writer.println("\tToken symbolRight;");
		writer.println("\tExpression expression;");
		writer.println("}");

		writer.println("{");

		if(! listUnaryOperators.isEmpty())
			for(SpecificationUnaire u : listUnaryOperators)
				writeARule(u, true, true, writer, 0, false);

		for(SpecificationPrimaire p : listPrimaryOperators)
			writeARule(p, false, false, writer, 0, false);

		writer.println("}");

	}

	/**
	 * Écrit tout les tokens des spécifications primaires dans un fichier.jj
	 * @param writer
	 */
	private void writePrimaryToken(PrintWriter writer)
	{
		for(SpecificationPrimaire p : listPrimaryOperators)
		{

			if(!codeOfSymbol.containsKey(p.getRegEx())){
				codeOfSymbol.put(p.getRegEx(), p.getName());

				String part = " < "+p.getName()+" : "+ p.getRegEx().substring(1) +" >";


				if(listBinaryOperators == null && listLeftUnaryOperators == null && listRightUnaryOperators == null && listUnaryOperators == null && p == listPrimaryOperators.get(0) && ! part.equals(""))
					writer.println(" "+part);

				else if(!part.equals(""))
					writer.println("|"+part);

			}
		}
	}


	/**
	 * Écrit tout les tokens des spécifications unaires dans un fichier.jj
	 * @param writer
	 */
	private void writeUnaryToken(PrintWriter writer)
	{
		for(SpecificationUnaire u : listUnaryOperators)
		{
			String leftPart = "";
			String rightPart = "";

			if(!codeOfSymbol.containsKey(u.getLeftSymbol()))
			{
				leftPart = " < "+u.getName()+"_LEFT : "+ u.getLeftSymbol() +" >";
				codeOfSymbol.put(u.getLeftSymbol(), u.getName()+"_LEFT");
			}
			if(!codeOfSymbol.containsKey(u.getRightSymbol()))
			{
				rightPart = " < "+u.getName()+"_RIGHT : "+ u.getRightSymbol() +" >";
				codeOfSymbol.put(u.getRightSymbol(), u.getName()+"_RIGHT");
			}

			if(listBinaryOperators == null && listLeftUnaryOperators == null && listRightUnaryOperators == null && u == listUnaryOperators.get(0) && ! leftPart.equals(""))
				writer.println(" "+leftPart);

			else if(!leftPart.equals(""))
				writer.println("|"+leftPart);

			if(!rightPart.equals(""))
				writer.println("|"+rightPart);
		}
	}

	/**
	 * Écrit tout les tokens des spécifications unaires gauche dans un fichier.jj
	 * @param writer
	 */
	private void writeLeftUnaryToken(PrintWriter writer)
	{
		for(SpecificationUnaire u : listLeftUnaryOperators)
		{
			String leftPart = "";

			if(!codeOfSymbol.containsKey(u.getLeftSymbol()))
			{
				leftPart = " < "+u.getName()+"_LEFT : "+ u.getLeftSymbol() +" >";
				codeOfSymbol.put(u.getLeftSymbol(), u.getName()+"_LEFT");
			}


			if(listBinaryOperators == null && u == listLeftUnaryOperators.get(0) && !leftPart.equals(""))
				writer.println(" "+leftPart);
			else if(!leftPart.equals(""))
				writer.println("|"+leftPart);

		}
	}

	/**
	 * Écrit tout les tokens des spécifications unaires droite dans un fichier.jj
	 * @param writer
	 */
	private void writeRightUnaryToken(PrintWriter writer)
	{
		String rightPart = "";

		for(SpecificationUnaire u : listRightUnaryOperators)
		{

			if(!codeOfSymbol.containsKey(u.getRightSymbol()))
			{
				rightPart = " < "+u.getName()+"_RIGHT : "+ u.getLeftSymbol() +" >";
				codeOfSymbol.put(u.getLeftSymbol(), u.getName()+"_RIGHT");
			}

			if(listBinaryOperators == null && listLeftUnaryOperators == null && u == listRightUnaryOperators.get(0) && !rightPart.equals(""))
				writer.println(" "+rightPart);
			else if(!rightPart.equals(""))
				writer.println("|"+rightPart);

		}
	}

	/**
	 * Écrit tout les tokens des spécifications binaires dans un fichier.jj
	 * @param writer
	 */
	private void writeBinaryToken(PrintWriter writer)
	{
		for(SpecificationBinaire b : listBinaryOperators)
		{
			String part = "";

			if(!codeOfSymbol.containsKey(b.getSymbol()))
			{
				part = " < "+b.getName()+"_OP : "+ b.getSymbol() +" >";
				codeOfSymbol.put(b.getSymbol(), b.getName()+"_OP");
			}

			if(b == listBinaryOperators.get(0) && !part.equals("") )
				writer.println(" "+part);
			else if(!part.equals(""))
				writer.println("|"+part);
		}
	}

	/**
	 * Écrit tout les tokens des spécifications de n'importe quel type
	 * @param writer
	 */
	private void writeAllToken(PrintWriter writer)
	{
		writer.println("TOKEN :\n{");

		if(listBinaryOperators != null)
			writeBinaryToken(writer);

		if(listLeftUnaryOperators != null)
			writeLeftUnaryToken(writer);

		if(listRightUnaryOperators != null)
			writeRightUnaryToken(writer);

		if(listUnaryOperators != null)
			writeUnaryToken(writer);

		if(listPrimaryOperators != null)
			writePrimaryToken(writer);


		String part = " < RULE_INPUT_TYPE : \"§\" ([\"a\"-\"z\",\"A\"-\"Z\",\"_\", \"-\"])+ >";

		if(listPrimaryOperators == null && listBinaryOperators == null && listLeftUnaryOperators == null && listRightUnaryOperators == null && listUnaryOperators == null)
			writer.println(" "+part);

		else
			writer.println("|"+part);

		part = " < LEFT_RULE_EQUIVALENT : \"<=(\" >";
		writer.println("|"+part);

		part = " < LEFT_RULE_NOT_EQUIVALENT : \"=(\" >";
		writer.println("|"+part);

		part = " < RIGHT_RULE : \")=>\" >";
		writer.println("|"+part);

		writer.println("}");

	}


/**
 * Génère la grammaire en créant un fichier .jj d'après les spécifications de toutes les listes
 * @throws FileNotFoundException
 * @throws UnsupportedEncodingException
 */
public void generateGrammar() throws FileNotFoundException, UnsupportedEncodingException
{
	try {
		//file.mkdirs();
		file.createNewFile();
	} catch (IOException e1) {
		e1.printStackTrace();
	}

	PrintWriter writer;
	try {
		writer = new PrintWriter(file);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		return;
	}

	writer.println("PARSER_BEGIN(RuleParser)");
	writer.println("package libreDragon.ruleParser;");
	writer.println("import java.util.ArrayList;");
	writer.println("import libreDragon.model.*;");
	writer.println("import java.io.InputStream;");
	writer.println("import java.io.File;");
	writer.println("import java.io.FileWriter;");
	writer.println("import java.nio.charset.StandardCharsets;");
	writer.println("import java.io.BufferedReader;");
	writer.println("import java.io.File;");
	writer.println("import java.io.InputStream;");
	writer.println("import java.io.FileNotFoundException;");
	writer.println("import java.io.FileReader;");
	writer.println("import java.io.ByteArrayInputStream;");
	writer.println("import java.io.IOException;");
	writer.println("public class RuleParser {");
	writer.println("private static RulesConfiguration rules;");
	writer.println("\tpublic static void main(String args[]) throws ParseException {");
	writer.println("\t\tRuleParser parser = new RuleParser(System.in);");
	writer.println("\t\tConfiguration.rules = new RulesConfiguration();");
	writer.println("\t\tparser.Rule();");
	writer.println("\t}");

	writer.println("public static void readRules(InputStream stream, RulesConfiguration r) throws ParseException {");
	writer.println("\trules = r;");
	writer.println("\tif (jj_initialized_once){");
		writer.println("\tRuleParser.ReInit(stream);");
		writer.println("\tRuleParser.RuleList();");
	writer.println("\t}");
	writer.println("\telse{");
			writer.println("\tRuleParser parser = new RuleParser(stream);");
			writer.println("\tparser.RuleList();");
	writer.println("\t}");
	writer.println("\t}");

	writer.println("\tpublic static void writeRule(Rule rule) {");
			writer.println("\tfinal String chemin = \"config//customRules.cfg\";");
			writer.println("\tfinal File fichier =new File(chemin);");
			writer.println("\ttry {");
					writer.println("\tif(!fichier.exists())");
						writer.println("\tfichier.createNewFile();");
					writer.println("\tfinal FileWriter writer = new FileWriter(fichier, true);");
					writer.println("\ttry {");
							writer.println("\twriter.write(rule.getInputModel().getExpr()+\"\t\"+\"=(§Custom)=>\"+rule.getResultModel().getExpr()+\"\\n\");");

					writer.println("\t} finally {");
							writer.println("\twriter.close();");
					writer.println("\t}");
			writer.println("\t} catch (Exception e) {");
					writer.println("\tSystem.out.println(\"Impossible de creer le fichier\");");
			writer.println("\t}");
	writer.println("\t}");

	writer.println("\t/**");
	 writer.println("\t* Lis les expressions jouables ainsi que les expression a atteindre pour finir la");
	 writer.println("\t* session de jeu dans le fichier config/formulas.cfg");
	 writer.println("\t*/");
	writer.println("\tpublic static void readExpressionsJouables(ArrayList<Pair<Expression,Expression>> liste ){");
		writer.println("\tString configPath = \"config\";");
		writer.println("\tBufferedReader lecteurAvecBuffer = null;");
		writer.println("\tString ligne,expression,resultat;");
		writer.println("\tint i;");
		writer.println("\ttry {");
			writer.println("\tlecteurAvecBuffer = new BufferedReader(new FileReader(configPath + \"/formula.cfg\"));");
		writer.println("\t}");
		writer.println("\tcatch(FileNotFoundException exc){");
			writer.println("\tSystem.out.println(\"Erreur d'ouverture\");");
		writer.println("\t}");
		writer.println("\ttry {");
			writer.println("\twhile ((ligne = lecteurAvecBuffer.readLine()) != null){");
				writer.println("\ti = 0;");
				writer.println("\twhile (i+2 < ligne.length() && ligne.substring(i,i+2).compareTo(\"=>\") != 0){");
					writer.println("\ti++;");
				writer.println("\t}");
				writer.println("\texpression = ligne.substring(0, i-1);");
				writer.println("\tresultat = ligne.substring(i+2);");
				writer.println("\ttry {");
					writer.println("\tInputStream expInitial = new ByteArrayInputStream(expression.getBytes(StandardCharsets.UTF_8));");
					writer.println("\tInputStream expFinal = new ByteArrayInputStream(resultat.getBytes(StandardCharsets.UTF_8));");
					writer.println("\tliste.add(new Pair (RuleParser.readExpression(expInitial),RuleParser.readExpression(expFinal)));");
				writer.println("\t} catch (libreDragon.ruleParser.ParseException e) {");
					writer.println("\te.printStackTrace();");
				writer.println("\t}");
			writer.println("\t}");
		writer.println("\t}");
		writer.println("\tcatch (IOException e){");
			writer.println("\te.printStackTrace();");
		writer.println("\t}");
	writer.println("\t}");

	writer.println("\tpublic static Expression readExpression(InputStream stream) throws ParseException {");
	writer.println("\t\tExpression expression = null;");
	writer.println("\tif (jj_initialized_once){");
	writer.println("\t\tRuleParser.ReInit(stream);");
	writer.println("\texpression = Terme0();");
	writer.println("\t}");
	writer.println("\telse {");
	writer.println("\tRuleParser parser =  new RuleParser(stream);");
	writer.println("\texpression = parser.Terme0();");
	writer.println("\t}");
	writer.println("\t\treturn expression;");
	writer.println("\t}");
	writer.println("}");
	writer.println("\nPARSER_END(RuleParser)\n");

	writer.println("SKIP :\n{");
	writer.println("  \" \"\n| \"\\t\"");	 // les caractères espace " " et \t
	writer.println("| \"\\n\"");			// le caractère \n
	writer.println("| \"\\r\"\n}");			// le caractère \r


	writeAllToken(writer);

	writer.println("\nExpression Epsilon() : { } { {return null;} }\n");//définit une transition epsilon

	writeAllRules(writer);

	writer.close();

}


}
