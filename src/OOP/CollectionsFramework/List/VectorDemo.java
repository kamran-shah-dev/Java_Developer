package CollectionsFramework.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) throws InterruptedException {
        // Vector is just like an Arraylists. It's a legacy class exists before collections but
        // Only difference is it is thread safe which comes with its own overhead.
        // By default, when initially capacity reaches and new element is added, the size of the
        // vector gets doubled.

        Vector<Integer> vector = new Vector<>();
        Vector<Integer> vector1 = new Vector<>(5); // Initial capacity
        Vector<Integer> vector2 = new Vector<>(5, 4);
        // Initial capacity with capacity increment

        Vector<Integer> vector4 = new Vector<>(Arrays.asList(1,2,3,4,5)); // Create using collection

        System.out.println(vector4.capacity()); // Unlike arraylist we can directly check vector's capacity



        // Example
        ArrayList<Integer> arrayList = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                arrayList.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                arrayList.add(i);
            }
        });


        t1.start();t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw e;
        }

        System.out.println(arrayList.size());

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                vector.add(i);
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                vector.add(i);
            }
        });


        t3.start();t4.start();
        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw e;
        }

        System.out.println(vector.size());

        /*
            arrayList size: 766 because it's not thread safe
            vector size: 1000 - It's thread safe so two threads cannot access same element at same time
        */
    }
}
