package cach;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base1");
        cache.add(base);
        assertThat(base.getName(), is(cache.memory.get(base.getId()).getName()));
    }

    @Test
    public void updateOK() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base newbase = new Base(1, 0);
        base.setName("Base1");
        cache.add(base);
        newbase.setName("Base2");
        cache.update(newbase);
        assertThat("Base2", is(cache.memory.get(base.getId()).getName()));
        assertThat(1, is(cache.memory.get(base.getId()).getVersion()));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base1");
        cache.add(base);
        cache.delete(base);
        assertThat(0, is(cache.memory.size()));
    }
}