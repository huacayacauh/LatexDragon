package libreDragon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Cette classe implémente la requête permettant au client de demander
 * la liste de toutes les formules jouables du jeu.
 * @author malo
 *
 */
@Path("/formula")
public class FormulaList {
/**
 * Cette fonction appele la classe Reponse pour générer un objet JSON
 * contenant toutes les formules jouables du jeu.
 * @return
 */
	@GET
	@Produces()
	public String answer () {
    Reponse reponse = new Reponse();
		return reponse.expressionList();
	}
}
