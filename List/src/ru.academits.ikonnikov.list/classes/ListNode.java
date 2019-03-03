package ru.academits.ikonnikov.list.classes;

public class ListNode<T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    ListNode<T> getNext() {
        return next;
    }

    void setNext(ListNode<T> next) {
        this.next = next;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "Node is null!";
        }

        return "Node :[DATA: " + data + ", NEXT: " + next + "]";
    }
}
