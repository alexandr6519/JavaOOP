package ru.academits.ikonnikov.tree;

import ru.academits.ikonnikov.tree.classes.TreeNode;

import java.util.*;

public class MyTree<T> {
    private TreeNode<T> root;
    private int size;
    public int startIndex = 1;

    public MyTree(T rootData) {
        size = 1;
        this.root = new TreeNode<>(rootData);
    }

    public MyTree() {
        root = null;
        size = 0;
    }

    /*public class TreeNode<T> {
        private TreeNode<T> left;
        private TreeNode<T> right;
        private T data;

        private TreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        private boolean hasNotChildren() {
            return this.getLeft() == null && this.getRight() == null;
        }

        private boolean hasBothChildren() {
            return this.getLeft() != null && this.getRight() != null;
        }

        private  TreeNode<T>[] getChildren() {
            //noinspection unchecked
            return (TreeNode<T>[]) new TreeNode[]{left, right};
        }

        private void setData(T data) {
            this.data = data;
        }

        private TreeNode<T> getLeft() {
            return left;
        }

        private void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        private TreeNode<T> getRight() {
            return right;
        }

        private void setRight(TreeNode<T> right) {
            this.right = right;
        }

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
    }*/

    public int getSize() {
        return this.size;
    }

    private Comparator<T> compareData = Comparator.comparingInt(Object::hashCode);

    public TreeNode<T>[] getNodeAndParentByValue(T value) {
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
            if (compareData.compare(value, currentNode.getData()) < 0) {
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
                if (compareData.compare(currentNode.getData(), parentNode.getData()) < 0) {
                    //noinspection unchecked
                    return (TreeNode<T>[]) new TreeNode[]{parentNode, currentNode, null};
                } else {
                    //noinspection unchecked
                    return (TreeNode<T>[]) new TreeNode[]{parentNode, null, currentNode};
                }
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
        int i = 0;

        while (i < size) {
            if (compareData.compare(value, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    i++;
                } else {
                    currentNode.setLeft(new TreeNode<>(value));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                    i++;
                } else {
                    currentNode.setRight(new TreeNode<>(value));
                    size++;
                    return;
                }
            }
        }
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
            throw new IllegalArgumentException("This tree is empty!");
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

    private void executeMethod1(TreeNode<T> node) {
        System.out.printf("%2d) %s%n", startIndex, node.toString());
        startIndex++;
    }

    public void goAroundInWidth() {
        if (root == null) {
            throw new IllegalArgumentException("This tree is empty!");
        }
        //noinspection unchecked
        TreeNode<T>[] queue = new TreeNode[size];
        queue[0] = root;
        int countQueueItems = 1;
        int indexFirstItem = 0;
        int indexLastItem = 0;

        while (countQueueItems > 0) {
            TreeNode<T> currentItem = queue[indexFirstItem];
            executeMethod1(currentItem);
            queue[indexFirstItem] = null;
            indexFirstItem++;

            if (currentItem.hasNotChildren()) {
                countQueueItems--;
                continue;
            }

            if (currentItem.hasBothChildren()) {
                indexLastItem++;
                queue[indexLastItem] = currentItem.getLeft();
                indexLastItem++;
                queue[indexLastItem] = currentItem.getRight();
                countQueueItems++;
            } else if (currentItem.getLeft() != null) {
                indexLastItem++;
                queue[indexLastItem] = currentItem.getLeft();
            } else {
                indexLastItem++;
                queue[indexLastItem] = currentItem.getRight();
            }
        }
    }

    public void goAroundInDepthUsingRecursion() {
        if (root == null) {
            throw new IllegalArgumentException("This tree is empty!");
        }
        int i = 0;
        visitNode(root);
    }

    private void visitNode(TreeNode<T> currentNode) {
        if (currentNode == null) {
            return;
        }
        executeMethod1(currentNode);

        for (TreeNode<T> child : currentNode.getChildren()) {
            visitNode(child);
        }
    }

    public void goAroundInDepth() {
        if (root == null) {
            throw new IllegalArgumentException("This tree is empty!");
        }
        //noinspection unchecked
        TreeNode<T>[] stack = new TreeNode[size];
        stack[0] = root;
        int countStackItems = 1;
        int indexLastItem = 0;

        while (countStackItems > 0) {
            TreeNode<T> currentItem = stack[indexLastItem];
            executeMethod1(currentItem);

            if (currentItem.hasBothChildren()) {
                stack[indexLastItem] = currentItem.getRight();
                indexLastItem++;
                stack[indexLastItem] = currentItem.getLeft();
                countStackItems++;
            } else if (currentItem.getLeft() != null) {
                stack[indexLastItem] = currentItem.getLeft();
            } else {
                stack[indexLastItem] = currentItem.getRight();
            }
            if (currentItem.hasNotChildren()) {
                stack[indexLastItem] = null;
                indexLastItem--;
                countStackItems--;
            }
        }
    }
}
