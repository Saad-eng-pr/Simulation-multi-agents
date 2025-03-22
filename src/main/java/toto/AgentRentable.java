package toto;

import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant un agent rentable dans un environnement.
 * Cette classe hérite de la classe {@link Agent} et implémente une logique spécifique
 * pour calculer le prochain mouvement en fonction de la rentabilité et de la barre de santé.
 */
public class AgentRentable extends Agent {

    /**
     * Constructeur pour créer un agent rentable.
     *
     * @param x La coordonnée X de l'agent dans la carte.
     * @param y La coordonnée Y de l'agent dans la carte.
     * @param inArea La zone dans laquelle l'agent évolue.
     */
    public AgentRentable(int x , int y, int inArea) {
        super(x, y, inArea);
    }

    /**
     * Récupère la stratégie utilisée par cet agent.
     * Cette méthode crée une nouvelle instance de la stratégie {@link Strategie2}.
     *
     * @return Une instance de {@link Strategie2}.
     */
    @Override
    public Strategie2 getStrategie() {
        Strategie2 strategie = new Strategie2();
        return strategie;
    }

    /**
     * Calcule le prochain mouvement de l'agent en fonction de son environnement et de la rentabilité.
     * L'agent scanne son environnement, utilise une stratégie basée sur la rentabilité et prend en compte
     * sa barre de santé pour déterminer son prochain pas. Si aucun mouvement rentable n'est trouvé,
     * un déplacement aléatoire est effectué.
     *
     * @param originalMap La carte originale représentant l'environnement de l'agent.
     * @param valeurs Un tableau d'entiers représentant des valeurs associées à chaque ressource dans l'environnement.
     * @return Une liste contenant les coordonnées du prochain pas sous forme de [x, y, direction],
     *         ou [-1, -1, -1] si aucun mouvement rentable n'est possible.
     */
    public List<Integer> calculerProchainPasRentable(char[][] originalMap, int[] valeurs) {
        List<List<Integer>> ress = scanEnvironment(originalMap, this.getScanArea(), this.getX(), this.getY());
        // Système de stratégie (p.ex., plus rentable)
        Strategie2 strategie = (Strategie2) this.getStrategie();
        List<Integer> prochainPas = strategie.PlusProfitable(ress, valeurs, this.getBarreDeSante(), originalMap);

        if (prochainPas.get(2) != -1) {
            prochainPas.set(0, this.getX() + prochainPas.get(0) - this.getScanArea());
            prochainPas.set(1, this.getY() + prochainPas.get(1) - this.getScanArea());
        } else {
            // Déplacement aléatoire si aucun mouvement rentable n'est trouvé
            this.randomMove(originalMap);
            return Arrays.asList(-1, -1, -1);
        }

        return prochainPas;
    }
}
