package ru.academits.ikonnikov.graph.main;

import ru.academits.ikonnikov.graph.MyGraph;

import java.util.LinkedList;

public class GraphMain {
    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph(11);

        if (myGraph.addVertex()) {
            System.out.printf("The item [%2s] was added to myGraph.%n", myGraph.getVertexCount() - 1);
        }

        if (myGraph.addEdge(0, 1)) {
            System.out.printf("The edge (%s , %s) was added.%n",0, 1);
        }

        myGraph.addEdge(0, 5);
        myGraph.addEdge(0, 6);
        myGraph.addEdge(5, 1);
        myGraph.addEdge(2, 5);
        myGraph.addEdge(4, 5);
        myGraph.addEdge(3, 7);
        myGraph.addEdge(3, 8);
        myGraph.addEdge(2, 4);
        myGraph.addEdge(9, 10);

        int[][] matrix = myGraph.getEdges();
        System.out.println("The matrix of edges is :");

        for (int j = 0; j < myGraph.getVertexCount(); j++) {
            for (int i = 0; i < myGraph.getVertexCount(); i++) {
                System.out.print(matrix[j][i] + "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("The result of method (goAroundInDepth) is : ");
        myGraph.goAroundInDepth((item) -> {
            System.out.printf("The vertex is: [%s], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            if (children != null) {
                System.out.printf("%s ", children.toString());
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("The result of method (goAroundInWidth) is : ");
        myGraph.goAroundInWidth((item) -> {
            System.out.printf("The vertex is: [%s], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            if (children != null) {
                System.out.printf("%s ", children.toString());
            }
            System.out.println("}");
        });
    }
}
