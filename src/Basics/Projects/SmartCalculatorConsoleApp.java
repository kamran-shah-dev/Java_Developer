package Basics.Projects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A console-based smart calculator application supporting basic arithmetic,
 * operation history tracking, summary statistics (min, max, mean, median, mode),
 * and consecutive operation shortcuts.
 */
public class SmartCalculatorConsoleApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Smart-Calculator");
        // Prompt user to define the storage capacity for history tracking
        int size = validateUserInput(input, " size of bucket to hold history of calculations");
        int[] latestResults = new int[size];
        String[] resultsDescription = new String[size];

        // Display user guide and command reference
        System.out.println("How to use.");
        System.out.println("Commands");
        System.out.println("Addition: (+)    |    Subtraction: (-)    |    Multiplication: (*)");
        System.out.println("Division: (/)    |    Modulus: (%)        |    Power: (^)");
        System.out.println("Calculation History: (h)                  |    Summary: (s)");
        System.out.println("Use Last Answer: (ans)");
        System.out.println("Close the application: (Exit)");

        char lastOperator = 0;
        int sameOperationCount = 0;
        int i = 0; // Tracks current history index/count

        while (true) {
            System.out.print("What operation do you want to perform: ");
            String choice = input.nextLine();

            // Exit condition
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
            char option = choice.charAt(0);
            int value1, value2;
            String description;
            String firstValueDesc, secondValueDesc;

            // Handle history display request
            if (option == 'h') {
                if (i == 0) {
                    System.out.println("No calculations yet! Perform some calculations...");
                } else {
                    printHistory(latestResults, resultsDescription);
                }
            }
            // Handle summary statistics request
            else if (option == 's') {
                printSummaryStatistics(latestResults, resultsDescription);
            }
            // Handle "use last answer" ('a' or 'ans' prefix) request
            else if (option == 'a') {
                if (i == 0) {
                    System.out.println("No calculations performed yet! Perform some calculations...");
                } else {
                    int lastAnswer = latestResults[i - 1];
                    do  {
                        System.out.print("What you want to perform with last result: ");
                        option = input.nextLine().charAt(0);
                    } while ((validOperator(option)));

                    value1 = validateUserInput(input, " X (" + lastAnswer + option + " X)");
                    int calculationResult = performCalculation(lastAnswer, value1, option);
                    System.out.println("Result: " + calculationResult);
                    description = (lastAnswer + String.valueOf(option) + value1);
                    i = updateHistory(latestResults, resultsDescription, i, calculationResult, description);
                }
            }
            // Handle standard arithmetic operations
            else {
                if (validOperator(option)) {
                    System.out.println("Invalid operator!!! Try again...");
                    continue;
                }

                // Dynamically label inputs based on the chosen operator
                firstValueDesc = (option == '/' || option == '%') ? "dividend" : option == '^' ? "base" : "first number";
                secondValueDesc = (option == '/' || option == '%') ? "divisor" : option == '^' ? "exponent" : "second number";

                // Detect consecutive uses of the same operator for chain/running calculations
                if (lastOperator == option) {
                    if (sameOperationCount == 2) {
                        System.out.println("Looks like you are doing many " +
                                (option == '+' ? "Additions" : (option == '-' ? "Subtraction" :
                                        (option == '*' ? "Multiplication" :
                                                (option == '/' ? "Division" : option == '%' ? "Modulus" : "Power Calculations")))));
                        System.out.println("Calculate a running total by entering next number..");

                        int runningTotal = 0;
                        for (int j = 0; j < filledCount(resultsDescription); j++) {
                            if (resultsDescription[j].contains(Character.toString(lastOperator))) {
                                runningTotal += latestResults[j];
                            }
                        }
                        runningTotal = calculateRunningTotal(input, runningTotal, option);
                        description = "Running total of " + option;
                        System.out.println("Running Total Result: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal , description);
                    } else {
                        sameOperationCount++;
                        value1 = validateUserInput(input, firstValueDesc);
                        value2 = validateUserInput(input, secondValueDesc);
                        int calculationResult = performCalculation(value1, value2, option);
                        System.out.println("Result: " + calculationResult);
                        description = (value1 + String.valueOf(option) + value2);
                        i = updateHistory(latestResults, resultsDescription, i, calculationResult, description);
                    }
                } else {
                    sameOperationCount = 0;
                    value1 = validateUserInput(input, firstValueDesc);
                    value2 = validateUserInput(input, secondValueDesc);
                    int calculationResult = performCalculation(value1, value2, option);
                    System.out.println("Result: " + calculationResult);
                    description = (value1 + String.valueOf(option) + value2);
                    i = updateHistory(latestResults, resultsDescription, i, calculationResult, description);
                }
            }
            lastOperator = option;
        }
    }

    /**
     * Continuously prompts the user until a valid integer is entered,
     * with special handling to prevent division by zero.
     */
    private static int validateUserInput(Scanner input, String requiredInput) {
        while (true) {
            System.out.print("Enter the " + requiredInput + ": ");
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

    /**
     * Checks whether a given string can be successfully parsed into an integer.
     */
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

    /**
     * Shifts historical arrays to the left when capacity is reached (FIFO queue behavior).
     */
    private static int dropOldestResult(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < latestResults.length - 1; i++) {
            latestResults[i] = latestResults[i + 1];
            resultsDescription[i] = resultsDescription[i + 1];
        }
        return latestResults.length - 1;
    }

    /**
     * Prints all recorded calculation expressions and their corresponding results.
     */
    private static void printHistory(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < filledCount(resultsDescription); i++) {
            System.out.println(resultsDescription[i] + " = " + latestResults[i]);
        }
    }

    /**
     * Computes and prints summary statistics (sum, average, min, max, median, mode)
     * based on current calculation history.
     */
    private static void printSummaryStatistics(int[] latestResults, String[] resultsDescription) {
        int sum = 0;
        double average;
        int min = latestResults[0];
        int max = latestResults[0];
        double median;
        int mode;
        int filledArraySize = filledCount(resultsDescription);

        if (filledArraySize == 0) {
            System.out.println("No calculations yet. Perform some calculations..");
            return;
        }

        for (int i = 0; i < filledArraySize; i++) {
            sum += latestResults[i];
            if (min > latestResults[i]) {
                min = latestResults[i];
            }
            if (max < latestResults[i]) {
                max = latestResults[i];
            }
        }

        mode = modeValue(latestResults, filledArraySize);
        int midIndex = filledArraySize / 2;
        if (filledArraySize % 2 == 0) {
            median = (latestResults[midIndex] + latestResults[midIndex - 1]) / (double) 2;
        } else {
            median = latestResults[midIndex];
        }
        average = sum / (double) filledArraySize;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
    }

    /**
     * Counts how many entries are currently populated in the history description array.
     */
    private static int filledCount(String[] resultsDescription) {
        int size = 0;
        while (size < resultsDescription.length && resultsDescription[size] != null) {
            size++;
        }
        return size;
    }

    /**
     * Calculates the statistical mode (most frequently occurring value) from results.
     */
    private static int modeValue(int[] latestResults, int filledArraySize) {
        HashMap<Integer, Integer> valueCount = new HashMap<>();
        for (int i = 0; i < filledArraySize; i++) {
            valueCount.merge(latestResults[i], 1, Integer::sum);
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

    /**
     * Inserts a new calculation result and description into history,
     * evicting the oldest entry if capacity is exceeded.
     */
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

    /**
     * Executes the appropriate arithmetic operation on two operands.
     */
    private static int performCalculation(int operandOne, int operandTwo, char operator) {
        return switch (operator) {
            case '+' -> operandOne + operandTwo;
            case '-' -> operandOne - operandTwo;
            case '*' -> operandOne * operandTwo;
            case '/' -> operandOne / operandTwo;
            case '%' -> operandOne % operandTwo;
            case '^' -> (int) Math.pow(operandOne, operandTwo); // Bitwise XOR replaced with proper power calculation
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }

    /**
     * Validates if the entered character corresponds to a supported operator.
     */
    private static boolean validOperator(char operator) {
        return operator != '+' && operator != '-' && operator != '*' &&
                operator != '/' && operator != '%' && operator != '^';
    }

    /**
     * Continuously accumulates values for consecutive chain operations.
     */
    private static int calculateRunningTotal(Scanner input, int lastResult, char operator) {
        int runningTotal = lastResult;
        String requiredInputDesc = (operator == '/' || operator == '%') ? "divisor" : operator == '^' ? "exponent" : "next number";
        do {
            int next = validateUserInput(input, requiredInputDesc);
            runningTotal = performCalculation(runningTotal, next, operator);
            System.out.print("Another number (Y/N): ");
        } while (!input.nextLine().equalsIgnoreCase("n"));
        return runningTotal;
    }
}