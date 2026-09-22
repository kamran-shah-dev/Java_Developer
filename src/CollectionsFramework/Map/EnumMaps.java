package CollectionsFramework.Map;

import java.util.EnumMap;
import java.util.Map;

public class EnumMaps {
    public static void main(String[] args) {
        Map<Days, String> dailyRoutine = new EnumMap<>(Days.class);
        dailyRoutine.put(Days.Monday , "Java");
        dailyRoutine.put(Days.Tuesday, "Python");

        // To get the index of an enum element
        System.out.println(Days.Monday.ordinal());
        System.out.println(dailyRoutine.get(Days.Monday));
    }
}

enum Days {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}
