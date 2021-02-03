package userstorage;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private Set<User> userList;

    public UserStore() {
        userList = new HashSet<>();
    }

    public synchronized boolean add (User user){
        return userList.add(user);
    }

    public synchronized boolean update(User user){
        boolean flag = false;
        for (User tmp:userList) {
            if (tmp.equals(user)) {
                tmp.setAmount(user.getAmount());
                flag = true;
                break;
            }
        }
        return flag;
    }

    public synchronized boolean delete(User user){
        return userList.remove(user);
    }

    public synchronized void transfer(int fromId, int toId, int amount){
        User userFrom = null;
        User userTo = null;
        for (User tmp:userList) {
            if (tmp.getId() == fromId) {
                userFrom = tmp;
            }
            if (tmp.getId() == toId) {
                userTo = tmp;
            }
        }
        if (userFrom != null && userTo != null) {
            update(new User(userFrom.getId(), userFrom.getAmount() - amount));
            update(new User(userTo.getId(), userTo.getAmount() + amount));
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
