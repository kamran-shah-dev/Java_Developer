package DateAndTime.Challenge;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TripPlanner {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static long calculateTripDuration (LocalDate departureDate, LocalDate returnDate) {
        return ChronoUnit.DAYS.between(departureDate, returnDate);
    }

    public static boolean validateTripDates (LocalDate departureDate, LocalDate returnDate) {
        LocalDate today = LocalDate.now();
        if (departureDate.isBefore(today)) {
            System.out.println("Error! Departure date cannot be in past.");
            return false;
        }

        if (returnDate.isBefore(departureDate)) {
            System.out.println("Error! Return Date cannot be before departure date.");
            return false;
        }

        if (calculateTripDuration(departureDate, returnDate) > 90) {
            System.out.println("Error! Trip cannot be longer than 90 days.");
            return false;
        }
        return true;
    }

    public static String hotelDates (LocalDate departureDate, LocalDate returnDate) {
        String checkIn = departureDate.format(DATE_TIME_FORMATTER);
        String checkOut = returnDate.format(DATE_TIME_FORMATTER);

        return "Check-in Date: " + checkIn + "\nCheck-out Date: " + checkOut;
    }

    public static boolean tripDaysOverlapHolidays (LocalDate departureDate, LocalDate returnDate, LocalDate holidayDate) {
        return (departureDate.isEqual(holidayDate)) || (returnDate.isEqual(holidayDate)) ||
                holidayDate.isAfter(departureDate) && holidayDate.isBefore(returnDate);
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Travel Planner Application ===");
        System.out.println("All dates should be entered in format dd/MM/yyyy");

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate trip duration");
            System.out.println("2. Validate travel dates");
            System.out.println("3. Calculate hotel check-in and check-out");
            System.out.println("4. Check if trip overlaps with a holiday");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String choice = input.nextLine();

            LocalDate departureDate;
            LocalDate returnDate;
            boolean isValid;
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(input.nextLine());

                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(input.nextLine());

                        isValid = validateTripDates(departureDate, returnDate);
                        if (isValid) {
                            long tripDuration = calculateTripDuration(departureDate, returnDate);
                            System.out.println("Your Trip Duration Is: " + tripDuration);
                        } else {
                            System.out.println("Invalid Dates");
                        }
                    }
                    case "2" -> {
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(input.nextLine());

                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(input.nextLine());

                        validateTripDates(departureDate, returnDate);
                    }

                    case "3" -> {
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(input.nextLine());

                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(input.nextLine());

                        isValid = validateTripDates(departureDate, returnDate);
                        if (isValid) {
                            System.out.println(hotelDates(departureDate, returnDate));
                        } else {
                            System.out.println("Invalid Dates");
                        }
                    }

                    case "4" -> {
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(input.nextLine());

                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(input.nextLine());

                        System.out.print("Enter holiday date (dd/MM/yyyy): ");
                        LocalDate holidayDate = parseDate(input.nextLine());

                        isValid = validateTripDates(departureDate, returnDate);
                        if (isValid) {
                            if (tripDaysOverlapHolidays(departureDate, returnDate, holidayDate)) {
                                System.out.println("Your Trip Overlaps With Holiday Date: " + holidayDate.format(DATE_TIME_FORMATTER));
                            } else {
                                System.out.println("Your Trip Does Not Overlap With Holiday Date" + holidayDate.format(DATE_TIME_FORMATTER));
                            }
                        } else {
                            System.out.println("Invalid Dates");
                        }
                    }

                    case "5" -> {
                        running = false;
                        System.out.println("Thank You For Using Our Trip Planner");
                    }

                    default -> System.out.println("Invalid Choice!!! Try Again");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error! Invalid Date Format. Please Use this Format: dd/MM/yyyy");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
}
