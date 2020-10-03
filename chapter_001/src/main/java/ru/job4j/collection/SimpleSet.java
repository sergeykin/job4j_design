package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> {
    SimpleArray<T> simpleArray = new SimpleArray<>();

    public void add(T value) {
        if (!contains(value)) {
            simpleArray.add(value);
        }
    }

    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iterator = simpleArray.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

}
