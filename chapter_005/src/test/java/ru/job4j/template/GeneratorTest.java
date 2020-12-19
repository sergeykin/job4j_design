package ru.job4j.template;


import org.junit.Test;
import ru.job4j.tdd.Session3D;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void produce01() throws Exception {
        Generator generator = new GeneratorImpl();
        assertThat("I am a Sergey, Who are you?", is(generator.produce("I am a ${name}, Who are ${subject}?", Map.of("name","Sergey", "subject", "you"))));
    }

    @Test(expected = Exception.class)
    public void produce02() throws Exception {
        Generator generator = new GeneratorImpl();
        assertThat("I am a Sergey, Who are you?", is(generator.produce("I am a , Who are ${subject}?", Map.of("name","Sergey", "subject", "you"))));
    }

    @Test(expected = AssertionError.class)
    public void produce03() throws Exception {
        Generator generator = new GeneratorImpl();
        assertThat("I am a Sergey, Who are you?", is(generator.produce("I am a ${name}, Who are ${subject}?", Map.of("name","Sergey"))));
    }
}
