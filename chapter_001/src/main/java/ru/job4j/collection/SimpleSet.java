package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> {
    SimpleArray<T> simpleArray = new SimpleArray<>();

    public void add(T value) {
        Iterator<T> iterator = simpleArray.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return;
            }
        }
        simpleArray.add(value);
    }

    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

}
