package OOP.InnterClasses;

public class StaticInnerClass {
    private static String Hello = "Hello";
    private String World = "World";

    static class innerClass {
        public innerClass(String H, String W) {
            Hello = H;
            // World = W; Error Cannot Access Outer class non static members
            // display();
            System.out.println(Hello);
        }
    }
    public void display() {
        System.out.println(Hello + " " + World);
    }

    public static void main(String[] args) {
        NonStaticInnerClass object = new NonStaticInnerClass();
        NonStaticInnerClass.innerClass innerObject = object.new innerClass("Greetings", "Kamran");
    }
}