package toto;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Classe de tests unitaires pour la classe Agent.
 * Les tests valident les fonctionnalités de l'Agent dans un environnement.
 */
public class AgentTest {

    private Agent agent;
    private Environnement environnement;

    /**
     * Initialisation avant chaque test.
     * Configure l'environnement et l'agent à tester.
     */
    @Before
    public void setUp() {
        environnement = Environnement.getInstance();
        environnement.generateMap(0, 0); // Génère la carte de l'environnement.
        environnement.getRessources();
        agent = new Agent(10, 10, 2);
    }

    /**
     * Test de la santé initiale de l'agent.
     * Vérifie que la santé par défaut de l'agent est bien égale à 100.
     */
    @Test
    public void testInitialHealth() {
        assertEquals(100, agent.getBarreDeSante());
        Agent agentWithCustomHealth = new Agent(5, 5, 3);
        assertEquals(100, agentWithCustomHealth.getBarreDeSante()); // La santé par défaut doit être de 100.
    }

    /**
     * Test de la méthode `setBarreDeSante` pour modifier la santé de l'agent.
     * Vérifie que la santé peut être correctement mise à jour.
     * Test également des valeurs invalides (négatives ou supérieures à la santé maximale).
     */
    @Test
    public void testSetBarreDeSante() {
        agent.setBarreDeSante(80);
        assertEquals(80, agent.getBarreDeSante()); // Vérifie que la santé est bien mise à jour à 80.
        agent.setBarreDeSante(-10); // Test d'une valeur négative pour la santé.
        assertEquals(-10, agent.getBarreDeSante()); // La santé peut être négative.
        agent.setBarreDeSante(150); // Test d'une valeur supérieure à la santé maximale.
        assertEquals(150, agent.getBarreDeSante()); // La santé peut être supérieure à la limite maximale.
    }

    /**
     * Test de la méthode `seDeplacer` pour vérifier le déplacement de l'agent.
     * Vérifie que l'agent se déplace correctement vers les coordonnées spécifiées.
     */
    @Test
    public void testSeDeplacer() {
        agent.seDeplacer(15, 20); // Déplace l'agent vers la position (15, 20).
        assertEquals(15, agent.getX()); // Vérifie que la coordonnée X est mise à jour.
        assertEquals(20, agent.getY()); // Vérifie que la coordonnée Y est mise à jour.
    }

    /**
     * Test de la méthode `randomMove` pour vérifier que l'agent perd de la santé après un déplacement aléatoire.
     * Vérifie que la santé de l'agent est réduite après le déplacement.
     */
    @Test
    public void testRandomMoveReducesHealth() {
        int initialHealth = agent.getBarreDeSante();
        agent.randomMove(environnement.map); // Déplace l'agent de manière aléatoire.
        assertEquals(initialHealth - 5, agent.getBarreDeSante()); // Vérifie que la santé a diminué de 5 points.
    }

    /**
     * Test de la méthode `randomMove` pour vérifier que l'agent reste dans les limites de la carte.
     * Vérifie que les coordonnées de l'agent sont valides après un mouvement aléatoire.
     */
    @Test
    public void testRandomMoveStaysWithinBounds() {
        agent.seDeplacer(0, 0); // Place l'agent à la position (0, 0).
        agent.randomMove(environnement.map); // Effectue un mouvement aléatoire.
        assertTrue(agent.getX() >= 0 && agent.getX() < environnement.size); // Vérifie que la coordonnée X est valide.
        assertTrue(agent.getY() >= 0 && agent.getY() < environnement.size); // Vérifie que la coordonnée Y est valide.
    }

    /**
     * Test de la méthode `scanEnvironment` pour vérifier que l'agent peut scanner l'environnement.
     * Vérifie que la méthode retourne une liste de ressources non vide.
     */
    @Test
    public void testScanEnvironment() {
        List<List<Integer>> ressources = agent.scanEnvironment(environnement.map, 10, 10, 10);
        assertNotNull("Le scan ne devrait pas retourner null", ressources); // Vérifie que le résultat n'est pas null.
        assertFalse("La liste des ressources ne doit pas être vide", ressources.isEmpty()); // Vérifie que la liste n'est pas vide.
        assertTrue(ressources.size() > 0); // Vérifie que la taille de la liste des ressources est supérieure à 0.
    }

    /**
     * Test de la méthode `isValid` pour vérifier si une position est valide dans l'environnement.
     * Vérifie que la méthode retourne la bonne valeur en fonction des coordonnées données.
     */
    @Test
    public void testIsValid() {
        assertFalse(agent.isValid(environnement.map, -1, 10)); // Vérifie que les coordonnées (-1, 10) sont invalides.
        assertTrue(agent.isValid(environnement.map, 1, 10)); // Vérifie que les coordonnées (1, 10) sont valides.
    }
}
