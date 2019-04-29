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
        personsList.add(new Person("Anna", 17));
        personsList.add(new Person("Anna", 36));
        personsList.add(new Person("Anna", 42));
        personsList.add(new Person("Boris", 16));
        personsList.add(new Person("Ivan", 17));
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

        List<Person> teensList = personsList.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());
        System.out.printf("The list of teens is : %n %s%n", teensList);

        if (teensList.size() > 0) {
            OptionalDouble ageAverage = teensList.stream()
                    .mapToDouble(Person::getAge)
                    .average();
            //noinspection OptionalGetWithoutIsPresent
            System.out.printf("The average of ages of teens is : %.2f %n", ageAverage.getAsDouble());
            System.out.println();
        } else {
            System.out.println("The personList has not person with age less than 18.");
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
