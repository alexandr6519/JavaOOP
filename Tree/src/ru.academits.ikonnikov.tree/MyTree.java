package ru.academits.ikonnikov.tree;

import ru.academits.ikonnikov.tree.classes.TreeNode;

import java.util.*;
import java.util.function.Consumer;

public class MyTree<T> {//implements Comparator<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator <T> comparator = new Comparator<T>() {
        @Override
        public int compare(T t1, T t2) {
            //noinspection deprecation,unchecked
            return ((Comparable<T>) t1).compareTo(t2);
        }
    };

    public MyTree(T rootData) {
        this.size = 1;
        this.root = new TreeNode<>(rootData);
    }

    public MyTree(T rootData, Comparator<T> comparator) {
        this.size = 1;
        this.root = new TreeNode<>(rootData);
        this.comparator = comparator;
    }

    public MyTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    private TreeNode<T>[] getNodeAndParentByValue(T value) {
        if (size == 0) {
            throw new IllegalArgumentException("This tree is empty!");
        }
        if (Objects.equals(value, root.getData())) {
            //noinspection unchecked
            return (TreeNode<T>[]) new TreeNode[]{null, root, null};
        }
        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode;
        int i = 1;

        while (i < size) {
            if (comparator.compare(value, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                    i++;
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                    i++;
                } else {
                    return null;
                }
            }
            if (Objects.equals(value, currentNode.getData())) {
                if (comparator.compare(currentNode.getData(), parentNode.getData()) < 0) {
                    //noinspection unchecked
                    return (TreeNode<T>[]) new TreeNode[]{parentNode, currentNode, null};
                }
                //noinspection unchecked
                return (TreeNode<T>[]) new TreeNode[]{parentNode, null, currentNode};
            }
        }
        return null;
    }

    public void insertNode(T value) {
        if (size == 0) {
            root = new TreeNode<>(value);
            size++;
            return;
        }
        TreeNode<T> currentNode = root;

        do {
            if (comparator.compare(value, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(value));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(value));
                    size++;
                    return;
                }
            }
        } while (currentNode != null);
    }

    private TreeNode<T>[] getMinLeftNodeAndParent(TreeNode<T> node) {
        TreeNode<T> minLeftNode = node.getRight();
        TreeNode<T> minLeftParentNode = node;
        TreeNode<T> currentMinLeftNode = null;

        while (minLeftNode.getLeft() != null) {
            currentMinLeftNode = minLeftNode.getLeft();
            minLeftParentNode = minLeftNode;
            minLeftNode = currentMinLeftNode;
        }
        //noinspection unchecked
        return (TreeNode<T>[]) new TreeNode[]{minLeftParentNode, minLeftNode, currentMinLeftNode};
    }

    public boolean removeNodeByValue(T value) {
        if (size == 0) {
            return false;
        }
        TreeNode<T>[] arrayRemoving = getNodeAndParentByValue(value);

        if (arrayRemoving == null) {
            return false;
        }
        TreeNode<T> removedNode;
        TreeNode<T> parentRemovedNode = arrayRemoving[0];

        //case, when RemovedNode is root:
        if (parentRemovedNode == null) {
            removedNode = root;
            //case, when root (as RemovedNode) has not children:
            if (root.hasNotChildren()) {
                root = null;
                size = 0;
                return true;
            }
            //case, when root (as RemovedNode) has only one child:
            if (root.getLeft() == null) {
                root = root.getRight();
                size--;
                return true;
            } else if (root.getRight() == null) {
                root = root.getLeft();
                size--;
                return true;
            }
            //case, when root (as RemovedNode) has both children:
            TreeNode<T>[] arrayMinLeft = getMinLeftNodeAndParent(root);
            root = arrayMinLeft[1];
            root.setLeft(removedNode.getLeft());
            arrayMinLeft[0].setLeft(arrayMinLeft[1].getRight());

            //case, when RemovedNode.getRight() is not minLeftItem:
            if (arrayMinLeft[2] != null) {
                root.setRight(removedNode.getRight());
            }
            size--;
            return true;
        } else {
            //case, when RemovedNode is not root:
            if (arrayRemoving[2] == null) {
                removedNode = arrayRemoving[1];
            } else {
                removedNode = arrayRemoving[2];
            }
            //case, when RemovedNode has not children:
            if (removedNode.hasNotChildren()) {
                if (arrayRemoving[2] == null) {
                    parentRemovedNode.setLeft(null);
                } else {
                    parentRemovedNode.setRight(null);
                }
                size--;
                return true;
            }
            //case, when RemovedNode has only one child:
            if (!removedNode.hasBothChildren()) {
                if (arrayRemoving[1] == null) {
                    if (removedNode.getLeft() != null) {
                        parentRemovedNode.setRight(removedNode.getLeft());
                    } else {
                        parentRemovedNode.setRight(removedNode.getRight());
                    }
                } else if (removedNode.getLeft() != null) {
                    parentRemovedNode.setLeft(removedNode.getLeft());
                } else {
                    parentRemovedNode.setLeft(removedNode.getRight());
                }
                size--;
                return true;
            }
            //case, when RemovedNode has both children:
            TreeNode<T>[] arrayMinLeft = getMinLeftNodeAndParent(removedNode);
            arrayMinLeft[1].setLeft(removedNode.getLeft());

            if (arrayRemoving[1] == null) {
                parentRemovedNode.setRight(arrayMinLeft[1]);
            } else {
                parentRemovedNode.setLeft(arrayMinLeft[1]);
            }
            //case, when RemovedNode.getRight() is not minLeftItem:
            if (arrayMinLeft[2] != null) {
                arrayMinLeft[0].setLeft(arrayMinLeft[1].getRight());
                arrayMinLeft[1].setRight(removedNode.getRight());
            }
            size--;
            return true;
        }
    }

    public void goAroundInWidth(Consumer<TreeNode<T>> method) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            TreeNode<T> currentNode = queue.peek();
            method.accept(currentNode);

            TreeNode<T>[] arrayChildren = currentNode.getChildren();
            queue.remove();

            for (TreeNode<T> child : arrayChildren) {
                if (child != null) {
                    queue.add(child);
                }
            }
        }
    }

    public void goAroundInDepthUsingRecursion(Consumer<TreeNode<T>> method) {
        if (root == null) {
            return;
        }
        int i = 0;
        visitNode(root, method);
    }

    private void visitNode(TreeNode<T> currentNode, Consumer<TreeNode<T>> method) {
        if (currentNode == null) {
            return;
        }
        method.accept(currentNode);

        if (currentNode.getLeft() != null) {
            visitNode(currentNode.getLeft(), method);
        }
        if (currentNode.getRight() != null) {
            visitNode(currentNode.getRight(), method);
        }
    }

    public void goAroundInDepth(Consumer<TreeNode<T>> method) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        list.add(root);

        while (list.size() > 0) {
            TreeNode<T> currentItem = list.getLast();
            method.accept(currentItem);

            TreeNode<T>[] arrayChildren = currentItem.getChildrenBackwards();
            list.removeLast();

            for (TreeNode<T> child : arrayChildren) {
                if (child != null) {
                    list.add(child);
                }
            }
        }
    }
}
