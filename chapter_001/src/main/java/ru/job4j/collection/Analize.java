package ru.job4j.collection;

import java.util.List;

public class Analize {

    public static void main(String[] args) {
        User user1 = new User(1, "User1");
    }

    public Info diff(List<User> previous, List<User> current) {
        return null;

    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Info {

        int added;
        int changed;

        int deleted;

    }

}