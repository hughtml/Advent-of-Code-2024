package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to model a solution for Day 1 in Advent of Code 2024
 * @author Hugh Fraser
 */
public class Day1 {

    private static ArrayList<Integer> list1 = new ArrayList<>();
    private static ArrayList<Integer> list2 = new ArrayList<>();
    private static ArrayList<Integer> distanceList = new ArrayList<>();
    public static void main(String[] args) {
        readInput();
        sortLists();
        findDifferences();
        System.out.println("The total distance is: " + totalDifference());
        System.out.println("The similarity score is: " + calculateSimilarityScore());
    }

    /**
     * Method to read the input from the text file
     * Takes the .txt file and sorts the two lists into ArrayLists
     */
    public static void readInput() {

        File inputFile = new File("day1/Day1Input.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] currentLineSegments = currentLine.split(" ");
                list1.add(Integer.valueOf(currentLineSegments[0]));
                list2.add(Integer.valueOf(currentLineSegments[currentLineSegments.length - 1]));
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Method to sort the lists in ascending order
     * Uses lambda expressions
     */
    public static void sortLists() {
        list1.sort((number1, number2) -> number1.compareTo(number2));
        list2.sort((number1, number2) -> number1.compareTo(number2));
    }

    /**
     * Method to find the difference between the distances
     */
    public static void findDifferences() {
        for (int i = 0; i < list1.size(); i++) {
            distanceList.add(Math.abs(list1.get(i) - list2.get(i)));
        }
    }

    /**
     * Method to find the total of the differences
     * @return The total added up
     */
    public static int totalDifference() {
        int total = 0;
        for (Integer number : distanceList) {
            total += number.intValue();
        }
        return total;
    }

    /**
     * Method to count the occurrences of a value from one list in another
     * @param number The value from the first list
     * @return The amount of times it occurs in the second list
     */
    public static int countOccurrences(Integer number) {
        //The lists have been sorted, so all occurrences will be next to each other
        int count = list2.lastIndexOf(number) - list2.indexOf(number) + 1;
        return count;
    }

    /**
     * Method to calculate the similarity score between two lists
     * @return The similarity score
     */
    public static int calculateSimilarityScore() {
        int score = 0;

        for (Integer number : list1) {
            if (list2.contains(number)) {
                score += number.intValue() * countOccurrences(number);
            }
        }

        return score;
    }

}