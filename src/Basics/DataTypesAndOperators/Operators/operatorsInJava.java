package Basics.DataTypesAndOperators.Operators;

public class operatorsInJava {
    public static void main(String[] args) {
        // Arithmetic Operators
        /*
            -> Enclosing code in {} creates a new local block scope.
            -> Variables declared inside that block only exist until the closing }.
            -> It combines multiple statements into a single logical unit.
        */

        System.out.println("--- Arithmetic Operators ---");
        // Addition (+ operator)
        {
            int a = 6, b = 7;
            int c = a + b;
            System.out.println(a + " + " + b + " = " + c);
        }

        // Subtraction (- operator)
        {
            int a = 6, b = 7;
            int c = a - b;
            System.out.println(a + " - " + b + " = " + c);
        }

        // Multiplication (* operator)
        {
            int a = 6, b = 7;
            int c = a * b;
            System.out.println(a + " * " + b + " = " + c);
        }

        // Division (/ operator)
        {
            int a = 6, b = 2;
            int c = a / b;
            System.out.println(a + " / " + b + " = " + c);
        }

        // Modulo (Remainder) (% operator)
        {
            int a = 6, b = 7;
            int c = a % b;
            System.out.println(a + " % " + b + " = " + c);
        }


        // --- 2. RELATIONAL OPERATORS ---

        {
            int a = 10;
            int b = 5;
            System.out.println("\n--- Relational Operators ---");

            // a. Equal to (==)
            boolean isEqual = (a == b); // Checks if 10 equals 5
            System.out.println("a == b: " + isEqual); // Prints false

            // b. Not equal to (!=)
            boolean notEqual = (a != b); // Checks if 10 is not equal to 5
            System.out.println("a != b: " + notEqual); // Prints true

            // c. Greater than (>)
            boolean isGreater = (a > b); // Checks if 10 is strictly greater than 5
            System.out.println("a > b: " + isGreater); // Prints true

            // d. Less than (<)
            boolean isLess = (a < b); // Checks if 10 is strictly less than 5
            System.out.println("a < b: " + isLess); // Prints false

            // e. Greater than or equal to (>=)
            boolean isGreaterOrEqual = (a >= b); // Checks if 10 is greater than or equal to 5
            System.out.println("a >= b: " + isGreaterOrEqual); // Prints true

            // f. Less than or equal to (<=)
            boolean isLessOrEqual = (a <= b); // Checks if 10 is less than or equal to 5
            System.out.println("a <= b: " + isLessOrEqual); // Prints false
        }

        // --- 3. LOGICAL OPERATORS ---
        {
            boolean x = true;
            boolean y = false;
            System.out.println("\n--- Logical Operators ---");

            // a. Logical AND (&&)
            // True only if both sides are true. Uses short-circuit (stops if first is false).
            boolean andResult = (x && y); // true && false
            System.out.println("x && y: " + andResult); // Prints false

            // b. Logical OR (||)
            // True if at least one side is true. Uses short-circuit (stops if first is true).
            boolean orResult = (x || y); // true || false
            System.out.println("x || y: " + orResult); // Prints true

            // c. Logical NOT (!)
            // Inverts the boolean value. Turns true to false, and false to true.
            boolean notX = !x; // Inverts true to false
            boolean notY = !y; // Inverts false to true
            System.out.println("!x: " + notX); // Prints false
            System.out.println("!y: " + notY); // Prints true
        }

        // --- 4. ASSIGNMENT OPERATORS ---
        {
            System.out.println("--- Assignment Operators ---");

            // a. Assign (=)
            int a = 10; // Assigns the value 10 to the variable 'a'
            System.out.println("Initial a = " + a); // Prints 10

            // b. Add and assign (+=)
            a += 5; // Equivalent to: a = a + 5 (10 + 5)
            System.out.println("a += 5  => " + a); // Prints 15

            // c. Subtract and assign (-=)
            a -= 2; // Equivalent to: a = a - 2 (15 - 2)
            System.out.println("a -= 2  => " + a); // Prints 13

            // d. Multiply and assign (*=)
            a *= 3; // Equivalent to: a = a * 3 (13 * 3)
            System.out.println("a *= 3  => " + a); // Prints 39

            // e. Divide and assign (/=)
            a /= 2; // Equivalent to: a = a / 2 (39 / 2 using integer division)
            System.out.println("a /= 2  => " + a); // Prints 19

            // f. Modulus and assign (%=)
            a %= 4; // Equivalent to: a = a % 4 (Remainder of 19 / 4)
            System.out.println("a %= 4  => " + a); // Prints 3
        }

        // --- 5. UNARY & Ternary OPERATORS ---
        {
            System.out.println("\n--- Unary Operators ---");
            int num = 10;

            // a. Unary plus (+)
            int positiveNum = +num; // Indicates a positive value explicitly
            System.out.println("+num    => " + positiveNum); // Prints 10

            // b. Unary minus (-)
            int negativeNum = -num; // Negates the value (multiplies by -1)
            System.out.println("-num    => " + negativeNum); // Prints -10

            // c. Increment (++)
            // (PostFix)
            num++; // Increases the value of 'num' by 1 (10 becomes 11)
            System.out.println("num++   => " + num); // Prints 11
            // (Prefix)
            ++num; // Increases the value of 'num' by 1 (11 becomes 12)
            System.out.println("++num   => " + num); // Prints 12


            // d. Decrement (--)
            // (PostFix)
            num--; // Decreases the value of 'num' by 1 (11 becomes 10)
            System.out.println("num--   => " + num); // Prints 10
            // (Prefix)
            --num; // Increases the value of 'num' by 1 (10 becomes 9)
            System.out.println("--num   => " + num); // Prints 9

            // --- 6. TERNARY OPERATOR ---
            System.out.println("\n--- Ternary Operator ---");
            int x = 10;
            int y = 20;

            // Syntax: condition ? expression_if_true : expression_if_false;
            // Since (10 > 20) is false, it skips 'x' and picks 'y'
            int max = (x > y) ? x : y;
            System.out.println("max value between 10 and 20 is: " + max); // Prints 20
        }

        { // --- 7. BITWISE OPERATORS ---
            int a = 5; // Binary: 0101
            int b = 3; // Binary: 0011
            System.out.println("--- Bitwise Operators ---");

            // a. Bitwise AND (&)
            // if both bits are 1 then the resulting bit will be 1
            // 0101 & 0011 = 0001 (Decimal: 1)
            int andResult = a & b;
            System.out.println("a & b   => " + andResult);

            // b. Bitwise OR (|)
            // if a single bit is 1 then the resulting bit will be 1
            // 0101 | 0011 = 0111 (Decimal: 7)
            int orResult = a | b;
            System.out.println("a | b   => " + orResult);

            // c. Bitwise XOR (^)
            // if both bits are different (i.e 1,0 or 0,1) then the resulting bit will be 1, in case of same
            // it will be 0
            // 0101 ^ 0011 = 0110 (Decimal: 6)
            int xorResult = a ^ b;
            System.out.println("a ^ b   => " + xorResult);

            // d. Bitwise Complement/NOT (~)
            // Inverts the bits (i.e 1 becomes 0 and 0 becomes 1) (Two's complement)
            // Inverts 0101 to ...11111010 (Decimal: -6 due to Two's Complement)
            int notResult = ~a;
            System.out.println("~a      => " + notResult);
        }

        { // --- 8. SHIFT OPERATORS ---
            int a = 5; // Binary: 0000 0101
            System.out.println("\n--- Shift Operators ---");

            // a. Left Shift (<<)
            // Shifts bits left by 1 position (multiplies by 2). 0101 becomes 1010 (Decimal: 10)
            int leftShift = a << 1;
            System.out.println("a << 1  => " + leftShift);

            // b. Signed Right Shift (>>)
            // Shifts bits right by 1 position, keeping the sign bit. 0101 becomes 0010 (Decimal: 2)
            int signedRightShift = a >> 1;
            System.out.println("a >> 1  => " + signedRightShift);

            // c. Unsigned Right Shift (>>>)
            // Shifts bits right by 1 position, always filling the far left with a 0.
            int unsignedRightShift = a >>> 1;
            System.out.println("a >>> 1 => " + unsignedRightShift);
        }

        // Unsigned and Signed Right shift only differ in case of negative numbers
        { // --- SIGNED RIGHT SHIFT (>>) ---
            int negativeNum = -8;
            // Binary: 11111111 11111111 11111111 11111000 (starts with 1)

            int result = negativeNum >> 1;
            // The computer slides everything right and copies the '1' to the front.
            // Binary: 11111111 11111111 11111111 11111100

            System.out.println("-8 >> 1 => " + result); // Prints -4 (Math preserved!)
        }

        { // --- UNSIGNED RIGHT SHIFT (>>>) ---
            int negativeNum = -8;
            // Binary: 11111111 11111111 11111111 11111000

            int result = negativeNum >>> 1;
            // The computer slides everything right and FORCES a '0' to the front.
            // Binary: 01111111 11111111 11111111 11111100

            System.out.println("-8 >>> 1 => " + result); // Prints 2147483644 (Massive positive number!)
        }

    }
}
