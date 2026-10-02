package DateAndTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FormattingUserGivenDate {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name and date of birth ");
        System.out.print("Enter your Full Name: ");
        String name = input.nextLine();
        System.out.print("Enter your DOB in format (yyyy-mm-dd): ");
        String dob = input.nextLine();

        LocalDate DateOfBirth = LocalDate.parse(dob);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy");

        String formattedDOB = DateOfBirth.format(formatter);

        System.out.println("Hello " + name + " Nice to meet you.");
        System.out.println("Your date of birth is " + formattedDOB);
    }
}
