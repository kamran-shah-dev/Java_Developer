package Basics.DataTypesAndOperators.Problems.Medium;

import java.util.Scanner;

public class problem006 {
    public static void main(String[] args) {
        /*
            Print every prime number between two user-provided numbers.
            Do not use libraries to determine primality.
        */

        Scanner input = new Scanner(System.in);
        System.out.println("Enter two numbers to check if the numbers are prime or not.");
        System.out.print("Enter first number: ");
        int firstNumber = input.nextInt();
        System.out.print("Enter last number: ");
        int lastNumber = input.nextInt();

        for (int i = firstNumber; i <= lastNumber; i++) {
            if (i > 1) {
                boolean isPrime = true;
                int squareRoot = (int) Math.sqrt(i);
                int j = 2;
                while (isPrime && j <= squareRoot) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                    j++;
                }
                if (isPrime) {
                    System.out.println(i);
                }
            }
        }
    }
}
