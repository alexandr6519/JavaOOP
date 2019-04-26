package ru.academits.ikonnikov.lambda.classes;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        if (name == null) {
            return "()";
        }
        return String.format("{name: %10s, age: %3d}%n", name, age);
    }
}
