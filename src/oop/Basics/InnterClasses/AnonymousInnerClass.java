package OOP.InnterClasses;

interface Greetings {
    void greet();
}
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Greetings greetings = new Greetings() {
            @Override
            public void greet() {
                System.out.println("Hello Mister!");
            }
        };
        greetings.greet();
    }
}
