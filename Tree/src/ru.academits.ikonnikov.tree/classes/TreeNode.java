package ru.academits.ikonnikov.tree.classes;

public class TreeNode<T>  {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean hasNotChildren() {
        return this.getLeft() == null && this.getRight() == null;
    }

    public boolean hasBothChildren() {
        return this.getLeft() == null || this.getRight() == null;
    }

    public TreeNode<T>[] getChildren() {
        //noinspection unchecked
        return (TreeNode<T>[]) new TreeNode[]{left, right};
    }

    public TreeNode<T>[] getChildrenBackwards() {
        //noinspection unchecked
        return (TreeNode<T>[]) new TreeNode[]{right, left};
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

    @Override
    public String toString() {
        if (left == null) {
            if (right == null) {
                return String.format("[DATA:%3s, L:   , R:    ]", data);
            } else {
                return String.format("[DATA:%3s, L:   , R:%3s ]", data, right.getData());
            }
        }
        if (right == null) {
            return String.format("[DATA:%3s, L:%3s, R:    ]", data, left.getData());
        }
        return String.format("[DATA:%3s, L:%3s, R:%3s ]", data, left.getData(), right.getData());
    }
}
