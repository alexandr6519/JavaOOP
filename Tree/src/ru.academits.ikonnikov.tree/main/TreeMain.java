package ru.academits.ikonnikov.tree.main;

import ru.academits.ikonnikov.tree.MyTree;
import ru.academits.ikonnikov.tree.classes.TreeNode;

import java.util.Comparator;

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
        System.out.println();
        System.out.println("The  result of method (goAroundInWidth) is : ");

        //ArrayList<TreeNode<String>> arrayNodes = new ArrayList<>(stringMyTree.getSize());

        stringMyTree.goAroundInWidth((TreeNode<String> node) -> {
            System.out.printf(" %s %n", node.toString());
        });
        System.out.println();

        String x = "j";

        if (stringMyTree.removeNodeByValue(x)) {
            System.out.printf("The value '%s' was removed from stringMyTree.%n", x);
        }
        System.out.printf("The size of stringMyTree after removing of item '%s' is : %s %n", x, stringMyTree.getSize());
        System.out.println();

        System.out.println("The  result of method (goAroundInDepthUsingRecursion) is : ");
        stringMyTree.goAroundInDepthUsingRecursion((TreeNode<String> node) -> {
            System.out.printf(" %s %n", node.toString());
        });
        System.out.println();

        Comparator<Integer> comparatorInteger = new Comparator<Integer>() {
            @Override
            public int compare(Integer item1, Integer item2) {
                return Integer.compare(item1, item2);
            }
        };

        MyTree<Integer> integerMyTree = new MyTree<>(10, comparatorInteger);
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

        System.out.println("The result of using of method (goAroundInDepth) is:");
        integerMyTree.goAroundInDepth((TreeNode<Integer> node) -> {
            System.out.printf(" %s %n", node.toString());
        });
        System.out.println();

        System.out.println("The result of using of method (goAroundInDepthUsingRecursion) is :");
        integerMyTree.goAroundInDepthUsingRecursion((TreeNode<Integer> node) -> {
            System.out.printf(" %s %n", node.toString());
        });
        System.out.println();

        System.out.println("The result of using of method (goAroundInWidth) is :");
        integerMyTree.goAroundInWidth((TreeNode<Integer> node) -> {
            System.out.printf(" %s %n", node.toString());
        });
    }

}
