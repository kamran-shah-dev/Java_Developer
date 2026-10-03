package DateAndTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ZonedDateAndTimeManipulation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("Current format: " + zonedDateTime);

        while (true) {
            System.out.print("""
                    Enter 1 for time manipulation (add or delete) to current time
                    Enter 2 to change the zone
                    Any other key to exit
                    Your Choice:\s""");
            String tOrZManipulation = input.nextLine();

            if (tOrZManipulation.equals("1")) {
                System.out.println("Enter which unit of time you want to add/delete:\n" +
                        "d - days, M - months, y - years, h - hours, m - minutes, s - seconds, w - weeks");
                String unitOpt = input.nextLine();

                System.out.print("Enter the quantity (i.e 3): ");
                long qty = Long.parseLong(input.nextLine());

                System.out.print("Enter the option a for add & d for delete: ");
                String addOrDel = input.nextLine();

                ZonedDateTime newDateTime;

                if (addOrDel.equals("a")) {
                    switch (unitOpt) {
                        case "d" -> newDateTime = zonedDateTime.plusDays(qty);
                        case "M" -> newDateTime = zonedDateTime.plusMonths(qty);
                        case "y" -> newDateTime = zonedDateTime.plusYears(qty);
                        case "h" -> newDateTime = zonedDateTime.plusHours(qty);
                        case "m" -> newDateTime = zonedDateTime.plusMinutes(qty);
                        case "s" -> newDateTime = zonedDateTime.plusSeconds(qty);
                        case "w" -> newDateTime = zonedDateTime.plusWeeks(qty);
                        default -> {
                            System.out.println("Invalid Input!!");
                            continue;
                        }
                    }
                } else if (addOrDel.equals("d")) {
                    switch (unitOpt) {
                        case "d" -> newDateTime = zonedDateTime.minusDays(qty);
                        case "M" -> newDateTime = zonedDateTime.minusMonths(qty);
                        case "y" -> newDateTime = zonedDateTime.minusYears(qty);
                        case "h" -> newDateTime = zonedDateTime.minusHours(qty);
                        case "m" -> newDateTime = zonedDateTime.minusMinutes(qty);
                        case "s" -> newDateTime = zonedDateTime.minusSeconds(qty);
                        case "w" -> newDateTime = zonedDateTime.minusWeeks(qty);
                        default -> {
                            System.out.println("Invalid Input!!");
                            continue;
                        }
                    }
                } else {
                    System.out.println("Invalid Input");
                    continue;
                }

                System.out.print("Enter the date and time format now: ");
                String timeDateFormat = input.nextLine();

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeDateFormat);
                System.out.println("Updated data and time: " + newDateTime.format(dateTimeFormatter));
            } else if (tOrZManipulation.equals("2")) {
                System.out.println("Choose from following zone options: ");
                Object[] zoneIds = ZoneId.getAvailableZoneIds().toArray();

                for (Object zone : zoneIds) {
                    System.out.println(zone);
                }

                System.out.print("Enter new Zone: ");
                String newZone = input.nextLine();

                ZonedDateTime newDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of(newZone));

                DateTimeFormatter formattedZoneDateTime = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy  hh:mm:ss a zzz");

                System.out.println("Date and time with new zone is: " + newDateTime.format(formattedZoneDateTime));
            } else {
                break;
            }
        }
    }
}
