package toto;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests unitaires pour la méthode `calculerProchainPasRentable` de l'agent rentable.
 * Les tests vérifient le comportement de l'agent lorsqu'il cherche un chemin en fonction des ressources,
 * des obstacles, et de l'impact de sa santé sur ses déplacements.
 */
public class TestRentable {

    private AgentRentable agentRentable;
    private char[][] map;

    /**
     * Initialisation avant chaque test.
     * Crée un agent rentable et initialise une carte de test avec des ressources et des obstacles.
     */
    @Before
    public void setUp() {
        agentRentable = new AgentRentable(47, 47, 5);
        agentRentable.setBarreDeSante(100); // Définir la barre de santé de l'agent
        map = new char[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = '*'; // Initialisation de la carte avec des cellules accessibles
            }
        }
        map[49][49] = 'O';
        map[49][48] = 'O';
        map[48][48] = 'X';
    }

    /**
     * Test de la méthode `getStrategie`.
     * Vérifie que la stratégie de l'agent rentable est correctement définie.
     */
    @Test
    public void testGetStrategie() {
        assertNotNull("La stratégie ne doit pas être null", agentRentable.getStrategie());
    }

    /**
     * Test du calcul du prochain pas pour un agent rentable lorsqu'il y a des ressources disponibles.
     * L'agent doit se déplacer vers la ressource la plus proche.
     */
    @Test
    public void testCalculerProchainPasRentable_WithResources() {
        int[] valeurs = {1, 10};
        List<Integer> nextStep = agentRentable.calculerProchainPasRentable(map, valeurs);
        assertNotNull("Next step should not be null", nextStep);
        List<Integer> expected = Arrays.asList(49, 49, 4);
        assertEquals(expected, nextStep);
    }

    /**
     * Test du calcul du prochain pas pour un agent rentable lorsqu'il rencontre un obstacle.
     * L'agent ne doit pas se déplacer vers une cellule d'obstacle.
     */
    @Test
    public void testCalculerProchainPasRentable_Obstacle() {
        map[48][49] = 'X';
        map[49][47] = 'X';// Mise en place d'un obstacle sur le chemin
        int[] valeurs = {1, 10};
        List<Integer> nextStep = agentRentable.calculerProchainPasRentable(map, valeurs);
        List<Integer> expected = Arrays.asList(-1, -1, -1);
        assertNotNull("Next step should not be null", nextStep);
        assertEquals(expected, nextStep);
        map[49][47] = '*';
        map[48][49] = '*';

    }

    /**
     * Test du calcul du prochain pas pour un agent rentable lorsqu'il n'y a plus de ressources disponibles.
     * L'agent doit effectuer un déplacement aléatoire si aucune ressource n'est présente.
     */
    @Test
    public void testCalculerProchainPasRentable_NoResources() {
        map[49][49] = '*'; 
        map[49][48] = '*';
        int[] valeurs = new int[0];
        List<Integer> nextStep = agentRentable.calculerProchainPasRentable(map, valeurs);
        List<Integer> expected = Arrays.asList(-1, -1, -1);
        assertNotNull("Next step should not be null", nextStep);
        assertEquals(expected, nextStep);
        map[49][49] = 'O'; 
        map[49][48] = 'O';

    }

    /*
     * Test du calcul du prochain pas pour un agent rentable lorsqu'il doit prioriser les ressources de santé.
     * Ce test est actuellement commenté, mais il montre le comportement attendu de l'agent lorsqu'il a une faible santé.
     *
     */
     @Test
     public void testCalculerProchainPasRentable_HealthImpact() {
        agentRentable.setBarreDeSante(3); // Définir une faible santé
        int[] valeurs = {1, 10};
        List<Integer> nextStep = agentRentable.calculerProchainPasRentable(map, valeurs);
        assertNotNull("Le prochain pas ne doit pas être null", nextStep);
        List<Integer> expected = Arrays.asList(49, 48, 3);
         assertEquals(expected, nextStep);
     }

}
