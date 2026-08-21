package Basics.Conditionals;

public class SwitchCase {
    public static void main(String[] args) {
        /*
         * STRUCTURE 1: Traditional Switch Statement (Simple)
         * Uses case labels with break statements to prevent fall-through.
         *
         * switch (variable) {
         *     case value1:
         *         // statements
         *         break;
         *     case value2:
         *         // statements
         *         break;
         *     default:
         *         // fallback statements
         * }
         */
        int day = 3;
        System.out.print("1. Traditional Switch: ");
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Other day");
                break;
        }

        /*
         * STRUCTURE 2: Multi-Label Switch with Intentional Fall-Through
         * Grouping cases together or omitting 'break' to run shared logic.
         *
         * switch (variable) {
         *     case val1:
         *     case val2:
         *         // shared statements
         *         break;
         *     default:
         *         // fallback statements
         * }
         */
        int month = 4;
        System.out.print("2. Fall-through Grouping: ");
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("Winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Spring");
                break;
            default:
                System.out.println("Summer/Autumn");
                break;
        }

        /*
         * STRUCTURE 3: Arrow-Syntax Switch Statement (Java 14+)
         * Eliminates the need for 'break' statements and prevents accidental fall-through.
         *
         * switch (variable) {
         *     case val1 -> statement;
         *     case val2, val3 -> { block of statements };
         *     default -> fallback;
         * }
         */
        String role = "ADMIN";
        System.out.print("3. Arrow Switch Statement: ");
        switch (role) {
            case "ADMIN" -> System.out.println("Full Access");
            case "EDITOR", "AUTHOR" -> System.out.println("Write Access");
            case "VIEWER" -> System.out.println("Read-Only Access");
            default -> System.out.println("No Access");
        }

        /*
         * STRUCTURE 4: Switch Expression (Direct Value Assignment)
         * Evaluates to a value and can be assigned directly to a variable.
         *
         * DataType result = switch (variable) {
         *     case val1 -> returnedValue;
         *     case val2, val3 -> returnedValue;
         *     default -> fallbackValue;
         * };
         */
        String fruit = "APPLE";
        String color = switch (fruit) {
            case "APPLE", "CHERRY", "STRAWBERRY" -> "Red";
            case "BANANA", "LEMON" -> "Yellow";
            case "GRAPE", "BLUEBERRY" -> "Purple/Blue";
            default -> "Unknown Color";
        };
        System.out.println("4. Switch Expression (Assigned): " + fruit + " is " + color);

        /*
         * STRUCTURE 5: Switch Expression with 'yield' Keyword
         * Used when a case branch requires a multi-line block before returning a value.
         *
         * DataType result = switch (variable) {
         *     case val1 -> {
         *         // multi-line calculations
         *         yield calculatedValue;
         *     }
         *     default -> fallbackValue;
         * };
         */
        int code = 500;
        String status = switch (code) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> {
                System.out.print("5. Yield Switch (Logging intermediate state)... ");
                yield "Internal Server Error";
            }
            default -> "Unknown Status";
        };
        System.out.println("Result: " + status);

        /*
         * STRUCTURE 6: Pattern Matching with Switch (Java 21+)
         * Matches against types, handles null directly, and supports 'when' guards.
         *
         * switch (obj) {
         *     case null -> ...
         *     case Type1 t -> ...
         *     case Type2 t when (guardCondition) -> ...
         *     default -> ...
         * }
         */
        Object data = 42;
        String typeDescription = switch (data) {
            case null -> "Received null value";
            case Integer i when i > 100 -> "Large Integer: " + i;
            case Integer i -> "Standard Integer: " + i;
            case String s -> "String of length: " + s.length();
            case Double d -> "Double value: " + d;
            default -> "Other Object type: " + data.getClass().getSimpleName();
        };
        System.out.println("6. Pattern Matching Switch: " + typeDescription);
    }
}
