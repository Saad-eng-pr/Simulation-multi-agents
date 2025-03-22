package toto;

import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant un agent local dans un environnement.
 * Cette classe hérite de la classe {@link Agent} et ajoute une logique spécifique
 * pour calculer le prochain mouvement en fonction d'une stratégie locale.
 */
public class AgentLocal extends Agent {

    /**
     * Constructeur pour créer un agent local.
     *
     * @param x La coordonnée X de l'agent dans la carte.
     * @param y La coordonnée Y de l'agent dans la carte.
     * @param inArea La zone dans laquelle l'agent évolue.
     */
    public AgentLocal(int x, int y, int inArea) {
        super(x, y, inArea);
    }

    /**
     * Calcule le prochain mouvement de l'agent en fonction de son environnement local.
     * L'agent scanne son environnement et utilise une stratégie pour déterminer son prochain pas.
     * Si un chemin est trouvé, l'agent se déplace vers ce chemin. Sinon, un déplacement aléatoire est effectué.
     *
     * @param originalMap La carte originale représentant l'environnement de l'agent.
     * @return Une liste contenant les coordonnées du prochain pas sous forme de [x, y, direction],
     *         ou [-1, -1, -1] si aucun mouvement n'est possible.
     */
    public List<Integer> calculerProchainPasLocal(char[][] originalMap) {
        List<List<Integer>> ress = scanEnvironment(originalMap, this.getScanArea(), this.getX(), this.getY());
        // Système de stratégie (p.ex., stratégie rapide)
        Strategie1 strategie = (Strategie1) this.getStrategie();
        List<Integer> prochainPas = strategie.CheminRapide(ress, originalMap);

        if (prochainPas.get(2) != -1) {
            prochainPas.set(0, this.getX() + prochainPas.get(0) - this.getScanArea());
            prochainPas.set(1, this.getY() + prochainPas.get(1) - this.getScanArea());
        } else {
            // Déplacement aléatoire si aucun mouvement n'est possible
            this.randomMove(originalMap);
            return Arrays.asList(-1, -1, -1);
        }

        return prochainPas;
    }
}
