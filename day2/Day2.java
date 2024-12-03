package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to model a solution for Day 2 in Advent of Code 2024
 * @author Hugh Fraser
 */
public class Day2 {

    private static ArrayList<ArrayList<Integer>> reports = new ArrayList<>();
    public static void main(String[] args) {
        readInput();
        int safeReports = 0;
        for (ArrayList<Integer> report : reports) {
            if (safeOrUnsafe(report)) {
                safeReports++;
            }
        }
        System.out.println("There are " + safeReports + " safe reports.");
    }

    /**
     * Method to read the input from the text file
     * Takes the .txt file and sorts the two lists into ArrayLists
     */
    public static void readInput() {

        File inputFile = new File("day2/Day2Input.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] currentLineSegments = currentLine.split(" ");
                ArrayList<Integer> levels = new ArrayList<>();
                for (String s : currentLineSegments) {
                    levels.add(Integer.valueOf(s));
                }
                reports.add(levels);
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Method to check if a report is safe
     * @param levels The levels in the report
     * @return If the report is safe
     */
    public static boolean safeOrUnsafe(ArrayList<Integer> levels) {
        boolean safe = false;
        if ((allDecreasing(levels) || allIncreasing(levels)) && safeDifference(levels)) {
            safe = true;
        }
        return safe;
    }

    /**
     * Method to check if all levels in a report are descending
     * @param levels The levels to check
     * @return Boolean value to represent if all levels are descending
     */
    public static boolean allDecreasing(ArrayList<Integer> levels) {
        boolean decreasing = true;
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) <= levels.get(i + 1)) {
                decreasing = false;
                break;
            }
        }
        return decreasing;
    }

    /**
     * Method to check if all levels in a report are increasing
     * @param levels The levels to check
     * @return Boolean value to represent if all levels are increasing
     */
    public static boolean allIncreasing(ArrayList<Integer> levels) {
        boolean increasing = true;
        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i) >= levels.get(i + 1)) {
                increasing = false;
                break;
            }
        }
        return increasing;
    }

    /**
     * Method to check if the levels in a report have a safe difference between each other
     * @param levels The levels to check
     * @return Boolean value to represent if all levels have a safe difference between each other
     */
    public static boolean safeDifference(ArrayList<Integer> levels) {
        boolean safeDifference = true;
        for (int i = 0; i < levels.size() - 1; i++) {
            int difference = Math.abs(levels.get(i) - levels.get(i + 1));
            if (difference < 1 || difference > 3) {
                safeDifference = false;
                break;
            }
        }
        return safeDifference;
    }
}
