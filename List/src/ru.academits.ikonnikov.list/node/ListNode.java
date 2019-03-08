package ru.academits.ikonnikov.list.node;

public class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode<T> next) {
        if (data == null && next == null) {
            throw new IllegalArgumentException("It's impossible to create empty node!");
        }
        this.data = data;
        this.next = next;
    }

    public ListNode<T> getNext() {
        if (next == null) {
            return null;
        }
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getData() {
        if (data == null) {
            throw new NullPointerException("The data is null!");
        }
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (data == null) {
            return "Node is null.";
        }
        if (next == null) {
            return "Node :[DATA: " + data + ", NEXT: NULL" + "]";
        }
        return "Node :[DATA: " + data + ", NEXT: " + next.getData() + "]";
    }
}


