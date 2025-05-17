package app;

import java.util.*;

public class GraphAssignment {

    static final int INF = 99999;
    static int comparisons = 0;
    static int dataExchanges = 0;

    public static void main(String[] args) {
        int[][] W = {
                {0, 3, INF, INF, 1},
                {INF, 0, 6, INF, 3},
                {1, INF, 0, INF, INF},
                {-4, INF, 5, 0, INF},
                {INF, INF, INF, 2, 0}
        };

        System.out.println("Original Graph (Weight Matrix):");
        printMatrix(W);

        System.out.println("\nReachability Matrix using Warshall's Algorithm:");
        int[][] reach = warshall(W);
        printMatrix(reach);

        System.out.println("\nShortest Paths using Floyd-Warshall Algorithm:");
        int[][] shortest = floydWarshall(W);
        printMatrix(shortest);

        System.out.println("\nNegative Cycle Present: " + hasNegativeCycle(shortest));

        System.out.println("\nComparisons: " + comparisons);
        System.out.println("Data Exchanges: " + dataExchanges);
    }

    public static int[][] warshall(int[][] W) {
        int n = W.length;
        int[][] R = new int[n][n];

        // Initialize
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                R[i][j] = (W[i][j] != INF || i == j) ? 1 : 0;

        // Update reachability
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    comparisons++;
                    if (R[i][j] == 0 && R[i][k] == 1 && R[k][j] == 1) {
                        R[i][j] = 1;
                        dataExchanges++;
                    }
                }

        return R;
    }

    public static int[][] floydWarshall(int[][] W) {
        int n = W.length;
        int[][] D = new int[n][n];

        for (int i = 0; i < n; i++)
            D[i] = Arrays.copyOf(W[i], n);

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (D[i][k] != INF && D[k][j] != INF) {
                        comparisons++;
                        if (D[i][k] + D[k][j] < D[i][j]) {
                            D[i][j] = D[i][k] + D[k][j];
                            dataExchanges++;
                        }
                    }
                }
        return D;
    }

    public static boolean hasNegativeCycle(int[][] D) {
        for (int i = 0; i < D.length; i++)
            if (D[i][i] < 0)
                return true;
        return false;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == INF) System.out.print("∞\t");
                else System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}