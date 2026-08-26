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
        byte addPerformed, subPerformed, mulPerformed , divPerformed, modPerformed, powPerformed;
        addPerformed = subPerformed = mulPerformed = divPerformed = modPerformed = powPerformed = 0;

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
                    if (addPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        do {
                            int next = validateUserInput(input, " next number");
                            runningTotal = addNumbers(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Addition Running Total");
                        addPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "First");
                        value2 = validateUserInput(input, "Second");
                        result = addNumbers(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " + " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        addPerformed++;
                        subPerformed = mulPerformed = divPerformed = modPerformed = powPerformed = 0;
                    }
                    break;
                case '-':
                    if (subPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        do {
                            int next = validateUserInput(input, " next number");
                            runningTotal = subtractNumbers(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Subtraction Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Subtraction Running Total");
                        subPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "First");
                        value2 = validateUserInput(input, "Second");
                        result = subtractNumbers(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " - " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        subPerformed++;
                        addPerformed = mulPerformed = divPerformed = modPerformed = powPerformed = 0;
                    }
                    break;
                case '*':
                    if (mulPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        do {
                            int next = validateUserInput(input, " next number");
                            runningTotal = multiplyNumbers(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Multiplication Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Multiplication Running Total");
                        mulPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "First");
                        value2 = validateUserInput(input, "Second");
                        result = multiplyNumbers(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " * " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        mulPerformed++;
                        addPerformed = subPerformed = divPerformed = modPerformed = powPerformed = 0;
                    }
                    break;
                case '/':
                    if (divPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        System.out.println("Running total for division. Keep entering divisor...");
                        do {
                            int next = validateUserInput(input, "divisor");
                            runningTotal = divideNumbers(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Division Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Division Running Total");
                        divPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "dividend");
                        value2 = validateUserInput(input, "divisor");
                        result = divideNumbers(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " / " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        divPerformed++;
                        addPerformed = subPerformed = mulPerformed = modPerformed = powPerformed = 0;
                    }
                    break;
                case '%':
                    if (modPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        System.out.println("Running total for modulus. Keep entering divisor...");
                        do {
                            int next = validateUserInput(input, "divisor");
                            runningTotal = performModulus(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Modulus Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Modulus Running Total");
                        modPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "dividend");
                        value2 = validateUserInput(input, "divisor");
                        result = performModulus(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " % " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        modPerformed++;
                        addPerformed = subPerformed = mulPerformed = divPerformed = powPerformed = 0;
                    }
                    break;
                case '^':
                    if (powPerformed == 3) {
                        int runningTotal = latestResults[i - 1];
                        do {
                            int next = validateUserInput(input, " next exponent");
                            runningTotal = takePowerOfNumber(runningTotal, next);
                            System.out.print("Another number (Y/N): ");
                        } while (!input.nextLine().equalsIgnoreCase("n"));
                        System.out.println("Power Running total: " + runningTotal);
                        i = updateHistory(latestResults, resultsDescription, i, runningTotal, "Power Running Total");
                        powPerformed = 0;
                    } else {
                        value1 = validateUserInput(input, "First");
                        value2 = validateUserInput(input, "Second");
                        result = takePowerOfNumber(value1, value2);
                        System.out.println("Result: " + result);
                        resultDescription = (value1 + " ^ " + value2);
                        i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                        powPerformed++;
                        addPerformed = subPerformed = mulPerformed = divPerformed = modPerformed = 0;
                    }
                    break;
                case 'h':
                    printHistory(latestResults, resultsDescription);
                    break;
                case 's':
                    printSummaryStatistics(latestResults, resultsDescription);
                    break;
                case 'a':
                    if (i == 0) {
                        System.out.println("No calculations performed yet! Perform some calculations...");
                        break;
                    }
                    int lastAnswer = latestResults[i - 1];
                    while (true) {
                        System.out.print("What operation you want to perform with ans: ");
                        option = input.nextLine().charAt(0);
                        if (option == '+') {
                            value1 = validateUserInput(input, " X (ans + X)");
                            result = addNumbers(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " + " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else if (option == '-') {
                            value1 = validateUserInput(input, " X (ans - X)");
                            result = subtractNumbers(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " - " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else if (option == '*') {
                            value1 = validateUserInput(input, " X (ans * X)");
                            result = multiplyNumbers(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " x " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else if (option == '/') {
                            value1 = validateUserInput(input, " X (ans / X)");
                            result = divideNumbers(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " / " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else if (option == '%') {
                            value1 = validateUserInput(input, " X (ans % X)");
                            result = performModulus(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " % " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else if (option == '^') {
                            value1 = validateUserInput(input, " X (ans ^ X)");
                            result = takePowerOfNumber(lastAnswer, value1);
                            System.out.println("Result: " + result);
                            resultDescription = (lastAnswer + " ^ " + value1);
                            i = updateHistory(latestResults, resultsDescription, i, result, resultDescription);
                            break;
                        } else {
                            System.out.println("Enter a valid operator! Try again...");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
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

    private static int addNumbers(int x, int y) {
        return x + y;
    }

    private static int subtractNumbers(int x, int y) {
        return x - y;
    }

    private static int multiplyNumbers(int x, int y) {
        return x * y;
    }

    private static int divideNumbers(int x, int y) {
        return x / y;
    }

    private static int performModulus(int x, int y) {
        return x % y;
    }

    private static int takePowerOfNumber(int x, int y) {
        return (int) Math.pow(x, y);
    }
}