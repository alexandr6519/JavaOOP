package ru.academits.ikonnikov.graph;

import java.util.*;
import java.util.function.IntConsumer;

public class MyGraph {
    private int[][] edges;

    public MyGraph(int[][] matrix) {
        edges = Arrays.copyOf(matrix, matrix.length);
    }

    public int[][] getEdges() {
        return this.edges;
    }

    public LinkedList<Integer> getChildren(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= edges.length) {
            return null;
        }

        LinkedList<Integer> children = new LinkedList<>();

        for (int i = 0; i < edges.length; i++) {
            if (edges[i][vertexIndex] > 0) {
                children.add(i);
            }
        }
        return children;
    }

    private LinkedList<Integer> getChildrenBackwards(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= edges.length) {
            return null;
        }

        LinkedList<Integer> children = new LinkedList<>();

        for (int i = edges.length - 1; i >= 0; i--) {
            if (edges[i][vertexIndex] > 0) {
                children.add(i);
            }
        }
        return children;
    }

    public void goAroundInDepthUsingRecursion(IntConsumer method) {
        if (edges.length == 0) {
            return;
        }

        boolean[] visited = new boolean[edges.length];

        for (int i = 0; i < edges.length; i++) {
            if (!visited[i]) {
                visitVertex(i, method, visited);
            }
        }
    }

    private void visitVertex(int currentNode, IntConsumer method, boolean[] visited) {
        if (visited[currentNode]) {
            return;
        }

        method.accept(currentNode);
        visited[currentNode] = true;
        LinkedList<Integer> children = this.getChildren(currentNode);

        if (children != null) {
            for (int i : children) {
                visitVertex(i, method, visited);
            }
        }
    }

    public void goAroundInDepth(IntConsumer method) {
        int vertexCount = edges.length;
        if (vertexCount == 0) {
            return;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            if (visited[i]) {
                continue;
            } else {
                stack.add(i);
            }

            while (stack.size() > 0) {
                int currentItem = stack.removeLast();
                if (visited[currentItem]) {
                    continue;
                }

                visited[currentItem] = true;
                method.accept(currentItem);
                LinkedList<Integer> children = getChildrenBackwards(currentItem);

                if (children != null) {
                    for (int child : children) {
                        if (!visited[child]) {
                            stack.add(child);
                        }
                    }
                }
            }
        }
    }

    public void goAroundInWidth(IntConsumer method) {
        int vertexCount = edges.length;
        if (vertexCount == 0) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            if (visited[i]) {
                continue;
            } else {
                queue.add(i);
            }

            while (queue.size() > 0) {
                int currentItem = queue.remove();
                if (visited[currentItem]) {
                    continue;
                }

                visited[currentItem] = true;
                method.accept(currentItem);
                LinkedList<Integer> children = getChildren(currentItem);

                for (Integer child : children) {
                    if (!visited[child]) {
                        queue.add(child);
                    }
                }
            }
        }
    }
}
