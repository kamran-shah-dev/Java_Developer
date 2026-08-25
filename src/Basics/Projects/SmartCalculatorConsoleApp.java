package Basics.Projects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SmartCalculatorConsoleApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Smart-Calculator");
        System.out.print("What should be the size of bucket to hold history of calculations: ");
        int size = Integer.parseInt(input.nextLine());
        int[] latestResults = new int[size];
        String[] resultsDescription = new String[size];

        System.out.println("How to use.");
        System.out.println("Commands");
        System.out.println("Addition: (+)    |    Subtraction: (-)    |    Multiplication: (*)");
        System.out.println("Division: (/)    |    Modulus: (%)        |    Power: (^)");
        System.out.println("Calculation History: (h)                  |    Summary: (s)");
        System.out.println("Use Last Answer: (ans)");
        System.out.println("Close the application: (Exit)");
        int i = 0;
        while (true) {
            System.out.print("What operation do you want to perform: ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
            char option = choice.charAt(0);
            int value1, value2, result;
            String resultDescription;
            switch (option) {
                case '+':
                    value1 = validateUserInput(input, "First");
                    value2 = validateUserInput(input , "Second");
                    result = value1 + value2;
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " + " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case '-':
                    value1 = validateUserInput(input, "First");
                    value2 = validateUserInput(input , "Second");
                    result = value1 - value2;
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " - " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case '*':
                    value1 = validateUserInput(input, "First");
                    value2 = validateUserInput(input , "Second");
                    result = value1 * value2;
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " * " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case '/':
                    value1 = validateUserInput(input, "dividend");
                    value2 = validateUserInput(input, "divisor");
                    result = value1 / value2;
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " / " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case '%':
                    value1 = validateUserInput(input, "dividend");
                    value2 = validateUserInput(input, "divisor");
                    result = value1 % value2;
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " % " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case '^':
                    value1 = validateUserInput(input, "First");
                    value2 = validateUserInput(input , "Second");
                    result = (int) Math.pow(value1 , value2);
                    System.out.println("Result: " + result);
                    resultDescription = (value1 + " ^ " + value2);
                    i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                    break;
                case 'h':
                    printHistory(latestResults, resultsDescription);
                    break;
                case 's':
                    printSummaryStatistics(latestResults);
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

    private static int validateUserInput(Scanner input, String requiredInput) {
        while (true) {
            System.out.print("Enter the " + requiredInput +": ");
            String userInput = input.nextLine();

            if (isValidNumber(userInput)) {
                if (requiredInput.equalsIgnoreCase("divisor")
                        && Integer.parseInt(userInput) == 0) {
                    System.out.println("Division By Zero Error! Try again...");
                    continue;
                }
                return Integer.parseInt(userInput);
            }
            System.out.println("Invalid Number! Try again...");
        }
    }

    private static boolean isValidNumber(String firstNumber) {
        if (firstNumber == null) {
            return false;
        }
        try {
            Integer.parseInt(firstNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int dropOldestResult(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < latestResults.length - 1; i++) {
            latestResults[i] = latestResults[i+1];
            resultsDescription[i] = resultsDescription[i+1];
        }
        return latestResults.length - 1;
    }

    private static void printHistory(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < latestResults.length; i++) {
            System.out.println(resultsDescription[i] + " = " + latestResults[i]);
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
        int key = 0;

        for (Map.Entry<Integer, Integer> entry : valueCount.entrySet()) {
            if (maxValue < entry.getValue()) {
                maxValue = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;
    }

    private static int updateHistory(int[] latestResults, String[] resultsDescription,
                                      int index, int calculationResult, String resultDesc) {
        if (index < latestResults.length) {
            latestResults[index] = calculationResult;
            resultsDescription[index] = resultDesc;
            return index + 1;
        }
        index = dropOldestResult(latestResults, resultsDescription);
        latestResults[index] = calculationResult;
        resultsDescription[index] = resultDesc;
        return index + 1;
    }
}