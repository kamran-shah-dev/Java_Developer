package Basics.DataTypesAndOperators.Problems.Easy;

import java.util.Scanner;

public class problem002 {
    public static void main(String[] args) {
        /*
            Given an integer, determine whether it is positive/negative/zero, even/odd,
            and divisible by both 3 and 5.
        */

        // Take an integer as input

        // Scanner is a class that is used to take input from user
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int x = input.nextInt();

        // check if divisible by 3 and 5
        if (x % 3 == 0 && x % 5 == 0) {
            System.out.println(x + " is divisible by 3 and 5 both.");
        } else {
            System.out.println(x + " is not divisible by 3 and 5 both.");
        }

        // check if even or odd (parity)
        if (x % 2 == 0) {
            System.out.println(x + " is even.");
        } else {
            System.out.println(x + " is odd.");
        }

        // check the sign
        if (x == 0) {
            System.out.println(x + " is zero.");
        } else if (x < 0) {
            System.out.println(x + " is negative.");
        } else {
            System.out.println(x + " is positive.");
        }
    }
}
