package ru.job4j.collection;


import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;
    private int position = 0;
    private final int defaultSize = 1;
    private int modCount = 0;

    public SimpleArray() {
        this(0);
    }

    public SimpleArray(int size) {
        if (size == 0) {
            this.array = new Object[defaultSize];
            size = defaultSize;
        } else if (size > 0) {
            this.array = new Object[size];
        } else {
            throw new IllegalArgumentException(String.format("Размер не может быть меньше нуля: %s", size));
        }
    }

    public void add(T model) {
        if (position == array.length) {
            this.array = Arrays.copyOf(this.array, position + 1);
        }
        this.array[position++] = model;
        modCount++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        this.array[index] = model;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(this.array, index + 1, this.array, index, array.length - index - 1);
        array[position--] = null;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pos < position;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[pos++];
            }
        };
    }
}
