package ru.job4j.generic;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

public class MemStoreTest {

    @Test
    public void userStoreTest() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "User1"));
        assertThat(userStore.findById("1").getName(), is("User1"));
        userStore.replace("1", new User("2", "Гена"));
        assertThat(userStore.findById("2").getName(), is("Гена"));
        userStore.delete("2");
        assertThat(userStore.findById("2"), is(IsNull.nullValue()));
    }

}