package CollectionsFramework.List.ArrayList;

import java.util.ArrayList;
import java.util.List;
// initial capacity of arraylist is 10 as it fills new elements will resize the arraylist
// the new array will be 1.5 times the size or the previous array (Growth Factor)
// All elements then get copied in the new array so this operation is of O(n) time complexity.
// Once an element is removed, all elements right to the removed element will be brought back by one position
// we can keep the initial capacity: List<Integer> list = new ArrayList<>(initial capacity: 100000);
public class ListFunctionsAll {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        // Add element
//        list.add(9); // added at index 0
//        list.add(6);
//        list.add(10);
//
//        list.add(3, 60); // 3rd index element will not be replaced it will move forward
//        // list.add(5, 100); // Error as the index till here is 3 and next value can be added at 4
//        list.add(0, 8);
//
//        // Replace element at an index
//        list.set(3, 70); // replace element at index 3 with 70
//
//        // Remove
//        list.remove(3); // remove an element by index
//        // we can remove an element by value as well
//        // for strings, characters and doubles we can easily remove them but when it comes to int then
//        // Intellij will take that as index not value. So do remove an integer by value from a list we use
//        // Wrappper Classses.
//        list.remove(Integer.valueOf(10)); // Use of wrapper class.
//
//        // After an element removal we can trim capacity of the arraylist using
//        list.trimToSize(); // A function of ArrayList class.
//
//        // Get elements based on index
//        System.out.println(list.get(2)); // get element at index 2
//        System.out.println(list.contains(6)); // check an element (it's value not index)
//
//        // simple for loop
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i) + "  ");
//        }
//
//
//        // For each loop on list
//        for (int i : list) {
//            System.out.print(i + "   ");
//        }
//
//        System.out.println(list); // Print as list
//
//
//        // Creating arraylist
//        List<Integer> alist = new ArrayList<>();
//        alist.add(5);
//        System.out.println(alist.getClass().getName());
//
//        List<Integer> blist = Arrays.asList(1,2,3,4,5); // Returns a fixed length list where we cannot add or remove values
//                                                        // we can just update values
//        // blist.add(5);
//        // blist.remove(2);
//        System.out.println(blist.getClass().getName());
//
//        Integer[] arr = {1,2,3,4,5};
//        List<Integer> clist = Arrays.asList(arr); // Returns a fixed length list where we cannot add or remove values
//                                                    // we can just update values
//        // clist.add(5);
//
//        // In order to have add or remove functionalities we need to convert them to array list
//        List<Integer> list2 = new ArrayList<>(clist);
//        list2.add(89);
//
//
//        System.out.println(clist.getClass().getName());
//
//
//        List<String> newArrList = List.of("Jumma");
//        // Here we cannot even replace the elements.
//
//        // Add all method is used to provide a complete list in constructor of the arrayList
//        List<String> newStringArrList = new ArrayList<>();
//        newStringArrList.addAll(newArrList);
//        // or simply
//        List<String> newStringArrList2 = new ArrayList<>();
//        newStringArrList.addAll(List.of("Monday"));
//        newStringArrList.remove("Monday"); // remove by value


        // converting a list to array
        List<Integer> intArr = new ArrayList<>();
        intArr.add(3);
        intArr.add(2);

        // To Sort a list at thsi point we will use list.sort

        intArr.sort(null);

        Object[] array = intArr.toArray();
        Integer[] integerArray = intArr.toArray(new Integer[0]); // new Integer[0] just tells which type of array is this


        System.out.println(array.toString());
            for (Integer i : integerArray) {
                System.out.println(i);
            }

        for (Object i : integerArray) {
            System.out.println((int) i);
        }

    }
}
