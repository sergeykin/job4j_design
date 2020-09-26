package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<T> implements Iterable<T> {

    SimpleLinkedList.Node<T> first;
    SimpleLinkedList.Node<T> last;
    int modCount = 0;
    int size = 0;

    public SimpleLinkedList() {
    }

    public boolean add(T e) {
        linkLast(e);
        return true;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.item;
    }

    void linkLast(T e) {
        final SimpleLinkedList.Node<T> l = last;
        final SimpleLinkedList.Node<T> newNode = new SimpleLinkedList.Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            Node<T> f = first;

            @Override
            public boolean hasNext() {
                return f != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> ftemp = f;
                f = f.next;
                return ftemp.item;
            }
        };
    }

    public T getFirst() {
        final SimpleLinkedList.Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    private static class Node<T> {
        T item;
        SimpleLinkedList.Node<T> next;
        SimpleLinkedList.Node<T> prev;

        Node(SimpleLinkedList.Node<T> prev, T element, SimpleLinkedList.Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
