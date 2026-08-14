package BasicsAndFundamentals.Problems.Easy;

import java.util.Scanner;

public class problem003 {
    public static void main(String[] args) {

        /*
            Create a grade calculator: `90–100 A`, `80–89 B`, etc. Reject values outside `0–100`.
        */

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your marks: ");
        int marks = input.nextInt();
        char grade = ' ';

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid input! Please enter a value between (0 - 100) inclusive.");
        }
        else if (marks >= 90) {
            grade = 'A';
        } else if (marks >= 80) {
            grade = 'B';
        } else if (marks >= 70) {
            grade = 'C';
        } else if (marks >= 60) {
            grade = 'D';
        } else if (marks >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        System.out.println("Your grade is: " + grade);
    }
}
