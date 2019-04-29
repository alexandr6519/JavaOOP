package ru.academits.ikonnikov.lambda.main;

import ru.academits.ikonnikov.lambda.classes.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaMain {
    public static void main(String[] args) {
        List<Person> personsList = new LinkedList<>();
        personsList.add(new Person("Alexandr", 19));
        personsList.add(new Person("Alexandr", 36));
        personsList.add(new Person("Anna", 37));
        personsList.add(new Person("Anna", 36));
        personsList.add(new Person("Anna", 42));
        personsList.add(new Person("Boris", 36));
        personsList.add(new Person("Ivan", 37));
        personsList.add(new Person("Ivan", 42));
        personsList.add(new Person("Maxim", 21));
        personsList.add(new Person("Maxim", 48));
        personsList.add(new Person("Elena", 21));
        personsList.add(new Person("Elena", 39));

        List<String> namesList = personsList.stream().map(Person::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("The sorted list of names is:  " + namesList);

        String personNamesUnique = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println("The unique names: " + personNamesUnique);
        System.out.println();

        Stream<Person> teensStream = personsList.stream().filter(x -> x.getAge() < 18);
        
        OptionalDouble ageAverage = teensStream
                .mapToDouble(Person::getAge)
                .average();
        if (ageAverage.isPresent()) {
            System.out.printf("The average of ages of teens is : %.2f %n", ageAverage.getAsDouble());
            System.out.println();
        }


        Map<String, Double> map = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("The map as result of grouping by name is :");
        map.forEach((name, age) -> System.out.printf("The KEY (as name) is : %10s, the VALUE (as average age) is : %.2f%n", name, age));
        System.out.println();

        List<Person> listPersonMiddleAge = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors.toList());
        System.out.println("The persons of middle age (from 20 to 45) are : ");
        listPersonMiddleAge.forEach(name -> System.out.printf("%s %n", name));
    }
}
