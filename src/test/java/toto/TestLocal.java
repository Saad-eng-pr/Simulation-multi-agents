package toto;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Classe de tests unitaires pour la méthode `calculerProchainPasLocal` de l'agent local.
 * Les tests vérifient le comportement de l'agent lorsqu'il cherche un chemin en fonction de la présence de ressources et d'obstacles.
 */
public class TestLocal {

    private AgentLocal agentLocal;
    private char[][] map;

    /**
     * Initialisation avant chaque test.
     * Crée un agent local et initialise une carte de test.
     */
    @Before
    public void setUp() {
        agentLocal = new AgentLocal(47, 47, 5);
        agentLocal.setBarreDeSante(100); // Définir la barre de santé de l'agent
        map = new char[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = '*'; // Initialisation de la carte avec des cellules accessibles
            }
        }
        map[44][47] = 'O';
        map[49][47] = 'O';
        map[48][48] = 'X';
    }

    /**
     * Test de la méthode `getStrategie`.
     * Vérifie que la stratégie de l'agent rentable est correctement définie.
     */
    @Test
    public void testGetStrategie() {
        assertNotNull("La stratégie ne doit pas être null", agentLocal.getStrategie());
    }

    /**
     * Test du calcul du prochain pas lorsque des ressources sont présentes sur la carte.
     * L'agent doit se diriger vers la ressource la plus proche.
     */
    @Test
    public void testCalculerProchainPasLocal_WithResources() {
        List<Integer> nextStep = agentLocal.calculerProchainPasLocal(map);
        assertNotNull("Le prochain pas ne doit pas être null", nextStep);
        List<Integer> expected = Arrays.asList(49,47,2); //ressource la plus proche de l'agent local
        assertEquals(expected, nextStep);
    }

    /**
     * Test du calcul du prochain pas lorsque l'agent rencontre un obstacle.
     * L'agent ne doit pas se déplacer vers une cellule d'obstacle.
     */
    @Test
    public void testCalculerProchainPasLocal_Obstacle() {
        map[48][47] = 'X'; // Mise en place d'un obstacle sur le chemin de la ressource qui etait la plus proche
        List<Integer> nextStep = agentLocal.calculerProchainPasLocal(map);
        assertNotNull("Le prochain pas ne doit pas être null", nextStep);
        List<Integer> expected = Arrays.asList(44,47,3);
        assertEquals(expected, nextStep);
    }

    /**
     * Test du calcul du prochain pas lorsque aucune ressource n'est trouvée.
     * L'agent doit retourner -1 pour indiquer qu'aucune ressource n'est disponible.
     */
    @Test
    public void testCalculerProchainPasLocal_NoResources() {
        map[44][47] = '*';
        map[49][47] = '*';
        int[] valeurs = new int[0];
        List<Integer> nextStep = agentLocal.calculerProchainPasLocal(map);
        List<Integer> expected = Arrays.asList(-1, -1, -1);
        assertNotNull("Next step should not be null", nextStep);
        assertEquals(expected, nextStep);
        map[49][49] = 'O';
        map[49][48] = 'O';

    }
}
