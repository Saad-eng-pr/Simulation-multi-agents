package toto;

/**
 * Représente l'état d'une ressource. L'état est défini par une valeur booléenne.
 */
public class RessourceState {

    protected boolean etat;

    /**
     * Constructeur pour initialiser l'état de la ressource.
     *
     * @param etat L'état de la ressource, représenté par une valeur booléenne.
     */
    public RessourceState(boolean etat) {
        this.etat = etat;
    }

    /**
     * Méthode qui permet de vérifier l'état de la ressource. (Cette méthode est vide dans l'exemple actuel)
     */
    public void isEtat() {
    }
}
