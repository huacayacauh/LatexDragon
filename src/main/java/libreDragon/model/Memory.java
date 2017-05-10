package libreDragon.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe permet de vérifier la sémantique de l'application d'une règle
 * sur une expression, puis d'appliquer cette règle à l'expression.
 * @author Pacôme
 */
public class Memory {

	Map<String, Expression> reductions;

	public Memory() {
		reductions = new HashMap<String, Expression>();
	}

	/**
	 * Renvoit s'il est possible d'enregistrer l'expression généraliste name
	 * à la valeur expression.
	 * Quand on applique 1 + 2 au modèle A + A,
	 * On enregistre A à 1
	 * puis A à 2 : ce qui génère une erreur.
	 *
	 * Cependant s'il y avait 1 à la place de 2, le processus ne poserait pas de problème.
	 * @param name : le nom de l'expression généraliste
	 * @param expression : le nom de l'expression à réduire
	 * @return un booléen
	 */
	private boolean canRegister(String name, Expression expression) {
		if( ! reductions.containsKey(name) ) return true;
		else return reductions.get(name).compare(expression);
	}

	/**
	 * Enregistre une certaine expression généraliste à une valeur.
	 * Ne fonctionnement seulement si une valeur n'est pas déjà affectée.
	 * @param name : le nom de l'expression généraliste
	 * @param expression : le nom de l'expression à réduire
	 */
	private void register(String name, Expression expression) {
		if( reductions.containsKey(name) ) return;
		reductions.put(name, expression);
	}

	/**
	 * Renvoit si l'expression généraliste name est déjà enregistrée.
	 * @param name : le nom de l'expression généraliste
	 * @return un booléen
	 */
	private boolean exists(String name) {
		return reductions.containsKey(name);
	}

	/**
	 * Renvoie l'expression réduite correspodant au terme généralisant name.
	 * @param name : le nom de l'expression généraliste
	 * @return un booléen
	 */
	private Expression get(String name) {
		return reductions.get(name);
	}

	/**
	 * Explore récursivement le noeud binaire en argument.
	 * Appelle explore sur les deux sous noeuds.
	 * @param input_model : le sous-modèle correspondant au noeud binaire
	 * @param input : le noeud binaire
	 * @return un booléen
	 */
	private boolean exploreBinary(Expression input_model, Expression input) {
		BinaryExpression bi_input_model = (BinaryExpression) input_model;
		BinaryExpression bi_input	    = (BinaryExpression) 	   input;

		if( ! explore(bi_input_model.firstExpression(),
							bi_input.firstExpression()) ) return false;
		if( ! explore(bi_input_model.secondExpression(),
							bi_input.secondExpression()) ) return false;

		return true;
	}

	/**
	 * Explore récursivement le noeud unaire en argument.
	 * Appelle explore sur le sous noeud.
	 * @param input_model : le sous-modèle correspondant au noeud unaire
	 * @param input : le noeud unaire
	 * @return un booléen
	 */
	private boolean exploreUnary(Expression input_model, Expression input) {
		UnaryExpression un_input_model = (UnaryExpression) input_model;
		UnaryExpression un_input	   = (UnaryExpression) 	   input;

		if( ! explore(un_input_model.subExpression(),
							un_input.subExpression()) ) return false;

		return true;
	}

	/**
	 * Explore le noeud primaire en argument.
	 * Si le noeud primaire est une expression généraliste, on essaye d'enregistrer
	 * l'expression input sous le nom de l'expression généraliste.
	 * @param input_model : le sous-modèle correspondant au noeud primaire
	 * @param input : le sous arbre
	 * @return un booléen
	 */
	private boolean explorePrimary(Expression input_model, Expression input) {
		PrimaryExpression pr_input_model = (PrimaryExpression) input_model;

		if( pr_input_model.getType() == PrimaryExpression.general_expression_type ) {
			if( ! canRegister(pr_input_model.getName(), input) ) return false;
			register(pr_input_model.getName(), input);
		}

		return true;
	}

	/**
	 * Explore l'arbre input_model en enregistrant tout les sous arbres de input
	 * qui correspondent à des noeuds primaires généralistes.
	 * On rappelle qu'en dehors des noeuds primaires généralistes dont le but
	 * est de simplifier l'arbre, les deux expressions sont structurellement identiques.
	 * Cette fonction est récursive.
	 * @param input_model : le modèle
	 * @param input : l'expression à réduire
	 * @return un booléen si l'exploration s'est bien effectuée
	 */
	private boolean explore(Expression input_model, Expression input) {
		if( input_model instanceof BinaryExpression )
			return exploreBinary(input_model, input);
		else if( input_model instanceof UnaryExpression )
			return exploreUnary(input_model, input);
		else
			return explorePrimary(input_model, input);
	}

	/**
	 * Initialise la mémoire en effectuant une exploration.
	 * Cette fonction est récursive.
	 * @param input_model : le modèle
	 * @param input : l'expression à réduire
	 * @return un booléen si l'exploration s'est bien effectuée
	 */
	public boolean init(Expression input_model, Expression input) {
		return explore(input_model, input);
	}

	/**
	 * Processus permettant de transformer récursivement toutes les expression généralistes
	 * à leur équivalent dans la mémoire.
	 * @param input : l'expression à transformer
	 * @return l'expression résultat
	 */
	private Expression fillBinary(Expression input) {
		BinaryExpression bi_input = (BinaryExpression) input;

		bi_input.setFirstExpression (  fill(bi_input.firstExpression()) );
		bi_input.setSecondExpression( fill(bi_input.secondExpression()) );

		return bi_input;
	}


	/**
	 * Processus permettant de transformer récursivement toutes les expression généralistes
	 * à leur équivalent dans la mémoire.
	 * @param input : l'expression à transformer
	 * @return l'expression résultat
	 */
	private Expression fillUnary(Expression input) {
		UnaryExpression un_input = (UnaryExpression) input;

		un_input.setSubExpression( fill(un_input.subExpression()) );

		return un_input;
	}

	/**
	 * Processus permettant de transformer récursivement toutes les expression généralistes
	 * à leur équivalent dans la mémoire.
	 * @param input : l'expression à transformer
	 * @return l'expression résultat
	 */
	private Expression fillPrimary(Expression input) {
		PrimaryExpression pr_input = (PrimaryExpression) input;

		if( pr_input.getType() == PrimaryExpression.general_expression_type )
			if( exists( pr_input.getName() ) )
				return get(pr_input.getName()).cloneExpression();

		return input;
	}


	/**
	 * Processus permettant de transformer récursivement toutes les expression généralistes
	 * à leur équivalent dans la mémoire.
	 * Ce processus est récursif.
	 * @param input : l'expression à transformer
	 * @return l'expression résultat
	 */
	private Expression fill(Expression input) {
		if( input instanceof BinaryExpression )
			return fillBinary(input);
		else if( input instanceof UnaryExpression )
			return fillUnary(input);
		else
			return fillPrimary(input);
	}

	/**
	 * Cette fonction permet d'appliquer la mémoire à un résultat de règle.
	 * La fonction copie le résultat de manière à ce que l'arbre obtenu soit bien
	 * différent en mémoire de l'arbre initial.
	 * @param result_model : le résultat à calculer
	 * @return l'expression obtenue
	 */
	public Expression applic(Expression result_model) {
		return fill( result_model.cloneExpression() );
	}

}
