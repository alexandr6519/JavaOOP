package ru.academits.ikonnikov.tree;

import ru.academits.ikonnikov.tree.classes.*;

import java.util.*;

public class MyTree<T> {
    private TreeNode<T> root;
    private int size;

    public MyTree(T root) {
        size = 1;
        this.root = new TreeNode<>(root);
    }

    public int getSize() {
        return this.size;
    }
}
