package ru.academits.ikonnikov.list.classes;

import ru.academits.ikonnikov.list.node.ListNode;

public class List<T> {
    private ListNode<T> head;
    private int size;

    public List(T head, int size) {
        if (head == null) {
            throw new NullPointerException("The head must not be null!");
        }

        this.head = new ListNode<>(head);
        this.size = size;
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
            } else {
                i++;
            }
        }
        return t;
    }

    public T getData(int index) {
        if (findByIndex(index) == null) {
            return null;
        }
        return findByIndex(index).getData();
    }

    public T setData(int index, T data) {
        T dataPast = findByIndex(index).getData();
        findByIndex(index).setData(data);

        return dataPast;
    }

    public T removeNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The removing of node is impossible, because the value of index is not correct!");
        }
        if (index == 0) {
            return removeHead();
        }

        ListNode<T> currentNode = findByIndex(index);
        T removedData = currentNode.getData();
        findByIndex(index - 1).setNext(currentNode.getNext());
        currentNode.setNext(null);
        size--;

        return removedData;
    }

    public T removeHead() {
        if (head == null) {
            throw new NullPointerException("The head must not be null!");
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
            if (p.getData().equals(data)) {
                wasDelete = true;
                removeNode(i);
                break;
            } else {
                i++;
            }
        }
        return wasDelete;
    }

    public void insertInHead(T data) {
        size++;
        head = new ListNode<>(data, head);
    }

    public void insertByIndex(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The inserting is impossible, because the value of index is not correct!");
        }

        if (index == 0) {
            insertInHead(data);
            return;
        }
        size++;
        ListNode<T> t = new ListNode<>(data);
        t.setNext(findByIndex(index));
        findByIndex(index - 1).setNext(t);
    }

    public void turn() {
        if (head == null || size == 0) {
            throw new NullPointerException("The list is empty!");
        }
        if (size == 1) {
            return;
        }

        if (size == 2) {
            ListNode<T> p = head.getNext();
            p.setNext(head);
            head.setNext(null);
            head = p;
            return;
        }

        for (ListNode<T> p = head; p != null; p = p.getNext()) {
            this.insertInHead(p.getData());
            size--;
        }
    }

    public List<T> copy() {
        ListNode<T> copyHead = new ListNode<>(head.getData());
        List<T> copy = new List<>(copyHead.getData(), 1);
        ListNode<T> p = head.getNext();

        for (int i = 1; i < this.size; i++) {
            ListNode<T> copyNode = new ListNode<>(p.getData());
            copy.insertInHead(copyNode.getData());
            p = p.getNext();
        }
        copy.turn();
        return copy;
    }

    @Override
    public String toString() {
        if (head == null || size == 0) {
            throw new NullPointerException("This list is empty!");
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

