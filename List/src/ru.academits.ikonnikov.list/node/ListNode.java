package ru.academits.ikonnikov.list.node;

import java.util.Objects;

public class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getData() {
        if (Objects.equals(data, null)) {
            return null;
        }
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (next == null) {
            return "Node :[DATA: " + data + ", NEXT: NULL" + "]";
        }
        return "Node :[DATA: " + data + ", NEXT: " + next.getData() + "]";
    }
}


