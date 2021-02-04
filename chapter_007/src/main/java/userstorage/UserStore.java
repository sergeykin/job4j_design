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

    public synchronized void transfer(int fromId, int toId, int amount){
        User userFrom = userList.get(fromId);
        User userTo = userList.get(toId);;
        if (userFrom != null && userTo != null) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            update(userFrom);
            update(userTo);
        }
    }

    @Override
    public synchronized String toString() {
        return "UserStore{" +
                "userList=" + userList +
                '}';
    }

    public static void main(String[] args) {
        UserStore stoge = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        stoge.add(user1);
        stoge.add(user2);
        stoge.transfer(1, 2, 50);
        System.out.println(stoge);
        System.out.println(user1);
        System.out.println(user2);
    }
}
