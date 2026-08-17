package Basics.Projects;

import java.util.Scanner;


// custom item not found exception
class itemNotFoundException extends Exception {
    public itemNotFoundException(String message) {
        super(message);
    }
}

public class GroceryShopping {

    // Function that searches items for a specific item and returns the index of the item if found
    // otherwise it returns -1
    public static int searchItem (String[] items, String item) {
        for (int i = 0 ; i < items.length; i++) {
            if (item.equalsIgnoreCase(items[i])) {
                return i;
            }
        }
        return -1;
    }

    // Function that calculates average price of all items
    public static float averagePrice (float[] prices) {
        float total = 0.0f;
        for (float price : prices) {
            total += price;
        }
        return total / prices.length;
    }

    // Function that calculates the items which are below a certain level based on provided threshold
    public static void belowCertainPrice (String[] items, float[] prices, float threshHold) {
        for (int i = 0; i < items.length; i++) {
            if (prices[i] < threshHold) {
                System.out.println(items[i] + "'s price: " + prices[i] + " < " + threshHold);
            }
        }
    }

    // Function that calculates the discount based on totalBill if total bill exceeds 100$
    public static float calculateDiscount (float totalBill) {
        if (totalBill > 100) {
            float discountedAmount = totalBill * 0.10f; // calculating 10% discount
            totalBill -= discountedAmount;
        }
        return totalBill;
    }

    public static void main(String[] args) {

        // 10 grocery items and their corresponding prices
        String[] items =  new String[10];
        float[] price = new float[10];
        int[] stockAvailable = new int[10];
        items[0] = "Rice"; stockAvailable[0] = 12; price[0] = 575.0f;
        items[1] = "Flour"; stockAvailable[1] = 20; price[1] = 400.0f;
        items[2] = "Sugar"; stockAvailable[2] = 30; price[2] = 150.0f;
        items[3] = "Salt"; stockAvailable[3] = 15; price[3] = 250.0f;
        items[4] = "Oil"; stockAvailable[4] = 40; price[4] = 240.0f;
        items[5] = "Milk"; stockAvailable[5] = 31; price[5] = 157.5f;
        items[6] = "Eggs"; stockAvailable[6] = 51; price[6] = 495.0f;
        items[7] = "Tea"; stockAvailable[7] = 43; price[7] = 80.0f;
        items[8] = "Lentils"; stockAvailable[8] = 29; price[8] = 200.0f;
        items[9] = "Bread"; stockAvailable[9] = 23; price[9] = 160.0f;

        Scanner input = new Scanner(System.in);

        float totalBill = 0;

        while (true) {
            System.out.println("You Can Perform Following Operations");
            System.out.println("Enter 1 for shopping");
            System.out.println("Enter 2 to find the average price");
            System.out.println("Enter 3 to find minimum threshold (i.e 23$) items");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    while (true) {
                        try {

                            System.out.print("Enter an item name or (finish) to end shopping: ");
                            String chooseItem = input.nextLine();
                            if (chooseItem.equalsIgnoreCase("finish")) {
                                System.out.println("Thank You for Shoping From our Store");
                                System.out.println("Your Total Bill Is: " + totalBill);
                                System.out.println("Your bill after discount is: " + calculateDiscount(totalBill));
                                break;
                            }

                            int indexOfItem = searchItem(items, chooseItem);

                            if (indexOfItem == -1) {
                                throw new itemNotFoundException("Item " + chooseItem + " not found! try again");
                            }

                            while (true) {
                                System.out.print("Enter the quantity (KG/LITRE): ");
                                int quantity = Integer.parseInt(input.nextLine());
                                if (quantity <= stockAvailable[indexOfItem]) {
                                    float currentTotalBill = price[indexOfItem] * (float) quantity;
                                    totalBill += currentTotalBill;
                                    stockAvailable[indexOfItem]--;
                                    break;
                                } else if (stockAvailable[indexOfItem] == 0) {
                                    System.out.println("Item " + items[indexOfItem] + " out of stock.");
                                    break;
                                } else {
                                    System.out.println("Quantity available for item " + items[indexOfItem] + " = " + stockAvailable[indexOfItem]);
                                    System.out.println("Try entering lower quantity");
                                }
                            }
                        } catch (itemNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Average price of available items = " + averagePrice(price));
                    break;
                case 3:
                    System.out.print("Enter minimum threshold price: ");
                    float minimumThreshold = input.nextFloat();
                    belowCertainPrice(items, price, minimumThreshold);
                    break;
                default:
                    System.out.println("Invalid option!!!");
                    break;
            }

            System.out.println("Do you want to Exit!!!");
            System.out.print("To Exit type \"Exit\" : ");
            String exitChoice = input.nextLine();
            if (exitChoice.equalsIgnoreCase("exit")) {
                break;
            }
        }
        input.close();
    }
}