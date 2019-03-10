package ru.academits.ikonnikov.list.main;

import ru.academits.ikonnikov.list.classes.List;

public class ListMain {
    public static void main(String[] args) {
        List<String> list = new List<>("Yes", 1);

        list.insertInHead("Good buy");
        list.insertByIndex(1, "Hello");
        list.insertByIndex(2, "null");
        list.insertByIndex(3,"Please");
       System.out.println("The list is: " + list.toString());

        System.out.println("The size of this list is: " + list.getLength());

        System.out.println("The data of head is: " + list.getHeadData());

        System.out.println("The data by index '1' is : " + list.getData(1));
        System.out.println("The data by index '4' before setting is : " + list.setData(4, "Not"));
        System.out.println("The list after changing data by index '4' is : " + list.toString());

        List<String> list1 = list.copy();
        System.out.println("The copy of list is: " + list1.toString());

        list.turn();
        System.out.println("The list after turning is : " + list.toString());

        System.out.println("The result of removing by data 'Well' is: " + list.removeNodeByValue("Well"));
        System.out.println("The list after removing by data 'Well' is :" + list.toString());

        System.out.println("The result of removing by data 'Hello' is: " + list.removeNodeByValue("Hello"));
        System.out.println("The list after removing by data 'Hello' is : " + list.toString());

        System.out.println("The removing data by index '2' is: " + list.removeNode(2));
        System.out.println("The list after removing data by index '2' is : " + list.toString());

        System.out.println("The data of removing 'head' is: " + list.removeHead());
        System.out.println("The list after removing 'head' is : " + list.toString());

        list.insertByIndex(1,"null");
        System.out.println("The list after inserting 'null' by index '1' is : " + list.toString());

        System.out.println("The result of removing by data 'null' is: " + list.removeNodeByValue("null"));
        System.out.println("The list after removing by data 'null' is :" + list.toString());
    }
}
