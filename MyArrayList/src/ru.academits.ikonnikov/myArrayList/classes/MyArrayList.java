package ru.academits.ikonnikov.arrayList.classes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class MyArrayList<T> implements List<T> {
    private int size;
    private T[] items;
    private int modCount = 0;

    public MyArrayList(int capacity) {
        //noinspection unchecked
        this.items = (T[]) new Object[capacity];
        size = 0;
    }

    public MyArrayList(T[] array) {
        size = array.length;
        //noinspection unchecked
        items = (T[]) new Object[size + 10];
        System.arraycopy(array, 0, items, 0, size);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The getting of item is impossible, because the value of index is not correct!");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The setting of item is impossible, because the value of index is not correct!");
        }

        T itemPast = items[index];
        items[index] = element;
        return itemPast;
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            this.items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            this.items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountCurrent = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is absent!");
            }
            if (modCountCurrent != modCount) {
                throw new ConcurrentModificationException("There was adding or removing elements!");
            }
            currentIndex++;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <V> V[] toArray(V[] array) {
        if (array == null) {
            throw new IllegalArgumentException("This array is null!");
        }

        if (array.length < size) {
            //noinspection unchecked
            array = (V[]) Arrays.copyOf(array, size, array.getClass());
        }

        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            array[i] = (V) items[i];
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return this.indexOf(object) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }

        for (Object cItem : c) {
            if (!this.contains(cItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(T t) {
        if (items.length == size) {
            ensureCapacity(size * 2);
        }
        items[size] = t;
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The adding of item is impossible, because the value of index is not correct!");
        }

        if (index == size) {
            this.add(element);
            return;
        }
        modCount++;
        System.arraycopy(items, index, items, index + 1, size - index);
        size++;
        this.items[index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        int indexForAdding = this.size;
        ensureCapacity(this.size + c.size());

        for (T cItem : c) {
            items[indexForAdding] = cItem;
            indexForAdding++;
        }
        this.size += c.size();

        if (c.size() > 0) {
            modCount++;
        }
        return c.size() > 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The adding of collection is impossible, because the value of index is not correct!");
        }
        if (index == size) {
            return this.addAll(c);
        }
        ensureCapacity(this.size + c.size());
        System.arraycopy(items, index, items, index + c.size(), this.size - index);

        for (T cItem : c) {
            items[index] = cItem;
            index++;
        }
        this.size += c.size();

        if (c.size() > 0) {
            modCount++;
        }
        return c.size() > 0;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The removing of item is impossible, because the value of index is not correct!");
        }
        T removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            items[size - 1] = null;
        }
        modCount++;
        size--;
        return removedItem;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean wasRemovedAll = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(items[i])) {
                remove(i);
                wasRemovedAll = true;
                i--;
            }
        }
        return wasRemovedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean wasRetainedAll = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                wasRetainedAll = true;
                i--;
            }
        }
        return wasRetainedAll;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{ }";
        }
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < (size - 1); i++) {
            String str = String.format(" %s ,", items[i]);
            result.append(str);
        }
        String str = String.format(" %s }", items[size - 1]);
        return result.append(str).toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
