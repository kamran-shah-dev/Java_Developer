package OOP.Polymorphism;

public class Animal {
    private String name;

    public Animal (String name) {
        this.name = name;
    }

    public String Sound() {
        return null;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                "sound='" + Sound() + '\''+
                '}';
    }
}

class Dog extends Animal {
    public Dog (String name) {
        super(name);
    }

    @Override
    public String Sound() {
        return "Bow! Bow!";
    }
}
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String Sound() {
        return "Meow Meow Meow";
    }

}
class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }

    @Override
    public String Sound() {
        return "Moo!   Moo!";
    }
}


