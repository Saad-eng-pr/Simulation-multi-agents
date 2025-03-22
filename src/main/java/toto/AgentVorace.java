package toto;

import java.util.Arrays;
import java.util.List;

/**
 * Classe représentant un agent vorace dans un environnement.
 * Cette classe hérite de la classe {@link Agent} et implémente une logique spécifique
 * pour calculer le prochain mouvement en fonction de la valeur maximale des ressources.
 */
public class AgentVorace extends Agent {

    /**
     * Constructeur pour créer un agent vorace.
     *
     * @param x La coordonnée X de l'agent dans la carte.
     * @param y La coordonnée Y de l'agent dans la carte.
     * @param inArea La zone dans laquelle l'agent évolue.
     */
    public AgentVorace(int x, int y, int inArea) {
        super(x, y, inArea);
    }

    /**
     * Récupère la stratégie utilisée par cet agent.
     * Cette méthode crée une nouvelle instance de la stratégie {@link Strategie3}.
     *
     * @return Une instance de {@link Strategie3}.
     */
    @Override
    public Strategie3 getStrategie() {
        Strategie3 strategie = new Strategie3();
        return strategie;
    }

    /**
     * Calcule le prochain mouvement de l'agent en fonction des ressources les plus avantageuses.
     * L'agent scanne son environnement et utilise une stratégie vorace pour se déplacer vers
     * la ressource ayant la plus grande valeur. Si aucune ressource n'est trouvée, un déplacement aléatoire est effectué.
     *
     * @param originalMap La carte originale représentant l'environnement de l'agent.
     * @param valeurs Un tableau d'entiers représentant les valeurs associées à chaque ressource dans l'environnement.
     * @return Une liste contenant les coordonnées du prochain pas sous forme de [x, y, direction],
     *         ou [-1, -1, -1] si aucun mouvement n'est possible.
     */
    public List<Integer> calculerProchainPasVorace(char[][] originalMap, int[] valeurs) {
        List<List<Integer>> ress = scanEnvironment(originalMap, this.getScanArea(), this.getX(), this.getY());
        // Utilisation de la stratégie vorace
        Strategie3 strategie = this.getStrategie();
        List<Integer> prochainPas = strategie.plusGrandValeur(ress, valeurs);

        if (prochainPas.get(2) != -1) {
            prochainPas.set(0, this.getX() + prochainPas.get(0) - this.getScanArea());
            prochainPas.set(1, this.getY() + prochainPas.get(1) - this.getScanArea());
        } else {
            // Déplacement aléatoire si aucune ressource n'est trouvée
            this.randomMove(originalMap);
            return Arrays.asList(-1, -1, -1);
        }

        return prochainPas;
    }
}
