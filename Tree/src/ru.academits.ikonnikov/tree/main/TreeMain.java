package ru.academits.ikonnikov.tree.main;

import ru.academits.ikonnikov.tree.MyTree;

public class TreeMain {
    public static void main(String[] args) {
        MyTree<String> stringMyTree = new MyTree<>();
        stringMyTree.insertNode("j");
        stringMyTree.insertNode("f");
        stringMyTree.insertNode("p");
        stringMyTree.insertNode("h");
        stringMyTree.insertNode("k");
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
        System.out.println("The size of stringMyTree is : " + stringMyTree.getSize());
        System.out.println("The  result of method *goAroundInWidth()* is : ");
        stringMyTree.goAroundInWidth();

        String x = "j";

        if (stringMyTree.removeNodeByValue(x)) {
            System.out.printf("The value '%s' was removed from stringMyTree.%n", x);
        }
        System.out.printf("The size of stringMyTree after removing of item '%s' is : %s %n", x, stringMyTree.getSize());

        String str1 = "q";

        if (stringMyTree.getNodeAndParentByValue(str1)[1] != null) {
            System.out.printf("The node (%2s ) is left child of node %2s.%n", stringMyTree.getNodeAndParentByValue(str1)[1], stringMyTree.getNodeAndParentByValue(str1)[0]);
        } else {
            System.out.printf("The node (%2s ) is left child of node %2s.%n", stringMyTree.getNodeAndParentByValue(str1)[2], stringMyTree.getNodeAndParentByValue(str1)[0]);
        }

        String str2 = "p";

        if (stringMyTree.getNodeAndParentByValue(str2)[1] != null) {
            System.out.printf("The node (%2s ) is left child of node %2s.%n", stringMyTree.getNodeAndParentByValue(str2)[1], stringMyTree.getNodeAndParentByValue(str2)[0]);
        } else {
            System.out.printf("The node (%2s ) is left child of node %2s.%n", stringMyTree.getNodeAndParentByValue(str2)[2], stringMyTree.getNodeAndParentByValue(str2)[0]);
        }

        String str3 = "t";

        if (stringMyTree.getNodeAndParentByValue(str3) != null) {
            System.out.printf("The parent of node with value (%2s ) is : %s %n", str3, stringMyTree.getNodeAndParentByValue(str3)[0]);
        }

        String str4 = "k";

        if (stringMyTree.getNodeAndParentByValue(str4) != null) {
            System.out.printf("The parent of node with value (%2s ) is : %s %n", str4, stringMyTree.getNodeAndParentByValue(str4)[0]);
        }
        System.out.println();

        stringMyTree.startIndex = 1;
        System.out.println("The  result of method *goAroundInDepthUsingRecursion()* is : ");
        stringMyTree.goAroundInDepthUsingRecursion();
        System.out.println();

        MyTree<Integer> integerMyTree = new MyTree<>(10);
        integerMyTree.insertNode(6);
        integerMyTree.insertNode(0);
        integerMyTree.insertNode(1);
        integerMyTree.insertNode(8);
        integerMyTree.insertNode(7);
        integerMyTree.insertNode(9);
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
        System.out.println("The size of integerMyTree is : " + integerMyTree.getSize());
        System.out.println();

       Integer y = 8;

        if (integerMyTree.removeNodeByValue(y)) {
            System.out.printf("The value '%s' was removed from integerMyTree.%n", y);
        }
        System.out.printf("The size of integerMyTree after removing of item '%s' is : %s %n", y, integerMyTree.getSize());
        System.out.println();
        System.out.println("The result of using of method \"integerMyTree.goAroundInDepth()\" is:");
        integerMyTree.goAroundInDepth();
        System.out.println();
        integerMyTree.startIndex = 1;
        System.out.println("The result of using of method *goAroundInDepthUsingRecursion()* is :");
        integerMyTree.goAroundInDepthUsingRecursion();
        System.out.println();

        integerMyTree.startIndex = 1;
        System.out.println("The result of using of method 'integerMyTree.goAroundInWidth()' is :");
        integerMyTree.goAroundInWidth();
    }
}
