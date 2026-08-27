package OOP;

class Car {
    protected String color;
    String model;
    int year;

    void display() {
        System.out.println("Car Model: " + model);
        System.out.println("Car Color: " + color);
        System.out.println("Year Year: " + year);
    }
}
public class ClassAndObjects {
    public static void main(String[] args) {
        Car mercedesBenz = new Car();
        mercedesBenz.color = "Black";
        mercedesBenz.model = "665AHGJ";
        mercedesBenz.year = 2020;
        mercedesBenz.display();
    }
}
