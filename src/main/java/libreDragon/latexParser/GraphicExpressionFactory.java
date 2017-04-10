package libreDragon.latexParser;

import libreDragon.model.Expression;
import libreDragon.model.Rule;
import libreDragon.api.Session;

/**
 * Cette interface décrit le pont entre le modèle physique et le modèle graphique.
 * Elle doit être implémentée par l'utilisateur de la librairie pour renseigner
 * aux noeuds de l'arbre la forme des équivalents graphiques leur correspondant.
 *
 *
 * @author Pacôme
 *
 */
public interface GraphicExpressionFactory {

	/**
	 * G�n�re un objet graphique.
	 * @param expression : l'expression binaire � traduire
	 * @param type : Son type
	 * @param first : l'objet graphique correspondant au premier sous noeud
	 * @param second : l'objet graphique correspondant au second sous noeud
	 * @return l'objet graphique utilisateur
	 */
	public String generateBinaryExpression(Expression expression, String type, Expression first, Expression second, String id,Session session);

	/**
	 * Génère un objet graphique.
	 * @param expression : l'expression unaire à traduire
	 * @param type : Son type
	 * @param sub : l'objet graphique correspondant au sous noeud
	 * @return l'objet graphique utilisateur
	 */
	public String generateUnaryExpression(Expression expression, String type, Expression sub, String id,Session session);

	/**
	 * Génère un objet graphique.
	 * @param expression : l'expression primaire à traduire
	 * @param type : Son type
	 * @param name : Le nom de l'instance de l'objet primaire
	 * @return l'objet graphique utilisateur
	 */
	public String generatePrimaryExpression(Expression expression, String type, String name, String id,Session session);

	public String generateSimpleBinaryExpression(Expression expression, String type, Expression first, Expression second);

	public String generateSimpleUnaryExpression(Expression expression, String type, Expression sub);

	public String generateSimplePrimaryExpression(Expression expression, String type, String name);

	/**
	 * Génère un objet graphique correspondant à une règle.
	 * @param rule : la règle à traduire
	 * @return l'objet graphique utilisateur
	 */

	public String generateRuleExpression(Rule rule);

	/**
	 * Fonction permettant à l'utilisateur d'initialiser d'éventuelles ressources.
	 * Est appellée par KrakenTree en début de vie du logiciel.
	 */

	public void init();
}
