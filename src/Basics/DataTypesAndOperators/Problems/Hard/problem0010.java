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
        double balance;
        while (true) {
            double balanceEntered = input.nextInt();
            if (balanceEntered >= 0) {
                balance = balanceEntered;
                break;
            }
            System.out.println("Initial Balance Cannot Be negative!!!!!");
            System.out.print("Try Again: ");
        }

        System.out.print("Choose an option -> Deposit (1) , Withdraw (2), Balance Inquiry (3) , Exit (4) : ");
        int choice = input.nextInt();

        while (choice != 4){
            switch (choice) {
                case 1:
                    System.out.print("Enter the amount for deposit: ");
                    double depositAmount = input.nextDouble();
                    if (depositAmount < 0) {
                        System.out.println("Negative Amount!! Operation Failed.");
                    } else {
                        balance += depositAmount;
                        System.out.println("Deposit Successful!!!!!!");
                    }
                    break;
                case 2:
                    System.out.print("Enter the amount for withdrawal: ");
                    double withdrawAmount = input.nextDouble();
                    if (balance < withdrawAmount) {
                        System.out.println("Insufficient Balance!!!!!");
                    } else {
                        if (withdrawAmount < 0) {
                            System.out.println("Negative Amount!! Operation Failed.");
                        } else {
                            balance -= withdrawAmount;
                            System.out.println("Withdrawal Successful!!!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Your Account Balance is: " + balance);
                    break;
                default:
                    System.out.println("Invalid option!!!!");
            }

            System.out.print("Choose an option -> Deposit (1) , Withdraw (2), Balance Inquiry (3) , Exit (4) : ");
            choice = input.nextInt();
        }


    }
}
