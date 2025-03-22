package toto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Interface représentant une stratégie de traitement des ressources dans un jeu ou une simulation.
 */
public interface Strategie {

    /**
     * Calcule le chemin le plus rapide parmi une liste de ressources.
     *
     * @param res La liste des ressources avec leurs informations de position et de distance.
     * @param originalMap La carte originale pour l'algorithme de calcul.
     * @return Une liste représentant les coordonnées du chemin rapide.
     */
    public List<Integer> CheminRapide(List<List<Integer>> res, char[][] originalMap);

    /**
     * Trouve la ressource la plus rentable en fonction de l'énergie et des valeurs des ressources.
     *
     * @param ress La liste des ressources avec leurs informations.
     * @param valeurs Les valeurs associées aux ressources.
     * @param energie L'énergie disponible pour le joueur.
     * @param originalMap La carte originale pour l'algorithme de calcul.
     * @return Une liste représentant la ressource la plus rentable.
     */
    public List<Integer> PlusProfitable(List<List<Integer>> ress, int[] valeurs, int energie, char[][] originalMap);

    /**
     * Trouve la ressource ayant la plus grande valeur parmi une liste de ressources.
     *
     * @param ress La liste des ressources avec leurs informations.
     * @param valeurs Les valeurs associées aux ressources.
     * @return Une liste représentant la ressource avec la plus grande valeur.
     */
    public List<Integer> plusGrandValeur(List<List<Integer>> ress, int[] valeurs);
}

/**
 * Implémentation de la stratégie 1 pour le traitement des ressources.
 * Cette stratégie trouve le chemin le plus rapide en utilisant la distance minimale.
 */
class Strategie1 implements Strategie {

    /**
     * Calcule le chemin le plus rapide en utilisant la distance minimale.
     *
     * @param res La liste des ressources avec leurs informations de position et de distance.
     * @param originalMap La carte originale pour l'algorithme de calcul.
     * @return Une liste représentant les coordonnées du chemin le plus rapide.
     */
    @Override
    public List<Integer> CheminRapide(List<List<Integer>> res, char[][] originalMap) {
        List<Integer> ans = Arrays.asList(-1, -1, -1);
        int d = Integer.MAX_VALUE;
        for (var l : res) {
            int distance = l.get(2);
            if (d > distance) {
                ans = l;
                d = distance;
            }
        }
        return ans;
    }

