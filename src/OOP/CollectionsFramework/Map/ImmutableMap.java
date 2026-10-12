package CollectionsFramework.Map;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMap {
    public static void main(String[] args) {
        // 1. Having an unmodifiable view of a Map.
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Kamran" , 99);
        map1.put("Ilyas" , 100);
        map1.put("Farhan" , 120);

        Map<String , Integer> map2 = Collections.unmodifiableMap(map1);
        // But it creates another map map2 means we can edit map1, put values, update values etc

        // 2. Creating an immutable map from Map.of(k1, v1, k2, v2, .....)
        Map<String , Integer> map3 = Map.of("Kamran" , 99, "Ilyas" , 100);
        // map3.put("Farhan" , 120); Throws an exception because it's an unmodifiable map
        // It has a limitation that it can only take 10 entries so to resolve it we use
        // Map.ofEntries(Map.entry(k1, v1) , Map.entry(k2, v2));
        Map<String , Integer> map4 = Map.ofEntries(Map.entry("Kamran" , 99) , Map.entry("Ilyas" , 100));

    }
}
