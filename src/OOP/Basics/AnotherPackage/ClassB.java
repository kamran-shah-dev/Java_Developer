package OOP.AnotherPackage;
import OOP.AccessSpecifiers.MainClass;

public class ClassB extends MainClass {
    public static void main(String... args){
        MainClass m = new MainClass();
        m.className = "Main Class";
        // m.numOfSubclasses = 5; Error! protected attributes cannot be accessed in another package with
        // Parent class reference.
        ClassB b = new ClassB();
        b.numOfSubclasses = 65;  // protected attributes of the parent class can be accessed using subclass
        // reference.
    }
}
