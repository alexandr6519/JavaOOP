package ru.academits.ikonnikov.graph.main;

import ru.academits.ikonnikov.graph.MyGraph;

import java.util.LinkedList;

public class GraphMain {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        MyGraph myGraph = new MyGraph(edges);

        int[][] matrix = myGraph.getEdges();
        System.out.println("The matrix of edges is :");

        for (int j = 0; j < edges.length; j++) {
            for (int i = 0; i < edges.length; i++) {
                System.out.printf(" %2d ", matrix[j][i]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("The result of method (goAroundInWidth) is : ");

        myGraph.goAroundInWidth(item -> {
            System.out.printf("The vertex is: [%2s], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (Integer index : children) {
                System.out.printf("[%s] ", index);
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("The result of method (goAroundInDepth) is : ");

        myGraph.goAroundInDepth(item -> {
            System.out.printf("The vertex is: [%2s], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (Integer index : children) {
                System.out.printf("[%s] ", index);
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("The result of method (goAroundInDepthUsingRecursion) is : ");

        myGraph.goAroundInDepthUsingRecursion(item -> {
            System.out.printf("The vertex is: [%2s], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (Integer index : children) {
                System.out.printf("[%s] ", index);
            }
            System.out.println("}");
        });
    }
}
