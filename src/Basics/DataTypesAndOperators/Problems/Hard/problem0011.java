package Basics.DataTypesAndOperators.Problems.Hard;

import java.util.Scanner;

public class problem0011 {
    public static void main(String[] args) {
        /*
            Without converting the value to a `String`, determine whether a number such
            as `12321` is a palindrome. Also handle `12021`, `7`, `10`.
        */

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a number to check if it's a palindrome: ");

        int number = input.nextInt();

        int temp = number;
        int reverse = 0;
        while (number != 0) {
            int remainder = number % 10;
            if (reverse != 0 && remainder ==0) {
                reverse *= 10;
            } else {
                reverse = reverse * 10 + remainder;
            }
            number /= 10;
        }

        System.out.println(reverse + ((reverse == temp) ? " is a palindrome." : " is not a palindrome"));
    }
}
