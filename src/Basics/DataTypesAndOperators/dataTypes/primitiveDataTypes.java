package Basics.DataTypesAndOperators.dataTypes;

public class primitiveDataTypes {

    public static void main(String[] args) {
        // byte - Used when memory optimization is needed, it ranges from -128 to 127 (Size 1 byte)
        byte age = 55;

        // short - use short for small integer data types ranging from -32,768 -> 32,767 (temperature values)
        // Size (2 bytes)
        short temperateInDegree = -21;

        // integer is generally used to store whole numbers (size 4 bytes)
        int population = 200000000;

        // long is used to store values that integers cannot handle (out of integer range) (Size 8 bytes)
        long distanceFromEarthToSun = 149_600_000;

        // float is used to store decimal values with lower precision (stores up to 7 decimal places)
        // size 4 bytes
        float productPrice = 98.09812345555f;

        // double is used to store decimal values with higher precision (stores up to 15-16 decimal places)
        // size 8 bytes
        double piValue = 3.141592653589793;

        // char is a datatype that is used to store a single character (size in java - 2 bytes , UTF 16 encoding)
        char keyPressed = 'a';

        // boolean is a single bit data type used to store (0,1) - (o represents false, and 1 represents true)
        boolean isValueGreater = true;
    }
}