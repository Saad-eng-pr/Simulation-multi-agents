package toto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Représente l'environnement dans lequel les agents interagissent avec des ressources.
 * L'environnement contient des agents, des ressources et une carte de grille.
 */
public class Environnement {

    int size;
    static int etape = 0;
    List<Agent> agents;
    List<Ressource> ressources;
    int nbAgents = 12;
    int nbRessources;
    char[][] map;
    Random rand = new Random();

    private final static Environnement instance = new Environnement(50);

    // Codes d'échappement ANSI pour la coloration de la sortie console
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";
    public static final String BRIGHT_BLUE = "\033[94m";
    public static final String BRIGHT_PURPLE = "\033[95m";

    /**
     * Constructeur de l'environnement avec une taille de grille donnée.
     * Initialise les agents et les ressources.
     * @param size la taille de la grille.
     */
    public Environnement(int size) {
        this.size = size;
        this.map = new char[size][size];
        this.agents = new ArrayList<>();
        this.ressources = new ArrayList<>();

        // Initialisation des agents dans l'environnement
        for (int i = 0; i < nbAgents; i++) {
            int selector = i % 3;
            if (selector == 0) {
                AgentVorace agent1 = new AgentVorace(1, i * 3 + 4, 10);
                agents.add(agent1);
            }
            if (selector == 1) {
                AgentLocal agent2 = new AgentLocal(4 + i * 3, 1, 10);
                agents.add(agent2);
            }
            if (selector == 2) {
                AgentRentable agent3 = new AgentRentable(4 + i * 3, 48, 10);
                agents.add(agent3);
            }
        }
    }

    /**
     * Retourne l'instance unique de l'environnement.
     * @return l'instance de l'environnement.
     */
    public static Environnement getInstance() {
        return instance;
    }

