package ru.job4j.array;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;
    private int position = 0;
    private final int defaultSize = 20;

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

    public SimpleArray() {
       this(0);
    }

    public void add(T model) {
        this.array[position++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        this.array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(this.array, index + 1, this.array, index, array.length - index - 1);
        array[position--] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = 0;
            @Override
            public boolean hasNext() {
                return pos < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[pos++];
            }
        };
    }

}
