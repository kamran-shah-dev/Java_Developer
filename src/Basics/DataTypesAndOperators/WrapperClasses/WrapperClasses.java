package Basics.DataTypesAndOperators.WrapperClasses;

import java.util.ArrayList;
import java.util.List;

public class WrapperClasses {
    public static void main(String[] args) {

        /*
         * STRUCTURE 1: Autoboxing & Unboxing
         * Autoboxing: Automatic conversion of primitive types to their wrapper class objects.
         * Unboxing: Automatic conversion of wrapper class objects back to primitive types.
         *
         * Primitive -> Wrapper (Autoboxing)
         * Wrapper   -> Primitive (Unboxing)
         */
        int primitiveInt = 25;
        Integer boxedInt = primitiveInt;       // Autoboxing: int -> Integer
        int unboxedInt = boxedInt;             // Unboxing: Integer -> int

        System.out.println("1. Autoboxing & Unboxing:");
        System.out.println("   Boxed Integer: " + boxedInt);
        System.out.println("   Unboxed int: " + unboxedInt);

        /*
         * STRUCTURE 2: Explicit Boxing & Unboxing (valueOf and xxxValue methods)
         * Using factory methods and instance methods explicitly instead of compiler magic.
         *
         * Wrapper.valueOf(primitive) -> Wrapper Object
         * wrapperObj.xxxValue()       -> Primitive Value
         */
        double primitiveDouble = 99.95;
        Double explicitBoxed = Double.valueOf(primitiveDouble); // Explicit Boxing
        double explicitUnboxed = explicitBoxed.doubleValue();    // Explicit Unboxing

        System.out.println("\n2. Explicit Boxing & Unboxing:");
        System.out.println("   Double object: " + explicitBoxed);
        System.out.println("   Extracted primitive double: " + explicitUnboxed);

        /*
         * STRUCTURE 3: Parsing Strings to Primitives (parseXxx methods)
         * Converts string representations of numbers/booleans into raw primitive types.
         *
         * Wrapper.parseXxx(String) -> primitive
         */
        String intString = "1024";
        String doubleString = "3.14159";
        String boolString = "true";

        int parsedInt = Integer.parseInt(intString);
        double parsedDouble = Double.parseDouble(doubleString);
        boolean parsedBool = Boolean.parseBoolean(boolString);

        System.out.println("\n3. Parsing Strings to Primitives:");
        System.out.println("   Parsed int: " + parsedInt);
        System.out.println("   Parsed double: " + parsedDouble);
        System.out.println("   Parsed boolean: " + parsedBool);

        /*
         * STRUCTURE 4: Converting Strings to Wrapper Objects (valueOf methods)
         * Converts string representations directly into wrapper object instances.
         *
         * Wrapper.valueOf(String) -> Wrapper Object
         */
        Integer objFromStr = Integer.valueOf("500");
        Float floatObjFromStr = Float.valueOf("12.34f");

        System.out.println("\n4. Strings to Wrapper Objects:");
        System.out.println("   Integer object from string: " + objFromStr);
        System.out.println("   Float object from string: " + floatObjFromStr);

        /*
         * STRUCTURE 5: Static Utility Methods & Constants
         * Built-in helper methods for range limits, conversions, and checks.
         *
         * Wrapper.MAX_VALUE / Wrapper.MIN_VALUE
         * Character.isDigit(), Character.isLetter(), Character.toUpperCase()
         * Integer.toBinaryString(), Integer.compare()
         */
        System.out.println("\n5. Utility Methods & Constants:");
        System.out.println("   Integer Range: [" + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + "]");
        System.out.println("   Binary representation of 42: " + Integer.toBinaryString(42));
        System.out.println("   Character 'a' isDigit: " + Character.isDigit('a'));
        System.out.println("   Character 'a' toUpperCase: " + Character.toUpperCase('a'));

        /*
         * STRUCTURE 6: Use in Generics & Collections
         * Java Generics only accept Object types, making Wrapper classes mandatory for primitives.
         *
         * List<WrapperClass> list = new ArrayList<>();
         */
        List<Integer> numberList = new ArrayList<>();
        numberList.add(10); // Automatically autoboxed to Integer.valueOf(10)
        numberList.add(20);
        numberList.add(30);

        System.out.println("\n6. Collections with Generics:");
        for (int num : numberList) { // Automatically unboxed to int
            System.out.print("   Item: " + num + " ");
        }
        System.out.println();

        /*
         * STRUCTURE 7: Integer Cache Quirk (Caching between -128 and 127)
         * Java caches Integer objects in the range [-128, 127]. Outside this range,
         * '==' checks reference identity, while '.equals()' checks content equality.
         */
        Integer cacheA = 100;
        Integer cacheB = 100;
        Integer nonCacheA = 200;
        Integer nonCacheB = 200;

        System.out.println("\n7. Integer Cache Behavior:");
        System.out.println("   100 == 100 (Cached range): " + (cacheA == cacheB));             // true (same object)
        System.out.println("   200 == 200 (Outside cache): " + (nonCacheA == nonCacheB));       // false (different objects)
        System.out.println("   200.equals(200) (Value check): " + nonCacheA.equals(nonCacheB)); // true
    }
}
