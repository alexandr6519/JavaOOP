package ru.academits.ikonnikov.hashTable.main;

import ru.academits.ikonnikov.hashTable.classes.MyHashTable;

import java.util.*;

public class MainHashTable {
    public static void main(String[] args) {
        Collection<String> myStringsCollection1 = new ArrayList<>(Arrays.asList("a", "c", "n", "p", "t", "55", "60", "56", "s", null, "q", "j", "30", "39"));
        MyHashTable<String> myHashTableStrings = new MyHashTable<>(100);
        myHashTableStrings.addAll(myStringsCollection1);
        System.out.println("The myHashTableStrings is :" + myHashTableStrings.toString());

        if (myHashTableStrings.isEmpty()) {
            System.out.println("The myHashTableStrings is empty!");
        } else {
            System.out.println("The myHashTableStrings is not empty!");
        }
        myHashTableStrings.add("x");
        Collection<String> myStringsCollection2 = new ArrayList<>(Arrays.asList("b", "d", "42", "f", "57", "99", "21", "&", null));
        System.out.println("The myStringsCollection is :" + myStringsCollection2.toString());

        if ( myHashTableStrings.addAll(myStringsCollection2)) {
            System.out.println("The myHashTableStrings after adding of myStringsCollection is :" + myHashTableStrings.toString());
        }

        System.out.println("The size of myHashTableStrings after adding of myStringsCollection is :" + myHashTableStrings.size());

        String stringItem1 = "q";

        if (myHashTableStrings.contains(stringItem1)) {
            System.out.printf("The myHashTableStrings has item '%s'.%n", stringItem1);
        } else {
            System.out.printf("The myHashTableStrings has not item '%s'.%n", stringItem1);
        }
        String stringItem2 = "e";

        if (myHashTableStrings.contains(stringItem2 )) {
            System.out.printf("The myHashTableStrings has item '%s'.%n", stringItem2 );
        } else {
            System.out.printf("The myHashTableStrings has not item '%s'.%n", stringItem2 );
        }

        if (myHashTableStrings.remove(stringItem1)) {
            System.out.printf("The item '%s' was removed from myHashTableStrings.%n", stringItem1);
        } else {
            System.out.printf("The item '%s' wasn't removed from myHashTableString.%n", stringItem1);
        }
        System.out.printf("The myHashTableString after removing item '%s' is : %n", stringItem1);
        System.out.println(myHashTableStrings.toString());
        System.out.println();


        Iterator iteratorString = myHashTableStrings.iterator();
        System.out.print("The items of myHashTableStrings are : ");

        while (iteratorString.hasNext()) {
            System.out.print(iteratorString.next() + "   ");
        }
        System.out.println();

       Collection<String> myCollectionStrings1 = new ArrayList<>(Arrays.asList("b", "c", "n", "p", "t", "55", "99", "&", null));

        if (myHashTableStrings.containsAll(myCollectionStrings1)) {
            System.out.printf("The myHashTableStrings has collection %s.%n", myCollectionStrings1.toString());
        } else {
            System.out.printf("The myHashTableStrings has not collection %s.%n", myCollectionStrings1.toString());
        }

        Collection<String> myCollectionStrings2 = new ArrayList<>(Arrays.asList("z", "p", "t", "15", "b", null));

        if (myHashTableStrings.containsAll(myCollectionStrings2)) {
            System.out.printf("The myHashTableStrings has collection %s.%n", myCollectionStrings2.toString());
        } else {
            System.out.printf("The myHashTableStrings has not collection %s.%n", myCollectionStrings2.toString());
        }

        boolean wasRetainAll = myHashTableStrings.retainAll(myCollectionStrings2);

        if (wasRetainAll) {
            System.out.printf("The result of method 'myHashTableStrings.retainAll(%s)'  is :%n", myCollectionStrings2.toString());
            System.out.println(myHashTableStrings.toString());
        }
        System.out.println();

        List<String> myListStringsSmall = new ArrayList<>(Arrays.asList("v1", "d1", "h1", null, "11", "21", "u1", null, "c1", "a1"));
        MyHashTable<String> myHashTableStringsSmall = new MyHashTable<>(10);
        myHashTableStringsSmall.addAll(myListStringsSmall);
        System.out.println("The myHashTableStringsSmall is :" + myHashTableStringsSmall.toString());

        Object[] hashTableToArray = myHashTableStringsSmall.toArray();
        System.out.print("The result of method 'myHashTableStringsSmall.toArray()' is :{  ");

        for (Object e : hashTableToArray) {
            System.out.print(e + "  ");
        }
        System.out.println("}");

        String[] arrayForStrings = {"@", "#", "$", "%", "[", "?", "!", "+", "-", "*", "/", "="};
        String[] hashTableToArrayStrings = myHashTableStringsSmall.toArray(arrayForStrings);
        System.out.print("The result of method 'hashTableToArrayStrings.toArray(arrayForStrings)' is :{  ");

        for (Object e : hashTableToArrayStrings) {
            System.out.print(e + "  ");
        }
        System.out.println("}");

        ArrayList<Integer> myArrayListIntegers = new ArrayList<>(Arrays.asList(1, 78, 79, 80, 85, null, 87, 88, 89, 90, 102, 168, 231, 226, 354, 389, 402, 423, 451, 487, 542, 633, 820));
        MyHashTable<Integer> myHashTableIntegers = new MyHashTable<>(100);
        System.out.println("The myHashTableIntegers is : " + myHashTableIntegers.toString());

        myHashTableIntegers.addAll(myArrayListIntegers);
        System.out.println("The myHashTableIntegers after adding of myArrayListIntegers is : ");
        System.out.println( myHashTableIntegers.toString());

        if (myHashTableIntegers.remove(402)) {
            System.out.println("The item '402' was removed from myHashTableIntegers.");
        } else {
            System.out.println("The item '402'  wasn't removed from myHashTableIntegers.");
        }
        System.out.println("The myHashTableIntegers after removing of item '402' is : ");
        System.out.println( myHashTableIntegers.toString());

        Collection<Integer> myCollectionIntegers = new ArrayList<>(Arrays.asList(1, 78, 79, 80, 85, 86, 87, 88, 89, 90, 102, 168, 231, null));

        if (myHashTableIntegers.removeAll(myCollectionIntegers)) {
            System.out.printf("The myCollectionIntegers %s was removed from myHashTableInteger.%n", myCollectionIntegers.toString());
        } else {
            System.out.printf("The myCollectionIntegers %s wasn't removed from myHashTableInteger.%n", myCollectionIntegers.toString());
        }
        System.out.printf("The myHashTableIntegers after removing of myCollectionIntegers %s is : %n", myCollectionIntegers.toString());
        System.out.println(myHashTableIntegers.toString());

        myHashTableIntegers.clear();
        System.out.printf("The myHashTableIntegers after clearing is : %s%n", myHashTableIntegers.toString());
        if (myHashTableIntegers.isEmpty()) {
            System.out.println("The myHashTableIntegers is empty!");
        } else {
            System.out.println("The myHashTableIntegers is not empty!");
        }
    }
}
