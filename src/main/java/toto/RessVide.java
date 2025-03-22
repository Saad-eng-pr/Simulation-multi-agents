package toto;

/**
 * Représente l'état "vide" d'une ressource, hérite de la classe {@link RessourceState}.
 * Dans cet état, la ressource est vide et n'est plus disponible.
 */
public class RessVide extends RessourceState {

    /**
     * Constructeur pour initialiser l'état de la ressource comme étant vide.
     *
     * @param etat L'état initial de la ressource (doit être `false` pour indiquer qu'elle est vide).
     */
    public RessVide(boolean etat) {
        super(etat);
    }

    /**
     * Change l'état de la ressource pour indiquer qu'elle est vide.
     * Dans ce cas, l'état de la ressource est défini sur `false`.
     */
    @Override
    public void isEtat() {
        this.etat = false;
    }
}
