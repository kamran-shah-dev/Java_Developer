package CollectionsFramework.Problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorVsCopyOnWriteArrayList {
    public static void main(String[] args) {
        // Scenario: if we directly use forEach and get and remove elements in that and the data structure we are
        // Using is not a copyOnWriteArrayList, then it will through an exception for concurrent modification
        // and to resolve that issue we have to opt for CopyOnWriteArrayList. But, CopyOnWriteArrayList
        // Besides having many implementations will update the references after the loop finishes.
        // So, Is there are way that we directly remove elements on the spot without using CopyOnWriteArrayList

        // Yes, we can use iterator and it's functions

        List<Integer> newList = new ArrayList<>();
        newList.add(1);
        newList.add(2);
        newList.add(3);
        newList.add(4);
        newList.add(5);
        newList.add(6);

        /* for (Integer i : newList) {
            if (i % 2 == 0) {
                newList.remove(i);
            }
        } */

        // we can use the removeIf function
        // newList.removeIf(n -> n % 2 == 0);

        // We can use the iterator as well

        Iterator<Integer> it = newList.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }

        // ListIterator has more functionalities
        ListIterator<Integer> listIterator = newList.listIterator();
        // All methods of listIterator
    }
}
