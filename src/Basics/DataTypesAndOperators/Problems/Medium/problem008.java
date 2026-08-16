package Basics.DataTypesAndOperators.Problems.Medium;

import java.util.Scanner;

public class problem008 {
    public static void main(String[] args) {
        /*
            Read numbers repeatedly until `-1` is entered. Then print count, sum, average,
            minimum and maximum. `-1` must not be included.
        */

        // I am defining the criteria as, all counts initially are 0
        Scanner input = new Scanner(System.in);
        int Minimum = 0, Maximum = 0, Count = 0, Sum = 0, Average = 0;

        System.out.println("Enter random numbers to count their sum, average, min, max");
        int number = input.nextInt();

        if (number != -1) {
            Minimum = Maximum = number;
        }

        while (number != -1) {
            Sum += number;
            Count++;

            if (number < Minimum) {
                Minimum = number;
            }

            if (number > Maximum) {
                Maximum = number;
            }

            number = input.nextInt();
        }

        System.out.println("Minimum of numbers is: " + Minimum);
        System.out.println("Maximum of numbers is: " + Maximum);
        System.out.println("Sum of numbers is: " + Sum);
        System.out.println("Total number count: " + Count);

        if (Count != 0) {
            System.out.println("Average | Mean of numbers: " + (Sum / Count));
        } else {
            System.out.println("Division by zero is not valid.");
        }

    }
}
