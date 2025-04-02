package app;

import java.util.Scanner;

public class AnagramChecker {

    /**
     * Checks whether two words are anagrams.
     * Converts both words to lowercase (and removes spaces),
     * then uses a frequency array (for letters 'a' to 'z') to compare letter counts.
     *
     * @param word1 The first word.
     * @param word2 The second word.
     * @return true if the words are anagrams, false otherwise.
     */
    public static boolean areAnagrams(String word1, String word2) {
        // Normalize the words: convert to lowercase and remove spaces.
        word1 = word1.toLowerCase().replaceAll("\\s+", "");
        word2 = word2.toLowerCase().replaceAll("\\s+", "");
        
        // If lengths differ, they cannot be anagrams.
        if (word1.length() != word2.length()) {
            return false;
        }
        
        // Create a frequency array for letters 'a' to 'z'.
        int[] frequency = new int[26];
        
        // Increase frequency for each letter in the first word.
        for (int i = 0; i < word1.length(); i++) {
            frequency[word1.charAt(i) - 'a']++;
        }
        
        // Decrease frequency for each letter in the second word.
        for (int i = 0; i < word2.length(); i++) {
            frequency[word2.charAt(i) - 'a']--;
        }
        
        // Check if all counts are zero.
        for (int count : frequency) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Display a greeting message.
        System.out.println("Welcome to the Joseph Abraham Anagram Checker for CST-201"
        		+ "\nGo Antilopes!!\n");
        
        System.out.println("Enter two words to check if they are anagrams.");
        System.out.println();
        
        // Automatically loop to check for anagrams continuously.
        while (true) {
            System.out.print("Enter the first word: ");
            String firstWord = scanner.nextLine();
            
            System.out.print("Enter the second word: ");
            String secondWord = scanner.nextLine();
            
            // Check if the words are anagrams and print the result.
            if (areAnagrams(firstWord, secondWord)) {
                System.out.println("We have an anagram!");
            } else {
                System.out.println("Dat not anagram!");
            }
            
            System.out.println(); // Blank line for readability.
        }
        // Note: The scanner is not closed since the loop runs indefinitely.
    }
}