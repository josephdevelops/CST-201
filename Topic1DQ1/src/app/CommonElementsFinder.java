package app;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsFinder {


    public static List<Integer> findCommonElements(int[] list1, int[] list2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        int comparisons = 0; // Counter for comparisons (for analysis purposes)

        // Traverse both arrays until one pointer reaches the end.
        while (i < list1.length && j < list2.length) {
            comparisons++; // A comparison is made in each iteration.
            if (list1[i] == list2[j]) {
                // Found a common element.
                result.add(list1[i]);
                i++;
                j++;
            } else if (list1[i] < list2[j]) {
                // Move pointer in list1 forward.
                i++;
            } else {
                // Move pointer in list2 forward.
                j++;
            }
        }
        System.out.println("Total comparisons (basic approach): " + comparisons);
        return result;
    }


    public static List<Integer> findCommonElementsWithSkip(int[] list1, int[] list2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        int comparisons = 0; // Counter for comparisons

        while (i < list1.length && j < list2.length) {
            comparisons++; // Counting the comparison at the start of the loop.
            if (list1[i] == list2[j]) {
                int common = list1[i];
                
                // Count duplicates in list1.
                int count1 = 0;
                while (i < list1.length && list1[i] == common) {
                    count1++;
                    i++;
                }
                
                // Count duplicates in list2.
                int count2 = 0;
                while (j < list2.length && list2[j] == common) {
                    count2++;
                    j++;
                }
                
                // Add the common element the minimum number of times it appears in both lists.
                int occurrences = Math.min(count1, count2);
                for (int k = 0; k < occurrences; k++) {
                    result.add(common);
                }
            } else if (list1[i] < list2[j]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println("Total comparisons (duplicate skipping): " + comparisons);
        return result;
    }

    public static void main(String[] args) {
        // Example input arrays (sorted lists).
        int[] list1 = {2, 5, 5, 5};
        int[] list2 = {2, 2, 3, 5, 5, 7};

        // Using the basic two-pointer approach.
        System.out.println("Using basic two-pointer approach:");
        List<Integer> commonElements = findCommonElements(list1, list2);
        System.out.println("Common elements: " + commonElements);

        // Using the duplicate skipping optimization.
        System.out.println("\nUsing two-pointer approach with duplicate skipping:");
        List<Integer> commonElementsWithSkip = findCommonElementsWithSkip(list1, list2);
        System.out.println("Common elements: " + commonElementsWithSkip);
}
}