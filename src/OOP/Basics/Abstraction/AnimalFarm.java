package OOP.Abstraction;

import java.util.Scanner;

public class AnimalFarm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the animal farm.");
        Animal[] animals = new Animal[10];

        System.out.println("""
                Menu
                    To view animals type: 1
                    To add an animal type: 2
                    Any other key to exit.
                """);

        String choice;
        int index = 0;
        while (true) {
            System.out.print("Your choice: ");
            choice = input.nextLine();
            if (choice.equals("1")) {
                for (Animal animal : animals) {
                    if (animal == null)
                        break;
                    System.out.println(animal);
                }
            } else if (choice.equals("2")) {
                System.out.println("""
                Menu
                    To add a dog: 1
                    To add a cat: 2
                    To add a cow: 3
                """);
                choice = input.nextLine();
                String animalName;
                String animalFood;
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter the dog name: ");
                        animalName = input.nextLine();
                        System.out.print("What " + animalName + " Eats: ");
                        animalFood = input.nextLine();
                        animals[index] = new Dog(animalName);
                        animals[index].setFood(animalFood);
                        index++;
                    }
                    case "2" -> {
                        System.out.print("Enter the cat name: ");
                        animalName = input.nextLine();
                        System.out.print("What " + animalName + " Eats: ");
                        animalFood = input.nextLine();
                        animals[index] = new Cat(animalName);
                        animals[index].setFood(animalFood);
                        index++;
                    }
                    case "3" -> {
                        System.out.print("Enter the cow name: ");
                        animalName = input.nextLine();
                        System.out.print("What " + animalName + " Eats: ");
                        animalFood = input.nextLine();
                        animals[index] = new Cow(animalName);
                        animals[index].setFood(animalFood);
                        index++;
                    }
                }
            } else {
                break;
            }
        }

    }
}
