package ru.academits.ikonnikov.tree.main;

import ru.academits.ikonnikov.tree.MyTree;

import java.util.Comparator;

public class TreeMain {
    public static void main(String[] args) {
        MyTree<String> stringMyTree = new MyTree<>();
        stringMyTree.insertNode("j");
        stringMyTree.insertNode("f");
        stringMyTree.insertNode("p");
        stringMyTree.insertNode("h");
        stringMyTree.insertNode(null);
        stringMyTree.insertNode("b");
        stringMyTree.insertNode("t");
        stringMyTree.insertNode("a");
        stringMyTree.insertNode("g");
        stringMyTree.insertNode("i");
        stringMyTree.insertNode("f");
        stringMyTree.insertNode("g");
        stringMyTree.insertNode("n");
        stringMyTree.insertNode("m");
        stringMyTree.insertNode("q");
        stringMyTree.insertNode("v");
        stringMyTree.insertNode("w");
        stringMyTree.insertNode("s");
        stringMyTree.insertNode("q");
        stringMyTree.insertNode("o");
        stringMyTree.insertNode("u");
        stringMyTree.insertNode("k");
        stringMyTree.insertNode(null);

        System.out.println("The size of stringMyTree is : " + stringMyTree.getSize());
        System.out.println();
        System.out.println("The  result of method (goAroundInWidth) is : ");

        stringMyTree.goAroundInWidth(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        System.out.println("The result of using of method (goAroundInDepth) is:");
        stringMyTree.goAroundInDepth(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.println();

        String x = "j";

        if (stringMyTree.removeNodeByValue(x)) {
            System.out.printf("The item [ %s ] was removed from stringMyTree.%n", x);
        }
        System.out.printf("The size of stringMyTree after removing of item [ %s ] is : %s %n", x, stringMyTree.getSize());
        System.out.println();

        System.out.println("The  result of method (goAroundInDepthUsingRecursion) is : ");
        stringMyTree.goAroundInDepthUsingRecursion(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        Comparator<Integer> comparatorInteger = (item1, item2) -> {
            if (item1 == null || item2 == null) {
                if (item1 == null && item2 == null) {
                    return 0;
                } else if (item1 == null) {
                    return -1;
                }
                return 1;
            }
            return Integer.compare(item1, item2);
        };

        MyTree<Integer> integerMyTree = new MyTree<>(10, comparatorInteger);
        integerMyTree.insertNode(6);
        integerMyTree.insertNode(0);
        integerMyTree.insertNode(1);
        integerMyTree.insertNode(8);
        integerMyTree.insertNode(7);
        integerMyTree.insertNode(null);
        integerMyTree.insertNode(6);
        integerMyTree.insertNode(7);
        integerMyTree.insertNode(16);
        integerMyTree.insertNode(12);
        integerMyTree.insertNode(14);
        integerMyTree.insertNode(15);
        integerMyTree.insertNode(13);
        integerMyTree.insertNode(20);
        integerMyTree.insertNode(17);
        integerMyTree.insertNode(19);
        integerMyTree.insertNode(17);
        integerMyTree.insertNode(22);
        integerMyTree.insertNode(21);
        integerMyTree.insertNode(23);
        integerMyTree.insertNode(9);
        integerMyTree.insertNode(null);

        System.out.println("The size of integerMyTree is : " + integerMyTree.getSize());
        System.out.println();

        System.out.println("The result of using of method (goAroundInDepth) is:");
        integerMyTree.goAroundInDepth(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.println();

        Integer y = null;

        if (integerMyTree.isInTree(y)) {
            integerMyTree.removeNodeByValue(y);
            System.out.printf("The item [ %s ] was removed from integerMyTree.%n", y);
            System.out.printf("The size of integerMyTree after removing of item [ %s ] is : %s %n", y, integerMyTree.getSize());
        } else {
            System.out.printf("This integerMyTree has not item [ %d ].", y);
        }
        System.out.println();

        Integer z = 10;

        if (integerMyTree.isInTree(z)) {
            integerMyTree.removeNodeByValue(z);
            System.out.printf("The item [ %s ] was removed from integerMyTree.%n", z);
            System.out.printf("The size of integerMyTree after removing of item [ %s ] is : %s %n", z, integerMyTree.getSize());
        } else {
            System.out.printf("This integerMyTree has not item [ %d ].", z);
        }
        System.out.println();

        System.out.println("The result of using of method (goAroundInDepthUsingRecursion) is :");
        integerMyTree.goAroundInDepthUsingRecursion(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.println();

        System.out.println("The result of using of method (goAroundInWidth) is :");
        integerMyTree.goAroundInWidth(nodeData -> System.out.printf(" [ %s ] %n", nodeData));
        System.out.println();
    }
}
