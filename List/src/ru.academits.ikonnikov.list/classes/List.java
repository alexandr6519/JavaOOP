package ru.academits.ikonnikov.list.classes;

import ru.academits.ikonnikov.list.node.ListNode;

import java.util.Objects;

public class List<T> {
    private ListNode<T> head;
    private int size;

    public List(T head) {
        if (Objects.equals(head, null)) {
            throw new NullPointerException("The head must not be null!");
        }
        size = 1;
        this.head = new ListNode<>(head);
    }

    public List() {
        head = null;
        size = 0;
    }

    public int getLength() {
        return size;
    }

    public T getHeadData() {
        if (Objects.equals(head, null)) {
            throw new NullPointerException("The list is empty!");
        }
        return head.getData();
    }

    private ListNode<T> findByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The finding of node is impossible, because the value of index is not correct!");
        }
        int i = 0;
        ListNode<T> t = null;

        for (ListNode<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                t = p;
                break;
            } else {
                i++;
            }
        }
        return t;
    }

    public T getData(int index) {
        ListNode<T> currentNode = findByIndex(index);

        if (Objects.equals(currentNode, null)) {
            return null;
        }
        return currentNode.getData();
    }

    public T setData(int index, T data) {
        ListNode<T> currentNode = findByIndex(index);
        T dataPast = currentNode.getData();
        currentNode.setData(data);

        return dataPast;
    }

    public T removeNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The removing of node is impossible, because the value of index is not correct!");
        }

        if (index == 0) {
            return removeHead();
        }
        ListNode<T> previousNode = findByIndex(index - 1);
        ListNode<T> currentNode = previousNode.getNext();
        T removedData = null;
        previousNode.setNext(null);

        if (!Objects.equals(currentNode, null)) {
            removedData = currentNode.getData();
            previousNode.setNext(currentNode.getNext());
        }
        currentNode.setNext(null);
        size--;

        return removedData;
    }

    public T removeHead() {
        if (Objects.equals(head, null)) {
            throw new NullPointerException("The list is empty!");
        }
        T removedData = head.getData();
        head.setData(null);
        head = head.getNext();
        size--;
        return removedData;
    }

    public boolean removeNodeByValue(T data) {
        boolean wasDelete = false;
        int i = 0;

        for (ListNode<T> p = head; p != null; p = p.getNext()) {
            if (Objects.equals(data, null)) {
                if (Objects.equals(p.getData(), null)) {
                    removeNode(i);
                    return true;
                }
            } else {
                if (Objects.equals(p.getData(), data)) {
                    wasDelete = true;
                    removeNode(i);
                    break;
                }
            }
            i++;
        }
        return wasDelete;
    }

    public void insertInHead(T data) {
        size++;
        head = new ListNode<>(data, head);
    }

    public void insertByIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The inserting is impossible, because the value of index is not correct!");
        }

        if (index == 0) {
            insertInHead(data);
            return;
        }
        size++;
        ListNode<T> previousNode = findByIndex(index - 1);
        ListNode<T> t = new ListNode<>(data);
        t.setNext(previousNode.getNext());
        previousNode.setNext(t);
    }

    public void turn() {
        if (size < 2) {
            return;
        }
        ListNode<T> prevNode = head, currentNode = head.getNext(), nextNode = null;

        if (size > 2) {
            nextNode = currentNode.getNext();
        }

        for (int i = 1; i < size; i++) {
            prevNode.setNext(nextNode);

            if (Objects.equals(currentNode, null)) {
                throw new NullPointerException("The currentNode is null!");
            } else {
                currentNode.setNext(head);
            }
            head = currentNode;

            if (size == 2) {
                break;
            }
            currentNode = nextNode;

            if (i < size - 2) {
                if (Objects.equals(currentNode, null)) {
                    throw new NullPointerException("The currentNode is null!");
                } else {
                    nextNode = currentNode.getNext();
                }
            } else {
                nextNode = null;
            }
        }
    }

    public List<T> copy() {
        if (size == 0) {
            return this;
        }
        this.turn();
        ListNode<T> copyHead = new ListNode<>(head.getData());
        List<T> copy = new List<T>(copyHead.getData());
        ListNode<T> nextNode = head.getNext();

        for (int i = 1; i < this.size; i++) {
            copy.insertInHead(nextNode.getData());
            nextNode = nextNode.getNext();
        }
        this.turn();
        return copy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        int size = this.size;
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < (size - 1); i++) {
            String str = String.format(" %s ,", this.getData(i));
            result.append(str);
        }
        String str = String.format(" %s ]", this.getData(size - 1));
        return result.append(str).toString();
    }
}

