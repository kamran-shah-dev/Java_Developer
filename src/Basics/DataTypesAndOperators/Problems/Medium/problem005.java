package Basics.DataTypesAndOperators.Problems.Medium;

import java.util.Scanner;

public class problem005 {
    public static void main(String[] args) {
        /*
            Take an integer and calculate its number of digits, sum of digits, reversed number and whether
            it is a palindrome.
        */

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: ");

        int inputNumber = input.nextInt();

        // if we calculate sum of digits we can actually know number of digits

        int numberOfDigits = 0;
        int sumOfDigits = 0;
        int reversedNumber = 0;

        // Take a temporary variable so the inputNumber does not change, so we can check for palindrome
        //  here we will not use strings so -121 as an integer is a palindrome

        int temp = inputNumber;

        while (temp != 0) {
            numberOfDigits++;
            int remainder = temp % 10;
            reversedNumber = (reversedNumber * 10) + remainder;
            sumOfDigits = sumOfDigits + remainder;
            temp = temp / 10;
        }

        if (sumOfDigits < 0) {
            sumOfDigits = -sumOfDigits;
        }
        if (inputNumber == 0) {
            numberOfDigits++;
        }

        System.out.println("Number of digits: " + numberOfDigits);
        System.out.println("Sum of digits: " + sumOfDigits);
        System.out.println("Reversed form: " + reversedNumber);

        System.out.println("Is the number a palindrome? " + (inputNumber == reversedNumber ? "It's a palindrome" : "It's not a palindrome"));


    }
}
