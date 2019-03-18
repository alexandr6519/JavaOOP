package ru.academits.ikonnikov.list.classes;

import ru.academits.ikonnikov.list.node.ListNode;

import java.util.Objects;

public class List<T> {
    private ListNode<T> head;
    private int size;

    public List(T head) {
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
        if (head == null) {
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
            }
            i++;
        }
        return t;
    }

    public T getData(int index) {
        ListNode<T> currentNode = findByIndex(index);

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

        T removedData = currentNode.getData();
        previousNode.setNext(currentNode.getNext());
        currentNode.setNext(null);
        size--;

        return removedData;
    }

    public T removeHead() {
        if (head == null) {
            throw new NullPointerException("The list is empty!");
        }
        T removedData = head.getData();
        head.setData(null);
        head = head.getNext();
        size--;
        return removedData;
    }

    public boolean removeNodeByValue(T data) {
        if (size == 0) {
            return false;
        }
        if (Objects.equals(head.getData(), data)) {
            removeHead();
            return true;
        }

        for (ListNode<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                prev.setNext(p.getNext());
                p.setNext(null);
                size--;
                return true;
            }
        }
        return false;
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
        ListNode<T> prevNode = head;
        ListNode<T> currentNode = head.getNext();
        ListNode<T> nextNode = currentNode.getNext();

        for (int i = 1; i < size; i++) {
            prevNode.setNext(nextNode);
            currentNode.setNext(head);
            head = currentNode;
            currentNode = nextNode;

            if (i == size - 1) {
                break;
            }
            nextNode = currentNode.getNext();
        }
    }

    public List<T> copy() {
        if (size == 0) {
            return new List<>();
        }
        List<T> copy = new List<>(head.getData());

        if (size == 1) {
            return copy;
        }
        ListNode<T> copyPreviousNode = copy.head;
        ListNode<T> nextNode = head.getNext();
        copy.size = this.size;

        for (int i = 1; i < this.size; i++) {
            ListNode<T> copyCurrentNode = new ListNode<>(nextNode.getData());
            copyPreviousNode.setNext(copyCurrentNode);
            nextNode = nextNode.getNext();
            copyPreviousNode = copyCurrentNode;
        }
        return copy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder result = new StringBuilder("[");
        ListNode<T> currentNode = head;

        for (int i = 0; i < (size - 1); i++) {
            String str = String.format(" %s ,", currentNode.getData());
            currentNode = currentNode.getNext();
            result.append(str);
        }
        String str = String.format(" %s ]", currentNode.getData());
        return result.append(str).toString();
    }
}

