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
    public static void main(String[] args) {
        readInput();
        sortLists();
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

}