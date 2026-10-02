package DateAndTime;
import java.time.LocalDateTime;

public class LocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        // prints date and time as: yyyy-mm-dd HH:MM:SS:NS

        System.out.println(localDateTime.toLocalDate()); // prints only date
        System.out.println(localDateTime.toLocalTime()); // prints only local time
        System.out.println(localDateTime.getDayOfMonth()); // Day of month
        System.out.println(localDateTime.getDayOfWeek()); // Day of week (Monday, Tuesday etc)
        System.out.println(localDateTime.getDayOfYear()); // Day of year i.e 275

        System.out.println(localDateTime.getHour()); // Hour
        System.out.println(localDateTime.getMinute()); // Minute
        System.out.println(localDateTime.getMonth());  // Month
        System.out.println(localDateTime.getSecond()); // Second
        System.out.println(localDateTime.getYear()); // Year

    }
}
