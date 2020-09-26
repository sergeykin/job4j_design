package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void addAndGet() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add("first");
        simpleLinkedList.add("second");
        simpleLinkedList.add("third");
        simpleLinkedList.add("four");
        String rsl = simpleLinkedList.get(3);
        assertThat(rsl, is("four"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add("first");
        String rsl = simpleLinkedList.iterator().next();
        assertThat(rsl, is("first"));
    }
}