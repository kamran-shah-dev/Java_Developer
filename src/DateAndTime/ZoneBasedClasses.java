package DateAndTime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ZoneBasedClasses {
    public static void main(String[] args) {
        ZoneId newZone = ZoneId.of("Asia/Karachi");
        System.out.println("Zone Id: " + newZone);

        // ZoneOffset (different from the UTC)
        ZoneOffset zoneOffset = ZoneOffset.of("+05:20"); // Here offset is 5 hours and 30 minutes + utc
        System.out.println("UTC + 5:20" + zoneOffset);

        // Using OffsetDateTime Class
        LocalDateTime localDateTime = LocalDateTime.now();

        ZoneOffset offset = ZoneOffset.ofHoursMinutes(-5, -45);

        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, offset);

        System.out.println("Offset Date time: " + offsetDateTime);


        // Printing ZonedDateTime for employees of different timezones
        ZonedDateTime meetingTime = ZonedDateTime.now(ZoneId.of("US/Pacific"));

        String[] timeZones = {"Asia/Aden", "America/Cuiaba", "Etc/GMT+9", "Etc/GMT+8", "Africa/Nairobi",
        "America/Marigot", "Asia/Aqtau", "Pacific/Kwajalein", "America/El_Salvador", "Asia/Pontianak"};

        DateTimeFormatter zoneDateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mm:ss a z");

        System.out.println("Original Meeting Time: " + meetingTime.format(zoneDateTimeFormatter));

        for (String zone : timeZones) {
            ZonedDateTime otherZone = ZonedDateTime.now(ZoneId.of(zone));

            System.out.println("Meeting time: " + otherZone.format(zoneDateTimeFormatter));
        }
    }
}
