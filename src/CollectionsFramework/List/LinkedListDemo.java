package CollectionsFramework.List;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        linkedList.add(3, 45);
        System.out.println(linkedList);
//
//        System.out.println(linkedList.get(1));
//        System.out.println(linkedList.getFirst());
//        System.out.println(linkedList.getLast());
//
//        linkedList.push(90); // Add at first
//        System.out.println(linkedList);
//
//        linkedList.offer(56); // Add at the end
//        System.out.println(linkedList);
//
//        linkedList.remove(2); // Remove at index
//        System.out.println(linkedList);
//
//        linkedList.remove(); // Remove first
//        System.out.println(linkedList);
//
//        linkedList.removeFirst();
//        System.out.println(linkedList);
//
//        linkedList.removeLast();
//        System.out.println(linkedList);
//
//        linkedList.offerFirst(90); // Add at first
//        System.out.println(linkedList);
//
//        linkedList.offerLast(56); // Add at last
//        System.out.println(linkedList);
//
//        linkedList.removeFirstOccurrence(56);
//        System.out.println(linkedList);
//
//        linkedList.set(0 , 95);
//        System.out.println(linkedList);
//
//        linkedList.addLast(56);
//        System.out.println(linkedList);
//
//        linkedList.addFirst(90);
//        System.out.println(linkedList);
//
//
//        System.out.println(linkedList.peek());
//        System.out.println(linkedList.peekFirst());
//        System.out.println(linkedList.peekLast());
//
//
//        System.out.println(linkedList.contains(4));
//
//        // getFirst
//        System.out.println(linkedList.element());

//        linkedList.clear(); // Delete all elements
//        System.out.println(linkedList);

//        linkedList.sort((o1, o2) -> o2 - o1);
//        System.out.println(linkedList);
//
//        System.out.println(linkedList.poll()); // Remove and return
//        System.out.println(linkedList.pollFirst());
//        System.out.println(linkedList.pollLast());
//
//        System.out.println(linkedList);
//        linkedList.pop(); // remove first
//        System.out.println(linkedList);

        linkedList.removeIf(x -> x % 3 == 0);
        System.out.println(linkedList);

        List<Integer> listToRemove = new LinkedList<>(Arrays.asList(2,3));
        linkedList.removeAll(listToRemove);

        System.out.println(linkedList);

        List<Integer> listToAdd = new LinkedList<>(Arrays.asList(2,3,6,7,8,9,0,10));
        linkedList.addAll(listToAdd);

        System.out.println(linkedList);

        List<Integer> listToAdd1 = new LinkedList<>(Arrays.asList(11,55,66,78));
        linkedList.addAll(1, listToAdd1);
        System.out.println(linkedList);

//        List<Integer> subList = linkedList.subList(3,4);
//        System.out.println("SubLinked List : " + subList);
    }
}
