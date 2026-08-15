package Basics.DataTypesAndOperators.Problems.Medium;

import java.util.Scanner;

public class problem004 {
    public static void main(String[] args) {
        // determine if a year is leap year or not

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a year: ");
        int year = input.nextShort();

        boolean leapYear = year % 4 == 0;

        if(year <= 0) {
            System.out.println("Year cannot be negative or zero");
        } else {
            if (year % 100 == 0) {
                leapYear = year % 400 == 0;
            }

            if (leapYear) {
                System.out.println(year + " is a leap year");
            } else {
                System.out.println(year + " is not a leap year");
            }
        }
    }
}
