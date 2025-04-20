// Encapsulation
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Inheritance
class AnimalBase {
    String speak() {
        return "Some sound";
    }
}

class InheritedDog extends AnimalBase {
    String speak() {
        return "Bark (from inheritance)";
    }
}

// Polymorphism
interface AnimalSound {
    void sound();
}

class Cat implements AnimalSound {
    public void sound() {
        System.out.println("Meow (from polymorphism)");
    }
}

class PolymorphicDog implements AnimalSound {
    public void sound() {
        System.out.println("Bark (from polymorphism)");
    }
}

// Abstraction
abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Woof (from abstraction)");
    }
}

public class Main {
    public static void main(String[] args) {

        // Encapsulation
        System.out.println("Encapsulation");
        Person person = new Person("Alice");
        System.out.println("Name: " + person.getName());
        person.setName("Bob");
        System.out.println("Updated Name: " + person.getName());

        // Inheritance
        System.out.println("\nInheritance");
        AnimalBase myDog = new InheritedDog();
        System.out.println(myDog.speak());

        // Polymorphism
        System.out.println("\nPolymorphism");
        AnimalSound cat = new Cat();
        AnimalSound dog = new PolymorphicDog();
        cat.sound();
        dog.sound();

        // Abstraction
        System.out.println("\nAbstraction");
        Animal abstractDog = new Dog();
        abstractDog.makeSound();
    }
}
