package Basics.Arrays;

import java.util.Objects;

public class ArraysInJava {
    public static void main(String[] args) {
        // One dimensional Array
        int[] array = {1,2,3,4,5};
        int[] array1 = new int[4];
        int[] array2 = new int[] {1,2,3,4,5};

        // Multidimensional Arrays (Jagged arrays and Strict rectangular matrices)
        int[][] twoDJaggedArray = {{1,2,3}, {1,2}}; // Jagged Array (All rows have different length)
        int[][] twoDJaggedArray1 = new int[5][]; // 5 Arrays of Arrays
        int[][] twoDArray = {{4,5,6}, {7,8,9}}; // Matrix with literals
        int[][] twoDArray1 = new int[5][4]; // A 5x4 Matrix

        // Reference type Arrays (An object type array can store any type of values)
        Object[] referenceArray = new Object[] {new Object(), "Alpha is Beta" , new int[5]};

        /*
            Note: If an array is created but not initialized, by default it's all indices will have the value
            0.
            In case of reference data type arrays, if not initialized, the default values will be null.
            Time Complexities in Array:
            1. Accessing an Element: O(1)
            2. Search an element: O(n) -> if array is not sorted | O(log n) -> if array is sorted
            3. Adding an element: O(1) -> if adding at the end | O(n) -> if adding at the middle or beginning
        */
        // Index of arrays are of type int so a compatible type can be used as the size of array
        long size = 24L;
        //int[] newArray = new int[size]; // Compilation error because array size expects int value
                                        // So here typecasting is necessary

        int[] newArray = new int[(int)size]; // Explicit conversion (long -> int) - Narrowing
        short sizeOfArray = 23;
        int[] newArray1 = new int[sizeOfArray]; // Implicit conversion (short -> int) - Widening


        // Empty arrays
        /*
            int[] emptyArray = new int[0]; // Array with no elements
            int[] emptyArray1 = {}; // Array with no elements

            emptyArray[0] = 5; // It will throw indexOutOfBound exception because, the array is empty
            int value = emptyArray1[0]; // It will throw indexOutOfBound exception too because, the array is empty
        */

        // Iterating through arrays (For loop and For-each loop)
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + " , ");
            }
        }

        // Enhanced (for-each) loop
        for (int value : array) {
            System.out.println("\n" + value + " ");
        }

        // Iterating through jagged / to dimensional arrays
        System.out.println("Iterating through 2D Arrays");
        for (int i = 0; i < twoDArray.length; i++) {
            for (int j = 0; j < twoDArray[i].length;j++) {
                if (j == twoDArray[i].length - 1) {
                    System.out.print(twoDArray[i][j]);
                } else {
                    System.out.print(twoDArray[i][j] + " , ");
                }
            }
            System.out.println();
        }


        // Enhanced (for-each) loop for 2D arrays
        System.out.println("Using enhanced for loop to print 2D arrays");
        for (int[] i : twoDArray) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
