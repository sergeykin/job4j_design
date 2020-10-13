package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<V> {

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float LOAD_FACTOR = 0.75f;

    public Entry<K, V>[] entries;
    private int size;
    private int modCount;
    private int threshold;
    private static int tableSize;

    public static void main(String[] args) {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        for (int i = 1; i < 30; i ++) {
            simpleHashMap.insert(i, String.valueOf(i));
        }

//        for (int i = 30; i > 0; i--) {
//            simpleHashMap.insert(i, String.valueOf(i));
//        }
//        for (int i = 6; i < 10; i++) {
//            simpleHashMap.delete(i);
//        }

        System.out.println(simpleHashMap.get(8));
    }

    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    public SimpleHashMap() {
        this.tableSize = DEFAULT_INITIAL_CAPACITY;
    }

    public int size() {
        return size;
    }

    public boolean insert(K key, V value) {
        return putVal(hash(key), key, value);
    }

    public V get(K key) {
        Entry<K, V> e = entries[(entries.length - 1) & hash(key)];
        return e == null ? null : Objects.equals(e.key, key) ? e.value : null;
    }

    boolean delete(K key) {
        return remove(hash(key), key);
    }

    private boolean remove(int hash, Object key) {
        int i = (entries.length - 1) & hash;
        if (Objects.equals(entries[i].key, key)) {
            entries[i] = null;
            ++modCount;
            --size;
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    private boolean putVal(int hash, K key, V value) {
        Entry<K, V>[] tab;
        int n, i;
        if ((tab = entries) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((tab[i = (n - 1) & hash]) == null) {
            tab[i] = new Entry<>(hash, key, value);
        } else {
            return false;
        }
        ++modCount;
        if (++size > threshold) {
            resize();
        }
        return true;
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    final Entry<K, V>[] resize() {
        Entry<K, V>[] oldTab = entries;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY
                    && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * LOAD_FACTOR;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY
                    ? (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"unchecked"})
        Entry<K, V>[] newTab = (Entry<K, V>[]) new Entry[newCap];
        entries = newTab;
        if (oldTab != null) {
            System.arraycopy(oldTab, 0, newTab, 0, oldTab.length);
        }
        if (newCap > tableSize) {
            tableSize = newCap;
            for (int i = 0; i < oldTab.length; i++) {
                if (oldTab[i] != null) {
                    int hash = hash(oldTab[i].key);
                    int ii = (newTab.length - 1) & hash;
                    newTab[ii] = oldTab[i];
                    newTab[ii].hash = hash;
                }
            }
        }
        return newTab;
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : key.hashCode() % tableSize;
    }

    private static class Entry<K, V> {
        int hash;
        final K key;
        V value;

        private Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof SimpleHashMap.Entry) {
                Entry<?, ?> e = (Entry<?, ?>) o;
                return Objects.equals(key, e.getKey())
                        && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    public Iterator<V> iterator() {
        return new HashIt();
    }

    private class HashIt implements Iterator<V> {
        Entry<K, V>[] container = SimpleHashMap.this.entries;
        private int cursor = 0;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            checkForComodification();
            return cursor < container.length && hasNextNotNullValue();
        }

        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[cursor++].value;
        }

        private boolean hasNextNotNullValue() {
            var has = false;
            for (int i = cursor; i < container.length; i++) {
                if (container[i] != null) {
                    has = true;
                    cursor = i;
                    break;
                }
            }
            return has;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}