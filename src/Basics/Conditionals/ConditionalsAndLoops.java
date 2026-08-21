package Basics.Conditionals;

public class ConditionalsAndLoops {
    public static void main(String[] args) {

        /*
         * STRUCTURE: Simple If Statement
         *
         * if (condition) {
         *     // code executed if condition is true
         * }
         */
        int score = 85;
        if (score >= 50) {
            System.out.println("Simple If: Passed the minimum threshold.");
        }

        /*
         * STRUCTURE: If-Else Statement
         *
         * if (condition) {
         *     // code executed if condition is true
         * } else {
         *     // code executed if condition is false
         * }
         */
        int age = 16;
        if (age >= 18) {
            System.out.println("If-Else: Eligible to vote.");
        } else {
            System.out.println("If-Else: Not eligible to vote.");
        }

        /*
         * STRUCTURE: If-Else-If Ladder
         *
         * if (condition1) {
         *     // code executed if condition1 is true
         * } else if (condition2) {
         *     // code executed if condition2 is true
         * } else {
         *     // code executed if all conditions are false
         * }
         */
        int marks = 78;
        if (marks >= 90) {
            System.out.println("Else-If Ladder: Grade A");
        } else if (marks >= 75) {
            System.out.println("Else-If Ladder: Grade B");
        } else if (marks >= 60) {
            System.out.println("Else-If Ladder: Grade C");
        } else {
            System.out.println("Else-If Ladder: Grade F");
        }

        /*
         * STRUCTURE: Traditional For Loop
         *
         * for (initialization; condition; update) {
         *     // code executed repeatedly while condition is true
         * }
         */
        System.out.print("For Loop: ");
        for (int i = 1; i <= 3; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        /*
         * STRUCTURE: Enhanced For Loop (For-Each)
         *
         * for (DataType element : collectionOrArray) {
         *     // code executed for each element
         * }
         */
        String[] fruits = {"Apple", "Banana", "Cherry"};
        System.out.print("For-Each Loop: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        /*
         * STRUCTURE: While Loop (Entry-controlled)
         *
         * while (condition) {
         *     // code executed repeatedly while condition is true
         * }
         */
        int whileCounter = 1;
        System.out.print("While Loop: ");
        while (whileCounter <= 3) {
            System.out.print(whileCounter + " ");
            whileCounter++;
        }
        System.out.println();

        /*
         * STRUCTURE: Do-While Loop (Exit-controlled)
         *
         * do {
         *     // code executed at least once, then repeated while condition is true
         * } while (condition);
         */
        int doWhileCounter = 1;
        System.out.print("Do-While Loop: ");
        do {
            System.out.print(doWhileCounter + " ");
            doWhileCounter++;
        } while (doWhileCounter <= 3);
        System.out.println();
    }
}
