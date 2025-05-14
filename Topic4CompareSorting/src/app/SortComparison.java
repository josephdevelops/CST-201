package app;

import java.util.*;

public class SortComparison {

    static class SortStats {
        long comparisons = 0;
        long swaps = 0;

        void reset() {
            comparisons = 0;
            swaps = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size (n): ");
        int n = scanner.nextInt();
        scanner.close();

        final int trials = 100;

        SortStats selectionStats = new SortStats();
        SortStats bubbleStats = new SortStats();
        SortStats mergeStats = new SortStats();
        SortStats quickStats = new SortStats();
        SortStats javaStats = new SortStats();

        for (int t = 0; t < trials; t++) {
            int[] base = generateShuffledArray(n);

            selectionSort(base.clone(), selectionStats);
            bubbleSort(base.clone(), bubbleStats);
            mergeSort(base.clone(), mergeStats);
            quickSort(base.clone(), quickStats);
            javaSort(base.clone(), javaStats);
        }

        System.out.println("\nAverage over " + trials + " trials:");
        printStats("Selection Sort", selectionStats, trials);
        printStats("Bubble Sort", bubbleStats, trials);
        printStats("Merge Sort", mergeStats, trials);
        printStats("Quick Sort", quickStats, trials);
        printStats("Java Built-in Sort", javaStats, trials);
    }

    // Generate shuffled array
    public static int[] generateShuffledArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        Collections.shuffle(Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)));
        return arr;
    }

    // Selection Sort
    public static void selectionSort(int[] arr, SortStats stats) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                stats.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx, stats);
        }
    }

    // Bubble Sort
    public static void bubbleSort(int[] arr, SortStats stats) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                stats.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1, stats);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr, SortStats stats) {
        mergeSortHelper(arr, 0, arr.length - 1, stats);
    }

    private static void mergeSortHelper(int[] arr, int left, int right, SortStats stats) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(arr, left, mid, stats);
            mergeSortHelper(arr, mid + 1, right, stats);
            merge(arr, left, mid, right, stats);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, SortStats stats) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            stats.comparisons++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                stats.swaps++; // Count as data movement
            }
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }

    // Quick Sort
    public static void quickSort(int[] arr, SortStats stats) {
        quickSortHelper(arr, 0, arr.length - 1, stats);
    }

    private static void quickSortHelper(int[] arr, int low, int high, SortStats stats) {
        if (low < high) {
            int pi = partition(arr, low, high, stats);
            quickSortHelper(arr, low, pi - 1, stats);
            quickSortHelper(arr, pi + 1, high, stats);
        }
    }

    private static int partition(int[] arr, int low, int high, SortStats stats) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            stats.comparisons++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j, stats);
            }
        }
        swap(arr, i + 1, high, stats);
        return i + 1;
    }

    // Java Built-in Sort
    public static void javaSort(int[] arr, SortStats stats) {
        Arrays.sort(arr); // No internal comparison tracking, but considered optimized
        stats.comparisons += (int)(arr.length * Math.log(arr.length)); // Rough estimate
        stats.swaps += arr.length; // Estimate for movement
    }

    // Swap and count
    public static void swap(int[] arr, int i, int j, SortStats stats) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            stats.swaps++;
        }
    }

    // Print formatted stats
    public static void printStats(String name, SortStats stats, int trials) {
        System.out.printf("%-20s | Avg Comparisons: %-10d | Avg Swaps: %-10d%n",
                name, stats.comparisons / trials, stats.swaps / trials);
    }
}