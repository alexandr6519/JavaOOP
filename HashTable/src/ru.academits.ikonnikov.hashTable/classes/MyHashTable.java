package ru.academits.ikonnikov.hashTable.classes;

import java.util.*;

import ru.academits.ikonnikov.arrayList.classes.MyArrayList;

public class MyHashTable<T> implements Collection<T> {
    private MyArrayList<T>[] lists;
    private int size;
    private int modCount;

    public MyHashTable(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity must be > 0!");
        }
        //noinspection unchecked
        this.lists = new MyArrayList[capacity];

        for (int i = 0; i < capacity; i++) {
            lists[i] = new MyArrayList<T>(10);
        }
        this.modCount = 0;
        this.size = 0;
    }

    public MyHashTable(T[] array, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity must be > 0!");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("The arrayList is empty!");
        }
        //noinspection unchecked
        this.lists = new MyArrayList[capacity];
        this.size = array.length;

        for (T item : array) {
            if (Objects.equals(item, null)) {
                size--;
                continue;
            }
            int indexObject = calculateIndexObject(item);

            if (lists[indexObject] == null) {
                lists[indexObject] = new MyArrayList<>(10);
            }
            lists[indexObject].add(item);
        }
        modCount = 0;
    }

    public MyHashTable(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("The list is empty!");
        }
        size = list.size();
        //noinspection unchecked
        this.lists = new MyArrayList[size];
        this.size = list.size();

        for (T item : list) {
            if (Objects.equals(item, null)) {
                size--;
                continue;
            }
            int indexObject = calculateIndexObject(item);

            if (lists[indexObject] == null) {
                lists[indexObject] = new MyArrayList<>(10);
            }
            lists[indexObject].add(item);
        }
        modCount = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int calculateIndexObject(Object object) {
        if (Objects.equals(object, null)) {
            throw new IllegalArgumentException("This object is null!");
        }

        if (lists.length == 0) {
            throw new IllegalArgumentException("This hashTable is null!");
        }
        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object object) {
        int indexObject = calculateIndexObject(object);

        if (Objects.equals(lists[indexObject], null)) {
            return false;
        }
        for (T item : lists[indexObject]) {
            if (Objects.equals(item, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            //noinspection unchecked
            T t = (T) item;

            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        if (Objects.equals(t, null)) {
            throw new IllegalArgumentException("It's impossible to add null!");
        }
        int indexObject = calculateIndexObject(t);
        if (lists[indexObject] == null) {
            lists[indexObject] = new MyArrayList<T>(10);
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
        boolean wasAddAll = false;

        for (Object item : c) {
            if (!Objects.equals(item, null)) {
                //noinspection unchecked
                this.add((T) item);
                wasAddAll = true;
            }
        }
        return wasAddAll;
    }

    @Override
    public boolean remove(Object object) {
        if (Objects.equals(object, null)) {
            throw new IllegalArgumentException("This object is null!");
        }
        int indexObject = calculateIndexObject(object);

        if (lists[indexObject] == null) {
            return false;
        }
        for (T item : lists[indexObject]) {
            if (Objects.equals(item, object)) {
                if (lists[indexObject].size() == 1) {
                    lists[indexObject] = null;
                } else {
                    lists[indexObject].remove(item);
                }
                modCount++;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("This collection is null!");
        }
        boolean wasRemovedAll = false;

        for (MyArrayList<T> list : lists) {
            if (list != null) {
                for (T item : list) {
                    if (c.contains(item)) {
                        remove(item);
                        wasRemovedAll = true;
                    }
                }
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

        for (MyArrayList<T> list : lists) {
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    if (!c.contains(list.get(j))) {
                        list.remove(j);
                        j--;
                        wasRetainedAll = true;
                    }
                }
            }
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
    public String toString() {
        if (size == 0 || lists == null) {
            return "{ }";
        }
        StringBuilder result = new StringBuilder("{").append(System.lineSeparator());

        for (List<T> list : lists) {
            if (!Objects.equals(list, null) && list.size() > 0) {
                int i = 0;
                for (T item : list) {
                    int indexObject = calculateIndexObject(item);

                    if (i == 0) {
                        String str = String.format(" %d: (%s) ", indexObject, item);
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

    @Override
    public Iterator<T> iterator() {
        return new MyTableIterator();
    }

    private class MyTableIterator implements Iterator<T> {
        private int currentIndexArray = 0;
        private int currentIndexList = 0;
        private int modCountCurrent = modCount;
        private boolean needContinue = true;

        @Override
        public boolean hasNext() {
            if (!needContinue) {
                return false;
            }
            while (currentIndexArray + 1 < lists.length && Objects.equals(lists[currentIndexArray], null)) {
                currentIndexArray++;
            }
            if (lists[lists.length - 1] == null) {
                return currentIndexArray + 1 < lists.length;
            }
            return (currentIndexArray + 1 < lists.length || currentIndexList < lists[lists.length - 1].size());
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is absent!");
            }
            if (modCountCurrent != modCount) {
                throw new ConcurrentModificationException("There was adding or removing elements!");
            }
            T item = lists[currentIndexArray].get(currentIndexList);

            if (currentIndexList == lists[currentIndexArray].size() - 1) {
                currentIndexList = 0;

                if (currentIndexArray < lists.length - 1) {
                    currentIndexArray++;
                } else {
                    needContinue = false;
                }
            } else {
                currentIndexList++;
            }
            return item;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arrayForLists = new Object[size];

        Iterator iterator = this.iterator();
        int i = 0;

        while (iterator.hasNext() && i < size) {
            arrayForLists[i] = iterator.next();
            if (i < size - 1) {
                i++;
            }
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
        Iterator iterator = this.iterator();
        int i = 0;

        while (iterator.hasNext() && i < size) {
            //noinspection unchecked
            array[i] = (T1) iterator.next();
            i++;
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
}
