import java.util.*;

public class TSPBranchAndBound {

    static int[][] graph;
    static int numCities;
    static boolean[] visited;
    static int minCost = Integer.MAX_VALUE;
    static List<Integer> minPath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        numCities = scanner.nextInt();

        graph = new int[numCities][numCities];
        visited = new boolean[numCities];
        minPath = new ArrayList<>();

        System.out.println("Enter the distances between cities (0 for no connection):");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        tspBranchAndBound(0, 0, new ArrayList<>());

        System.out.println("Minimum cost: " + minCost);
        System.out.println("Path: " + minPath);

        scanner.close();
    }

    static void tspBranchAndBound(int city, int cost, List<Integer> path) {
        if (path.size() == numCities) {
            if (graph[city][0] > 0) { // If there's a path back to the starting city
                cost += graph[city][0]; // Add the cost of returning to the starting city
                if (cost < minCost) { // Update minimum cost and path if necessary
                    minCost = cost;
                    minPath = new ArrayList<>(path);
                    minPath.add(0); // Add the starting city to complete the path
                }
            }
            return;
        }

        for (int nextCity = 0; nextCity < numCities; nextCity++) {
            if (!visited[nextCity] && graph[city][nextCity] > 0) {
                visited[nextCity] = true;
                path.add(nextCity);
                tspBranchAndBound(nextCity, cost + graph[city][nextCity], path);
                visited[nextCity] = false;
                path.removeLast();
            }
        }
    }
}