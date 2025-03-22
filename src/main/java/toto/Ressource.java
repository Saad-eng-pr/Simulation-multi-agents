package toto;

/**
 * Représente une ressource dans l'environnement, qui possède une position (x, y),
 * un poids et une valeur. L'état de la ressource peut aussi être défini.
 */
public class Ressource extends Position {

    private int poids; // jamais exploité
    private int valeur;
    private RessourceState etat;

    /**
     * Constructeur pour créer une ressource avec une position, un poids et une valeur spécifiés.
     *
     * @param x La coordonnée x de la ressource.
     * @param y La coordonnée y de la ressource.
     * @param poids Le poids de la ressource (non exploité dans cette version).
     * @param valeur La valeur de la ressource.
     */
    public Ressource(int x, int y, int poids, int valeur) {
        super(x, y);
        this.poids = poids;
        this.valeur = valeur;
    }

    /**
     * Obtient le poids de la ressource.
     *
     * @return Le poids de la ressource.
     */
    public int getPoids() {
        return poids;
    }

    /**
     * Définit le poids de la ressource.
     *
     * @param poids Le poids à attribuer à la ressource.
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }

    /**
     * Obtient la valeur de la ressource.
     *
     * @return La valeur de la ressource.
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur de la ressource.
     *
     * @param valeur La valeur à attribuer à la ressource.
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Obtient l'état actuel de la ressource.
     *
     * @return L'état de la ressource.
     */
    public RessourceState getEtat() {
        return etat;
    }

    /**
     * Définit l'état de la ressource.
     *
     * @param etat L'état à attribuer à la ressource.
     */
    public void setEtat(RessourceState etat) {
        this.etat = etat;
    }
}
