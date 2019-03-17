package ru.academits.ikonnikov.myArrayList.classes;

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
        size = capacity;
        //noinspection unchecked
        this.items = (T[]) new Object[capacity];
    }

    public MyArrayList(T[] array) {
        items = array;
        size = array.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The getting of item is impossible, because the value of index is not correct!");
        }
        if (Objects.equals(items[index], null)) {
            return null;
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

    private void ensureCapacity(int capacity) {
        if (size < capacity) {
            size = capacity;
            this.items = Arrays.copyOf(items, capacity);
        }
    }

    private void trimToSize() {
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
        int mCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is absent!");
            }
            if (mCount != modCount) {
                throw new ConcurrentModificationException("There was adding or removing elements!");
            }
            currentIndex++;
            T currentItem = items[currentIndex];

            if (currentItem == null) {
                return null;
            }
            return currentItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {
            throw new NullPointerException("This list is empty!");
        }
        Object[] object = new Object[size];

        for (int i = 0; i < size; i++) {
            object[i] = (Object)items[i];
        }
        return object;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            array = (T[]) new MyArrayList<>(size).items;
        }
        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            array[i] = (T)items[i];
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        //noinspection unchecked
        T item = (T) object;

        for (T itemCurrent : this) {
            if (Objects.equals(itemCurrent, item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean isContainsAll = true;

        for (Object cItem : c) {
            isContainsAll = isContainsAll && this.contains(cItem);
        }
        return isContainsAll;
    }

    @Override
    public int indexOf(Object object) {
        //noinspection unchecked
        T item = (T) object;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        //noinspection unchecked
        T item = (T) object;
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        modCount++;
        set(size - 1, t);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean wasAddedAll = true;

        for (T cItem : c) {
            wasAddedAll = wasAddedAll && add(cItem);
        }
        return wasAddedAll;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean wasAddedAll = false;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The getting of item is impossible, because the value of index is not correct!");
        }
        wasAddedAll = true;
        ensureCapacity(size + c.size());
        System.arraycopy(items, index, items, index + c.size(), this.size() - index - c.size());

        for (Object cItem : c) {
            //noinspection unchecked
            T item = (T) cItem;
            wasAddedAll = wasAddedAll && !Objects.equals(set(index, item), null);
            index++;
        }
        return wasAddedAll;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The adding of item is impossible, because the value of index is not correct!");
        }

        System.arraycopy(items, index, items, index + 1, size - index - 1);
        this.items[index] = element;
        modCount++;
    }

    @Override
    public boolean remove(Object object) {
        //noinspection unchecked
        T item = (T) object;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
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
            return false;
        }
        boolean wasRemovedAll = true;

        for (Object cItem : c) {
            wasRemovedAll = wasRemovedAll && remove(cItem);
        }
        this.trimToSize();
        return wasRemovedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean wasRetainedAll = false;

        for (int i = 0; i < size; i++) {
            boolean needDelete = !c.contains(items[i]);
            if (needDelete) {
                T r = remove(i);
                wasRetainedAll = wasRetainedAll || Objects.equals(r, items[i]);
                i--;
            }
        }
        return wasRetainedAll;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{ }";
        }
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < (size - 1); i++) {
            String str = String.format(" %s ,", this.get(i));
            result.append(str);
        }
        String str = String.format(" %s }", this.get(size - 1));
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
