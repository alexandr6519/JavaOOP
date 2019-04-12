package ru.academits.ikonnikov.hashTable.classes;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public MyHashTable(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity must be > 0!");
        }
        //noinspection unchecked
        this.lists = new ArrayList[capacity];
        this.modCount = 0;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int calculateObjectIndex(Object object) {
        if (object == null) {
            return 0;
        }
        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object object) {
        int indexObject = calculateObjectIndex(object);

        if (lists[indexObject] == null) {
            return false;
        }
        return lists[indexObject].contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }

        for (Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        int indexObject = calculateObjectIndex(t);

        if (lists[indexObject] == null) {
            int initialListCapacity = 10;
            lists[indexObject] = new ArrayList<>(initialListCapacity);
        }
        lists[indexObject].add(t);
        modCount++;
        size++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }

        for (T item : c) {
            this.add(item);
        }
        return c.size() > 0;
    }

    @Override
    public boolean remove(Object object) {
        int indexObject = calculateObjectIndex(object);

        if (lists[indexObject] == null) {
            return false;
        }
        if (lists[indexObject].remove(object)) {
            modCount++;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean wasRemovedAll = false;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int listSizeBeforeRemoving = list.size();

                if (list.removeAll(c)) {
                    wasRemovedAll = true;
                    size -= listSizeBeforeRemoving - list.size();
                }
            }
        }
        if (wasRemovedAll) {
            modCount++;
        }
        return wasRemovedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean wasRetainedAll = false;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int listSizeBeforeRetaining = list.size();

                if (list.retainAll(c)) {
                    wasRetainedAll = true;
                    size -= listSizeBeforeRetaining - list.size();
                }
            }
        }
        if (wasRetainedAll) {
            modCount++;
        }
        return wasRetainedAll;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < lists.length; i++) {
            lists[i] = null;
        }
        modCount++;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyTableIterator();
    }

    private class MyTableIterator implements Iterator<T> {
        private int currentArrayIndex = getIndexNextNotNullList(0);
        private int currentListIndex = 0;
        private int currentCollectionIndex = -1;
        private int modCountCurrent = modCount;

        private int getIndexNextNotNullList(int start) {
            if (start < 0 || start >= lists.length) {
                throw new IllegalArgumentException("The value of argument isn't correct!");
            }

            for (int i = start; i < lists.length; i++) {
                if (lists[i] != null && lists[i].size() > 0) {
                    return i;
                }
            }
            return lists.length - 1;
        }

        @Override
        public boolean hasNext() {
            return currentCollectionIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is absent!");
            }
            if (modCountCurrent != modCount) {
                throw new ConcurrentModificationException("There was adding or removing elements!");
            }
            currentCollectionIndex++;
            T item = lists[currentArrayIndex].get(currentListIndex);

            if (currentListIndex + 1 == lists[currentArrayIndex].size() && currentArrayIndex + 1 < lists.length) {
                currentListIndex = 0;
                currentArrayIndex++;
                currentArrayIndex = getIndexNextNotNullList(currentArrayIndex);
            } else {
                currentListIndex++;
            }
            return item;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arrayForLists = new Object[size];
        int i = 0;

        for (T item : this) {
            arrayForLists[i] = item;
            i++;
        }
        return arrayForLists;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array == null) {
            throw new IllegalArgumentException("This array is null!");
        }
        if (array.length < size) {
            //noinspection unchecked
            array = (T1[]) Arrays.copyOf(array, size, array.getClass());
        }
        int i = 0;

        for (T item : this) {
            //noinspection unchecked
            array[i] = (T1) item;
            i++;
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    @Override
    public String toString() {
        if (lists.length == 0) {
            return "{ }";
        }
        StringBuilder result = new StringBuilder("{").append(System.lineSeparator());

        for (ArrayList<T> list : lists) {
            if (list != null && list.size() > 0) {
                int i = 0;

                for (T item : list) {
                    int indexObject = calculateObjectIndex(item);

                    if (i == 0) {
                        String str = String.format(" %3d: (%s) ", indexObject, item);
                        result.append(str);
                        i++;
                    } else {
                        String str = String.format(" (%s) ", item);
                        result.append(str);
                    }
                }
                result.append(System.lineSeparator());
            }
        }
        return result.append("}").toString();
    }
}
