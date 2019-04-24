package ru.academits.ikonnikov.graph;

import java.util.*;
import java.util.function.Consumer;

public class MyGraph {
    private int[][] edges;
    private int vertexCount;

    public MyGraph() {
        edges = new int[50][50];
        this.vertexCount = 0;
    }

    public MyGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        edges = new int[vertexCount][vertexCount];
    }

    public boolean addVertex() {
        if (vertexCount < edges.length) {
            vertexCount++;
            return true;
        }
        edges = new int[vertexCount * 2][vertexCount * 2];
        edges = Arrays.copyOf(this.edges, vertexCount * 2);
        vertexCount++;
        return true;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public LinkedList<Integer> getChildren(int vertexIndex) {
        if (vertexIndex < 0) {
            return null;
        }
        LinkedList<Integer> children = new LinkedList<>();

        for (int i = 0; i < vertexCount; i++) {
            if (edges[i][vertexIndex] == 1) {
                children.add(i);
            }
        }
        return children;
    }

    private LinkedList<Integer> getChildrenBackwards(int vertexIndex) {
        if (vertexIndex < 0) {
            return null;
        }
        LinkedList<Integer> children = new LinkedList<>();

        for (int i = vertexCount - 1; i >= 0; i--) {
            if (edges[i][vertexIndex] == 1) {
                children.add(i);
            }
        }
        return children;
    }

    public int[][] getEdges() {
        return this.edges;
    }

    public boolean addEdge(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= vertexCount || index2 >= vertexCount || index1 == index2) {
            throw new IllegalArgumentException("The value of index isn't correct!");
        }
        edges[index1][index2] = 1;
        edges[index2][index1] = 1;
        return true;
    }

    public void goAroundInDepth(Consumer<Integer> method) {
        if (vertexCount == 0) {
            return;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[vertexCount];
        int currentItem;

        for (int i = 0; i < vertexCount; i++) {
            if (visited[i]) {
                continue;
            } else {
                stack.add(i);
            }

            while (stack.size() > 0) {
                currentItem = stack.removeLast();
                visited[currentItem] = true;

                method.accept(currentItem);
                LinkedList<Integer> children = getChildrenBackwards(currentItem);

                assert children != null;
                for (Integer child : children) {
                    if (!visited[child] && !stack.contains(child)) {
                        stack.add(child);
                    }
                }
            }
        }
    }

    public void goAroundInWidth(Consumer<Integer> method) {
        if (vertexCount == 0) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertexCount];
        int currentItem;

        for (int i = 0; i < vertexCount; i++) {
            if (visited[i]) {
                continue;
            } else {
                queue.add(i);
            }

            while (queue.size() > 0) {
                currentItem = queue.remove();
                visited[currentItem] = true;

                method.accept(currentItem);
                LinkedList<Integer> children = getChildren(currentItem);

                for (Integer child : children) {
                    if (!visited[child] && !queue.contains(child)) {
                        queue.add(child);
                    }
                }
            }
        }
    }
}
