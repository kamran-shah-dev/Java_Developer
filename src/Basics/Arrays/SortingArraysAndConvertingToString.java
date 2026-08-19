package Basics.Arrays;

import java.util.Arrays;
import java.util.Collections;

public class SortingArraysAndConvertingToString {
    public static void main(String[] args) {
        int[] array = new int[] {1,0,9,4,2};
        System.out.println("Unsorted: " + Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("Sorted array (Increasing Order): " + Arrays.toString(array));

        //Arrays.sort(array, 0, array.length, Collections.reverseOrder()); // Does not work for primitive types
        System.out.println("Sorted array (Reverse Order): " + Arrays.toString(array));

        // Sorting String Arrays
        String[] students = {"Zahid" , "Adnan" , "Javed"};
        System.out.println("Unsorted: " + Arrays.toString(students));
        Arrays.sort(students);
        System.out.println("Sorted array: " + Arrays.toString(students));

        Arrays.sort(students, 0, students.length, Collections.reverseOrder()); // Works only for Reference types
        System.out.println("Sorted array (Reverse Order): " + Arrays.toString(students));

        // Sorting 2D Arrays
        int[][] array2D = new int[][] {{9,0,1,4,5},{6,1,7,3,0}};
        System.out.println("Before sorting: ");
        for (int[] arrays: array2D) {
            System.out.println(Arrays.toString(arrays));
        }

        for (int[] arrays : array2D) {
            Arrays.sort(arrays);
        }

        System.out.println("After sorting: ");
        for (int[] arrays: array2D) {
            System.out.println(Arrays.toString(arrays));
        }
    }
}
