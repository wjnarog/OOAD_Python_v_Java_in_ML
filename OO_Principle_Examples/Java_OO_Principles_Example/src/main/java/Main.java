// Encapsulation
class EncapsulatedAnimal {
    private String species;
    private int age;

    public EncapsulatedAnimal(String species, int age) {
        this.species = species;
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
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
        return "Bark";
    }
}

// Polymorphism
interface AnimalSound {
    void sound();
}

class Cat implements AnimalSound {
    public void sound() {
        System.out.println("Meow");
    }
}

class PolymorphicDog implements AnimalSound {
    public void sound() {
        System.out.println("Bark");
    }
}

// Abstraction
abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Woof");
    }
}

public class Main {
    public static void main(String[] args) {

        // Encapsulation
        System.out.println("\n== Encapsulation ==");
        EncapsulatedAnimal animal = new EncapsulatedAnimal("Dog", 3);
        System.out.println("Species: " + animal.getSpecies());
        System.out.println("Age: " + animal.getAge());

        animal.setSpecies("Cat");
        animal.setAge(5);

        System.out.println("Updated Species: " + animal.getSpecies());
        System.out.println("Updated Age: " + animal.getAge());

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
