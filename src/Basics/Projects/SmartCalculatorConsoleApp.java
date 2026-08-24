package Basics.Projects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;

public class SmartCalculatorConsoleApp {


    public static void main(String[] args) {
        int[] latestResults = new int[5];
        String[] resultsDescription = new String[5];

        Scanner input = new Scanner(System.in);
        System.out.println("How to use.");
        System.out.println("Commands");
        System.out.println("Addition: (+)    |    Subtraction: (-)    |    Multiplication: (*)");
        System.out.println("Division: (/)    |    Modulus: (%)        |    Power: (^)");
        System.out.println("Calculation History: (h)                  |    Summary: (s)");
        System.out.println("Use Last Answer: (ans)");
        System.out.println("Close the application: (Exit)");

        while (true) {
            System.out.print("What operation do you want to perform: ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
            char option = choice.charAt(0);
            switch (option) {
                case '+':
                    System.out.println("Performs addition");
                    break;
                case '-':
                    System.out.println("Perform subtraction");
                    break;
                case '*':
                    System.out.println("Performs multiplication");
                    break;
                case '/':
                    System.out.println("Perform division");
                    break;
                case '%':
                    System.out.println("Performs modulus");
                    break;
                case '^':
                    System.out.println("Perform power");
                    break;
                case 'h':
                    printHistory(latestResults, resultsDescription);
                    break;
                case 's':
                    System.out.println("Print Summary Statistics");
                    break;
                case 'a':
                    System.out.println("Perform something with last answer");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        }

    }

    private static void dropOldestResult() {

    }

    private static void printHistory(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < latestResults.length; i++) {
            System.out.println(resultsDescription[i] + " : " + latestResults[i]);
        }
    }

    private static void printSummaryStatistics(int[] latestResults) {
        int sum = 0;
        double average;
        int min = latestResults[0];
        int max = latestResults[0];
        double median;
        int mode;
        for (int latestResult : latestResults) {
            sum += latestResult;
            if (min > latestResult) {
                min = latestResult;
            }
            if (max < latestResult) {
                max = latestResult;
            }
        }
        mode = modeValue(latestResults);
        median = medianValue(latestResults);
        average = sum / (double) latestResults.length;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
    }

    private static double medianValue(int[] latestResults) {
        int midIndex = latestResults.length / 2;
        if (latestResults.length % 2 == 0) {
            return (latestResults[midIndex] + latestResults[midIndex - 1]) / (double) 2;
        }
        return latestResults[midIndex];
    }

    private static int modeValue (int[] latestResults) {
        HashMap<Integer, Integer> valueCount = new HashMap<>();
        for (int number : latestResults) {
            /* Traditional Way
            if (valueCount.containsKey(number)) {
                valueCount.put(number, valueCount.getOrDefault(number, 0) + 1);
            }
            */

            // One-liner
            valueCount.merge(number, 1, Integer::sum);
        }

        int maxValue = 0;
        Integer key = null;

        for (Map.Entry<Integer, Integer> entry : valueCount.entrySet()) {
            if (maxValue < entry.getValue()) {
                key = entry.getKey();
            }
        }

        return 1;
    }

}
