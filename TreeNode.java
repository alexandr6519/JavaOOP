package ru.academits.ikonnikov.tree.classes;

import java.util.Comparator;

public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public boolean hasNotChildren() {
        return this.getLeft() == null && this.getRight() == null;
    }

    public boolean hasBothChildren() {
        return this.getLeft() != null && this.getRight() != null;
    }

    public TreeNode<T>[] getChildren() {
        //noinspection unchecked
        return (TreeNode<T>[]) new TreeNode[]{left, right};
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

     Runnable run = new Runnable() {
        int startIndex = 1;
        @Override
        public void run() {
            System.out.printf("%2d) %s%n", startIndex, this.toString());
            startIndex++;
        }
    };

    @Override
    public String toString() {
        if (left == null) {
            if (right == null) {
                return String.format("[DATA:%3s, LEFT:   , RIGHT:    ]", data);
            } else {
                return String.format("[DATA:%3s, LEFT:   , RIGHT:%3s ]", data, right.getData());
            }
        }
        if (right == null) {
            return String.format("[DATA:%3s, LEFT:%3s, RIGHT:    ]", data, left.getData());
        }
        return String.format("[DATA:%3s, LEFT:%3s, RIGHT:%3s ]", data, left.getData(), right.getData());
    }

    private Comparator<T> compareData = new Comparator<>(){
        @Override
        public int compare(T data1, T data2) {
            return Integer.compare(data1.hashCode(), data2.hashCode());
        }
    };
}
