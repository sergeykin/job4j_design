package ref;


import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@NotThreadSafe
//public class UserCache {
//    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
//    private final AtomicInteger id = new AtomicInteger();
//
//    public void add(User user) {
//        users.put(id.incrementAndGet(), user);
//    }
//
//    public User findById(int id) {
//        return users.get(id);
//    }
//}
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (User user:users.values() ) {
            userList.add(User.of(user.getName()));
        }
        return userList;
    }
}