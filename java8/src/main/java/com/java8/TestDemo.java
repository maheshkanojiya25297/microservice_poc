package com.java8;


public class TestDemo {
    public static void main(String[] args) {
        //  Cat c = new Animal();  // This will cause a compile-time error
        //  c.doSomething();


        Animal a = new Cat();  // Correct, Cat is a subclass of Animal
        a.doSomething1();  // Output will be "Cat is doing something."

        try {
            Animal c = new Cat();  // Animal reference, but Cat object
            c.doSomething();  // Will call the overridden method in Cat
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

    }
}

    class Animal {
        void doSomething() throws Exception {
            System.out.println("Animal is doing something.");
            // Simulate an exception
            throw new Exception("Animal exception");
        }

        void doSomething1() {
            System.out.println("Animal is doing something.");
            // Simulate an exception
        }
    }

    class Cat extends Animal {
        @Override
        void doSomething() throws Exception {
            System.out.println("Cat is doing something.");
            // Simulate an exception
            throw new Exception("Cat exception");
        }

        void doSomething1() {
            System.out.println("Cat is doing something.");
            // Simulate an exception
        }
    }

