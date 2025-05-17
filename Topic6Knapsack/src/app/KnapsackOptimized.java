package app;

import java.util.*;

public class KnapsackOptimized {

    static int greedySteps = 0;
    static int dpSteps = 0;

    public static void main(String[] args) {
        int[] weights = {20, 30, 40, 60, 70, 90};
        int[] values = {70, 80, 90, 110, 120, 200};
        int[] quantities = {1, 2, 1, 3, 1, 2};
        int capacity = 280;

        System.out.println("=== Knapsack: Single Instance per Item ===");
        runScenario(weights, values, capacity);

        System.out.println("\n=== Knapsack: Multiple Instances per Item ===");
        int[] expandedWeights = expandItems(weights, quantities);
        int[] expandedValues = expandItems(values, quantities);
        runScenario(expandedWeights, expandedValues, capacity);
    }

    private static void runScenario(int[] weights, int[] values, int capacity) {
        greedySteps = 0;
        dpSteps = 0;

        int greedyResult = greedyKnapsack(weights, values, capacity);
        int dpResult = dynamicKnapsack(weights, values, capacity);

        System.out.println("Greedy Result: " + greedyResult + " | Steps: " + greedySteps);
        System.out.println("Dynamic Programming Result: " + dpResult + " | Steps: " + dpSteps);
    }

    public static int greedyKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
            greedySteps++;
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        greedySteps += n * (int) (Math.log(n) / Math.log(2)); // Approx. for sort

        int value = 0, weight = 0;
        for (Item item : items) {
            greedySteps++;
            if (weight + item.weight <= capacity) {
                weight += item.weight;
                value += item.value;
            }
        }
        return value;
    }

    public static int dynamicKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dpSteps++;
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            values[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    private static int[] expandItems(int[] base, int[] counts) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                list.add(base[i]);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    static class Item {
        int weight, value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }
}