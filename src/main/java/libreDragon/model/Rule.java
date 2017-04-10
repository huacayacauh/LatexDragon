package libreDragon.model;

/**
 * Cette classe représente une règle.
 * Une règle prend un modèle d'entrée et un modèle de sortie.
 * Pour plus détails sur le fonctionnement d'une règle, checker le wiki du projet
 * sur github.
 * @author Pacôme
 *
 */
public class Rule {

	Expression input_model;
	Expression result_model;
	
	public Rule(Expression input_model, Expression result_model) {
		this.input_model = input_model;
		this.result_model = result_model;
	}
	
	/**
	 * Cette fonction renvoit si la règle peut s'appliquer sur l'expression en paramètre.
	 * Elle procède d'abord à vérifier la structure du paramètre,
	 * puis essaye d'affecter des valeurs correctes à toutes les expressions généralistes.
	 * Exemple :
	 * avec la règle "A + A donne 2 * A"
	 * l'expression input "1 + 2"
	 * Correspond structurellement au modèle d'entrée,
	 * mais ne correspond pas sémantiquement puisque
	 * A ne peut pas valoir 1 et 2 en même temps.
	 * @param input : l'expression à tester
	 * @return un booléen
	 */
	public boolean canApplic(Expression input) {
		//if(input.getSize() != input_model.getSize()) return false;
		if( ! input.doesMatchModel(input_model)){
			return false;
		}
		Memory memory = new Memory();
		
		if( ! memory.init(input_model, input) ) return false;
		return true;
	}
	
	/**
	 * Cette fonction essaye d'appliquer la règle à l'expression en paramètre.
	 * Un test de validité est effectué en premier lieu.
	 * Puis, si l'expression est correcte, on effectue la lecture d'une mémoire
	 * sur l'expression, puit on applique la mémoire sur le résultat.
	 * @param input : l'expression cible
	 * @return l'expression résultat
	 * @throws IllegalArgumentException
	 */
	public Expression applic(Expression input) throws IllegalArgumentException
	{
		if( ! input.doesMatchModel(input_model) ) throw new IllegalArgumentException();
		
		Memory memory = new Memory();
		
		if( ! memory.init(input_model, input) ) throw new IllegalArgumentException();
		
		return memory.applic(result_model);
	}
	
	@Override
	public String toString() {
		return input_model.expressionToString() + " ==> " + result_model.expressionToString();
	}
	
	public Expression getInputModel() {
		return input_model;
	}
	
	public Expression getResultModel() {
		return result_model;
	}
	
	/**
	 * Utilise la configuration graphique de l'utilisateur pour générer un objet graphique
	 * correspondant à la règle.
	 * @return l'objet graphique correspondant
	 */
	/*public Object generateExpression() {
		return Configuration.graphic.generateRuleExpression(this);
	}*/

}
