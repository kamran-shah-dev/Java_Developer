package Basics.DataTypesAndOperators.Problems.Hard;

import java.util.Scanner;

public class problem009 {
    public static void main(String[] args) {
        /*
            Create an electricity-bill calculator with different per-unit rates for consumption ranges. The price must be progressive,
            not simply one rate applied to every unit.
        */

        Scanner input = new Scanner(System.in);

        System.out.print("Enter total units you used this month: ");
        int unitsUsed = input.nextInt();

        int totalPrice = 0;
        int pricePerUnit = 5;

        if (unitsUsed < 0) {
            System.out.println("Negative Bill.");
        } else if(unitsUsed <= 100) {
            totalPrice += unitsUsed * pricePerUnit;
            System.out.println("Total Bill = " + totalPrice);
        } else {
            if (unitsUsed < 200) {
                int remainingUnits = unitsUsed % 100;
                totalPrice += 100 * 5;
                totalPrice += remainingUnits * 8;
                System.out.println("Total Bill = " + totalPrice);
            } else {
                int remainingUnits = unitsUsed - 200;
                totalPrice += 100 * 5;
                totalPrice += 100 * 8;
                totalPrice += remainingUnits * 12;
                System.out.println("Total Bill = " + totalPrice);
            }
        }
    }
}
