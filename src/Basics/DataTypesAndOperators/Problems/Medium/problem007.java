package Basics.DataTypesAndOperators.Problems.Medium;

import java.util.Scanner;

public class problem007 {
    public static void main(String[] args) {
        /*
            Build a calculator that continuously accepts operations `+ - * / %` until the user chooses Exit.
            Protect against division by zero.
        */

        Scanner input = new Scanner(System.in);
        System.out.println("*************** Simple Calculator ***************");
        char choice;
        while (true) {
            System.out.println("*************** Menu ***************");
            System.out.println("To exit enter 1");
            System.out.print("What operation you want to perform (+,-,*,/,%) | 1: ");
            choice = input.next().charAt(0);
            if (choice == '1') {
                break;
            }

            int firstOperand, secondOperand;

            switch (choice) {
                case '+':
                    System.out.print("Enter first number: ");
                    firstOperand = input.nextInt();
                    System.out.print("Enter second number: ");
                    secondOperand = input.nextInt();
                    System.out.println(firstOperand + " + " + secondOperand + " = " + (firstOperand + secondOperand));
                    break;
                case '-':
                    System.out.print("Enter first number: ");
                    firstOperand = input.nextInt();
                    System.out.print("Enter second number: ");
                    secondOperand = input.nextInt();
                    System.out.println(firstOperand + " - " + secondOperand + " = " + (firstOperand - secondOperand));
                    break;
                case '*':
                    System.out.print("Enter first number: ");
                    firstOperand = input.nextInt();
                    System.out.print("Enter second number: ");
                    secondOperand = input.nextInt();
                    System.out.println(firstOperand + " x " + secondOperand + " = " + (firstOperand * secondOperand));
                    break;
                case '/':
                    System.out.print("Enter first number: ");
                    firstOperand = input.nextInt();
                    System.out.print("Enter second number: ");
                    secondOperand = input.nextInt();
                    if (secondOperand != 0) {
                        System.out.println(firstOperand + " / " + secondOperand + " = " + (firstOperand / secondOperand));
                    } else {
                        System.out.println("Division by zero is invalid...");
                    }
                    break;
                case '%':
                    System.out.print("Enter first number: ");
                    firstOperand = input.nextInt();
                    System.out.print("Enter second number: ");
                    secondOperand = input.nextInt();
                    if (secondOperand != 0) {
                        System.out.println(firstOperand + " % " + secondOperand + " = " + (firstOperand % secondOperand));
                    } else {
                        System.out.println("Division by zero is invalid...");
                    }
                    break;
                default:
                    System.out.println("Enter a valid input...");
                    break;
            }
        }
    }
}
