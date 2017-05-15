package libreDragon.model;

import java.util.List;
import libreDragon.api.Session;

/**
 * Cette interface décrit un noeud de l'arbre représentant l'expression.
 * Elle oblige les noeuds à disposer d'un ensemble de services.
 *
 * @author Pacôme
 */
public interface Expression {

	String getExpr();
	/**
	 * Cette fonction génère l'équivalent graphique du noeud.
	 * A noter que dans l'implémentation MathJax, on a rajouté un argument identifiant.
	 * Ca permet de donner un tag à chaque expression.
	 * @return L'objet graphique (le type dépend de la factory de la configuration)
	 */
	Object generateExpression(String id);

	/**
	 * Cette fonction génère l'équivalent graphique Simple du noeud.
	 * @return L'objet graphique (le type dépend de la factory de la configuration)
	 */
	Object generateSimpleExpression();

	/**
	 * Cette fonction génère les ids des expressions et des règles.
	 * @param id : id de l'expression
	 * @param tree : l'objet contenant les id des règles et des expressions
	 */
	void generateRulesAndIdExpression(String id,KrakenTree tree);

	/**
	 * Compare si une expression est équivalente à une autre.
	 * Ce processus est récursif.
	 *
	 * @param expression : l'expression avec qui comparer
	 * @return un booléen
	 */
	boolean compare(Expression expression);

	/**
	 * Renvoit si cet arbre est réductible au modèle.
	 * Pour cela, le modèle doit être équivalent en terme de structure
	 * avec l'expression. Certains sous arbre de l'expressions peuvent
	 * cependant être réduit dans le modèle à des expression généralisées.
	 * Exemple :
	 * "(4 * 3) + 5" peut se résoudre au modèle "A + B"
	 * @param model :le modèle
	 * @return un booléen
	 */
	boolean doesMatchModel(Expression model);

	/**
	 * Renvoit le type d'expression.
	 * Une expression binaire "A + B" a pour type "PLUS"
	 * Le nom exact dépend des fichiers de configurations utilisateur.
	 * @return le type
	 *
	 */
	String getType();

	/**
	 * Renvoit une copie de l'expression.
	 * Les sous arbres sont également copiés, de manière à ce qu'aucune référence
	 * dans la copie de réfère à un objet précédemment existant ie la copie et l'original
	 * sont indépendants.
	 * @return la copie
	 */
	Expression cloneExpression();

	/**
	 * Renvoit un équivalent de l'expression sous forme de chaine de caractère.
	 * Utile pour le début.
	 * @return une chaîne de caractère decriptive
	 */
	String expressionToString();

	/**
	 * Informe à l'expression que le noeud père est le noeud en paramètre.
	 * @param expression : le noeud père
	 * @see KrakenTree
	 */
	void setFather(Expression expression);

	/**
	 * Donne le noeud père. Peut être null.
	 * @return le père s'il existe
	 */
	Expression getFather();

	/**
	 * Génère une liste d'expression décrivant les pères dans l'ordre, jusqu'à la racine.
	 * @return la liste
	 */
	List<Expression> generatePathList();

	String getId();
}