    /**
     * Trouve l'index d'une ressource dans la liste des ressources en fonction de sa position (x, y).
     * @param x la coordonnée x de la ressource.
     * @param y la coordonnée y de la ressource.
     * @return l'index de la ressource dans la liste, ou -1 si la ressource n'existe pas.
     */
    public int getIndexRessource(int x, int y) {
        int index = -1;
        for (int i = 0; i < ressources.size(); i++) {
            if ((x == (ressources.get(i)).getX()) && (y == (ressources.get(i)).getY())) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Simule une étape de l'environnement. Les agents se déplacent et interagissent avec les ressources.
     */
    public void simuleretape() {
        etape++;
        List<Agent> newAgents = new ArrayList<>();
        int[] valeurs = new int[ressources.size()];
        for (int i = 0; i < ressources.size(); i++) {
            valeurs[i] = ressources.get(i).getValeur();
        }
        // Traitement de chaque agent
        for (Agent agent : agents) {
            if (agent instanceof AgentVorace) {
                newAgents.add((AgentVorace) agent);
            } else if (agent instanceof AgentLocal) {
                newAgents.add((AgentLocal) agent);
            } else if (agent instanceof AgentRentable) {
                newAgents.add((AgentRentable) agent);
            }
        }

        for (Agent agent : newAgents) {
            List<Integer> prochainPas = new ArrayList<>();
            if (agent instanceof AgentVorace) {
                prochainPas = ((AgentVorace) agent).calculerProchainPasVorace(map, valeurs);
            } else if (agent instanceof AgentLocal) {
                prochainPas = ((AgentLocal) agent).calculerProchainPasLocal(map);
            } else if (agent instanceof AgentRentable) {
                prochainPas = ((AgentRentable) agent).calculerProchainPasRentable(map, valeurs);
            }

            int add = 0;
            int damage = 0;

            // Vérifie si l'agent peut interagir avec une ressource
            if (prochainPas.get(2) != -1 && getIndexRessource(prochainPas.get(0), prochainPas.get(1)) != -1) {
                add = valeurs[getIndexRessource(prochainPas.get(0), prochainPas.get(1))];
                ressources.remove(getIndexRessource(prochainPas.get(0), prochainPas.get(1)));
            }

            // Déplace l'agent et applique les effets
            if (prochainPas.get(2) != -1) {
                agent.seDeplacer(prochainPas.get(0), prochainPas.get(1));
                damage = add - prochainPas.get(2);
                agent.setBarreDeSante(agent.getBarreDeSante() + damage);
                map[prochainPas.get(0)][prochainPas.get(1)] = '*';
            }

            // Si l'agent est mort, il est supprimé
            if (agent.getBarreDeSante() <= 0) {
                agent.tuer();
                agents.remove(agent);
            }
        }
    }

    /**
     * Génère la carte de l'environnement de manière récursive en partant d'une position de départ.
     * @param starti la coordonnée de départ pour l'axe des lignes.
     * @param startj la coordonnée de départ pour l'axe des colonnes.
     */
    public void generateMap(int starti, int startj) {
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        List<int[]> shuffledDirections = Arrays.asList(directions);
        Collections.shuffle(shuffledDirections, rand);

        for (int[] dir : shuffledDirections) {
            int newi = starti + dir[0];
            int newj = startj + dir[1];

            if (newi >= 0 && newi < size && newj >= 0 && newj < size && map[newi][newj] != '*' && map[newi][newj] != 'X') {
                int r = rand.nextInt(20);
                map[newi][newj] = '*';
                if (newi >= 3 && newi < size - 3 && newj >= 3 && newj < size - 3) {
                    if (r == 0) {
                        if (newi - 1 >= 0) {
                            map[newi - 1][newj] = 'X';
                        }
                        if (newi + 1 < size) {
                            map[newi + 1][newj] = 'X';
                        }
                    } else if (r == 1) {
                        if (newj - 1 >= 0) {
                            map[newi][newj - 1] = 'X';
                        }
                        if (newj + 1 < size) {
                            map[newi][newj + 1] = 'X';
                        }
                    }
                    if (map[newi][newj] != 'X' && isValid(newi - 1, newj) && map[newi - 1][newj] != 'X'
                            && isValid(newi + 1, newj) && map[newi + 1][newj] != 'X' && isValid(newi, newj - 1)
                            && map[newi][newj - 1] != 'X' && isValid(newi, newj + 1) && map[newi][newj + 1] != 'X') {
                        map[newi][newj] = 'O';
                    }
                }
                generateMap(newi, newj);
            }
        }
    }

    /**
     * Vérifie si une position donnée est valide dans la carte.
     * @param x la coordonnée x.
     * @param y la coordonnée y.
     * @return true si la position est valide, false sinon.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * Récupère toutes les ressources présentes sur la carte et les ajoute à la liste des ressources.
     */
    public void getRessources() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == 'O') {
                    ressources.add(new Ressource(i, j, 1, rand.nextInt(10,100)));
                }
            }
        }
    }

    /**
     * Affiche la carte de l'environnement avec les agents et les ressources.
     * @param map la carte à afficher.
     */
    public void afficherMap(char[][] map) {
        System.out.println("Etape : " + etape);
        System.out.println("Ressources restant : " + ressources.size());
        System.out.println("Agents restant : " + agents.size());

        // Affiche la barre de santé de chaque agent
        for (var agent : agents) {
            System.out.println(agent.getBarreDeSante());
        }

        // Affiche la carte
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                boolean isPrinted = false;
                // Vérifie si un agent est à cette position
                for (int agentIndex = 0; agentIndex < agents.size(); agentIndex++) {
                    if (isAgentAtPosition(i, j, agentIndex)) {
                        if (agents.get(agentIndex) instanceof AgentVorace) {
                            System.out.print(RED + "A " + RESET);}
                        else if (agents.get(agentIndex) instanceof AgentLocal) {
                            System.out.print(GREEN + "A " + RESET);}
                        else if (agents.get(agentIndex) instanceof AgentRentable) {
                            System.out.print(YELLOW + "A " + RESET);}
                        else {
                            System.out.print("A ");
                        }
                        isPrinted = true;
                        break;
                    }
                }

                if (!isPrinted) {
                    for (Ressource ressource : ressources) {
                        if (ressource.getX() == i && ressource.getY() == j) {
                            if (ressource.getValeur() < 18 + 10) {
                                System.out.print(BRIGHT_BLUE + map[i][j] + " " + RESET);}
                            else if (ressource.getValeur() < 18*2+10) {
                                System.out.print(BRIGHT_GREEN + map[i][j] + " " + RESET);}
                            else if (ressource.getValeur() < 18*3+10) {
                                System.out.print(BRIGHT_YELLOW + map[i][j] + " " + RESET);}
                            else if (ressource.getValeur() < 18*4+10) {
                                System.out.print(BRIGHT_RED + map[i][j] + " " + RESET);}
                            else if (ressource.getValeur() < 18*5+10) {
                                System.out.print(BRIGHT_PURPLE + map[i][j] + " " + RESET);}
                            else{
                                System.out.print(map[i][j] + " ");
                            }
                            isPrinted = true;
                            break;
                        }
                    }
                }

                if (!isPrinted) {
                    if (i < 3 && j < 3) {
                        System.out.print(RED + map[i][j] + " " + RESET);
                    } else if (i >= size - 3 && j < 3) {
                        System.out.print(GREEN + map[i][j] + " " + RESET);
                    } else if (i < 3 && j >= size - 3) {
                        System.out.print(YELLOW + map[i][j] + " " + RESET);
                    } else if (i >= size - 3 && j >= size - 3) {
                        System.out.print(BLUE + map[i][j] + " " + RESET);
                    } else {
                        System.out.print(map[i][j] + " ");
                    }
                }
            }
            System.out.println(); // Nouvelle ligne après chaque ligne de la carte
        }

        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Vérifie si un agent se trouve à une position donnée (x, y).
     * @param x la coordonnée x de la position.
     * @param y la coordonnée y de la position.
     * @param agentIndex l'index de l'agent à vérifier.
     * @return true si l'agent est à la position (x, y), false sinon.
     */
    private boolean isAgentAtPosition(int x, int y, int agentIndex) {
        return x == agents.get(agentIndex).getX() && y == agents.get(agentIndex).getY();
    }


    /**
     * Fonction principale pour démarrer la simulation.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        int Mortdeparesseux = 0;
        int Mortdeglouton = 0;
        int Mortdesmart = 0;
        double ressourcerestant = 0;
        double ressourcesinitale = 0;
        int simsize = 1;
        List<Integer> SanteParesseux = new ArrayList<>();
        List<Integer> SanteGlouton = new ArrayList<>();
        List<Integer> SanteSmart = new ArrayList<>();
        for (int p = 0 ; p < simsize ; p++){
            Environnement map = getInstance();
            map.map[0][0] = '*';
            map.generateMap(0, 0);
            map.getRessources();
            ressourcesinitale += map.ressources.size();
            map.nbRessources = map.ressources.size();
            while (map.agents.size() > 1) {
                map.simuleretape();
                map.afficherMap(map.map);
            }

            boolean hasParesseux = false;
            boolean hasGlouton = false;
            boolean hasSmart = false;
            for (Agent agent : map.agents) {
                if (agent instanceof AgentLocal) {
                    hasParesseux = true;
                    Mortdeparesseux++;
                    SanteParesseux.add(agent.getBarreDeSante());
                } else if (agent instanceof AgentVorace) {
                    hasGlouton = true;
                    Mortdeglouton++;
                    SanteGlouton.add(agent.getBarreDeSante());
                } else if (agent instanceof AgentRentable) {
                    hasSmart = true;
                    Mortdesmart++;
                    SanteSmart.add(agent.getBarreDeSante());
                }
            }
            if (hasParesseux && hasGlouton && hasSmart){
                ressourcerestant += map.ressources.size();}
        }
        System.out.println("Morts AgentLocals : " + Mortdeparesseux + "/" + simsize);
        System.out.println("Morts AgentVoraces : " + Mortdeglouton + "/" + simsize);
        System.out.println("Morts AgentRentables : " + Mortdesmart + "/" + simsize);
        System.out.println("Ressources restantes : " + ressourcerestant/ simsize);
    }
}
