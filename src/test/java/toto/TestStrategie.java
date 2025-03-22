package toto;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestStrategie {

    @Test
    public void BFSTest() {
        char[][] map = {
                { '*', '*', 'X', 'O', 'X', '*', 'X' },
                { 'X', '*', '*', '*', 'O', 'X', '*' },
                { 'O', 'X', 'O', '*', 'X', 'O', '*' },
                { 'X', 'X', 'O', 'A', '*', 'O', 'X' },
                { '*', '*', '*', '*', '*', '*', '*' },
                { '*', 'O', '*', 'X', '*', '*', 'X' },
                { '*', '*', 'X', '0', 'X', '*', 'O' }
        };

        Strategie1 bfs = new Strategie1();
        List<List<Integer>> bfsResult = bfs.BFS(map, 3);

        List<List<Integer>> expectedResult = Arrays.asList(
                Arrays.asList(3, 2, 1),
                Arrays.asList(3, 5, 2),
                Arrays.asList(2, 2, 2),
                Arrays.asList(2, 5, 3),
                Arrays.asList(1, 4, 3),
                Arrays.asList(0, 3, 3),
                Arrays.asList(5, 1, 4),
                Arrays.asList(6, 6, 6)
        );
        assertEquals(expectedResult, bfsResult);
    }

    @Test
    public void PlusCourtCheminTest1() {
        AgentLocal agent = new AgentLocal(3, 3, 3);
        char[][] map = {
                { '*', '*', 'X', 'O', 'X', '*', 'X' },
                { 'X', '*', '*', '*', 'O', 'X', '*' },
                { 'O', 'X', 'O', '*', 'X', 'O', '*' },
                { 'X', 'X', 'O', 'A', '*', 'O', 'X' },
                { '*', '*', '*', '*', '*', '*', '*' },
                { '*', '*', '*', '*', '*', '*', '*' },
                { '*', '*', '*', '*', '*', '*', '*' }
        };

        List<Integer> shortestPath = agent.getStrategie()
                        .CheminRapide(((Strategie1) agent.getStrategie()).BFS(map, 3), map);
                
        assertEquals(Arrays.asList(3, 2, 1), shortestPath);
    }
    
    @Test
    public void PlusCourtCheminTest2() {
        
        AgentLocal agent = new AgentLocal(2, 2, 2);

        char[][] map = {
                { 'X', '*', '*', 'O', 'X', },
                { 'X', 'O', '*', '*', '*', },
                { '*', 'X', 'A', 'X', 'O', },
                { '*', '*', '*', '*', '*', },
                { 'X', 'O', 'X', '*', 'O', }
        };

        List<List<Integer>> bfsResult = ((Strategie1) agent.getStrategie()).BFS(map, 2);
        List<Integer> shortestPath = agent.getStrategie().CheminRapide(bfsResult, map);

        assertEquals(Arrays.asList(1, 1, 2), shortestPath);
    }

    @Test
    public void PlusRentableTest1() {
        AgentRentable agent = new AgentRentable(1, 1, 1);

        char[][] map = {
                { 'X', '*', '*' },
                { 'X', 'A', '*' },
                { '*', 'X', '*' }
        };

        Strategie1 bfs = new Strategie1();
        List<List<Integer>> bfsResult = bfs.BFS(map, 1);

        int[] valeur = new int[0];
        List<Integer> shortestPath = agent.getStrategie().PlusProfitable(bfsResult, valeur, agent.getBarreDeSante(), map);

        assertEquals(Arrays.asList(-1, -1, -1), shortestPath);
    }

    @Test
    public void PlusRentableTest2() {
        AgentRentable agent = new AgentRentable(2, 2, 2);
        agent.setBarreDeSante(3);

        char[][] map = {
                { 'X', '*', '*', 'O', 'X', },
                { 'X', 'O', '*', '*', '*', },
                { '*', 'X', 'A', 'X', 'O', },
                { '*', '*', '*', '*', '*', },
                { 'X', 'O', 'X', '*', 'X', }
        };

        Strategie1 bfs = new Strategie1();
        List<List<Integer>> bfsResult = bfs.BFS(map, 2);

        int[] valeur = { 1, 6, 6, 20 };
        List<Integer> shortestPath = agent.getStrategie().PlusProfitable(bfsResult, valeur, agent.getBarreDeSante(), map);

        assertEquals(Arrays.asList(4, 1, 3), shortestPath);
    }

    @Test
    public void PlusProfitableTest1() {
        AgentVorace agent = new AgentVorace(2, 2, 2);

        char[][] map = {
                { 'X', '*', '*', 'O', 'X', },
                { 'O', 'X', '*', '*', '*', },
                { 'X', 'X', 'A', 'X', '*', },
                { '*', '*', 'O', '*', '*', },
                { 'O', '*', 'X', '*', 'X', }
        };

        Strategie1 bfs = new Strategie1();
        List<List<Integer>> bfsResult = bfs.BFS(map, 2);

        int[] valeur = { 1, 3, 10 };
        List<Integer> shortestPath = agent.getStrategie().plusGrandValeur(bfsResult, valeur);

        assertEquals(Arrays.asList(4, 0, 4), shortestPath);
    }

    @Test
    public void PlusProfitableTest2() {
        AgentVorace agent = new AgentVorace(2, 2, 2);
        agent.setBarreDeSante(1);

        char[][] map = {
                { 'X', '*', '*', 'O', 'X', },
                { 'O', 'X', '*', '*', '*', },
                { 'X', 'X', 'A', 'X', '*', },
                { '*', '*', 'O', '*', '*', },
                { 'X', '*', 'X', '*', 'X', }
        };

        Strategie1 bfs = new Strategie1();
        List<List<Integer>> bfsResult = bfs.BFS(map, 2);

        int[] valeur = { 1, 6 };
        List<Integer> shortestPath = agent.getStrategie().plusGrandValeur(bfsResult, valeur);

        assertEquals(Arrays.asList(0, 3, 3), shortestPath);
    }
}
