package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        Iterator<T> iterator = linked.iterator();
        T temp = null;
        while (iterator.hasNext()) {
            temp = iterator.next();
        }
        linked.deleteLast();
        return temp;
    }

    public void push(T value) {
        linked.add(value);
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }
}
