package BasicsAndFundamentals.Problems.Easy;
import java.util.Scanner;

public class problem001 {
    public static void main(String[] args) {
        /*
            Take three integers and determine the largest without using Math.max().
            Handle equal values correctly.
        */

        // Take three integers from user

        // Scanner is a class that is used to take input from user
        Scanner input = new Scanner(System.in);

        System.out.println("Enter first number: ");
        int number1 = input.nextInt();

        System.out.println("Enter second number: ");
        int number2 = input.nextInt();

        System.out.println("Enter third number: ");
        int number3 = input.nextInt();

        int largest = number1;

        // logic that handles equal conditions - Using if - else only
        if (number2 > largest) {
            largest = number2;
        }
        if (number3 > largest) {
            largest = number3;
        }
        System.out.println("Largest number = " + largest);
    }
}
