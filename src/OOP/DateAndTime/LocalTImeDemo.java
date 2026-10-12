package DateAndTime;
import java.time.LocalTime;

public class LocalTImeDemo {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        System.out.println(time);
        // it displays time as: HH:MM:SS:NS
    }
}
