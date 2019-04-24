package ru.academits.ikonnikov.graph.main;

import ru.academits.ikonnikov.graph.MyGraph;

import java.util.LinkedList;
import java.util.Arrays;

public class GraphMain {
    String [] arrayCities = new String[] {"Novosibirsk", "Kemerovo", "Omsk", "Krasnoyarsk", "Tomsk", "Moscow", "St.Peterburg", "Abakan",
        "Irkutsk", "Ufa", "Kazan"};

        LinkedList<String> listCities = new LinkedList<>(Arrays.asList(arrayCities));
        MyGraph myGraph = new MyGraph(listCities.size());
    
        listCities.add("Yakutsk");
    
        if (myGraph.addVertex()) {
            System.out.printf("The vertex [%s] was added to myGraph ", listCities.get(myGraph.getVertexCount() - 1));
        };

        int index1 = listCities.indexOf("Novosibirsk");
        int index2 = listCities.indexOf("Kemerovo");

        if (myGraph.addEdge(index1, index2)) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", arrayCities[index1], arrayCities[index2]);
        }
        if (myGraph.addEdge(listCities.indexOf("Novosibirsk"), listCities.indexOf("Moscow"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Novosibirsk", "Moscow");
        }
        if (myGraph.addEdge(listCities.indexOf("Novosibirsk"), listCities.indexOf("St.Peterburg"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Novosibirsk", "St.Peterburg");
        }
        if (myGraph.addEdge(listCities.indexOf("Kemerovo"), listCities.indexOf("Moscow"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Kemerovo", "Moscow");
        }
        if (myGraph.addEdge(listCities.indexOf("Omsk"), listCities.indexOf("Moscow"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Omsk", "Moscow");
        }
        if (myGraph.addEdge(listCities.indexOf("Tomsk"), listCities.indexOf("Moscow"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Tomsk", "Moscow");
        }
        if (myGraph.addEdge(listCities.indexOf("Krasnoyarsk"), listCities.indexOf("Abakan"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Krasnoyarsk", "Abakan");
        }
        if (myGraph.addEdge(listCities.indexOf("Krasnoyarsk"), listCities.indexOf("Irkutsk"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Krasnoyarsk", "Irkutsk");
        }
        if (myGraph.addEdge(listCities.indexOf("Omsk"), listCities.indexOf("Tomsk"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Omsk", "Tomsk");
        }
        if (myGraph.addEdge(listCities.indexOf("Kazan"), listCities.indexOf("Ufa"))) {
            System.out.printf("The edge (%s , %s) was added to myGraph.%n", "Kazan", "Ufa");
        }
        System.out.println();

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
            System.out.printf("The vertex is: [%12s], verticesConnected are : { ", listCities.get(item));
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (Integer indexItem : children) {
                System.out.printf("[%s] ", listCities.get(indexItem));
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("The result of method (goAroundInWidth) is : ");
        myGraph.goAroundInWidth((item) -> {
            System.out.printf("The vertex is: [%12s], verticesConnected are : { ", listCities.get(item));
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (Integer indexItem : children) {
                System.out.printf("[%s] ", listCities.get(indexItem));
            }
            System.out.println("}");
        });
    }
}
