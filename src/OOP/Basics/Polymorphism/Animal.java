package OOP.Polymorphism;

public class Animal {
    private String name;
    private String food;

    public Animal (String name) {
        this.name = name;
    }

    public String Sound() {
        return null;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFood() {
        return this.food;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                "sound='" + Sound() + '\''+
                "Eats Food='" + getFood() + '\''+
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

    @Override
    public void setFood(String food) {
        super.setFood(food);
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

    @Override
    public void setFood(String food) {
        super.setFood(food);
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

    @Override
    public void setFood(String food) {
        super.setFood(food);
    }
}


