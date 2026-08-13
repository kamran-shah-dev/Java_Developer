package BasicsAndFundamentals.Operators;

public class operatorsInJava {
    /*
     Operators in Java
     1. Arithmetic Operators - used to perform basic mathematical operations on variables and values.
        In the following examples int a = 10; and int b = 5;
        a. +  (addition)       - adds two operands                         => a + b   // 15
        b. -  (subtraction)    - subtracts right operand from left operand => a - b   // 5
        c. *  (multiplication) - multiplies two operands                   => a * b   // 50
        d. /  (division)       - divides left operand by right operand     => a / b   // 2
        e. %  (modulus)        - returns the remainder of a division       => a % b   // 0

     2. Relational Operators - used to compare two values, they always return a boolean result (true/false).
        In the following examples int a = 10; and int b = 5;
        a. ==  (equal to)                 - checks if two values are equal         => a == b   // false
        b. !=  (not equal to)             - checks if two values are not equal     => a != b   // true
        c. >   (greater than)             - checks if left is greater than right   => a > b    // true
        d. <   (less than)                - checks if left is less than right      => a < b    // false
        e. >=  (greater than or equal to) - checks if left >= right                => a >= b   // true
        f. <=  (less than or equal to)    - checks if left <= right                => a <= b   // false

     3. Logical Operators - used to combine multiple boolean expressions.
        In the following examples boolean x = true; and boolean y = false;
        a. &&  (logical AND) - true only if both expressions are true, uses short-circuit evaluation
           => if (a > b && b < c) { ... }
        b. ||  (logical OR)  - true if at least one expression is true, uses short-circuit evaluation
           => if (a > b || b < c) { ... }
        c. !   (logical NOT) - reverses/negates the boolean value of an expression
           => if (!(a > b)) { ... }

     4. Assignment Operators - used to assign values to a variable, often combined with another operation.
        a. =   (assign)              - assigns right operand to left variable      => a = 10
        b. +=  (add and assign)      - adds right operand to variable and assigns  => a += 5
        c. -=  (subtract and assign) - subtracts right operand and assigns         => a -= 2
        d. *=  (multiply and assign) - multiplies right operand and assigns        => a *= 3
        e. /=  (divide and assign)   - divides variable by right operand           => a /= 2
        f. %=  (modulus and assign)  - takes modulus and assigns                   => a %= 4

     5. Unary Operators - operate on a single operand to manipulate data or perform calculations.
        In the following example int a = 10;
        a. +  (unary plus)    - indicates a positive value          => +a   // 10
        b. -  (unary minus)   - negates the value of an operand     => -a   // -10
        c. ++ (increment)     - increases the value of a variable by 1  => a++ or ++a
        d. -- (decrement)     - decreases the value of a variable by 1  => a-- or --a

     6. Ternary Operator - a shorthand form of the if-else conditional statement, uses three operands.
        Syntax: condition ? expression1 : expression2;
        In the following example int a = 10; and int b = 20;
        => int max = (a > b) ? a : b;   // assigns a if a > b, otherwise assigns b (result: 20)

     7. Bitwise Operators - operate directly on the individual bits of integer types (byte, short, int, long).
        In the following examples int a = 5; (0101 in binary) and int b = 3; (0011 in binary)
        a. &  (bitwise AND) - sets each bit to 1 if both corresponding bits are 1  => a & b   // 1  (0001)
        b. |  (bitwise OR)  - sets each bit to 1 if at least one bit is 1         => a | b   // 7  (0111)
        c. ^  (bitwise XOR) - sets each bit to 1 if exactly one bit is 1         => a ^ b   // 6  (0110)
        d. ~  (bitwise complement/NOT) - inverts all bits of the operand         => ~a      // -6

     8. Shift Operators - shift the bits of a number left or right, effectively multiplying/dividing by powers of 2.
        In the following example int a = 5; (0000 0101 in binary)
        a. <<  (left shift)          - shifts bits left, fills with 0s on the right   => a << 1   // 10
        b. >>  (signed right shift)  - shifts bits right, preserves the sign bit      => a >> 1   // 2
        c. >>> (unsigned right shift)- shifts bits right, fills with 0s regardless of sign => a >>> 1  // 2
    */
}