    /**
     * Algorithme de recherche en largeur (BFS) pour explorer la carte et trouver les ressources.
     *
     * @param map La carte à explorer.
     * @param rayon La portée autour du joueur pour l'exploration.
     * @return Une liste de ressources trouvées avec leurs coordonnées et distances.
     */
    public List<List<Integer>> BFS(char[][] map, int rayon) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] visite = new int[2 * rayon + 1][2 * rayon + 1];
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int size, r, c, pas;
        int x = rayon, y = rayon;
        int[] cur;
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y, 0 });
        visite[x][y] = 1;
        while (!q.isEmpty()) {
            size = q.size();
            for (int k = 0; k < size; k++) {
                cur = q.poll();
                for (int[] d : directions) {
                    r = cur[0] + d[0];
                    c = cur[1] + d[1];
                    pas = cur[2] + 1;
                    if (r >= 0 && r < (2 * rayon + 1) && c >= 0
                            && c < (2 * rayon + 1) && visite[r][c] == 0
                            && (map[r][c] == 'O' || map[r][c] == '*')) {
                        if (map[r][c] == 'O') {
                            List<Integer> temp = Arrays.asList(r, c, pas);
                            ans.add(temp);
                        }
                        q.offer(new int[] { r, c, pas });
                        visite[r][c] = 1;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Ne contient aucune logique pour la stratégie "Plus profitable".
     *
     * @param ress Liste des ressources.
     * @param valeurs Valeurs associées aux ressources.
     * @param energie L'énergie du joueur.
     * @param originalMap La carte originale.
     * @return Retourne null.
     */
    @Override
    public List<Integer> PlusProfitable(List<List<Integer>> ress, int[] valeurs, int energie, char[][] originalMap) {
        return null;
    }

    /**
     * Ne contient aucune logique pour la stratégie "Plus grande valeur".
     *
     * @param ress Liste des ressources.
     * @param valeurs Valeurs associées aux ressources.
     * @return Retourne null.
     */
    @Override
    public List<Integer> plusGrandValeur(List<List<Integer>> ress, int[] valeurs) {
        return null;
    }
}

/**
 * Implémentation de la stratégie 2 pour traiter les ressources.
 * Cette stratégie se concentre sur la rentabilité des ressources, en maximisant le profit.
 */
class Strategie2 implements Strategie {

    /**
     * Calcule la ressource la plus rentable en fonction de l'énergie et de la valeur de chaque ressource.
     *
     * @param ress La liste des ressources avec leurs informations.
     * @param valeurs Les valeurs associées aux ressources.
     * @param energie L'énergie disponible pour le joueur.
     * @param originalMap La carte originale pour l'algorithme de calcul.
     * @return La ressource la plus rentable.
     */
    @Override
    public List<Integer> PlusProfitable(List<List<Integer>> ress, int[] valeurs, int energie, char[][] originalMap) {
        List<Integer> ans = Arrays.asList(-1, -1, -1);
        int maxProfit = Integer.MIN_VALUE;
        int profit;
        for (int i = 0; i < ress.size(); i++) {
            profit = energie + valeurs[i] - ress.get(i).get(2);
            if ((profit > maxProfit) && (energie - ress.get(i).get(2)) >= 0) {
                ans = ress.get(i);
                maxProfit = profit;
            }
        }
        return ans;
    }

    /**
     * Ne contient aucune logique pour la stratégie "Chemin rapide".
     *
     * @param res Liste des ressources.
     * @param originalMap La carte originale.
     * @return Retourne null.
     */
    @Override
    public List<Integer> CheminRapide(List<List<Integer>> res, char[][] originalMap) {
        return null;
    }

    /**
     * Ne contient aucune logique pour la stratégie "Plus grande valeur".
     *
     * @param ress Liste des ressources.
     * @param valeurs Valeurs associées aux ressources.
     * @return Retourne null.
     */
    @Override
    public List<Integer> plusGrandValeur(List<List<Integer>> ress, int[] valeurs) {
        return null;
    }
}

/**
 * Implémentation de la stratégie 3 pour traiter les ressources.
 * Cette stratégie se concentre sur la recherche de la ressource ayant la plus grande valeur.
 */
class Strategie3 implements Strategie {

    /**
     * Trouve la ressource ayant la plus grande valeur parmi une liste donnée.
     *
     * @param ress La liste des ressources avec leurs informations.
     * @param valeurs Les valeurs associées aux ressources.
     * @return La ressource avec la plus grande valeur.
     */
    @Override
    public List<Integer> plusGrandValeur(List<List<Integer>> ress, int[] valeurs) {
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ress.size(); i++) {
            if (valeurs[i] > max) {
                index = i;
                max = valeurs[i];
            }
        }
        if (index == -1)
            return Arrays.asList(-1, -1, -1);
        return ress.get(index);
    }

    /**
     * Ne contient aucune logique pour la stratégie "Chemin rapide".
     *
     * @param res Liste des ressources.
     * @param originalMap La carte originale.
     * @return Retourne null.
     */
    @Override
    public List<Integer> CheminRapide(List<List<Integer>> res, char[][] originalMap) {
        return null;
    }

    /**
     * Ne contient aucune logique pour la stratégie "Plus profitable".
     *
     * @param ress Liste des ressources.
     * @param valeurs Valeurs associées aux ressources.
     * @param energie L'énergie du joueur.
     * @param originalMap La carte originale.
     * @return Retourne null.
     */
    @Override
    public List<Integer> PlusProfitable(List<List<Integer>> ress, int[] valeurs, int energie, char[][] originalMap) {
        return null;
    }
}
