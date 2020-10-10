package ru.job4j.collection;


import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.User;

import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    SimpleHashMap<UUID, User> simpleHashMap = new SimpleHashMap<>();
    Calendar date = Calendar.getInstance();
    int size = 0;
    UUID firstUid = UUID.randomUUID();

    @Before
    public void setUp() {
        date.set(2000, Calendar.JANUARY, 01);
        User user1 = new User("User1", 2, date);
        if (simpleHashMap.insert(firstUid, user1)) {
            size++;
        }
        User user2 = new User("User2", 2, date);
        if (simpleHashMap.insert(UUID.randomUUID(), user2)) {
            size++;
        }
        User user3 = new User("User3", 2, date);
        if (simpleHashMap.insert(UUID.randomUUID(), user3)) {
            size++;
        }
        User user4 = new User("User4", 2, date);
        if (simpleHashMap.insert(UUID.randomUUID(), user4)) {
            size++;
        }
        User user5 = new User("User5", 2, date);
        if (simpleHashMap.insert(UUID.randomUUID(), user5)) {
            size++;
        }
    }

    @Test
    public void whenInsertElement() {
        if (simpleHashMap.insert(UUID.randomUUID(), new User("User6", 2, date))) {
            size++;
        }
        assertThat(simpleHashMap.size(), is(size));
    }

    @Test
    public void whenInsertThenGet() {
        var uid = UUID.randomUUID();
        if (simpleHashMap.insert(uid, new User("NewUser", 2, date))) {
            assertThat(simpleHashMap.get(uid).getName(), is("NewUser"));
        }
        assertThat(simpleHashMap.get(firstUid).getName(), is("User1"));
    }

    @Test
    public void whenDelete() {
        simpleHashMap.delete(firstUid);
        assertThat(simpleHashMap.get(firstUid), nullValue());
        assertThat(simpleHashMap.size(), is(size - 1));
    }

    @Test
    public void whenIterate() {
        var iterator = simpleHashMap.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        assertThat(i, is(simpleHashMap.size()));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<UUID, User> hashMap = new SimpleHashMap<>();
        var uid = UUID.randomUUID();
        hashMap.insert(uid, new User("New_User", 2, date));
        var iterator = hashMap.iterator();
        hashMap.insert(UUID.randomUUID(), new User("New_User_1", 2, date));
        iterator.next();
    }

}