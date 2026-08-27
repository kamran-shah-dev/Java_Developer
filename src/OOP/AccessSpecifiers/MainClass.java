package OOP.AccessSpecifiers;

public class MainClass {

    public String className;
    private int classId;
    protected int numOfSubclasses;
    int classStrength;


    public static void main(String[] args) {
        ClassA A = new ClassA();
        A.className = "First Class";
        // A.classId = 55; Error! Private variable being accessed.
        A.numOfSubclasses = 14; // Protected attribute can be accessed directly within same package
                                // With parent class reference.
        A.classStrength = 50; // Default attributes can be used in same package

    }
}
class ClassA {
    public String className;
    private int classId;
    protected int numOfSubclasses;
    int classStrength;
}

