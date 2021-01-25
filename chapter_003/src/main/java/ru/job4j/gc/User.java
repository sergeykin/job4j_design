package ru.job4j.gc;

public class User {
    private String id;
    private String fam;
    private String name;
    private String otch;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String id, String fam, String name, String otch) {
        this.id = id;
        this.fam = fam;
        this.name = name;
        this.otch = otch;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %s %s %s %n", id, fam, name, otch);
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }
}
