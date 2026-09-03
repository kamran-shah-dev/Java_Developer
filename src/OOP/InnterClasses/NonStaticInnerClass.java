package OOP.InnterClasses;
// Using inner class object we cannot call outer class methods. Instead, we can create
// Caller function in inner class to call outer class function.
// Non static inner class can access both static and non-static members of outer class.
public class NonStaticInnerClass {
    private static String Hello = "Hello";
    private String World = "World";

    class innerClass {
        public innerClass(String H, String W) {
            Hello = H;
            World = W;
            display();
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
