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
        int minimum = 0, maximum = 0, count = 0, sum = 0;

        System.out.println("Enter random numbers to count their sum, average, min, max");
        int number = input.nextInt();

        if (number != -1) {
            minimum = maximum = number;
        }

        while (number != -1) {
            sum += number;
            count++;

            if (number < minimum) {
                minimum = number;
            }

            if (number > maximum) {
                maximum = number;
            }

            number = input.nextInt();
        }

        if (count != 0) {
            System.out.println("Minimum of numbers is: " + minimum);
            System.out.println("Maximum of numbers is: " + maximum);
            System.out.println("Sum of numbers is: " + sum);
            System.out.println("Total number count: " + count);
            System.out.println("Average | Mean of numbers: " + ((double) sum / (double) count));
        } else {
            System.out.println("No values exist.");
        }

    }
}
