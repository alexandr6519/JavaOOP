package ru.academits.ikonnikov.list.classes;

public class List<T> {
    private ListNode<T> head;
    private int size;

    public List(ListNode<T> head, int size) {
        this.head = head;
        this.size = size;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public void setHead(ListNode<T> head) {
        this.head = head;
    }

    public int getLength() {
        return size;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T getData(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("The getting of data is impossible, because the value of index is not correct!");
        }

        int i = 0;
        T result = null;

        for (ListNode<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                result = p.getData();
                break;
            } else {
                i++;
            }
        }
        return result;
    }

    public T setData(int index, T data) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("The setting of data is impossible, because the value of index is not correct!");
        }

        int i = 0;
        T dataPast = null;

        for (ListNode<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                dataPast = p.getData();
                p.setData(data);
                break;
            } else {
                i++;
            }
        }
        return dataPast;
    }

    public T removeNode(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("The removing is impossible, because the value of index is not correct!");
        }

        T removedData = null;
        int i = 0;

        for (ListNode<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (i == index) {
                if (prev == null) {
                    removeHead();
                } else {
                    removedData = p.getData();
                    prev.setNext(p.getNext());
                    p.setData(null);
                    p.setNext(null);
                    size--;
                    break;
                }
            } else {
                i++;
            }
        }
        return removedData;
    }

    public T removeHead() {
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
            throw new RuntimeException("The value of index is not correct!");
        }

        if (index == 0) {
            insertInHead(data);
            return;
        }

        int i = 1;

        for (ListNode<T> p = head.getNext(), prev = head; i < size; prev = p, p = p.getNext()) {
            if (i == index) {
                ListNode<T> t = new ListNode<>(data);
                if (p == null) {
                    t.setNext(null);
                } else {
                    t.setNext(p);
                }
                prev.setNext(t);
                size++;
                break;
            } else {
                i++;
            }
        }
    }

    public List turn() {
        if (size < 2) {
            return this;
        }
        ListNode<T> copyHead = new ListNode<>(head.getData());
        List<T> copy = new List<>(copyHead, size);

        for (ListNode<T> p = head.getNext(); p != null; p = p.getNext()) {
            copy.insertInHead(p.getData());
        }

        this.head = copy.getHead();
        return this;
    }

    public List copy() {
        ListNode<T> copyHead = new ListNode<>(head.getData());
        List<T> copy = new List<>(copyHead, size);

        int i = 1;

        for (ListNode<T> p = head.getNext(); p != null; p = p.getNext()) {
            copy.insertByIndex(i, p.getData());
            i++;
        }
        return copy;
    }

    @Override
    public String toString() {
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

