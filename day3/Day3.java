package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to model a solution for Day 3 in Advent of Code 2024
 * @author Hugh Fraser
 */
public class Day3 {

    private static ArrayList<String> corruptedInput = new ArrayList<>();
    private static ArrayList<String> foundInstructions = new ArrayList<>();
    public static void main(String[] args) {
        readInput();
        //System.out.println(corruptedInput);
        
        for (String input : corruptedInput) {
            findInstructions(input);
        }
        System.out.println(foundInstructions);

        System.out.println("The total of all the multiplications is: " + runInstructions());
    }

    /**
     * Method to read the input from the text file
     * Takes the .txt file and sorts the two lists into ArrayLists
     */
    public static void readInput() {

        File inputFile = new File("day3/Day3Input.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                corruptedInput.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Method to find and extract any instructions from the corruption and put them in the specified ArrayList
     * @param input The corrupted input to check
     */
    public static void findInstructions(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1}|\\d{2}|\\d{3}),(\\d{1}|\\d{2}|\\d{3})\\)|do\\(\\)|don\\'t\\(\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            foundInstructions.add(input.substring(matcher.start(), matcher.end()));
        }
    }

    /**
     * Method to run the instructions found
     * It splits the instructions to find the values to multiply
     * @return The total of all the multiplications
     */
    public static int runInstructions() {
        int total = 0;
        boolean doInstructions = true;
        for (int i = 0; i < foundInstructions.size(); i++) {
            if (doInstructions) {
                System.out.println(foundInstructions.get(i));
                if (foundInstructions.get(i).equals("don't()")) {
                    doInstructions = false;
                } else if (!foundInstructions.get(i).equals("do()")) {
                    String[] parts = foundInstructions.get(i).split("[\\(\\,\\)]");
                    total += (Integer.parseInt(parts[1]) * Integer.parseInt(parts[2]));
                }
            } else {
                if (foundInstructions.get(i).equals("do()")) {
                    doInstructions = true;
                }
            }
            // String output = "";
            // for (String part : parts) {
            //     output += part + " : ";
            // }
            // System.out.println(output);
        }
        return total;
    }

}
