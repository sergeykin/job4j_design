package userstorage;

import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private Map<Integer,User> userList;

    public UserStore() {
        userList = new HashMap<>();
    }

    public synchronized void add (User user){
        userList.put(user.getId(), user);
    }

    public synchronized void update(User user){
        userList.put(user.getId(), user);
    }

    public synchronized void delete(User user){
        userList.remove(user.getId());
    }

    public synchronized User get(Integer id) {
        return userList.get(id);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount){
        boolean success = false;
        User userFrom = userList.get(fromId);
        User userTo = userList.get(toId);;
        if (userFrom != null && userTo != null) {
            if (userFrom.getAmount() >= amount) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
                update(userFrom);
                update(userTo);
                success = true;
            }
        }
        return success;
    }

    @Override
    public synchronized String toString() {
        return "UserStore{" +
                "userList=" + userList +
                '}';
    }
}
