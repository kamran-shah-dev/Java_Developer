package CollectionsFramework.List;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>(List.of("Apple", "Banana", "Mango", "Orange", "Grapes"));

        Thread reader = new Thread(() -> {
            for (String fruit : list) {
                System.out.println(fruit);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread writer1 = new Thread(() -> {
            try {
                Thread.sleep(100);
                list.add("Kela");
                System.out.println("Item added to list");

                list.remove("Kela");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        reader.start();
        writer1.start();

        System.out.println(list);
    }
}
