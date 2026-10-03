package DateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LocalTimeAndDateFormatWithUserSpecificFormat {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        LocalDate localDate = LocalDate.now();
        System.out.println("""
                Enter the format you would like to print the date in
                dd for date,\s
                M for month, MM for zero-padded month, MMM for abbreviated month, MMMM for full name,\s
                yy or yyyy for year""");

        String format = input.nextLine();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        System.out.println("Current date in your requested format is: " + localDate.format(dateTimeFormatter));


        LocalTime localTime = LocalTime.now();
        System.out.println("""
                Enter the format you would like to print the time in
                H for Hour of day (0-23), HH for Zero-padded hour of day (00-23),\s
                h for Hour of am/pm (1-12), hh for Zero-padded hour of am/pm (01-12)\s
                m for Minute of hour (0-59)
                mm for Zero-padded minute of hour (00-59)
                s for Second of minute (0-59), ss for Zero-padded second of minute (00-59)""");

        String timeFormat = input.nextLine();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeFormat);
        System.out.println("Current time in your requested format is: " + localTime.format(dtf));


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("To print date and time together enter the date and time format together (date then time): ");

        String dateTimeFormat = input.nextLine();

        DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern(dateTimeFormat);
        System.out.println("Current date and time in your requested format is: " + localDateTime.format(dateTimeFormater));
    }
}
