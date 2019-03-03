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
        StringBuilder result = new StringBuilder("Node :[");
        if (this.getNext() == null) {
            return null;
        }
        String str = String.format("DATA: %s , NEXT: %s ]", data, next);
        return result.append(str).toString();
    }
}
