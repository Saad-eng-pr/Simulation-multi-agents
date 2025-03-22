package toto;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Classe de tests unitaires pour la méthode `calculerProchainPasVorace` de l'agent vorace.
 * Les tests vérifient le comportement de l'agent lorsqu'il cherche un chemin en fonction des ressources et des obstacles.
 */
public class TestVorace {

    private AgentVorace agentVorace;
    private char[][] map;

    /**
     * Initialisation avant chaque test.
     * Crée un agent vorace et initialise une carte de test.
     */
    @Before
    public void setUp() {
        agentVorace = new AgentVorace(47, 47, 5);
        map = new char[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = '*'; // Initialisation de la carte avec des cellules accessibles
            }
        }
        map[49][47] = 'O';
        map[44][47] = 'O';
    }

    /**
     * Test de la méthode `getStrategie`.
     * Vérifie que la stratégie de l'agent vorace est correctement définie.
     */
    @Test
    public void testGetStrategie() {
        assertNotNull("La stratégie ne doit pas être null", agentVorace.getStrategie());
    }

    /**
     * Test du calcul du prochain pas pour un agent vorace lorsqu'il y a des ressources disponibles sur la carte.
     * L'agent doit se déplacer vers la ressource la plus proche.
     */
    @Test
    public void testCalculerProchainPasVorace_WithResources() {
        int[] valeurs = {1, 10};
        List<Integer> nextStep = agentVorace.calculerProchainPasVorace(map, valeurs);
        assertNotNull("Next step should not be null", nextStep);
        List<Integer> expected = Arrays.asList(44, 47, 3); //resources plus lointaines, mais avec une valeur plus grande
        assertEquals(expected, nextStep);
    }

    /**
     * Test du calcul du prochain pas pour un agent vorace lorsqu'il rencontre un obstacle.
     * L'agent ne doit pas se déplacer vers une cellule d'obstacle.
     */
    @Test
    public void testCalculerProchainPasVorace_Obstacle() {
        map[46][47] = 'X'; // Mise en place d'un obstacle sur le chemin vers la ressource avec la plus grande valeur
        int[] valeurs = {0, 1};
        List<Integer> nextStep = agentVorace.calculerProchainPasVorace(map, valeurs);
        assertNotNull("Next step should not be null", nextStep);
        List<Integer> expected = Arrays.asList(44, 47, 5); //resources plus lointaines, mais avec une valeur plus grande
        assertEquals(expected, nextStep);
    }
}
