package userstorage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void add() {
        UserStore stoge = new UserStore();
        User user1 = new User(1, 100);
        stoge.add(user1);
        assertThat(stoge.get(1).getAmount(), is(100));
    }

    @Test
    public void update() {
        UserStore stoge = new UserStore();
        User user1 = new User(1, 100);
        stoge.add(user1);
        stoge.update(new User(1,200));
        assertThat(stoge.get(1).getAmount(), is(200));
    }

    @Test

    public void delete() {
        UserStore stoge = new UserStore();
        User user1 = new User(1, 100);
        stoge.add(user1);
        stoge.delete(user1);
        assertNull(stoge.get(1));
    }

    @Test
    public void transfer() {
        UserStore stoge = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        stoge.add(user1);
        stoge.add(user2);
        stoge.transfer(1, 2, 50);
        assertThat(stoge.get(1).getAmount(), is(50));
        assertThat(stoge.get(2).getAmount(), is(250));
        assertFalse(stoge.transfer(1, 2, 100));
        assertThat(stoge.get(1).getAmount(), is(50));
        assertThat(stoge.get(2).getAmount(), is(250));

    }
}