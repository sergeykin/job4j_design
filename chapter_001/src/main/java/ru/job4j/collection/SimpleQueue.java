package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        try {
            return out.pop();
        } catch (NoSuchElementException e) {
            T tmpin;
            try {
                while (true) {
                    tmpin = in.pop();
                    out.push(tmpin);
                }
            } catch (NoSuchElementException e2) {
                return out.pop();
            }

        }
    }

    public void push(T value) {
        in.push(value);
    }
}
