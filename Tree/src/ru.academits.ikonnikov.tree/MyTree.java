package ru.academits.ikonnikov.tree;

import ru.academits.ikonnikov.tree.classes.TreeNode;

import java.util.*;
import java.util.function.Consumer;

public class MyTree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public MyTree(T rootData) {
        size = 1;
        root = new TreeNode<>(rootData);
        comparator = (item1, item2) -> {
            if (item1 == null || item2 == null) {
                if (item1 == null && item2 == null) {
                    return 0;
                } else if (item1 == null) {
                    return -1;
                }
                return 1;
            }
            //noinspection deprecation,unchecked
            return ((Comparable<T>) item1).compareTo(item2);
        };
    }

    public MyTree(T rootData, Comparator<T> comparator) {
        size = 1;
        root = new TreeNode<>(rootData);
        this.comparator = comparator;
    }

    public MyTree(Comparator<T> comparator) {
        size = 0;
        root = null;
        this.comparator = comparator;
    }

    public MyTree() {
        root = null;
        size = 0;
        comparator = (item1, item2) -> {
            if (item1 == null || item2 == null) {
                if (item1 == null && item2 == null) {
                    return 0;
                } else if (item1 == null) {
                    return -1;
                }
                return 1;
            }
            //noinspection deprecation,unchecked
            return ((Comparable<T>) item1).compareTo(item2);
        };
    }

    public int getSize() {
        return this.size;
    }

    public boolean isInTree(T value) {
        TreeNode[] arrayNodeAndParent = getNodeAndParentByValue(value);
        return arrayNodeAndParent != null;
    }

    private TreeNode<T>[] getNodeAndParentByValue(T value) {
        if (size == 0) {
            throw new IllegalArgumentException("This tree is empty!");
        }
        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = null;

        while (currentNode != null) {
            int resultCompareValueAndCurrentNodeData = comparator.compare(value, currentNode.getData());

            if (resultCompareValueAndCurrentNodeData == 0) {
                if (parentNode == null) {
                    //noinspection unchecked
                    return (TreeNode<T>[]) new TreeNode[]{null, root, null};
                }
                if (comparator.compare(currentNode.getData(), parentNode.getData()) < 0) {
                    //noinspection unchecked
                    return (TreeNode<T>[]) new TreeNode[]{parentNode, currentNode, null};
                }
                //noinspection unchecked
                return (TreeNode<T>[]) new TreeNode[]{parentNode, null, currentNode};
            }

            if (resultCompareValueAndCurrentNodeData < 0) {
                if (currentNode.getLeft() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return null;
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

        while (currentNode != null) {
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
            return false;
        }
        TreeNode<T>[] arrayRemoving = getNodeAndParentByValue(value);

        if (arrayRemoving == null) {
            return false;
        }
        TreeNode<T> removedNode;
        TreeNode<T> parentRemovedNode = arrayRemoving[0];

        if (arrayRemoving[2] == null) {
            removedNode = arrayRemoving[1];
        } else {
            removedNode = arrayRemoving[2];
        }
        //case, when RemovedNode has not children:
        if (removedNode.hasNotChildren()) {
            if (parentRemovedNode == null) {
                root = null;
                size = 0;
                return true;
            } else if (arrayRemoving[2] == null) {
                parentRemovedNode.setLeft(null);
            } else {
                parentRemovedNode.setRight(null);
            }
            size--;
            return true;
        }
        //case, when RemovedNode has only one child:
        if (removedNode.hasNotBothChildren()) {
            if (arrayRemoving[1] == null && parentRemovedNode != null) {
                if (removedNode.getLeft() != null) {
                    parentRemovedNode.setRight(removedNode.getLeft());
                } else {
                    parentRemovedNode.setRight(removedNode.getRight());
                }
            } else if (parentRemovedNode != null) {
                if (removedNode.getLeft() != null) {
                    parentRemovedNode.setLeft(removedNode.getLeft());
                } else {
                    parentRemovedNode.setLeft(removedNode.getRight());
                }
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
            size--;
            return true;
        }
        //case, when RemovedNode has both children:
        TreeNode<T>[] arrayMinLeft = getMinLeftNodeAndParent(removedNode);
        arrayMinLeft[1].setLeft(removedNode.getLeft());
        arrayMinLeft[0].setLeft(arrayMinLeft[1].getRight());

        if (parentRemovedNode != null) {
            if (arrayRemoving[1] == null) {
                parentRemovedNode.setRight(arrayMinLeft[1]);
            } else {
                parentRemovedNode.setLeft(arrayMinLeft[1]);
            }
            if (arrayMinLeft[2] != null) {
                arrayMinLeft[1].setRight(removedNode.getRight());
            }
        } else {
            root = arrayMinLeft[1];
            root.setLeft(removedNode.getLeft());
        }
        if (arrayMinLeft[2] != null) {
            root.setRight(removedNode.getRight());
        }
        size--;
        return true;
    }


    public void goAroundInWidth(Consumer<T> method) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            TreeNode<T> currentNode = queue.remove();
            method.accept(currentNode.getData());
            TreeNode<T>[] arrayChildren = currentNode.getChildren();

            for (TreeNode<T> child : arrayChildren) {
                if (child != null) {
                    queue.add(child);
                }
            }
        }
    }

    public void goAroundInDepthUsingRecursion(Consumer<T> method) {
        if (root == null) {
            return;
        }
        visitNode(root, method);
    }

    private void visitNode(TreeNode<T> currentNode, Consumer<T> method) {
        if (currentNode == null) {
            return;
        }
        method.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            visitNode(currentNode.getLeft(), method);
        }
        if (currentNode.getRight() != null) {
            visitNode(currentNode.getRight(), method);
        }
    }

    public void goAroundInDepth(Consumer<T> method) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);

        while (stack.size() > 0) {
            TreeNode<T> currentItem = stack.removeLast();
            method.accept(currentItem.getData());
            TreeNode<T>[] arrayChildren = currentItem.getChildrenBackwards();

            for (TreeNode<T> child : arrayChildren) {
                if (child != null) {
                    stack.add(child);
                }
            }
        }
    }
}
