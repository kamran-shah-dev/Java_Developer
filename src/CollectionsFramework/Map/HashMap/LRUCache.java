package CollectionsFramework.Map.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int Capacity) {
        super(Capacity, 0.80f, true);
        capacity = Capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        String[] processes = {"P1" , "P2" , "P3" , "P4" , "P5"};
        int[] processNum = {2,1,4,6,5};

        LRUCache<String, Integer> processesInCache = new LRUCache<>(3);
         for (int i = 0; i < processes.length; i++) {
            processesInCache.put(processes[i] , processNum[i]);
            if(i % 2 == 0 && i != 0) {
                processesInCache.get(processes[i - 1]);
            }
        }

        System.out.println("Final Cache Shape");
        System.out.println(processesInCache);
    }
}
