package Basics.DataTypesAndOperators.Problems.Hard;

import java.util.Scanner;

public class problem0010 {
    public static void main(String[] args) {
        /*
            Build an ATM simulation with balance, deposit, withdrawal, balance inquiry and exit.
            Prevent invalid withdrawals and keep the menu running until exit.
        */

        Scanner input = new Scanner(System.in);

        System.out.println("-*-*-*-*-*-*-      Welcome To Our Bank      -*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-      Menu                     -*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-      Deposit - 1              -*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-      Withdraw - 2             -*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-      Balance Inquiry - 3      -*-*-*-*-*-*-");
        System.out.println("-*-*-*-*-*-*-      To Exit - 4      -*-*-*-*-*-*-");

        System.out.print("Enter Your Account Balance: ");
        double Balance = input.nextDouble();

        System.out.print("Choose an option -> Deposit (1) , Withdraw (2), Balance Inquiry (3) , Exit (4) : ");
        int choice = input.nextInt();

        while (choice != 4){
            switch (choice) {
                case 1:
                    System.out.print("Enter the amount for deposit: ");
                    double depositAmount = input.nextDouble();
                    Balance += depositAmount;
                    System.out.println("Deposit Successful!!!!!!");
                    break;
                case 2:
                    System.out.print("Enter the amount for withdrawal: ");
                    double withdrawAmount = input.nextDouble();
                    if (Balance < withdrawAmount) {
                        System.out.println("Insufficient Balance!!!!!");
                    } else {
                        Balance -= withdrawAmount;
                    }
                    break;
                case 3:
                    System.out.println("Your Account Balance is: " + Balance);
                    break;
                default:
                    System.out.println("Invalid option!!!!");
            }

            System.out.print("Choose an option -> Deposit (1) , Withdraw (2), Balance Inquiry (3) , Exit (4) : ");
            choice = input.nextInt();
        }


    }
}
