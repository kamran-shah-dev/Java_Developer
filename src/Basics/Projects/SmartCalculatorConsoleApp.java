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
}
