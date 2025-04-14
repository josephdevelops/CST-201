package app;

import java.util.Scanner;

public class TowerOfHanoiMoves {

    // Function to compute 2^(n - i)
    public static int movesForDisk(int n, int i) {
        return (int) Math.pow(2, n - i);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for number of disks (n)
        System.out.print("Enter the total number of disks (n): ");
        int n = scanner.nextInt();

        // Prompt user for ith largest disk
        System.out.print("Enter the disk position (i) from smallest to largest (1 = smallest): ");
        int i = scanner.nextInt();

        // Convert i to ith largest (since 1 = smallest)
        int ithLargest = n - i + 1;

        // Validate input
        if (i < 1 || i > n) {
            System.out.println("Invalid input. Make sure 1 <= i <= n.");
        } else {
            int moves = movesForDisk(n, ithLargest);
            System.out.println("The " + i + "th smallest disk (disk " + ithLargest + " from largest) makes " + moves + " move(s).");
        }

        scanner.close();
    }
}
