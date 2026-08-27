package Basics.Projects;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SmartCalculatorConsoleApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Smart-Calculator");
        int size = validateUserInput(input, " size of bucket to hold history of calculations");
        int[] latestResults = new int[size];
        String[] resultsDescription = new String[size];

        System.out.println("How to use.");
        System.out.println("Commands");
        System.out.println("Addition: (+)    |    Subtraction: (-)    |    Multiplication: (*)");
        System.out.println("Division: (/)    |    Modulus: (%)        |    Power: (^)");
        System.out.println("Calculation History: (h)                  |    Summary: (s)");
        System.out.println("Use Last Answer: (ans)");
        System.out.println("Close the application: (Exit)");

        char lastOperator = 0;
        int sameOperationCount = 0;
        int i = 0;
        while (true) {
            System.out.print("What operation do you want to perform: ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
            char option = choice.charAt(0);
            int value1, value2;
            String description;
            String firstValueDesc, secondValueDesc;

            if (option == 'h') {
                if (i == 0) {
                    System.out.println("No calculations yet! Perform some calculations...");
                } else {
                    printHistory(latestResults, resultsDescription);
                }
            } else if (option == 's') {
                printSummaryStatistics(latestResults, resultsDescription);
            } else if (option == 'a') {
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
            } else {
                if (validOperator(option)) {
                    System.out.println("Invalid operator!!! Try again...");
                    continue;
                }
                firstValueDesc = (option == '/' || option == '%') ? "dividend" : option == '^' ? "base" : "first number";
                secondValueDesc = (option == '/' || option == '%') ? "divisor" : option == '^' ? "exponent" : "second number";
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
            latestResults[i] = latestResults[i + 1];
            resultsDescription[i] = resultsDescription[i + 1];
        }
        return latestResults.length - 1;
    }

    private static void printHistory(int[] latestResults, String[] resultsDescription) {
        for (int i = 0; i < filledCount(resultsDescription); i++) {
            System.out.println(resultsDescription[i] + " = " + latestResults[i]);
        }
    }

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

    private static int filledCount(String[] resultsDescription) {
        int size = 0;
        while (size < resultsDescription.length && resultsDescription[size] != null) {
            size++;
        }
        return size;
    }

    private static int modeValue(int[] latestResults, int filledArraySize) {
        HashMap<Integer, Integer> valueCount = new HashMap<>();
        for (int i = 0; i < filledArraySize; i++) {
            /* Traditional Way
            if (valueCount.containsKey(number)) {
                valueCount.put(number, valueCount.getOrDefault(number, 0) + 1);
            }
            */

            // One-liner
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


    private static int performCalculation (int operandOne, int operandTwo, char operator) {
        return switch (operator) {
            case '+' -> operandOne + operandTwo;
            case '-' -> operandOne - operandTwo;
            case '*' -> operandOne * operandTwo;
            case '/' -> operandOne / operandTwo;
            case '%' -> operandOne % operandTwo;
            case '^' -> operandOne ^ operandTwo;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }

    private static boolean validOperator(char operator) {
        return operator != '+' && operator != '-' && operator != '*' &&
                operator != '/' && operator != '%' && operator != '^';
    }

    private static int calculateRunningTotal(Scanner input, int lastResult, char operator) {
        int runningTotal = lastResult;
        String requiredInputDesc = (operator == '/' || operator == '%') ? "divisor" : operator == '^' ? "exponent" : "next number";
        do {
            int next = validateUserInput(input, requiredInputDesc);
            runningTotal = performCalculation(runningTotal, next , operator);
            System.out.print("Another number (Y/N): ");
        } while (!input.nextLine().equalsIgnoreCase("n"));
        return runningTotal;
    }
}