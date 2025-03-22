package toto;

/**
 * Représente l'état "plein" d'une ressource, hérite de la classe {@link RessourceState}.
 * Dans cet état, la ressource est pleine et disponible.
 */
public class RessPlein extends RessourceState {

    /**
     * Constructeur pour initialiser l'état de la ressource comme étant plein.
     *
     * @param etat L'état initial de la ressource (doit être `true` pour indiquer qu'elle est pleine).
     */
    public RessPlein(boolean etat) {
        super(etat);
    }

    /**
     * Change l'état de la ressource pour indiquer qu'elle est pleine.
     * Dans ce cas, l'état de la ressource est défini sur `true`.
     */
    @Override
    public void isEtat() {
        this.etat = true;
    }
}
