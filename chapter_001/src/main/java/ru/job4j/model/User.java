package ru.job4j.model;

import java.util.*;

public class User {
    String name;
    int children;
    Calendar birthday;

    public static void main(String[] args) {
        User user1 = new User("firstName", 1, new GregorianCalendar(2000, 0, 1));
        User user2 = new User("firstName", 1, new GregorianCalendar(2000, 0, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, 1);
        map.put(user2, 1);
        System.out.println(user1);
        System.out.println(user2);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(map);
        System.out.println(binary(123 >> 16));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}
