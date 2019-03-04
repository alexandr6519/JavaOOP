package ru.academits.ikonnikov.list.main;

import ru.academits.ikonnikov.list.classes.List;
import ru.academits.ikonnikov.list.classes.ListNode;

public class ListMain {
    public static void main(String[] args) {
        ListNode<String> head = new ListNode<>("Yes");

        List<String> list = new List<>(head, 1);

        list.insertInHead("Good buy");
        list.insertByIndex(1, "Hello");
        list.insertByIndex(2, "Thank you");
        list.insertByIndex(2, "Please");
        System.out.println("The list is:" + list.toString());

        System.out.println("The size of this list is: " + list.getLength());

        System.out.println("The head of list is: " + list.getHead().toString());

        System.out.println("The data of head is: " + list.getHeadData());

        System.out.println("The data by index '1' before setting is : " + list.setData(1, "Not"));
        System.out.println("The list after changing data by index '1' is :" + list.toString());

        System.out.println("The data by index '2' is: " + list.getData(2));

        System.out.println("The copy of list is:" + list.copy().toString());

        System.out.println("The list after turning is :" + list.turn().toString());

        System.out.println("The result of removing by data 'Well' is: " + list.removeNodeByValue("Well"));
        System.out.println("The list after removing by data 'Well' is :" + list.toString());

        System.out.println("The result of removing by data 'Hello' is: " + list.removeNodeByValue("Hello"));
        System.out.println("The list after removing by data 'Hello' is :" + list.toString());

        System.out.println("The removing data by index '2' is: " + list.removeNode(2));
        System.out.println("The list after removing data by index '2' is :" + list.toString());

        System.out.println("The data of removing of 'head' is: " + list.removeHead());
        System.out.println("The list after removing 'head' is : " + list.toString());

        System.out.println("The head of list is: " + list.getHead().toString());
    }
}
