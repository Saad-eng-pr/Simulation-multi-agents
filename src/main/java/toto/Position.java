package toto;

/**
 * Représente une position dans un environnement, définie par des coordonnées x et y.
 * Cette classe permet de gérer les positions de manière générique.
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructeur pour créer une position avec des coordonnées spécifiées.
     *
     * @param x La coordonnée x de la position.
     * @param y La coordonnée y de la position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtient la coordonnée x de la position.
     *
     * @return La coordonnée x de la position.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée y de la position.
     *
     * @return La coordonnée y de la position.
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée x de la position.
     *
     * @param x La coordonnée x à attribuer à la position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Définit la coordonnée y de la position.
     *
     * @param y La coordonnée y à attribuer à la position.
     */
    public void setY(int y) {
        this.y = y;
    }
}
