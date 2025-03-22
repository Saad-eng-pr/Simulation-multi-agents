package toto;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Représente un agent autonome capable de se déplacer sur une carte,
 * scanner son environnement et prendre des décisions en fonction d'une stratégie.
 * L'agent dispose d'une barre de santé, d'une zone de scan et peut changer d'état.
 */
public class Agent extends Position {

    /**
     * La barre de santé de l'agent (initialisée à 100).
     */
    private int barreDeSante;

    /**
     * La zone de scan de l'agent, indiquant le rayon autour de lui qu'il peut explorer.
     */
    private final int scanArea;

    /**
     * L'état actuel de l'agent (en vie ou mort).
     */
    private AgentState Etat;

    /**
     * La stratégie utilisée par l'agent pour prendre des décisions.
     */
    protected final Strategie strategie;

    /**
     * Constructeur de la classe Agent.
     *
     * @param x       La position x initiale de l'agent.
     * @param y       La position y initiale de l'agent.
     * @param inArea  Le rayon de la zone de scan.
     */
    public Agent(int x, int y, int inArea) {
        super(x, y);
        this.barreDeSante = 100;
        this.scanArea = inArea;
        this.strategie = new Strategie1();
        this.Etat = new AgentEnVie();
        ((AgentEnVie) Etat).enVie();
    }

    /**
     * Retourne la barre de santé actuelle de l'agent.
     *
     * @return La valeur de la barre de santé.
     */
    public final int getBarreDeSante() {
        return this.barreDeSante;
    }

    /**
     * Retourne la stratégie actuelle de l'agent.
     *
     * @return L'objet stratégie utilisé par l'agent.
     */
    public Strategie getStrategie() {
        return this.strategie;
    }

    /**
     * Retourne la zone de scan de l'agent.
     *
     * @return Le rayon de la zone de scan.
     */
    public final int getScanArea() {
        return this.scanArea;
    }

    /**
     * Retourne l'état actuel de l'agent (en vie ou mort).
     *
     * @return L'objet représentant l'état de l'agent.
     */
    public AgentState getEtat() {
        return this.Etat;
    }

    /**
     * Définit l'état de l'agent comme mort.
     */
    public void tuer() {
        this.Etat = new AgentDead();
        ((AgentDead) Etat).enVie();
    }

    /**
     * Met à jour la barre de santé de l'agent.
     *
     * @param barreDeSante La nouvelle valeur de la barre de santé.
     */
    public final void setBarreDeSante(int barreDeSante) {
        this.barreDeSante = barreDeSante;
    }

    /**
     * Scanne l'environnement autour de l'agent pour détecter les ressources.
     *
     * @param originalMap La carte originale.
     * @param scanArea    Le rayon de la zone de scan.
     * @param x           La position x de l'agent.
     * @param y           La position y de l'agent.
     * @return Une liste des ressources détectées, représentées par leurs coordonnées et distances.
     */
    public List<List<Integer>> scanEnvironment(char[][] originalMap, int scanArea, int x, int y) {
        int rows = 50;
        int cols = 50;
        int diameter = scanArea * 2 + 1;

        char[][] result = new char[diameter][diameter];

        for (int i = 0; i < diameter; i++) {
            for (int j = 0; j < diameter; j++) {
                int originalX = x - scanArea + i;
                int originalY = y - scanArea + j;

                if (originalX < 0 || originalX >= rows || originalY < 0 || originalY >= cols) {
                    result[i][j] = 'X';
                } else {
                    result[i][j] = originalMap[originalX][originalY];
                }
            }
        }

        return ((Strategie1) strategie).BFS(result, scanArea);
    }

    /**
     * Déplace l'agent à une nouvelle position.
     *
     * @param x La nouvelle position x.
     * @param y La nouvelle position y.
     */
    public void seDeplacer(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Vérifie si une position donnée est valide pour le déplacement.
     *
     * @param originalMap La carte originale.
     * @param x           La position x à vérifier.
     * @param y           La position y à vérifier.
     * @return {@code true} si la position est valide, {@code false} sinon.
     */
    public boolean isValid(char[][] originalMap, int x, int y) {
        return x >= 0 && x < 50 && y >= 0 && y < 50 && originalMap[x][y] == '*';
    }

    /**
     * Déplace l'agent aléatoirement dans une direction valide.
     *
     * @param originalMap La carte originale.
     */
    public void randomMove(char[][] originalMap) {
        Random rand = new Random();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};
        Set<Integer> count = new HashSet<>();

        while (true) {
            int index = rand.nextInt(4);
            int[] d = directions[index];
            int newX = this.getX() + d[0];
            int newY = this.getY() + d[1];

            if (isValid(originalMap, newX, newY)) {
                seDeplacer(newX, newY);
                setBarreDeSante(getBarreDeSante() - 5);
                break;
            }

            count.add(index);
            if (count.size() == 4) break;
        }
    }
}
