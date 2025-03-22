package toto;

/**
 * Représente un obstacle dans l'environnement, héritant de la classe Position.
 * Un obstacle est défini par sa position (x, y) et un poids associé.
 */
public class Obstacle extends Position {

    private int poids;

    /**
     * Constructeur pour créer un obstacle à une position donnée avec un poids spécifié.
     *
     * @param id L'identifiant unique de l'obstacle (non utilisé dans cette classe mais hérité de la classe Position).
     * @param x La coordonnée x de l'obstacle.
     * @param y La coordonnée y de l'obstacle.
     * @param poids Le poids de l'obstacle, utilisé pour déterminer son impact dans l'environnement.
     */
    public Obstacle(int id, int x, int y, int poids) {
        super(x, y);
        this.poids = poids;
    }

    /**
     * Obtient le poids de l'obstacle.
     *
     * @return Le poids de l'obstacle.
     */
    public int getPoids() {
        return poids;
    }

    /**
     * Définit le poids de l'obstacle.
     *
     * @param poids Le poids à attribuer à l'obstacle.
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }
}
