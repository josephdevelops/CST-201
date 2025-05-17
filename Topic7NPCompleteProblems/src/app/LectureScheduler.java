package app;

import java.util.*;

public class LectureScheduler {

    static final int SIZE = 7;

    public static void main(String[] args) {
        String input = "0110100101101011010110110011100001101111000011100";
        int[][] matrix = parseMatrix(input);

        System.out.println("Adjacency Matrix:");
        printMatrix(matrix);

        int minColors = graphColoring(matrix);
        int conflictEdges = countConflicts(matrix);

        System.out.println("\nMinimum number of time slots (colors): " + minColors);
        System.out.println("Minimum number of unique student conflicts (edges): " + conflictEdges);
    }

    // Parse 1D string into 2D adjacency matrix
    public static int[][] parseMatrix(String input) {
        int[][] matrix = new int[SIZE][SIZE];
        int index = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                matrix[i][j] = input.charAt(index++) - '0';
        return matrix;
    }

    // Greedy Graph Coloring Algorithm
    public static int graphColoring(int[][] graph) {
        int[] result = new int[SIZE];
        Arrays.fill(result, -1);
        result[0] = 0;

        boolean[] available = new boolean[SIZE];

        for (int u = 1; u < SIZE; u++) {
            Arrays.fill(available, true);
            for (int i = 0; i < SIZE; i++)
                if (graph[u][i] == 1 && result[i] != -1)
                    available[result[i]] = false;

            for (int color = 0; color < SIZE; color++) {
                if (available[color]) {
                    result[u] = color;
                    break;
                }
            }
        }

        int maxColor = 0;
        for (int color : result) {
            if (color > maxColor) maxColor = color;
        }

        return maxColor + 1;
    }

    // Count total unique edges (conflicts)
    public static int countConflicts(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = i + 1; j < SIZE; j++)
                if (matrix[i][j] == 1)
                    count++;
        return count;
    }

    // Print adjacency matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}