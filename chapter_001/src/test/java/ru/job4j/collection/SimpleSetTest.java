package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("first");
        set.add("second");
        set.add("first");
        Iterator<String> iterator = set.iterator();
        iterator.next();
        String rsl = iterator.next();
        assertThat(rsl, is("second"));
    }
}